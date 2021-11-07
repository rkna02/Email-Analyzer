package cpen221.mp2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class AdditionalTestsAllTasks {
    
    //DW Test Graphs
    private static DWInteractionGraph dwig;
    private static DWInteractionGraph dwig1;
    private static DWInteractionGraph dwig2;
    private static DWInteractionGraph dwig3;
    private static DWInteractionGraph dwig4;
    private static DWInteractionGraph dwig5;
    
    //UDW Test Graphs
    private static UDWInteractionGraph udwig;
    private static UDWInteractionGraph udwig1;
    private static UDWInteractionGraph udwig2;
    private static UDWInteractionGraph udwig3;
    private static UDWInteractionGraph udwig4;
    private static UDWInteractionGraph udwig5;

    //Set up test graphs
    @BeforeAll
    public static void setupTests() {
        
        //Setup DW test graphs
        dwig = new DWInteractionGraph("resources/Task1-2Transactions.txt");
        dwig1 = new DWInteractionGraph(dwig, new int[]{3, 9});
        dwig2 = new DWInteractionGraph(dwig, Arrays.asList(2, 3, 4));
        
        //Setup UDW test graphs
        udwig = new UDWInteractionGraph("resources/Task1-2UDWTransactions.txt");
        udwig1 = new UDWInteractionGraph(udwig, new int[]{0, 9});
        udwig2 = new UDWInteractionGraph(udwig, new int[]{10, 11});
    }
    
    //Task 1 DW tests
    
    //Task 1 UDW tests
    
    //Task 2 DW tests
    
    //Task 2 UDW tests
    
    //Task 3 DW tests
    
    //Task 3 UDW tests
    
    //Task 4 DW tests
}
