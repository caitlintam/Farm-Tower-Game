package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.PlayerManager;
import seng201.team0.models.Player;
import seng201.team0.models.RandomEvent;
import seng201.team0.models.Tower;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RandomEventManagerTest {
    private PlayerManager playerManager;
    private Player player;
    /**
     * Initializes the test environment before each test method.
     * Creates a PlayerManager instance with no-op consumers and a no-op runnable.
     * Creates a Player instance with a name "test" and an initial balance of 1000.
     */
    @BeforeEach
    public void init(){
        Consumer<PlayerManager> noOpConsumer = gm -> {};
        Runnable noOpRunnable = () -> {};
        playerManager = new PlayerManager(noOpConsumer,noOpConsumer,noOpRunnable,noOpConsumer,noOpConsumer,noOpConsumer,noOpConsumer,noOpConsumer,noOpConsumer,noOpConsumer,noOpConsumer,noOpConsumer);
        player = new Player("test", 1000);
    }
    /** Testing Blue Sky Level Increase
     * Should increase level from 1 to 2 */
    @Test
     void executeLevelIncreaseTest(){
        Tower tower = new Tower("testTower",2,"testResource",1,1,100,"In-Game");
        player.addTowersToInventory(tower);
        RandomEvent randomEvent = new RandomEvent(playerManager,player);
        randomEvent.executeLevelIncrease(tower);
        assertEquals(2, tower.getTowerLevel());
    }
    /** Testing Error Scenario for Level decrease
     * Should decrease level from 0 to 0
     * As you can't decrease level below 0*/
    @Test
     void executeLevelDecreaseTest(){
        Tower tower = new Tower("testTower",2,"testResource",1,0,100,"Reserve");
        player.addTowersToInventory(tower);
        RandomEvent randomEvent = new RandomEvent(playerManager,player);
        randomEvent.executeLevelDecrease(tower);
        assertEquals(0, tower.getTowerLevel());
    }
    /** Testing Blue Sky Scenario for Level decrease
     * Should decrease level from 1 to 0*/
    @Test
     void testExecuteLevelDecreaseTest(){
        Tower tower = new Tower("testTower",2,"testResource",1,2,100,"Reserve");
        player.addTowersToInventory(tower);
        RandomEvent randomEvent = new RandomEvent(playerManager,player);
        randomEvent.executeLevelDecrease(tower);
        assertEquals(1, tower.getTowerLevel());
    }
    /**
     * simulates tower breaking event
    */
    @Test
    void executeBreakTowerTest(){
        Tower tower = new Tower("testTower",2,"testResource",1,2,100,"Reserve");
        Tower tower1 = new Tower("testTower",2,"testResource",1,2,100,"In-Game");
        player.addTowersToInventory(tower);
      //  player.addTowersToInventory(tower1);
        RandomEvent randomEvent = new RandomEvent(playerManager,player);
        randomEvent.executeBreakTower(tower);
        assertFalse(player.getTowerInventory().contains(tower));
    }

}
