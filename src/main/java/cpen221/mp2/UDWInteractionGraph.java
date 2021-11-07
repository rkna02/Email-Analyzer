package cpen221.mp2;

import java.util.List;
import java.util.Set;

public class UDWInteractionGraph {

    /* ------- Task 1 ------- */
    /* Building the Constructors */

    /**
     * Creates a new UDWInteractionGraph using an email interaction file.
     * The email interaction file will be in the resources directory.
     *
     * @param fileName the name of the file in the resources
     *                 directory containing email interactions
     */
    public UDWInteractionGraph(String fileName) {
        try {
            File transactions = new File(fileName);
            Scanner fileReader = new Scanner(transactions);
            userIds = new HashSet<>();
            allSenders = new ArrayList<>();
            allReceivers = new ArrayList<>();
            allSendTimes = new ArrayList<>();
            int largestUserId = 0;

            while (fileReader.hasNextInt()) {
                for (int i = 0; i < 3; i++) {
                    int nextInt = fileReader.nextInt();
                    if (i == 0) {
                        userIds.add(nextInt);
                        allSenders.add(nextInt);
                        if (nextInt > largestUserId) {
                            largestUserId = nextInt;
                        }
                    }
                    if (i == 1) {
                        userIds.add(nextInt);
                        allReceivers.add(nextInt);
                        if (nextInt > largestUserId) {
                            largestUserId = nextInt;
                        }
                    }
                    if (i == 2) {
                        allSendTimes.add(nextInt);
                    }
                }
            }
            fileReader.close();
            fileReader = new Scanner(transactions);

            UDWInteractions = new LinkedList[largestUserId + 1];

            while (fileReader.hasNextInt()) {
                int sender = 0;
                int receiver = 0;
                for (int i = 0; i < 3; i++) {
                    int nextInt = fileReader.nextInt();
                    if (i == 0) {
                        sender = nextInt;
                    }
                    if (i == 1) {
                        receiver = nextInt;
                    }
                    if (i == 2) {
                        break;
                    }
                }

                if (UDWInteractions[sender] == null) {
                    UDWInteractions[sender] = new LinkedList<Integer>();
                }
                if (UDWInteractions[receiver] == null) {
                    UDWInteractions[receiver] = new LinkedList<Integer>();
                }
                if(!UDWInteractions[sender].contains(receiver)) {
                    UDWInteractions[sender].add(receiver);
                }
                if (!UDWInteractions[receiver].contains(sender)) {
                    UDWInteractions[receiver].add(sender);
                }
            }
            fileReader.close();

            for (int i = 0; i <= largestUserId; i++) {
                if (UDWInteractions[i] != null) {
                    Collections.sort(UDWInteractions[i]);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading file");
            e.printStackTrace();
        }
    }

    /**
     * Creates a new UDWInteractionGraph using an email interaction file.
     * The email interaction file will be in the resources directory.
     *
     * @param fileName the name of the file in the resources
     *                 directory containing email interactions
     * @param timeFilter an integer array of length 2: [t0, t1]
     *                   where t0 <= t1. The created UDWInteractionGraph
     *                   should only include those emails in the input
     *                   UDWInteractionGraph with send time t in the
     *                   t0 <= t <= t1 range.
     */
    public UDWInteractionGraph(String fileName, int[] timeFilter) {
        try {
            File transactions = new File(fileName);
            Scanner fileReader = new Scanner(transactions);
            userIds = new HashSet<>();
            allSenders = new ArrayList<>();
            allReceivers = new ArrayList<>();
            allSendTimes = new ArrayList<>();
            int largestUserId = 0;

            //Find the new start of the transaction history
            while (fileReader.hasNextInt()) {
                fileReader.nextInt();
                fileReader.nextInt();
                if (fileReader.nextInt() >= timeFilter[0]) {
                    break;
                }
            }

            //Update variables
            while (fileReader.hasNextInt()) {
                int[] transaction = new int[3];
                for (int i = 0; i < 3; i++) {
                    transaction[i] = fileReader.nextInt();
                }
                if (transaction[2] <= timeFilter[1]) {
                    userIds.add(transaction[0]);
                    userIds.add(transaction[1]);
                    allSenders.add(transaction[0]);
                    allReceivers.add(transaction[1]);
                    allSendTimes.add(transaction[2]);
                    if (transaction[0] > largestUserId) {
                        largestUserId = transaction[0];
                    }
                    if (transaction[1] > largestUserId) {
                        largestUserId = transaction[1];
                    }
                } else {
                    break;
                }
            }
            fileReader.close();
            fileReader = new Scanner(transactions);

            UDWInteractions = new LinkedList[largestUserId + 1];

            //Construct UDW Graph
            for(int i = 0; i < allSendTimes.size(); i++) {
                if (UDWInteractions[allSenders.get(i)] == null) {
                    UDWInteractions[allSenders.get(i)] = new LinkedList<Integer>();
                }
                if (UDWInteractions[allReceivers.get(i)] == null) {
                    UDWInteractions[allReceivers.get(i)] = new LinkedList<Integer>();
                }
                if(!UDWInteractions[allSenders.get(i)].contains(allReceivers.get(i))) {
                    UDWInteractions[allSenders.get(i)].add(allReceivers.get(i));
                }
                if (!UDWInteractions[allReceivers.get(i)].contains(allSenders.get(i))) {
                    UDWInteractions[allReceivers.get(i)].add(allSenders.get(i));
                }
            }
            fileReader.close();

            for (int i = 0; i <= largestUserId; i++) {
                if (UDWInteractions[i] != null) {
                    Collections.sort(UDWInteractions[i]);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading file");
            e.printStackTrace();
        }
    }

    /**
     * Creates a new UDWInteractionGraph from a UDWInteractionGraph object
     * and considering a list of User IDs.
     *
     * @param inputUDWIG a UDWInteractionGraph object
     * @param userFilter a List of User IDs. The created UDWInteractionGraph
     *                   should exclude those emails in the input
     *                   UDWInteractionGraph for which neither the sender
     *                   nor the receiver exist in userFilter.
     */
    public UDWInteractionGraph(UDWInteractionGraph inputUDWIG, List<Integer> userFilter) {
        // TODO: Implement this constructor
    }

    /**
     * Creates a new UDWInteractionGraph from a DWInteractionGraph object.
     *
     * @param inputDWIG a DWInteractionGraph object
     */
    public UDWInteractionGraph(DWInteractionGraph inputDWIG) {
        // TODO: Implement this constructor
    }

    /**
     * @return a Set of Integers, where every element in the set is a User ID
     * in this DWInteractionGraph.
     */
    public Set<Integer> getUserIDs() {
        // TODO: Implement this getter method
        return null;
    }

    /**
     * @param sender the User ID of the sender in the email transaction.
     * @param receiver the User ID of the receiver in the email transaction.
     * @return the number of emails sent from the specified sender to the specified
     * receiver in this DWInteractionGraph.
     */
    public int getEmailCount(int sender, int receiver) {
        // TODO: Implement this getter method
        return 0;
    }

    /* ------- Task 2 ------- */

    /**
     * @param timeWindow is an int array of size 2 [t0, t1]
     *                   where t0<=t1
     * @return an int array of length 2, with the following structure:
     *  [NumberOfUsers, NumberOfEmailTransactions]
     */
    public int[] ReportActivityInTimeWindow(int[] timeWindow) {
        // TODO: Implement this method
        return null;
    }

    /**
     * @param userID the User ID of the user for which
     *               the report will be created
     * @return an int array of length 2 with the following structure:
     *  [NumberOfEmails, UniqueUsersInteractedWith]
     * If the specified User ID does not exist in this instance of a graph,
     * returns [0, 0].
     */
    public int[] ReportOnUser(int userID) {
        // TODO: Implement this method
        return null;
    }

    /**
     * @param N a positive number representing rank. N=1 means the most active.
     * @return the User ID for the Nth most active user
     */
    public int NthMostActiveUser(int N) {
        // TODO: Implement this method
        return -1;
    }

    /* ------- Task 3 ------- */

    /**
     * @return the number of completely disjoint graph
     *    components in the UDWInteractionGraph object.
     */
    public int NumberOfComponents() {
        // TODO: Implement this method
        return 0;
    }

    /**
     * @param userID1 the user ID for the first user
     * @param userID2 the user ID for the second user
     * @return whether a path exists between the two users
     */
    public boolean PathExists(int userID1, int userID2) {
        // TODO: Implement this method
        return false;
    }

}
