package cpen221.mp2;

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
    AF: A 2d to represent adjacency matrix
     */
    //private int emailCount; // count of email sent from specified sender to specified receiver]
    //private Set<Integer> idSet ; // the ID Set of a graph

    private int [][] array1; // adjacency matrix
    private List<Integer> realSender;
    private List<Integer> realReceiver;
    private List<Integer> realTime ;
    //private List<Integer> xx = new ArrayList<>(); // a List of integer; all sender, receiver, and time info;



    //private StringBuilder Etext = new StringBuilder();

    private List<Integer> sender = new ArrayList<>(); // a List of sender info
    private List <Integer> receiver = new ArrayList<>(); // a list of receiver info
    private List<Integer> time = new ArrayList<>(); // a list of time info

    private List<Integer> realSender1 = new ArrayList<>();
    private List<Integer> realReceiver1 = new ArrayList<>();
    private List<Integer> realTime1 = new ArrayList<>();

    /**
     * Creates a new DWInteractionGraph using an email interaction file.
     * The email interaction file will be in the resources directory.
     *
     * @param timeWindow an array of length 2 used to specify contraints
     * @param fileName   the name of the file in the resources
     *                   directory containing email interactions
     */
    public DWInteractionGraph(String fileName, int[] timeWindow) {
        // TODO: Implement this constructor

//         List<Integer> sender = new ArrayList<>(); // a List of sender info
//         List<Integer> receiver = new ArrayList<>(); // a list of receiver info
//         List<Integer> time = new ArrayList<>(); // a list of time info
//
//         List<Integer> realSender1 = new ArrayList<>();
//         List<Integer> realReceiver1 = new ArrayList<>();
//         List<Integer> realTime1 = new ArrayList<>();

        StringBuilder Etext = new StringBuilder();
        reader(fileName,Etext);
        helper(sender, receiver, time, Etext);

        // filter out senders and receivers who are not in the time window
        for(int i = 0; i < time.size(); i++){
            if(time.get(i)>= timeWindow[0] && time.get(i) <=timeWindow[1]){
                realSender1.add(sender.get(i));
                realReceiver1.add(receiver.get(i));
                realTime1.add(time.get(i));
            }
        }

        int [][] array1 = new int[realSender1.size()][realSender1.size()];

        for(int i_1 = 0; i_1 < array1.length; i_1++){
            for(int j_1 =0; j_1 <array1.length; j_1++){
                array1[i_1][j_1]= 0;
            }
        }

        int count =0;
        for(Integer i: realSender1){
            array1[i][realReceiver1.get(count)] = 1;
            count++;
        }

        this.array1 = array1;
        realSender = realSender1;
        realReceiver = realReceiver1;
        realTime = realTime1;
    }

    private void reader(String fileName, StringBuilder Etext){
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
        String[] stuff= Etext.toString().split(" ");
        for (String word :stuff ) {
            if(word!="" && word!=" ")
                xx.add(Integer.parseInt(word));
            else{
                break;
            }
        }
        int count =7;
        for(int i = 0; i < xx.size(); i++){
            if(count ==7){
                sender.add(i);
                count = 3;
            }
            else if(count == 3){
                receiver.add(i);
                count = 5;
            }
            else if(count ==5){
                time.add(i);
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

        StringBuilder Etext = new StringBuilder();
        // use a 2-d array to represent this object as adjacency matrix
        reader(fileName , Etext); // Etext is a string and it will be read and
        //xx is an arraylist with elements of string that rep integers
        helper(sender, receiver, time, Etext);

        realSender1 = new ArrayList<>(sender);
        realReceiver1 = new ArrayList<>(receiver);
        realTime1 = new ArrayList<>(time);

        int [][] array2 = new int[realSender1.size()][realSender1.size()];

        for(int i_1 = 0 ; i_1 < array2.length; i_1++){
            for(int j_1 =0; j_1 <array2.length; j_1++){
                array2[i_1][j_1]= 0;
            }
        }

        int count =0;
        for(Integer i: realSender1){
            array2[i][realReceiver1.get(count)] = 1;
            count++;
        }

        array1 = array2; // instance of the graph
        realSender = realSender1;
        realReceiver = realReceiver1;
        realTime = realTime1;

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
        int length = inputDWIG.array1.length;
        int array2[][] = new int[length][];


        for(int i = 0; i < length; i++ ){
            if(inputDWIG.realTime.get(i) >= timeFilter[0] && inputDWIG.realTime.get(i) <= timeFilter[1]){
                array2[inputDWIG.realSender.get(i)][inputDWIG.realReceiver.get(i)]=1;
            }
            else{
                array2[inputDWIG.realSender.get(i)][inputDWIG.realReceiver.get(i)]=0;
            }
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
        // TODO: Implement this constructor

        int length = inputDWIG.array1.length;
        int array2[][] = inputDWIG.array1;

        for(int i = 0; i<length; i++ ){
            if(!userFilter.contains(inputDWIG.realSender.get(i))){
                array2[inputDWIG.realSender.get(i)][inputDWIG.realReceiver.get(i)]=0;
            }
            else if(!userFilter.contains(inputDWIG.realReceiver.get(i))){
                array2[inputDWIG.realSender.get(i)][inputDWIG.realReceiver.get(i)]=0;
            }
            else{
                array2[inputDWIG.realSender.get(i)][inputDWIG.realReceiver.get(i)]=1;
            }
        }

        array1 = array2;

    }

    /**
     * @return a Set of Integers, where every element in the set is a User ID
     * in this DWInteractionGraph.
     */
    public Set<Integer> getUserIDs() {
        // TODO: Implement this getter method
        Set <Integer> idSet = new HashSet<>();

        for(int i=0; i< array1.length; i++){
            for(int j=0; j< array1.length;j++){
                if(array1[i][j]==1){
                    idSet.add(realSender.get(i));
                    idSet.add(realReceiver.get(j));
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

        int emailCount =0;
        for(int i=0;i<realSender.size(); i++){
            for(int j = 0 ; j <realReceiver.size(); j++){
                if(realSender.get(i)==sender && realReceiver.get(j)==receiver){
                    emailCount++;
                }
            }
        }
        return emailCount;
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
        return null;
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
        return null;
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
        // TODO: Implement this method
        return -1;
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
        DWInteractionGraph graph;
        graph = new DWInteractionGraph("resources/Task3Transactions1.txt");
        List<Integer> BFS = new ArrayList<>();

        int user1 = userID1;
        int user2 = userID2;

        int totalnodes = 9;                              //number of nodes in the graph
        LinkedList<Integer> adj[];
        Queue<Integer> queue = new LinkedList<Integer>();

        boolean nodes[] = new boolean[totalnodes];       //Holds true and false
        int b = 0;

        nodes[user1]=true;
        queue.add(user1);   //root node is added to the top of the queue
        int top = user1;

        int i = 0;

            while (queue.size() != 0)
            {
                top = queue.poll();

                if (top != user2) {
                    BFS.add(top);   //Add top to the path list
                }

                else {
                    break;
                }

                for (int j = 0; j < adj[user1].size(); j++)  //iterate through the linked list and push all neighbors into queue
                {
                    b = adj[user1].get(i);
                    if (!nodes[b])                    //only insert nodes into queue if they have not been explored already
                    {
                        nodes[b] = true;
                        queue.add(b);
                    }
                }
            }

        return BFS;
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
        return null;
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