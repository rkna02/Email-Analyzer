package cpen221.mp2;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
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
            UDWInteractions = new LinkedList[100];
            while(fileReader.hasNextLine()) {
                int sender = 0;
                int receiver = 0;
                int time = 0;
                for (int i = 0; i < 3; i++) {
                    switch (i) {
                        case 0:
                            sender = fileReader.nextInt();
                            break;
                        case 1:
                            receiver = fileReader.nextInt();
                            break;
                        case 2:
                            time = fileReader.nextInt();
                            break;
                    }
                }
                if (UDWInteractions[sender] == null) {
                    UDWInteractions[sender] = new LinkedList<>();
                }
                if (UDWInteractions[receiver] == null) {
                    UDWInteractions[receiver] = new LinkedList<>();
                }
                UDWInteractions[sender].addFirst(receiver);
                UDWInteractions[receiver].addFirst(sender);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be read properly");
        }

    }

    private int numParticipants(File file) {
        while(fileReader.hasNextLine())
            return 1;
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
            while (fileReader.hasNextLine()) {
                int sender = 0;
                int receiver = 0;
                int time = timeFilter[0];
                if (time > timeFilter[1]) {
                    break;
                }
                for (int i = 0; i < 3; i++) {
                    switch(i) {
                        case 0:
                            sender = fileReader.nextInt();
                            break;
                        case 1:
                            receiver = fileReader.nextInt();
                            break;
                        case 2:
                            time = fileReader.nextInt();
                            break;
                    }
                }
                System.out.println("oi");
                if (time >= timeFilter[0]) {
                    UDWInteractions[sender].addFirst(receiver);
                    UDWInteractions[receiver].addFirst(sender);
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be read properly");
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