package cpen221.mp2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
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

    /** Task 2 UDW tests */

    /** Task 3 DW tests */
    @Test
    public void testBFSInvalidUserID() {
        Assertions.assertEquals(null, dwig1a.BFS(1, 8));
    }

    @Test
    public void testDFSInvalidUserID() {
        Assertions.assertEquals(null, dwig1a.DFS(1, 8));
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
    public void testBFSGraph2() {
        List<Integer> expected = Arrays.asList(1, 3, 5, 6, 4, 8, 7, 2, 9, 10);
        Assertions.assertEquals(expected, dwig2a.BFS(1, 10));
    }

    @Test
    public void testDFSGraph2() {
        List<Integer> expected = Arrays.asList(1, 3, 4, 8, 5, 7, 2, 9, 10);
        Assertions.assertEquals(expected, dwig2a.DFS(1, 10));
    }
}


/** Task 3 UDW tests */

/** Task 4 DW tests */

