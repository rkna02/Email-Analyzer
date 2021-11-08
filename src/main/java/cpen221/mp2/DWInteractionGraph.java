package cpen221.mp2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DWInteractionGraph {

    /* ------- Task 1 ------- */
    /** Concrete Representation */

    /** An adjacency matrix made of a 2D array */
    private int[][] DWInteractions; // adjacency matrix

    /** Lists of senders, receivers, and the time sent for all Emails */
    private List<Integer> realSender;
    private List<Integer> realReceiver;
    private List<Integer> realTime;

    //Representation Invariant:
    //  (realSender.size() == realReceiver.size() && realReceiver.size() == realTime.size())
    //  realTime[i] >= 0, for 0 <= i < realTime.size()

    //Abstract Function:
    //  Graph interactions represented by a 2D adjacency matrix:
    //  2D array at DWInteractions[x][y] represents a matrix of users, where rows represent senders and
    //  columns represent email receivers and DWInteractions[x][y] == 1 implies an interaction from x to y
    //  Example:
    //  2D array at DWInteractions[1][2] implies that user with ID '1' has emailed user with ID '2' (an interaction)
    //
    //  Graph interaction details of the users represented by three ArrayLists:
    //  realSender.get(N) represents the Nth Email sender
    //  realReceiver.get(N) represents the Nth Email receiver
    //  realTime.get(N) represents the Nth Email send time (in seconds)
    //  Example:
    //  realSender.get(2) represents the sender of the 2nd Email
    //  realReceiver.get(3) represents the receiver of the 3rd Email
    //  realTime.get(1) represents a send time of the 1st Email (in seconds)
    //

    /**
     * Checks the representation invariant.
     *
     * effects: no effects if this satisfies rep invariant,
     * 		    otherwise throws a runtime exception.
     */
    private void checkRep() {
        if (realSender.size() != realReceiver.size() ||
            realReceiver.size() != realTime.size() ||
            realSender.size() != realTime.size()) {
            throw new RuntimeException("missing data from file");
        }
        for (int time: realTime) {
            if (time < 0) {
                throw new RuntimeException("time out of bounds");
            }
        }
    }

    /**
     * Creates a new DWInteractionGraph using an email interaction file with a time filter.
     * The email interaction file will be in the resources directory.
     *
     * @param fileName the name of the file in the resources
     *                 directory containing email interactions
     * @param timeWindow an integer array of length 2: [t0, t1]
     *                   where t0 <= t1. The created UDWInteractionGraph
     *                   should only include those emails in the input
     *                   UDWInteractionGraph with send time t in the
     *                   t0 <= t <= t1 range.
     *
     */
    public DWInteractionGraph(String fileName, int[] timeWindow) {
        try {
            List<Integer> sender = new ArrayList<>(); // a List of sender info
            List<Integer> receiver = new ArrayList<>(); // a list of receiver info
            List<Integer> time = new ArrayList<>(); // a list of time info
            StringBuilder Etext = new StringBuilder();
            int largestSenderId = 0;
            int largestReceiverId = 0;
            int largestId = 0;

            realSender = new ArrayList<>();
            realReceiver = new ArrayList<>();
            realTime = new ArrayList<>();

            reader(fileName, Etext);
            helper(sender, receiver, time, Etext);

            // filter out senders and receivers who are not in the time window
            for (int i = 0; i < time.size(); i++) {
                if (time.get(i) >= timeWindow[0] && time.get(i) <= timeWindow[1]) {
                    realSender.add(sender.get(i));
                    realReceiver.add(receiver.get(i));
                    realTime.add(time.get(i));
                }
            }

            //Find largest user id
            if (realSender.size() > 0) {
                largestSenderId = Collections.max(realSender);
            }
            if (realReceiver.size() > 0) {
                largestReceiverId = Collections.max(realReceiver);
            }
            if (largestSenderId > largestReceiverId) {
                largestId = largestSenderId;
            } else {
                largestId = largestReceiverId;
            }

            DWInteractions = new int[largestId + 1][largestId + 1];

            for (int i_1 = 0; i_1 < DWInteractions.length; i_1++) {
                for (int j_1 = 0; j_1 < DWInteractions.length; j_1++) {
                    DWInteractions[i_1][j_1] = 0;
                }
            }

            int count = 0;
            for (Integer i : realSender) {
                DWInteractions[i][realReceiver.get(count)] = 1;
                count++;
            }
        } finally {
            checkRep();
        }

    }

    /**
     * Reads a file given a file name and the data stored inside it
     *
     * @param Etext      Data within the file
     * @param fileName   the name of the file in the resources
     *                   directory containing email interactions
     * effects:          IoException if file cannot be read
     */
    private void reader(String fileName, StringBuilder Etext) {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            for (String fileLine = reader.readLine();
                 fileLine != null;
                 fileLine = reader.readLine()) {
                System.out.println(fileLine);

                Etext.append(" ");
                Etext.append(fileLine);

            }
            reader.close();

        } catch (IOException ioe) {
            System.out.println("Problem reading file!");

        }
    }

    /**
     * Ensures Senders, Receivers, and Time are holding the correct data
     *
     * @param sender a list which holds all senders
     * @param receiver a list which holds all receivers
     * @param time a list which holds the time at each email interaction
     * @param Etext lines of data extracted from file
     */
    private void helper(List sender, List receiver, List time, StringBuilder Etext) {
        List<Integer> xx = new ArrayList<>();

        String[] stuff = Etext.toString().split(" ");

        for (String word : stuff) {
            if (!word.equals("") && !word.equals(" "))
                xx.add(Integer.parseInt(word));

        }

        int count = 7;
        for (Integer integer : xx) {
            if (count == 7) {
                sender.add(integer);
                count = 3;
            } else if (count == 3) {
                receiver.add(integer);
                count = 5;
            } else if (count == 5) {
                time.add(integer);
                count = 7;
            }
        }

    }

    /**
     * Creates a new DWInteractionGraph using an email interaction file.
     * The email interaction file will be in the resources directory.
     *
     * @param fileName the name of the file in the resources
     *                 directory containing email interactions
     */
    public DWInteractionGraph(String fileName) {
        try {
            List<Integer> sender = new ArrayList<>(); // a List of sender info
            List<Integer> receiver = new ArrayList<>(); // a list of receiver info
            List<Integer> time = new ArrayList<>(); // a list of time info
            StringBuilder Etext = new StringBuilder();


            // use a 2-d array to represent this object as adjacency matrix
            reader(fileName, Etext); // Etext is a string and it will be read and
            //xx is an arraylist with elements of string that rep integers
            helper(sender, receiver, time, Etext);

            realSender = new ArrayList<>(sender);
            realReceiver = new ArrayList<>(receiver);
            realTime = new ArrayList<>(time);

            // find the largest user ID
            int maxsize = 0;
            for (Integer value : realSender) {
                if (maxsize < value) {
                    maxsize = value;
                }
            }
            for (Integer integer : realReceiver) {
                if (maxsize < integer) {
                    maxsize = integer;
                }
            }

            this.DWInteractions = new int[maxsize + 1][maxsize + 1];

            int count = 0;
            for (Integer i : realSender) {
                this.DWInteractions[realSender.get(count)][realReceiver.get(count)] = 1;
                count++;
            }
        } finally {
            checkRep();
        }

    }

    /**
     * Creates a new DWInteractionGraph from a DWInteractionGraph object
     * and considering a time window filter.
     *
     * @param inputDWIG  a DWInteractionGraph object
     * @param timeFilter an integer array of length 2: [t0, t1]
     *                   where t0 <= t1. The created DWInteractionGraph
     *                   should only include those emails in the input
     *                   DWInteractionGraph with send time t in the
     *                   t0 <= t <= t1 range.
     */
    public DWInteractionGraph(DWInteractionGraph inputDWIG, int[] timeFilter) {
        try {
            realSender = new ArrayList<>();
            realReceiver = new ArrayList<>();
            realTime = new ArrayList<>();

            int length = inputDWIG.DWInteractions.length;

            this.DWInteractions = new int[length + 1][length + 1];

            for (int i = 0; i < inputDWIG.realTime.size(); i++) {
                if (inputDWIG.realTime.get(i) >= timeFilter[0] && inputDWIG.realTime.get(i) <= timeFilter[1]) {
                    this.DWInteractions[inputDWIG.realSender.get(i)][inputDWIG.realReceiver.get(i)] = 1;
                    realSender.add(inputDWIG.realSender.get(i));
                    realReceiver.add(inputDWIG.realReceiver.get(i));
                    realTime.add(inputDWIG.realTime.get(i));
                } else {
                    this.DWInteractions[inputDWIG.realSender.get(i)][inputDWIG.realReceiver.get(i)] = 0;

                }
            }
        } finally {
            checkRep();
        }
    }

    /**
     * Creates a new DWInteractionGraph from a DWInteractionGraph object
     * and considering a list of User IDs.
     *
     * @param inputDWIG  a DWInteractionGraph object
     * @param userFilter a List of User IDs. The created DWInteractionGraph
     *                   should exclude those emails in the input
     *                   DWInteractionGraph for which neither the sender
     *                   nor the receiver exist in userFilter.
     */
    public DWInteractionGraph(DWInteractionGraph inputDWIG, List<Integer> userFilter) {
        try {
            realSender = new ArrayList<>();
            realReceiver = new ArrayList<>();
            realTime = new ArrayList<>();

            int length = inputDWIG.DWInteractions.length;

            this.DWInteractions = new int[length + 1][length + 1];
            for (int i = 0; i < inputDWIG.realTime.size(); i++) {

                if (!userFilter.contains(inputDWIG.realSender.get(i)) && !userFilter.contains(inputDWIG.realReceiver.get(i))) {
                    DWInteractions[inputDWIG.realSender.get(i)][inputDWIG.realReceiver.get(i)] = 0;

                } else {
                    DWInteractions[inputDWIG.realSender.get(i)][inputDWIG.realReceiver.get(i)] = 1;
                    realSender.add(inputDWIG.realSender.get(i));
                    realReceiver.add(inputDWIG.realReceiver.get(i));
                    realTime.add(inputDWIG.realTime.get(i));
                }
            }
        } finally {
            checkRep();
        }
    }

    /**
     * Gets all the users that has sent/received an Email
     *
     * @return a Set of Integers, where every element in the set is a User ID
     * in this DWInteractionGraph.
     */
    public Set<Integer> getUserIDs() {
        Set<Integer> idSet = new HashSet<>();

        for (int i = 0; i < DWInteractions.length; i++) {
            for (int j = 0; j < DWInteractions.length; j++) {
                if (DWInteractions[i][j] == 1) {
                    idSet.add(i);
                    idSet.add(j);
                }
            }
        }

        return idSet;
    }

    /**
     * Counts the Emails that has been sent from a user to another user
     *
     * @param sender   the User ID of the sender in the email transaction.
     * @param receiver the User ID of the receiver in the email transaction.
     * @return the number of emails sent from the specified sender to the specified
     *         receiver in this DWInteractionGraph.
     */
    public int getEmailCount(int sender, int receiver) {

        int emailCount = 0;
        for (int i = 0; i < realSender.size(); i++) {
            if (realSender.get(i) == sender && realReceiver.get(i) == receiver) {
                emailCount++;
            }
        }
        return emailCount;
    }
    
    /**
     * Gets the user IDs of the senders of all Emails
     *
     * @return an arraylist of the senders of all Emails
     */
    List<Integer> getRealSender() {
        return this.realSender;
    }

    /**
     * Gets the user IDs of the receivers of all Email
     *
     * @return an arraylist of the receivers of all Emails
     */
    List<Integer> getRealReceiver() {
        return this.realReceiver;
    }

    /**
     * Gets the time of all the Emails sent
     * 
     * @return an arrayList of the times the Emails were sent
     */
    List<Integer> getRealTime() {
        return this.realTime;
    }

    /* ------- Task 2 ------- */

    /**
     * Given an int array, [t0, t1], reports email transaction details.
     * Suppose an email in this graph is sent at time t, then all emails
     * sent where t0 <= t <= t1 are included in this report.
     *
     * @param timeWindow is an int array of size 2 [t0, t1] where t0<=t1.
     * @return an int array of length 3, with the following structure:
     *         [NumberOfSenders, NumberOfReceivers, NumberOfEmailTransactions]
     */

    public int[] ReportActivityInTimeWindow(int[] timeWindow) {

        Set<Integer> senderSet = new HashSet<>();
        Set<Integer> receiverSet = new HashSet<>();
        List<Integer> timeList = new ArrayList<>();

        int[] reportNum = new int[3];

        for (int i = 0; i < realTime.size(); i++) {
            if (realTime.get(i) >= timeWindow[0] && realTime.get(i) <= timeWindow[1]) {
                if (DWInteractions[realSender.get(i)][realReceiver.get(i)] == 1) {
                    senderSet.add(realSender.get(i));
                    receiverSet.add((realReceiver.get(i)));
                    timeList.add(realTime.get(i));
                }
            }
        }
        reportNum[0] = senderSet.size();
        reportNum[1] = receiverSet.size();
        reportNum[2] = timeList.size();

        return reportNum;
    }

    /**
     * Given a User ID, reports the specified User's email transaction history.
     *
     * @param userID the User ID of the user for which the report will be
     *               created.
     * @return an int array of length 3 with the following structure:
     *         [NumberOfEmailsSent, NumberOfEmailsReceived, UniqueUsersInteractedWith]
     *         If the specified User ID does not exist in this instance of a graph,
     *         returns [0, 0, 0].
     */
    public int[] ReportOnUser(int userID) {
        int[] theOne = new int[3];

        List<Integer> unique = new ArrayList<>();
        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < realSender.size(); i++) {
            //making sure it's not filtered out by dwig 1 or dwig 2
            if (DWInteractions[realSender.get(i)][realReceiver.get(i)] == 1) {
                if (realSender.get(i) == userID) {
                    count1++;
                    unique.add(realReceiver.get(i));
                }
                if (realReceiver.get(i) == userID) {
                    count2++;
                    unique.add(realSender.get(i));
                }
            }
        }

        Collections.sort(unique);
        Set<Integer> copy = new HashSet<>(unique);

        theOne[0] = count1;
        theOne[1] = count2;
        theOne[2] = copy.size();

        return theOne;
    }

    /**
     * Finds the most active Email sender or receiver 
     * 
     * @param N               a positive number representing rank. N=1 means the most active.
     * @param interactionType Represent the type of interaction to calculate the rank for
     *                        Can be SendOrReceive.Send or SendOrReceive.RECEIVE
     * @return the User ID for the Nth most active user in specified interaction type.
     *         Sorts User IDs by their number of sent or received emails first. 
     *         In the case of a tie, secondarily sorts the tied User IDs in ascending order.
     */

    public int NthMostActiveUser(int N, SendOrReceive interactionType) {
        //Convert the set of userIds to an array for accessing inner elements
        Integer[] userIdsArray = new Integer[getUserIDs().size()];
        getUserIDs().toArray(userIdsArray);

        List<Integer> userIdsList = new ArrayList<>();
        List<Integer> userRanks = new ArrayList<>();

        if (interactionType == SendOrReceive.SEND) {
            int mostSentEmails = 0;
            int nextMostSentEmails = 0;
            int rankedUsers = 0;

            for (int i = 0; i < userIdsArray.length; i++) {
                if (realSender.contains(userIdsArray[i])) {
                    userIdsList.add(userIdsArray[i]);
                }
            }
            if (N > userIdsList.size()) {
                return -1;
            }

            //Find the mostInteractions done by a user
            for (int i = 0; i < userIdsList.size(); i++) {
                if (ReportOnUser(userIdsList.get(i))[0] > mostSentEmails) {
                    mostSentEmails = ReportOnUser(userIdsList.get(i))[0];
                }
            }

            //Create a List of Lists with user rankings at every Nth rank
            while (rankedUsers < userIdsList.size()) {
                List<Integer> NthRankUsers = new ArrayList<>();
                for (int i = 0; i < userIdsList.size(); i++) {
                    if (ReportOnUser(userIdsList.get(i))[0] == mostSentEmails) {
                        NthRankUsers.add(userIdsList.get(i));
                        rankedUsers++;
                    }
                }
                Collections.sort(NthRankUsers);
                userRanks.addAll(NthRankUsers);

                //Update mostInteractions
                nextMostSentEmails = 0;
                for (int i = 0; i < userIdsList.size(); i++) {
                    if (ReportOnUser(userIdsList.get(i))[0] < mostSentEmails &&
                        ReportOnUser(userIdsList.get(i))[0] > nextMostSentEmails) {
                        nextMostSentEmails = ReportOnUser(userIdsList.get(i))[0];
                    }
                }
                mostSentEmails = nextMostSentEmails;
            }

        } else if (interactionType == SendOrReceive.RECEIVE) {
            int mostReceivedEmails = 0;
            int nextMostReceivedEmails = 0;
            int rankedUsers = 0;

            for (int i = 0; i < userIdsArray.length; i++) {
                if (realReceiver.contains(userIdsArray[i])) {
                    userIdsList.add(userIdsArray[i]);
                }
            }
            if (N > userIdsList.size()) {
                return -1;
            }

            //Find the mostInteractions done by a user
            for (int i = 0; i < userIdsList.size(); i++) {
                if (ReportOnUser(userIdsList.get(i))[1] > mostReceivedEmails) {
                    mostReceivedEmails = ReportOnUser(userIdsList.get(i))[1];
                }
            }
            while (rankedUsers < userIdsList.size()) {
                List<Integer> NthRankUsers = new ArrayList<>();
                for (int i = 0; i < userIdsList.size(); i++) {
                    if (ReportOnUser(userIdsList.get(i))[1] == mostReceivedEmails) {
                        NthRankUsers.add(userIdsList.get(i));
                        rankedUsers++;
                    }
                }
                Collections.sort(NthRankUsers);
                userRanks.addAll(NthRankUsers);

                //Update mostInteractions
                nextMostReceivedEmails = 0;
                for (int i = 0; i < userIdsList.size(); i++) {
                    if (ReportOnUser(userIdsList.get(i))[1] < mostReceivedEmails &&
                        ReportOnUser(userIdsList.get(i))[1] > nextMostReceivedEmails) {
                        nextMostReceivedEmails = ReportOnUser(userIdsList.get(i))[1];
                    }
                }
                mostReceivedEmails = nextMostReceivedEmails;
            }
        }
        return userRanks.get(N - 1);

    }

    /* ------- Task 3 ------- */

    /**
     * performs breadth first search on the DWInteractionGraph object
     * to check path between user with userID1 and user with userID2.
     *
     * @param userID1 the user ID for the first user
     * @param userID2 the user ID for the second user
     * @return if a path exists, returns aa list of user IDs
     *         in the order encountered in the search.
     *         If no path exists, return null.
     */
    public List<Integer> BFS(int userID1, int userID2) {
        int user1 = userID1;
        int user2 = userID2;

        Set<Integer> size = new HashSet<>();
        for (Integer integer : realSender) {
            size.add(integer);
        }
        for (Integer integer1 : realReceiver) {
            size.add(integer1);
        }
        int max = Collections.max(size);

        Set<Integer> IDs = getUserIDs();

        if (!IDs.contains(user1) || !IDs.contains(user2)) {
            return null;
        }
        // int sizeOfV = size.size();
        List<Integer> BFS = new ArrayList<>();

        int[][] Dgraph = DWInteractions;
        int[] visited = new int[max + 1];

        //Creates a queue list
        Queue<Integer> queue = new LinkedList<>();
        queue.add(user1);
        visited[user1] = 1;

        while(!queue.isEmpty()){
            int presentNode = queue.poll();
            BFS.add(presentNode);
            int nextNode;

            //Add all unvisited nodes to the queue
            while(((nextNode = neighbourNodes(presentNode, Dgraph, visited, user2)) != -1) && (nextNode != user2)){
                visited[nextNode] = 1;
                queue.add(nextNode);
            }


            //If the node we are currently at is user2, then add remaining items on queue and user2 to the path list
            if (nextNode == user2) {
                while(!queue.isEmpty()){
                    BFS.add(queue.poll());
                }
                queue.add(user2);
                BFS.add(queue.poll());
            }
        }
        //There exists a path
        if (!BFS.contains(user2)) {
            return null;
        }
        return BFS;
    }

    /**
     * Finds an adjacent node to another node 
     *
     * @param presentNode the node/vertex we are currently at in the search
     * @param graph DirectedWeightedgraph created from given data
     * @param visited An array which stores whether a node has been accessed
     * @param user2 the user ID for the second user
     * @return If a path exists, returns the index of an adjacent node
     *         else, returns -1.
     */
    private int neighbourNodes(int presentNode, int graph [][], int visited[], int user2){
        //Loops through all UserIds to see if there is an interaction
        for(int index = 0; index < graph.length; index++){
            if(visited[index] == 0 && graph[presentNode][index] == 1){
                return index;
            }

            if(index == user2 && graph[presentNode][index] == 1) {
                return user2;
            }
        }
        return -1;
    }

    /**
     * Performs depth first search on the DWInteractionGraph object
     * to check path between user with userID1 and user with userID2.
     *
     * @param userID1 the user ID for the first user
     * @param userID2 the user ID for the second user
     * @return if a path exists, returns aa list of user IDs
     *         in the order encountered in the search.
     *         If no path exists, should return null.
     */
    public List<Integer> DFS(int userID1, int userID2) {
        Set<Integer> size = new HashSet<>();
        for (Integer integer : realSender) {
            size.add(integer);
        }
        for (Integer integer1 : realReceiver) {
            size.add(integer1);
        }
        int max = Collections.max(size);
        List<Integer> theList = new ArrayList<>();
        theList.add(userID1);
        DFS1(userID1, max + 1, userID2, theList);
        List<Integer> thereturnone = new ArrayList<>();
        boolean havenorNot = false;
        for (int k = 0; k < theList.size(); k++) {
            thereturnone.add(theList.get(k));
            if (theList.get(k) == userID2) {
                havenorNot = true;
                break;
            }
        }
        if (havenorNot == true) {
            return thereturnone;
        }
        return null;
    }

    /**
     * sets up a boolean array that stores the visited nodes in a search
     * Array element = true if the node at index has been visited,
     * Array element = false if the node at index has not been visited.
     *
     * @param start A userId to start the search with
     * @param S The number of vertices + 1
     * @param userID2 The destination userId to find a path to
     * @param theList The order of DFS
     */
    void DFS1(int start, int S, int userID2, List<Integer> theList) {
        //S is num of vertices
        int[][] array2 = new int[DWInteractions.length][DWInteractions.length];
        boolean visited[] = new boolean[S]; // mark every vertices to be unvisited

        DFSUtil(start, visited, userID2, theList);
    }

    /**
     * DFS algorithm, stops when it finish searching its possibilities
     *
     * @param v the current node
     * @param visited the boolean array that indicates visited nodes 
     * @param userID2 the destination node
     * @param theList the list of order of the visit
     */
    void DFSUtil(int v, boolean[] visited, int userID2, List<Integer> theList) {
        // mark the current node as visited
        // and add the node to the return list
        visited[v] = true;
        for (int i = 0; i < DWInteractions.length; i++) {
            if (DWInteractions[v][i] == 1) {
                if (i == userID2) {
                    theList.add(i);
                    break;
                } else if (!visited[i]) {
                    theList.add(i);
                    DFSUtil(i, visited, userID2, theList);
                }
            }
        }
    }


    /* ------- Task 4 ------- */

    /**
     * Calculates the maximum number of users that can be polluted by a hacker's Email in N hours
     * Users can be polluted by receiving polluted Emails from their colleagues,
     * and would spread the malware as more Emails are being sent and received.
     *
     * @param hours Number of hours after the first victim receives an Email
     * @return the maximum number of users that can be polluted in N hours
     */
    public int MaxBreachedUserCount(int hours) {
        int seconds = 3600*hours;
        int totalUsers = 0;
        int temp = 0;
        for(int i = 0; i < realSender.size(); i++) {
            int start = realTime.get(i);
            int end = start + seconds;
            int endUser = 0;
            for (int j = i; j < realTime.size(); j++) {
                if (realTime.get(j) <= end) {
                    endUser = realReceiver.get(j);
                } else
                    break;
            }
            //Gets the size of the output array of BFS
            List<Integer> CheckSize = BFS(realSender.get(i), endUser);
            temp = CheckSize.size();

            if (temp >= totalUsers) {
                totalUsers = temp;
            }
        }
        return totalUsers;
    }

}
