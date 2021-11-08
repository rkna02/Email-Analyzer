package cpen221.mp2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class AdditionalTestsAllTasks {

    /** DW Test Graphs */
    private static DWInteractionGraph dwig1a, dwig1b, dwig1c, dwig1d, dwig1e;
    private static DWInteractionGraph dwig2a, dwig2b, dwig2c, dwig2d, dwig2e;
    private static DWInteractionGraph dwig3a, dwig3b, dwig3c, dwig3d, dwig3e;

    /** UDW Test Graphs */
    private static UDWInteractionGraph udwig1a, udwig1b, udwig1c, udwig1d, udwig1e, udwig1f;
    private static UDWInteractionGraph udwig2a, udwig2b, udwig2c, udwig2d, udwig2e, udwig2f;


    /** Set up test graphs */
    @BeforeAll
    public static void setupTests() {
        //Setup DW test graphs
        dwig1a = new DWInteractionGraph("resources/Task3Transactions1.txt");
        dwig1b = new DWInteractionGraph("resources/Task3Transactions1.txt", new int[]{0, 0});
        dwig1c = new DWInteractionGraph(dwig1a, new int[]{2, 3});
        dwig1d = new DWInteractionGraph(dwig1a, List.of(7));  //Empty Graph
        dwig1e = new DWInteractionGraph(dwig1a, Arrays.asList(3, 6));

        dwig2a = new DWInteractionGraph("resources/Task3Transactions2.txt");
        dwig2b = new DWInteractionGraph("resources/Task3Transactions2.txt", new int[]{12, 14});
        dwig2c = new DWInteractionGraph(dwig2a, new int[]{15, 16});  //Empty Graph
        dwig2d = new DWInteractionGraph(dwig2a, List.of(5));
        dwig2e = new DWInteractionGraph(dwig2a, Arrays.asList(0, 2, 3));

        dwig3a = new DWInteractionGraph("resources/Task4Transactions1.txt");
        dwig3b = new DWInteractionGraph("resources/Task4Transactions2.txt");
        dwig3c = new DWInteractionGraph("resources/Task4Transactions3.txt");

        //Setup UDW test graphs
        udwig1a = new UDWInteractionGraph("resources/Task3Transactions1.txt");
        udwig1b = new UDWInteractionGraph("resources/Task3Transactions1.txt", new int[]{0, 0});
        udwig1c = new UDWInteractionGraph(udwig1a, new int[]{2, 3});
        udwig1d = new UDWInteractionGraph(udwig1a, List.of(7));  //Empty graph
        udwig1e = new UDWInteractionGraph(udwig1a, Arrays.asList(3, 6));
        udwig1f = new UDWInteractionGraph(dwig1a);

        udwig2a = new UDWInteractionGraph("resources/Task3Transactions2.txt");
        udwig2b = new UDWInteractionGraph("resources/Task3Transactions2.txt", new int[]{12, 14});
        udwig2c = new UDWInteractionGraph(udwig2a, new int[]{15, 16});  //Empty graph
        udwig2d = new UDWInteractionGraph(udwig2a, List.of(5));
        udwig2e = new UDWInteractionGraph(udwig2a, Arrays.asList(0, 2, 3));
        udwig2f = new UDWInteractionGraph(dwig2a);

    }
  
  
    /** Task 1 DW tests */
    @Test
    public void DWTestConstruction1() {
        Assertions.assertEquals(new HashSet<>(Arrays.asList(1, 2, 3, 4, 6)), dwig1a.getUserIDs());
        Assertions.assertEquals(1, dwig1a.getEmailCount(1, 2));
        Assertions.assertEquals(0, dwig1a.getEmailCount(2, 1));
        Assertions.assertEquals(0, dwig1a.getEmailCount(0, 6));
    }

    @Test
    public void DWTestConstruction2() {
        Assertions.assertEquals(new HashSet<>(Arrays.asList(1, 2)), dwig1b.getUserIDs());
        Assertions.assertEquals(1, dwig1b.getEmailCount(1, 2));
        Assertions.assertEquals(0, dwig1b.getEmailCount(2, 1));
        Assertions.assertEquals(0, dwig1b.getEmailCount(1, 3));
    }

    @Test
    public void DWTestConstruction3() {
        Assertions.assertEquals(new HashSet<>(Arrays.asList(1, 3, 4, 6)), dwig1c.getUserIDs());
        Assertions.assertEquals(1, dwig1c.getEmailCount(1, 4));
        Assertions.assertEquals(0, dwig1c.getEmailCount(4, 1));
        Assertions.assertEquals(0, dwig1c.getEmailCount(1, 2));
    }

    @Test
    public void DWTestConstruction4() {
        Assertions.assertEquals(new HashSet<>(), dwig1d.getUserIDs());
        Assertions.assertEquals(0, dwig1d.getEmailCount(1, 4));
        Assertions.assertEquals(0, dwig1d.getEmailCount(4, 1));
        Assertions.assertEquals(0, dwig1d.getEmailCount(1, 2));
    }

    @Test
    public void DWTestConstruction5() {
        Assertions.assertEquals(new HashSet<>(Arrays.asList(1, 2, 3, 4, 6)), dwig1e.getUserIDs());
        Assertions.assertEquals(0, dwig1e.getEmailCount(1, 4));
        Assertions.assertEquals(1, dwig1e.getEmailCount(3, 4));
        Assertions.assertEquals(0, dwig1e.getEmailCount(1, 2));
    }


    /** Task 1 UDW tests */
    @Test
    public void UDWTestConstruction1() {
        Assertions.assertEquals(new HashSet<>(Arrays.asList(1, 2, 3, 4, 6)), udwig1a.getUserIDs());
        Assertions.assertEquals(1, udwig1a.getEmailCount(3, 6));
        Assertions.assertEquals(1, udwig1a.getEmailCount(4, 6));
        Assertions.assertEquals(0, udwig1a.getEmailCount(1, 6));
    }

    @Test
    public void UDWTestConstruction2() {
        Assertions.assertEquals(new HashSet<>(Arrays.asList(1, 2)), udwig1b.getUserIDs());
        Assertions.assertEquals(1, udwig1b.getEmailCount(1, 2));
        Assertions.assertEquals(0, udwig1b.getEmailCount(3, 4));
        Assertions.assertEquals(0, udwig1b.getEmailCount(1, 4));
    }

    @Test
    public void UDWTestConstruction3() {
        Assertions.assertEquals(new HashSet<>(Arrays.asList(1, 3, 4, 6)), udwig1c.getUserIDs());
        Assertions.assertEquals(1, udwig1c.getEmailCount(1, 4));
        Assertions.assertEquals(1, udwig1c.getEmailCount(4, 6));
        Assertions.assertEquals(0, udwig1c.getEmailCount(1, 2));
    }

    @Test
    public void UDWTestConstruction4() {
        Assertions.assertEquals(new HashSet<>(), udwig1d.getUserIDs());
        Assertions.assertEquals(0, udwig1d.getEmailCount(1, 2));
        Assertions.assertEquals(0, udwig1d.getEmailCount(0, 6));
        Assertions.assertEquals(0, udwig1d.getEmailCount(1, 6));
    }

    @Test
    public void UDWTestConstruction5() {
        Assertions.assertEquals(new HashSet<>(Arrays.asList(1, 2, 3, 4, 6)), udwig1e.getUserIDs());
        Assertions.assertEquals(1, udwig1e.getEmailCount(3, 4));
        Assertions.assertEquals(1, udwig1e.getEmailCount(6, 4));
        Assertions.assertEquals(0, udwig1e.getEmailCount(1, 2));
    }

    @Test
    public void UDWTestConstruction6() {
        Assertions.assertEquals(new HashSet<>(Arrays.asList(1, 2, 3, 4, 6)), udwig1f.getUserIDs());
        Assertions.assertEquals(1, udwig1f.getEmailCount(1, 2));
        Assertions.assertEquals(0, udwig1f.getEmailCount(0, 6));
        Assertions.assertEquals(0, udwig1f.getEmailCount(1, 6));
    }
  
  
    /** Task 2 DW tests */
    @Test
    public void DWReportActivityInTimeWindow1() {
        int[] expected1 = new int[]{4, 4, 7};
        int[] expected2 = new int[]{1, 1, 1};
        Assertions.assertArrayEquals(expected1, dwig1a.ReportActivityInTimeWindow(new int[]{0, 2}));
        Assertions.assertArrayEquals(expected2, dwig1a.ReportActivityInTimeWindow(new int[]{0, 0}));
    }

    @Test
    public void DWReportActivityInTimeWindow2() {
        int[] expected1 = new int[]{1, 1, 1};
        int[] expected2 = new int[]{0, 0, 0};
        Assertions.assertArrayEquals(expected1, dwig1b.ReportActivityInTimeWindow(new int[]{0, 2}));
        Assertions.assertArrayEquals(expected2, dwig1b.ReportActivityInTimeWindow(new int[]{2, 3}));
    }

    @Test
    public void DWReportActivityInTimeWindow3() {
        int[] expected1 = new int[]{3, 2, 3};
        int[] expected2 = new int[]{0, 0, 0};
        Assertions.assertArrayEquals(expected1, dwig1c.ReportActivityInTimeWindow(new int[]{0, 3}));
        Assertions.assertArrayEquals(expected2, dwig1c.ReportActivityInTimeWindow(new int[]{0, 0}));
    }

    @Test
    public void DWReportActivityInTimeWindow4() {
        int[] expected1 = new int[]{0, 0, 0};
        int[] expected2 = new int[]{0, 0, 0};
        Assertions.assertArrayEquals(expected1, dwig1d.ReportActivityInTimeWindow(new int[]{0, 2}));
        Assertions.assertArrayEquals(expected2, dwig1d.ReportActivityInTimeWindow(new int[]{0, 0}));
    }

    @Test
    public void DWReportActivityInTimeWindow5() {
        int[] expected1 = new int[]{4, 3, 5};
        int[] expected2 = new int[]{3, 2, 3};
        Assertions.assertArrayEquals(expected1, dwig1e.ReportActivityInTimeWindow(new int[]{1, 2}));
        Assertions.assertArrayEquals(expected2, dwig1e.ReportActivityInTimeWindow(new int[]{0, 1}));
    }

    @Test
    public void DWReportOnUser1() {
        Assertions.assertArrayEquals(new int[]{3, 0, 3}, dwig1a.ReportOnUser(1));
        Assertions.assertArrayEquals(new int[]{0, 2, 2}, dwig1a.ReportOnUser(6));
    }

    @Test
    public void DWReportOnUser2() {
        Assertions.assertArrayEquals(new int[]{0, 1, 1}, dwig1b.ReportOnUser(2));
        Assertions.assertArrayEquals(new int[]{1, 0, 1}, dwig1b.ReportOnUser(1));
    }

    @Test
    public void DWReportOnUser3() {
        Assertions.assertArrayEquals(new int[]{0, 0, 0}, dwig1c.ReportOnUser(0));
        Assertions.assertArrayEquals(new int[]{1, 1, 2}, dwig1c.ReportOnUser(4));
    }

    @Test
    public void DWReportOnUser4() {
        Assertions.assertArrayEquals(new int[]{0, 0, 0}, dwig1d.ReportOnUser(0));
        Assertions.assertArrayEquals(new int[]{0, 0, 0}, dwig1d.ReportOnUser(1));
    }

    @Test
    public void DWReportOnUser5() {
        Assertions.assertArrayEquals(new int[]{1, 0, 1}, dwig1e.ReportOnUser(1));
        Assertions.assertArrayEquals(new int[]{1, 1, 2}, dwig1e.ReportOnUser(4));
    }

    @Test
    public void DWNthMostActiveUser1() {
        Assertions.assertEquals(2, dwig1a.NthMostActiveUser(3, SendOrReceive.SEND));
        Assertions.assertEquals(3, dwig1a.NthMostActiveUser(1, SendOrReceive.RECEIVE));
    }

    @Test
    public void DWNthMostActiveUser2() {
        Assertions.assertEquals(1, dwig1b.NthMostActiveUser(1, SendOrReceive.SEND));
        Assertions.assertEquals(-1, dwig1b.NthMostActiveUser(2, SendOrReceive.SEND));
        Assertions.assertEquals(2, dwig1b.NthMostActiveUser(1, SendOrReceive.RECEIVE));
    }

    @Test
    public void DWNthMostActiveUser3() {
        Assertions.assertEquals(3, dwig1c.NthMostActiveUser(2, SendOrReceive.SEND));
        Assertions.assertEquals(1, dwig1c.NthMostActiveUser(1, SendOrReceive.SEND));
        Assertions.assertEquals(4, dwig1c.NthMostActiveUser(2, SendOrReceive.RECEIVE));
    }

    @Test
    public void DWNthMostActiveUser4() {
        Assertions.assertEquals(-1, dwig1d.NthMostActiveUser(1, SendOrReceive.SEND));
        Assertions.assertEquals(-1, dwig1d.NthMostActiveUser(3, SendOrReceive.RECEIVE));
    }

    @Test
    public void DWNthMostActiveUser5() {
        Assertions.assertEquals( 1, dwig1e.NthMostActiveUser(2, SendOrReceive.SEND));
        Assertions.assertEquals( 2, dwig1e.NthMostActiveUser(3, SendOrReceive.SEND));
        Assertions.assertEquals( 4, dwig1e.NthMostActiveUser(3, SendOrReceive.RECEIVE));
    }

  
    /** Task 2 UDW tests */
    @Test
    public void UDWReportActivityInTimeWindow1() {
        int[] expected1 = new int[]{5, 7};
        int[] expected2 = new int[]{2, 1};
        Assertions.assertArrayEquals(expected1, udwig1a.ReportActivityInTimeWindow(new int[]{0, 2}));
        Assertions.assertArrayEquals(expected2, udwig1a.ReportActivityInTimeWindow(new int[]{0, 0}));
    }

    @Test
    public void UDWReportActivityInTimeWindow2() {
        int[] expected1 = new int[]{2, 1};
        int[] expected2 = new int[]{0, 0};
        Assertions.assertArrayEquals(expected1, udwig1b.ReportActivityInTimeWindow(new int[]{0, 2}));
        Assertions.assertArrayEquals(expected2, udwig1b.ReportActivityInTimeWindow(new int[]{2, 3}));
    }

    @Test
    public void UDWReportActivityInTimeWindow3() {
        int[] expected1 = new int[]{4, 3};
        int[] expected2 = new int[]{0, 0};
        Assertions.assertArrayEquals(expected1, udwig1c.ReportActivityInTimeWindow(new int[]{0, 3}));
        Assertions.assertArrayEquals(expected2, udwig1c.ReportActivityInTimeWindow(new int[]{0, 1}));
    }

    @Test
    public void UDWReportActivityInTimeWindow4() {
        int[] expected1 = new int[]{0, 0};
        int[] expected2 = new int[]{0, 0};
        Assertions.assertArrayEquals(expected1, udwig1d.ReportActivityInTimeWindow(new int[]{0, 2}));
        Assertions.assertArrayEquals(expected2, udwig1d.ReportActivityInTimeWindow(new int[]{0, 0}));
    }

    @Test
    public void UDWReportActivityInTimeWindow5() {
        int[] expected1 = new int[]{5, 5};
        int[] expected2 = new int[]{4, 3};
        Assertions.assertArrayEquals(expected1, udwig1e.ReportActivityInTimeWindow(new int[]{1, 2}));
        Assertions.assertArrayEquals(expected2, udwig1e.ReportActivityInTimeWindow(new int[]{0, 1}));
    }

    @Test
    public void UDWReportActivityInTimeWindow6() {
        int[] expected1 = new int[]{5, 7};
        int[] expected2 = new int[]{2, 1};
        Assertions.assertArrayEquals(expected1, udwig1f.ReportActivityInTimeWindow(new int[]{0, 2}));
        Assertions.assertArrayEquals(expected2, udwig1f.ReportActivityInTimeWindow(new int[]{0, 0}));
    }

    @Test
    public void UDWReportOnUser1() {
        Assertions.assertArrayEquals(new int[]{3, 3}, udwig1a.ReportOnUser(1));
        Assertions.assertArrayEquals(new int[]{2, 2}, udwig1a.ReportOnUser(6));
    }

    @Test
    public void UDWReportOnUser2() {
        Assertions.assertArrayEquals(new int[]{1, 1}, udwig1b.ReportOnUser(2));
        Assertions.assertArrayEquals(new int[]{1, 1}, udwig1b.ReportOnUser(1));
    }

    @Test
    public void UDWReportOnUser3() {
        Assertions.assertArrayEquals(new int[]{0, 0}, udwig1c.ReportOnUser(0));
        Assertions.assertArrayEquals(new int[]{2, 2}, udwig1c.ReportOnUser(4));
    }

    @Test
    public void UDWReportOnUser4() {
        Assertions.assertArrayEquals(new int[]{0, 0}, udwig1d.ReportOnUser(0));
        Assertions.assertArrayEquals(new int[]{0, 0}, udwig1d.ReportOnUser(1));
    }

    @Test
    public void UDWReportOnUser5() {
        Assertions.assertArrayEquals(new int[]{1, 1}, udwig1e.ReportOnUser(1));
        Assertions.assertArrayEquals(new int[]{2, 2}, udwig1e.ReportOnUser(4));
    }

    @Test
    public void UDWReportOnUser6() {
        Assertions.assertArrayEquals(new int[]{3, 3}, udwig1f.ReportOnUser(1));
        Assertions.assertArrayEquals(new int[]{2, 2}, udwig1f.ReportOnUser(6));
    }

    @Test
    public void UDWNthMostActiveUser1() {
        Assertions.assertEquals(3, udwig1a.NthMostActiveUser(1));
        Assertions.assertEquals(1, udwig1a.NthMostActiveUser(2));
    }

    @Test
    public void UDWNthMostActiveUser2() {
        Assertions.assertEquals(1, udwig1b.NthMostActiveUser(1));
        Assertions.assertEquals(-1, udwig1b.NthMostActiveUser(3));
    }

    @Test
    public void UDWNthMostActiveUser3() {
        Assertions.assertEquals(4, udwig1c.NthMostActiveUser(1));
        Assertions.assertEquals(1, udwig1c.NthMostActiveUser(3));
    }

    @Test
    public void UDWNthMostActiveUser4() {
        Assertions.assertEquals(-1, udwig1d.NthMostActiveUser(2));
        Assertions.assertEquals(-1, udwig1d.NthMostActiveUser(3));
    }

    @Test
    public void UDWNthMostActiveUser5() {
        Assertions.assertEquals(3, udwig1e.NthMostActiveUser(1));
        Assertions.assertEquals(2, udwig1e.NthMostActiveUser(5));
    }

    @Test
    public void UDWNthMostActiveUser6() {
        Assertions.assertEquals(3, udwig1f.NthMostActiveUser(1));
        Assertions.assertEquals(1, udwig1f.NthMostActiveUser(2));
    }
  
    
    /** Task 3 DW Tests */
    @Test
    public void testBFSInvalidUserID() {
        Assertions.assertEquals(null, dwig1a.BFS(1, 8));
    }

    @Test
    public void testDFSInvalidUserID() {
        Assertions.assertEquals(null, dwig1a.DFS(1, 8));
    }

    @Test
    public void testBFSNoPath() {
        Assertions.assertEquals(null, dwig2a.BFS(2,8));
    }

    @Test
    public void testDFSNoPath() {
        Assertions.assertEquals(null, dwig2a.DFS(2,8));
    }

    @Test
    public void testBFSSameUser() {
        List<Integer> expected = Arrays.asList(2);
        Assertions.assertEquals(expected, dwig2a.BFS(2, 2));
    }

    @Test
    public void testDFSSameUser() {
        List<Integer> expected = Arrays.asList(3);
        Assertions.assertEquals(expected, dwig2a.DFS(3, 3));
    }

    @Test
    public void testBFSGraph1() {
        List<Integer> expected = Arrays.asList(1, 3, 5, 6, 4, 8, 7, 2, 9, 10);
        Assertions.assertEquals(expected, dwig2a.BFS(1, 10));
    }

    @Test
    public void testDFSGraph1() {
        List<Integer> expected = Arrays.asList(1, 3, 4, 8, 5, 7, 2, 9, 10);
        Assertions.assertEquals(expected, dwig2a.DFS(1, 10));
    }

    @Test
    public void testBFSGraph2() {
        List<Integer> expected = Arrays.asList(3, 1, 4, 8, 5, 6);
        Assertions.assertEquals(expected, dwig2a.BFS(3, 6));
    }

    @Test
    public void testDFSGraph2() {
        List<Integer> expected = Arrays.asList(3, 1, 5, 4, 7, 2, 9, 10, 6);
        Assertions.assertEquals(expected, dwig2a.DFS(3, 6));
    }

    @Test
    public void testDFSGraph3() {
        List<Integer> expected = Arrays.asList(3, 1, 5, 4, 7, 2);
        Assertions.assertEquals(expected, dwig2a.DFS(3, 2));
    }

    
    /** Task 3 UDW tests */
    @Test
    public void UDWtestNumComponent1() {
        Assertions.assertEquals(1, udwig2a.NumberOfComponents());
    }

    @Test
    public void UDWtestNumComponent2() {
        Assertions.assertEquals(1, udwig2b.NumberOfComponents());
    }

    @Test
    public void UDWtestNumComponent3() {
        Assertions.assertEquals(0, udwig2c.NumberOfComponents());
    }

    @Test
    public void UDWtestNumComponent4() {
        Assertions.assertEquals(1, udwig2d.NumberOfComponents());
    }

    @Test
    public void UDWtestNumComponent5() {
        Assertions.assertEquals(2, udwig2e.NumberOfComponents());
    }

    @Test
    public void UDWtestNumComponent6() {
        Assertions.assertEquals(1, udwig2f.NumberOfComponents());
    }

    @Test
    public void UDWtestPathExists1() {
        Assertions.assertTrue(udwig2a.PathExists(1, 9));
        Assertions.assertTrue(udwig2a.PathExists(3, 7));
        Assertions.assertFalse(udwig2a.PathExists(1, 13));
    }

    @Test
    public void UDWtestPathExists2() {
        Assertions.assertTrue(udwig2b.PathExists(7, 9));
        Assertions.assertTrue(udwig2b.PathExists(6, 9));
        Assertions.assertFalse(udwig2b.PathExists(7, 1));
    }

    @Test
    public void UDWtestPathExists3() {
        Assertions.assertFalse(udwig2c.PathExists(1, 9));
        Assertions.assertFalse(udwig2c.PathExists(3, 7));
        Assertions.assertFalse(udwig2c.PathExists(1, 13));
    }

    @Test
    public void UDWtestPathExists4() {
        Assertions.assertTrue(udwig2d.PathExists(6, 5));
        Assertions.assertTrue(udwig2d.PathExists(5, 7));
        Assertions.assertFalse(udwig2d.PathExists(1, 9));
    }

    @Test
    public void UDWtestPathExists5() {
        Assertions.assertTrue(udwig2e.PathExists(2, 7));
        Assertions.assertTrue(udwig2e.PathExists(3, 1));
        Assertions.assertFalse(udwig2e.PathExists(3, 2));
        Assertions.assertFalse(udwig2e.PathExists(4, 2));
    }

    @Test
    public void UDWtestPathExists6() {
        Assertions.assertTrue(udwig2f.PathExists(1, 9));
        Assertions.assertTrue(udwig2f.PathExists(3, 7));
        Assertions.assertFalse(udwig2f.PathExists(1, 13));
    }

    @Test
    public void UDWtestSingleUser() {
        UDWInteractionGraph t = new UDWInteractionGraph("resources/Task3-components-test2.txt");
        Assertions.assertEquals(2, t.getEmailCount(1, 1));
        Assertions.assertEquals(1, t.NumberOfComponents());
        Assertions.assertTrue(t.PathExists(1, 1));
    }

    @Test
    public void UDWtestNoUser() {
        UDWInteractionGraph t = new UDWInteractionGraph("resources/Task3-components-test2.txt");
        UDWInteractionGraph t1 = new UDWInteractionGraph(t, List.of(2));
        Assertions.assertEquals(0, t1.NumberOfComponents());
    }

  
    /** Task 4 DW tests */
    @Test
    public void testMaxedBreachedUserCount1() {
        // Attacking user 7 any time in the window [0,120] will pollute 8 users in a 2 hour window.
        Assertions.assertEquals(8, dwig3a.MaxBreachedUserCount(2));
    }

    @Test
    public void testMaxedBreachedUserCount2() {
        // Attacking user 3 at t=0, or attacking user 5 any time in the window [0,60] will pollute
        // 10 users in a 4 hour window.
        Assertions.assertEquals(10, dwig3b.MaxBreachedUserCount(4));
    }

    @Test
    public void testMaxedBreachedUserCount3() {
        // Attacking user 4 at t=3600 will lead to users 4, 5, 6, 3, and 1 (5 users) to be polluted
        // in a 6-hour-long window after the attack starts.
        Assertions.assertEquals(5, dwig3c.MaxBreachedUserCount(6));
    }
}
