package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Tests tower functionality
 */
public class TowersTest {
    private Tower towerManager;
     List<Tower> testTowerList = new ArrayList<Tower>();
    /**
     * instantiates 3 new towers of boundary attributes
     */
    @BeforeEach
    void init(){
        towerManager = new Tower();
        Tower tower0 = new Tower("TowerTest0", 0, "Test1", 0, 0, 0,"Reserve");
        Tower tower1 = new Tower("TowerTest2", 10, "Test1", 25, 1, 100,"In-Game");
        Tower tower2 = new Tower("TowerTest2", 100, "Test1", 100, 100, 100,"Reserve");
        testTowerList.addAll(List.of(tower0,tower1,tower2));
        towerManager.setTowerList(testTowerList);
    }
    /** tests if tower list is size 3 */
    @Test
    void towerListTest(){
        assertEquals(3, towerManager.getTowerList().size());
    }
    /** tests if the new tower added to tower list is set as in-game status.
     * Tests status updates from Tower Class
     * */
    @Test
    void towerStatusTest(){
        Tower tower0 = testTowerList.get(0);
        towerManager.setTowerStatus(tower0);
        assertEquals("In-Game", tower0.getTowerStatus());
        tower0.updateTowerStatus(tower0);
        assertEquals("Reserve", tower0.getTowerStatus());
    }
    @Test
    /**
    upgrades reload speed if greater than 1 ( cant decrease reload speed <= 0)
     */
    void towerReloadTest(){
        Tower tower0 = testTowerList.get(0);
        tower0.upgradeReloadSpeed(tower0);
        assertEquals(0,tower0.getTowerReloadSpeed());
    }
    @Test
    /**
     * Tests upgrading tower resource amount and assessing tower level.
     * Upgrades tower resource amount.
     * Checks if resource amount increased to 2.
     * Assesses tower level.
     * Verifies tower level is now 1.
     * @throws AssertionError if test conditions fail.
     */
    void towerResAmountTest(){
        Tower tower0 = testTowerList.get(0);
        tower0.upgradeTowerResourceAmount(tower0);
        assertEquals(2, tower0.getTowerResourceAmount());
        tower0.assessTowerLevel(tower0);
        assertEquals(1, tower0.getTowerLevel());
    }
    @Test
    /** tests increase of tower1 from level 1 changes res amount = (1*6), reload speed = (30-(4))=26, level = 2
        then reverse changes with decrease level method : res amount = (1*6), reload speed = (30-(2))=28, level = 1
     */
    void towerIncreaseLevelTest(){
        Tower tower1 = testTowerList.get(1);
        tower1.increaseTowerLevel(tower1);
        assertEquals(6,tower1.getTowerResourceAmount());
        assertEquals(26, tower1.getTowerReloadSpeed());
        assertEquals(2, tower1.getTowerLevel());
        tower1.decreaseTowerLevel(tower1);
        assertEquals(6,tower1.getTowerResourceAmount());
        assertEquals(28, tower1.getTowerReloadSpeed());
        assertEquals(1, tower1.getTowerLevel());
    }

}
