//package seng201.team0.unittests.services;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import seng201.team0.models.RandomEvent;
//import seng201.team0.models.Tower;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class RandomEventManagerTest {
//    private RandomEvent randomEventManager;
//    private Tower tower1;
//    private Tower tower2;
//    @BeforeEach
//    public void init(){
//        Tower tower1 = new Tower("Tower1", 5, "resource", 10, 1, 100.00, "Reserve")
//        Tower tower2 = new Tower("Tower2", 5, "resource", 10, 0, 100.00, "In-Game")
//
//    }
//    @Test
//    /* Testing Blue Sky Level Increase
//     * Should increase level from 1 to 2*/
//    public void executeLevelIncreaseTest(){
//        tower1.randomEventManager.executeLevelIncrease;
//        assertEquals(2, tower1.executeLevelIncrease());
//    }
//    @Test
//    /* Testing Error Scenario for Level decrease
//     * Should decrease level from 0 to 0
//     * As you can't decrease level below 0*/
//    public void executeLeveldecreaseTest(){
//        tower2.rexecuteLeveldecrease;
//        assertEquals(0, tower2.executeLevelIncrease());
//    }
//    @Test
//    /* Testing Blue Sky Scenario for Level decrease
//     * Should decrease level from 1 to 0*/
//    public void executeLeveldecreaseTest(){
//        tower1.executeLeveldecrease;
//        assertEquals(0, tower1.executeLevelIncrease());
//    }
//
//}
