package cpen221.mp2;

import org.w3c.dom.Node;

import javax.xml.stream.events.EndDocument;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.*;

public class DWInteractionGraph {

    /* ------- Task 1 ------- */
    /* Building the Constructors */

    /*
    RI: array1 cannot be greater than the size of all the vertices,
        and it only contains zero and one.

    AF: A 2d to represent adajacency matrix
     */
    //private int emailCount; // count of email sent from specified sender to specified receiver]
    //private Set<Integer> idSet ; // the ID Set of a graph

    private int[][] array1; // adajency matrix
    private List<Integer> realSender;
    private List<Integer> realReceiver;
    private List<Integer> realTime;

    /**
     * Creates a new DWInteractionGraph using an email interaction file.
     * The email interaction file will be in the resources directory.
     *
     * @param timeWindow an array of length 2 used to specify contraints
     * @param fileName   the name of the file in the resources
     *                   directory containing email interactions
     */
    public DWInteractionGraph(String fileName, int[] timeWindow) {
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

        array1 = new int[largestId + 1][largestId + 1];

        for (int i_1 = 0; i_1 < array1.length; i_1++) {
            for (int j_1 = 0; j_1 < array1.length; j_1++) {
                array1[i_1][j_1] = 0;
            }
        }

        int count = 0;
        for (Integer i : realSender) {
            array1[i][realReceiver.get(count)] = 1;
            count++;
        }

    }

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
        // TODO: Implement this constructor

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

        this.array1 = new int[maxsize + 1][maxsize + 1];

        int count = 0;
        for (Integer i : realSender) {
            this.array1[realSender.get(count)][realReceiver.get(count)] = 1;
            count++;
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
        // TODO: Implement this constructor
        realSender = new ArrayList<>();
        realReceiver = new ArrayList<>();
        realTime = new ArrayList<>();

        int length = inputDWIG.array1.length;

        this.array1 = new int[length + 1][length + 1];

        for (int i = 0; i < inputDWIG.realTime.size(); i++) {
            if (inputDWIG.realTime.get(i) >= timeFilter[0] && inputDWIG.realTime.get(i) <= timeFilter[1]) {
                this.array1[inputDWIG.realSender.get(i)][inputDWIG.realReceiver.get(i)] = 1;
                realSender.add(inputDWIG.realSender.get(i));
                realReceiver.add(inputDWIG.realReceiver.get(i));
                realTime.add(inputDWIG.realTime.get(i));
            } else {
                this.array1[inputDWIG.realSender.get(i)][inputDWIG.realReceiver.get(i)] = 0;

            }
        }

//        this.realSender = new ArrayList<>(inputDWIG.realSender);
//        this.realReceiver = new ArrayList<>(inputDWIG.realReceiver);
//        this.realTime = new ArrayList<>(inputDWIG.realTime);
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
        // TODO: Implement this constructor
        realSender = new ArrayList<>();
        realReceiver = new ArrayList<>();
        realTime = new ArrayList<>();

        int length = inputDWIG.array1.length;

        this.array1 = new int[length + 1][length + 1];
        for (int i = 0; i < inputDWIG.realTime.size(); i++) {

            if (!userFilter.contains(inputDWIG.realSender.get(i)) && !userFilter.contains(inputDWIG.realReceiver.get(i))) {
                array1[inputDWIG.realSender.get(i)][inputDWIG.realReceiver.get(i)] = 0;

            } else {
                array1[inputDWIG.realSender.get(i)][inputDWIG.realReceiver.get(i)] = 1;
                realSender.add(inputDWIG.realSender.get(i));
                realReceiver.add(inputDWIG.realReceiver.get(i));
                realTime.add(inputDWIG.realTime.get(i));
            }
        }

//        this.realSender = new ArrayList<>(inputDWIG.realSender);
//        this.realReceiver = new ArrayList<>(inputDWIG.realReceiver);
//        this.realTime = new ArrayList<>(inputDWIG.realTime);
    }

    /**
     * @return a Set of Integers, where every element in the set is a User ID
     * in this DWInteractionGraph.
     */
    public Set<Integer> getUserIDs() {
        // TODO: Implement this getter method
        Set<Integer> idSet = new HashSet<>();

        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1.length; j++) {
                if (array1[i][j] == 1) {
                    idSet.add(i);
                    idSet.add(j);
                }
            }
        }

        return idSet;
    }

    /**
     * @param sender   the User ID of the sender in the email transaction.
     * @param receiver the User ID of the receiver in the email transaction.
     * @return the number of emails sent from the specified sender to the specified
     * receiver in this DWInteractionGraph.
     */
    public int getEmailCount(int sender, int receiver) {
        // TODO: Implement this getter method

        int emailCount = 0;
        for (int i = 0; i < realSender.size(); i++) {
            if (realSender.get(i) == sender && realReceiver.get(i) == receiver) {
                emailCount++;
            }
        }
        return emailCount;
    }
    
    
    List<Integer> getRealSender() {
        return this.realSender;
    }

    List<Integer> getRealReceiver() {
        return this.realReceiver;
    }

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
     * [NumberOfSenders, NumberOfReceivers, NumberOfEmailTransactions]
     */

    public int[] ReportActivityInTimeWindow(int[] timeWindow) {
        // TODO: Implement this method

        Set<Integer> senderSet = new HashSet<>();
        Set<Integer> receiverSet = new HashSet<>();
        List<Integer> timeList = new ArrayList<>();

        int[] reportNum = new int[3];

        int start = -1;
        int end = -1;

        for (int i = 0; i < realTime.size(); i++) {
            if (realTime.get(i) >= timeWindow[0] && realTime.get(i) <= timeWindow[1]) {
                if (array1[realSender.get(i)][realReceiver.get(i)] == 1) {
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
     * [NumberOfEmailsSent, NumberOfEmailsReceived, UniqueUsersInteractedWith]
     * If the specified User ID does not exist in this instance of a graph,
     * returns [0, 0, 0].
     */
    public int[] ReportOnUser(int userID) {
        // TODO: Implement this method
        int[] theOne = new int[3];

        List<Integer> unique = new ArrayList<>();
        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < realSender.size(); i++) {
            //making sure it's not filtered out by dwig 1 or dwig 2
            if (array1[realSender.get(i)][realReceiver.get(i)] == 1) {
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
     * @param N               a positive number representing rank. N=1 means the most active.
     * @param interactionType Represent the type of interaction to calculate the rank for
     *                        Can be SendOrReceive.Send or SendOrReceive.RECEIVE
     * @return the User ID for the Nth most active user in specified interaction type.
     * Sorts User IDs by their number of sent or received emails first. In the case of a
     * tie, secondarily sorts the tied User IDs in ascending order.
     */

    public int NthMostActiveUser(int N, SendOrReceive interactionType) {
        //Convert the set of userIds to an array for accessing inner elements
        Integer[] userIdsArray = new Integer[getUserIDs().size()];
        getUserIDs().toArray(userIdsArray);

        List<Integer> userIdsList = new ArrayList<>();
        List<List<Integer>> userRanks = new ArrayList<>();

        if (interactionType == SendOrReceive.SEND) {
            int mostSentEmails = 0;
            int nextMostSentEmails = 0;
            int rankedUsers = 0;

            for (int i = 0; i < userIdsArray.length; i++) {
                if (realSender.contains(userIdsArray[i])) {
                    userIdsList.add(userIdsArray[i]);
                }
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
                userRanks.add(NthRankUsers);

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
                userRanks.add(NthRankUsers);

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

        if (N > userRanks.size()) {
            return -1;
        } else if (userRanks.get(N - 1).size() == 0){
            return -1;
        } else {
            return userRanks.get(N - 1).get(0);
        }
    }

    /* ------- Task 3 ------- */

    /**
     * performs breadth first search on the DWInteractionGraph object
     * to check path between user with userID1 and user with userID2.
     *
     * @param userID1 the user ID for the first user
     * @param userID2 the user ID for the second user
     * @return if a path exists, returns aa list of user IDs
     * in the order encountered in the search.
     * if no path exists, should return null.
     */
    public List<Integer> BFS(int userID1, int userID2) {
        // TODO: Implement this method
        return null;
    }

    /**
     * performs depth first search on the DWInteractionGraph object
     * to check path between user with userID1 and user with userID2.
     *
     * @param userID1 the user ID for the first user
     * @param userID2 the user ID for the second user
     * @return if a path exists, returns aa list of user IDs
     * in the order encountered in the search.
     * if no path exists, should return null.
     */
    public List<Integer> DFS(int userID1, int userID2) {
        // TODO: Implement this method
        Set<Integer> size = new HashSet<>();
        for (Integer integer : realSender) {
            size.add(integer);
        }
        for (Integer integer1 : realReceiver) {
            size.add(integer1);
        }
        int max = Collections.max(size);
        // int sizeOfV = size.size();
        List<Integer> theList = new ArrayList<>();
        theList.add(userID1);
        DFS1(userID1, max + 1, userID2, theList);

        if (theList.contains(userID2)) {
            return theList;
        }

        return null;
    }

    // v is number of vertices
    void DFS1(int start, int S, int userID2, List<Integer> theList) {
        //S is num of vertices
        boolean visited[] = new boolean[S]; // mark every vertices to be unvisited
        DFSUtil(start, visited, userID2, theList,  0);
    }

    void DFSUtil(int v, boolean[] visited, int userID2, List<Integer> theList, int track) {
        // mark the current node as visited
        // and add the node to the return list
        visited[v] = true;

        //int track = 0;
        for (int i = 0; i < array1.length; i++) {
            if (array1[v][i] == 1) {

                if (i == userID2) {
                    track++;
                    theList.add(i);


                    break;
                } else if (!visited[i]) {
                    theList.add(i);
                    DFSUtil(i, visited, userID2, theList,track);
                    break;
                }
            }
            // go to the previous one to check
        }

        if(track==0){
            Boolean b = true;
            for (Boolean bb : visited) {
                if (bb == false) {
                    b = bb;
                }
            }

            int previous = 0;
            // obtain the previous node
            for (int j = 0; j < array1.length; j++) {
                if (array1[j][v] == 1) {
                    previous = j;
                    break;
                }
            }
            if (track == 0 && b == false) {
                DFSUtil(previous, visited, userID2, theList,track);

            } else {
                System.out.println("end");
            }
        }
    }



    /* ------- Task 4 ------- */

    /**
     * Read the MP README file carefully to understand
     * what is required from this method.
     *
     * @param hours
     * @return the maximum number of users that can be polluted in N hours
     */
    public int MaxBreachedUserCount(int hours) {
        // TODO: Implement this method
        return 0;
    }

}
