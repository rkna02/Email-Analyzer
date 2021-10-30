package cpen221.mp2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DWInteractionGraph {

    /* ------- Task 1 ------- */
    /* Building the Constructors */

    /*
    RI: array1 cannot be greater than the size of all the vertices,
        and it only contains zero and one.

    AF: A 2d to represent adajacency matrix
     */
    private int [][] array1; // adajency matrix

    //
    private StringBuilder Etext = new StringBuilder();
    private List<Integer> xx = new ArrayList<>(); // a List of integer; all sender, receiver, and time info;
    private List<Integer> realSender = new ArrayList<>();
    private List<Integer> realReceiver = new ArrayList<>();
    private List<Integer> realTime = new ArrayList<>();
    private List<Integer> sender = new ArrayList<>(); // a List of sender info
    private List<Integer> receiver = new ArrayList<>(); // a list of receiver info
    private List<Integer> time = new ArrayList<>(); // a list of time info
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

        reader(fileName);
        helper();

        // filter out senders and receivers who are not in the time window
        for(int i = 0 ; i < time.size(); i++){
            if(time.get(i)<= timeWindow[0] && time.get(i)>=timeWindow[1]){
                realSender.add(sender.get(i));
                realReceiver.add(receiver.get(i));
                realTime.add(time.get(i));
            }
        }

        int [][] array1 = new int[realSender.size()][realSender.size()];

        for(int i_1 = 0 ; i_1 < array1.length; i_1++){
            for(int j_1 =0; j_1 <array1.length; j_1++){
                array1[i_1][j_1]= 0;
            }
        }

        int count =0;
        for(Integer i: realSender){
            array1[i][realReceiver.get(count)] = 1;
            count++;
        }

        this.array1 = array1; // instance of the graph

    }
    private void reader(String fileName){
        try {
            String fileName2 = new String(fileName); // myFile.txt should be in the root directory for your project

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

    private void helper() {
        String stuff = new String(Etext);
        for (String i : stuff.split(" ")) {
            if(i!=""){
                 xx.add(Integer.valueOf(i));
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
        // use a 2-d array to represent this object as adjacency matrix
        reader(fileName);

        helper();
        realSender = new ArrayList<>(sender);
        realReceiver = new ArrayList<>(receiver);
        realTime = new ArrayList<>(time);

        int [][] array1 = new int[realSender.size()][realSender.size()];

        for(int i_1 = 0 ; i_1 < array1.length; i_1++){
            for(int j_1 =0; j_1 <array1.length; j_1++){
                array1[i_1][j_1]= 0;
            }
        }

        int count =0;
        for(Integer i: realSender){
            array1[i][realReceiver.get(count)] = 1;
            count++;
        }

        this.array1 = array1; // instance of the graph
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
     * @param sender   the User ID of the sender in the email transaction.
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
