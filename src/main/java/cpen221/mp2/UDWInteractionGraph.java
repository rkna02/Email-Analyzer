package cpen221.mp2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Queue;

/** UDWInteractionGraph represents an undirected weighted graph */
public class UDWInteractionGraph {

    /** Concrete Representation */

    /** An adjacency list made of a 1D array of LinkedLists */
    private LinkedList<Integer>[] UDWInteractions;

    /** All vertices: the unique user IDs of users that sends/receives Emails */
    private Set<Integer> userIds;

    /** Lists of all Email senders, receivers, and the time sent.
     *  Combined elements from the three lists at each index represents an edge of the graph */
    private List<Integer> allSenders;
    private List<Integer> allReceivers;
    private List<Integer> allSendTimes;

    //Representation Invariant:
    //  (allSenders.size() == allReceivers.size() && allReceivers.size() == allSendTimes.size())
    //  allSendTimes[i] >= 0, 0 <= i < allSendTimes.size()

    //Abstract Function:
    //  An adjacency list:
    //  LinkedList at UDWInteractions[0] represents the users that user 0 interacted with
    //  LinkedList at UDWInteractions[1] represents the users that user 1 interacted with
    //  UDWInteractions[largestUserId - 1] represents the users that the last user interacted with
    //  Vertices of the adjacency list:
    //  userIds
    //  Specific edges of the adjacency list:
    //
    //

    // adjacent list contains only the interactions
    // 3 lists contains time and the receiver sender

    /** Other Graph Properties */

    /** The node/user ID with the largest magnitude in the graph */
    private int largestUserId;

     public static void main(String[] args) {
        int[] arr = {10, 11};
        int[] arr2 = {0, 2};
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        UDWInteractionGraph obj = new UDWInteractionGraph("resources/Task1-2UDWTransactions.txt");
        UDWInteractionGraph obj2 = new UDWInteractionGraph(obj, arr);
        UDWInteractionGraph obj5 = new UDWInteractionGraph(obj, arr2);
        UDWInteractionGraph obj3 = new UDWInteractionGraph("resources/Task3-components-test.txt");
        UDWInteractionGraph obj4 = new UDWInteractionGraph(obj, list);

    }

    /* ------- Task 1 ------- */
    /* Building the Constructors */

    /**
     * Checks the representation invariant.
     *
     * effects: no effects if this satisfies rep invariant,
     * 		    otherwise throws a runtime exception.
     */
    private void checkRep() {
        if (allSenders.size() != allReceivers.size() ||
            allReceivers.size() != allSendTimes.size() ||
            allSenders.size() != allSendTimes.size()) {
            throw new RuntimeException("missing data from file");
        }
        for (int time: allSendTimes) {
            if (time < 0) {
                throw new RuntimeException("time out of bounds");
            }
        }
    }

    /**
     * Creates a new UDWInteractionGraph using an email interaction file.
     * The email interaction file will be in the resources directory.
     *
     * @param fileName the name of the file in the resources
     *                 directory containing email interactions
     * effects:        FileNotFound Exception if file is missing from the resources directory
     */
    public UDWInteractionGraph(String fileName) {
        checkRep();
        try {
            File transactions = new File(fileName);
            Scanner fileReader = new Scanner(transactions);
            userIds = new HashSet<>();
            allSenders = new ArrayList<>();
            allReceivers = new ArrayList<>();
            allSendTimes = new ArrayList<>();
            largestUserId = 0;

            //Save 1st integer in every line to allSenders
            //Save 2nd integer in every line to allReceivers
            //Save 3rd integer in every line to allSendTimes
            //Constantly update userIds and largestUserId
            while (fileReader.hasNextInt()) {
                int[] transaction = new int[3];
                for (int i = 0; i < 3; i++) {
                    transaction[i] = fileReader.nextInt();
                }
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
            }
            fileReader.close();

            //Construct Adjacency List
            UDWInteractions = new LinkedList[largestUserId + 1];

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

            //Sort each LinkedList in ascending order of user IDs
            for (int i = 0; i <= largestUserId; i++) {
                if (UDWInteractions[i] != null) {
                    Collections.sort(UDWInteractions[i]);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading file");
            e.printStackTrace();
        } finally {
            checkRep();
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
     * effects:          Creates a new UDWInteractionGraph where
     *                   the size of the new UDWInteractionGraph is less than or equal to
     *                   the size of inputUDWIG.
     *                   FileNotFound Exception if file is missing from the resources directory
     */
    public UDWInteractionGraph(String fileName, int[] timeFilter) {
        checkRep();
        try {
            File transactions = new File(fileName);
            Scanner fileReader = new Scanner(transactions);
            userIds = new HashSet<>();
            allSenders = new ArrayList<>();
            allReceivers = new ArrayList<>();
            allSendTimes = new ArrayList<>();
            largestUserId = 0;

            //Save 1st integer in every line to allSenders
            //Save 2nd integer in every line to allReceivers
            //Save 3rd integer in every line to allSendTimes
            //Constantly update userIds and largestUserId
            while (fileReader.hasNextInt()) {
                int[] transaction = new int[3];
                for (int i = 0; i < 3; i++) {
                    transaction[i] = fileReader.nextInt();
                }
                //Apply time filter
                if (transaction[2] >= timeFilter[0] && transaction[2] <= timeFilter[1]) {
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
                }
            }
            fileReader.close();

            //Construct Adjacency List
            UDWInteractions = new LinkedList[largestUserId + 1];

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

            //Sort each LinkedList in ascending order of user IDs
            for (int i = 0; i <= largestUserId; i++) {
                if (UDWInteractions[i] != null) {
                    Collections.sort(UDWInteractions[i]);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading file");
            e.printStackTrace();
        } finally {
             checkRep();
        }
    }

    /**
     * Creates a new UDWInteractionGraph from a UDWInteractionGraph object
     * and considering a time window filter.
     *
     * @param inputUDWIG a UDWInteractionGraph object
     * @param timeFilter an integer array of length 2: [t0, t1]
     *                   where t0 <= t1. The created UDWInteractionGraph
     *                   should only include those emails in the input
     *                   UDWInteractionGraph with send time t in the
     *                   t0 <= t <= t1 range.
     * effects:          Creates a new UDWInteraction graph where
     *                   the size of the new UDWInteractionGraph is less than or equal to
     *                   the size of inputUDWIG.
     */
    public UDWInteractionGraph(UDWInteractionGraph inputUDWIG, int[] timeFilter) {
        checkRep();
        try {
            userIds = new HashSet<>();
            allSenders = new ArrayList<>();
            allReceivers = new ArrayList<>();
            allSendTimes = new ArrayList<>();
            largestUserId = 0;

            //Apply time filter
            for (int i = 0; i < inputUDWIG.allSendTimes.size(); i++) {
                if (inputUDWIG.allSendTimes.get(i) >= timeFilter[0] &&
                    inputUDWIG.allSendTimes.get(i) <= timeFilter[1]) {
                    allSenders.add(inputUDWIG.allSenders.get(i));
                    allReceivers.add(inputUDWIG.allReceivers.get(i));
                    allSendTimes.add(inputUDWIG.allSendTimes.get(i));
                    userIds.add(inputUDWIG.allSenders.get(i));
                    userIds.add(inputUDWIG.allReceivers.get(i));
                }
            }

            if (userIds.size() != 0) {
                largestUserId = Collections.max(userIds);
            }

            //Construct Adjacency List
            UDWInteractions = new LinkedList[largestUserId + 1];

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

            //Sort each LinkedList in ascending order of user IDs
            for (int i = 0; i <= largestUserId; i++) {
                if (UDWInteractions[i] != null) {
                    Collections.sort(UDWInteractions[i]);
                }
            }
        } finally {
            checkRep();
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
     * requires:         List is not null
     * effects:          Creates a new UDWInteraction graph where
     *                   the size of the new UDWInteractionGraph is less than or equal to
     *                   the size of inputUDWIG.
     */
    public UDWInteractionGraph(UDWInteractionGraph inputUDWIG, List<Integer> userFilter) {
        checkRep();
        try {
            userIds = new HashSet<>();
            allSenders = new ArrayList<>();
            allReceivers = new ArrayList<>();
            allSendTimes = new ArrayList<>();
            largestUserId = 0;

            //Apply user filter
            for (int i = 0; i < inputUDWIG.allSendTimes.size(); i++) {
                if (userFilter.contains(inputUDWIG.allSenders.get(i)) ||
                    userFilter.contains(inputUDWIG.allReceivers.get(i))) {
                    allSenders.add(inputUDWIG.allSenders.get(i));
                    allReceivers.add(inputUDWIG.allReceivers.get(i));
                    allSendTimes.add(inputUDWIG.allSendTimes.get(i));
                    userIds.add(inputUDWIG.allSenders.get(i));
                    userIds.add(inputUDWIG.allReceivers.get(i));
                }
            }

            if (userIds.size() != 0) {
                largestUserId = Collections.max(userIds);
            }

            //Construct Adjacency List
            UDWInteractions = new LinkedList[largestUserId + 1];

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

            //Sort each LinkedList in ascending order of user IDs
            for (int i = 0; i <= largestUserId; i++) {
                if (UDWInteractions[i] != null) {
                    Collections.sort(UDWInteractions[i]);
                }
            }
        } finally {
            checkRep();
        }
    }

    /**
     * Creates a new UDWInteractionGraph from a DWInteractionGraph object.
     *
     * @param inputDWIG a DWInteractionGraph object
     */
    public UDWInteractionGraph(DWInteractionGraph inputDWIG) {
        checkRep();
        try {




        } finally {
            checkRep();
        }
    }

    /**
     * Creates a Set that includes all user IDs in this UDWInteractionGraph.
     *
     * @return a Set of Integers, where every element in the set is a User ID
     *         in this UDWInteractionGraph.
     *         If there are no users in UDWInteractionGraph,
     *         returns an empty Set.
     *
     */
    public Set<Integer> getUserIDs() {
        return this.userIds;
    }

    /**
     * Finds the number of email interactions (send/receive) between user1 and user2.
     *
     * @param user1 the User ID of the first user.
     * @param user2 the User ID of the second user.
     * @return The number of email interactions (send/receive) between user1 and user2.
     *         If the User ID of user1 or/and user2 is not found,
     *         returns 0.
     */
    public int getEmailCount(int user1, int user2) {
        int count = 0;

        for (int i = 0; i < this.allSenders.size(); i++) {
            if (this.allSenders.get(i) == user1 && this.allReceivers.get(i) == user2 ||
                this.allSenders.get(i) == user2 && this.allReceivers.get(i) == user1) {
                count++;
            }
        }
        return count;
    }

    /* ------- Task 2 ------- */

    /**
     * Finds the number of users sending/receiving Emails in a certain time window.
     * Finds the number of Emails sent in the same time window.
     *
     * @param timeWindow is an int array of size 2 [t0, t1]
     *                   where t0<=t1
     * @return an int array of length 2, with the following structure:
     *         [NumberOfUsers, NumberOfEmailTransactions]
     */
    public int[] ReportActivityInTimeWindow(int[] timeWindow) {
        UDWInteractionGraph graphInTimeWindow = new UDWInteractionGraph(this, timeWindow);
        Set<Integer> numUsersSet = graphInTimeWindow.getUserIDs();
        int[] data = new int[2];
        int numUsers = numUsersSet.size();
        int numEmails = graphInTimeWindow.allSenders.size();

        data[0] = numUsers;
        data[1] = numEmails;

        return data;
    }

    /**
     * Report the number of Emails sent and received by a user,
     * and the user IDs of the unique users they interacted with.
     *
     * @param userID the User ID of the user for which
     *               the report will be created
     * @return an int array of length 2 with the following structure:
     *         [NumberOfEmails, UniqueUsersInteractedWith]
     *         If the specified User ID does not exist in this instance of a graph,
     *         returns [0, 0].
     */
    public int[] ReportOnUser(int userID) {
        Set<Integer> numUsersSet = new HashSet<>();
        int numEmails = 0;
        int numUsers = 0;
        int[] data = new int[2];

        if (!this.allSenders.contains(userID) && !this.allSenders.contains(userID)) {
            return new int[]{0, 0};
        } else {
            for (int i = 0; i < this.allSenders.size(); i++) {
                if (this.allSenders.get(i) == userID) {
                    numEmails++;
                    numUsersSet.add(allReceivers.get(i));
                }
                if (this.allReceivers.get(i) == userID) {
                    numEmails++;
                    numUsersSet.add(allSenders.get(i));
                }
            }
        }
        numUsers = numUsersSet.size();
        data[0] = numEmails;
        data[1] = numUsers;

        return data;
    }

    /**
     * Finds the Nth most active user in an adjacency list.
     * The user is more active if they send or/and receive more Emails.
     *
     * @param N a positive number representing rank. N=1 means the most active.
     * requires: N must be > 0
     * @return the User ID for the Nth most active user.
     *         If two or more users sends or receivers the same number of Emails (same rank),
     *         returns the user with the smallest user ID
     *         If the Nth most active user does not exist,
     *         returns -1.
     */
    public int NthMostActiveUser(int N) {
        //Convert the set of userIds to an array for accessing inner elements
        Integer[] userIdsArray = new Integer[this.userIds.size()];
        this.userIds.toArray(userIdsArray);

        List<List<Integer>> userRanks = new ArrayList<>();
        int mostInteractions = 0;
        int nextMostInteractions = 0;
        int rankedUsers = 0;

        //Find the mostInteractions done by a user
        for (int i = 0; i < this.getUserIDs().size(); i++) {
            if (this.ReportOnUser(i)[0] > mostInteractions) {
                mostInteractions = this.ReportOnUser(i)[0];
            }
        }

        //Create a List of Lists with user rankings at every Nth rank
        while (rankedUsers < this.userIds.size()) {
            List<Integer> NthRankUsers = new ArrayList<>();
            for (int i = 0; i < this.userIds.size(); i++) {
                if (this.ReportOnUser(i)[0] == mostInteractions) {
                    NthRankUsers.add(userIdsArray[i]);
                    rankedUsers++;
                }
            }
            Collections.sort(NthRankUsers);
            userRanks.add(NthRankUsers);

            //Update mostInteractions
            nextMostInteractions = 0;
            for (int i = 0; i < this.getUserIDs().size(); i++) {
                if (this.ReportOnUser(i)[0] < mostInteractions &&
                    this.ReportOnUser(i)[0] > nextMostInteractions) {
                    nextMostInteractions = this.ReportOnUser(i)[0];
                }
            }
            mostInteractions = nextMostInteractions;
        }

        if (userRanks.get(N - 1) == null) {
            return -1;
        } else {
            return userRanks.get(N - 1).get(0);
        }

    }

    /* ------- Task 3 ------- */

    /**
     * Creates an ArrayList of user IDs using an iterative method,
     * indicating all visited users from rootUser
     *
     * @param rootUser the user ID to start the search with
     * @return An arrayList of visited user IDs after a Breadth First Search
     *         If no users have interactions with rootUser, or if rootUser does not exist,
     *         returns empty List.
     */
    private List<Integer> UDWGraphBFS(int rootUser) {

        if (UDWInteractions[rootUser] == null) {
            return new ArrayList<>();
        }

        //Initialize queue and boolean array for keeping track of the search path
        boolean[] visitedUser = new boolean[userIds.size() + 1];
        Queue<Integer> queue = new LinkedList<Integer>();

        ArrayList<Integer> graphComponents = new ArrayList<>();

        //Add the root user to the beginning of the queue
        visitedUser[rootUser] = true;
        queue.add(rootUser);

        while (queue.size() != 0) {
            //Remove the first user in the queue then search for the user's linked neighbours
            rootUser = queue.poll();
            graphComponents.add(rootUser);
            for (int i = 0; i < UDWInteractions[rootUser].size(); i++) {
                //Insert user ID into queue only if the user has not been visited by the search
                if (!visitedUser[UDWInteractions[rootUser].get(i)]) {
                    visitedUser[UDWInteractions[rootUser].get(i)] = true;
                    queue.add(UDWInteractions[rootUser].get(i));
                }
            }
        }

        return graphComponents;
    }

    /**
     * Finds the number of completely disjoint graph
     * components in the UDWInteractionGraph object.
     *
     * @return the number of completely disjoint graph
     *         components in the UDWInteractionGraph object.
     * effects: number returned >= 0;
     */
    public int NumberOfComponents() {
        //Convert the set of userIds to an array for accessing inner elements
        Integer[] userIdsArray = new Integer[largestUserId];
        userIds.toArray(userIdsArray);

        List<Integer> searchedUsers = new ArrayList<>();
        int count = 0;

            for (int i = 0; i < userIdsArray.length; i++) {
                if (!searchedUsers.contains(userIdsArray[i])) {
                    searchedUsers.addAll(this.UDWGraphBFS(userIdsArray[i]));
                    count++;
                }
            }

        return count;
    }

    /**
     * Tests whether a path exists between two users in an adjacency list
     *
     * @param userID1 the user ID for the first user
     * @param userID2 the user ID for the second user
     * @return true if a path exists between the two users.
     *         false if a path does not exist between the two users.
     *         false if userID1 or/and userID2 does not exist in file.
     */
    public boolean PathExists(int userID1, int userID2) {
        if (this.UDWGraphBFS(userID1).contains(userID2)) {
            return true;
        } else {
            return false;
        }
    }

}
