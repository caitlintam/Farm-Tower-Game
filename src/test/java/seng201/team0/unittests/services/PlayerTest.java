package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Player;
import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PlayerTest {
    private Player player;
    /**
     * Initializes the Player object before each test method.
     * Creates a new Player instance with the name "hi" and an initial balance of 0.
     */
    @BeforeEach
    void  init(){
        this.player = new Player("hi",0);
    }
    /** test setter and getter for player name*/
    @Test
    public void testSetAndGetName(){
        player.setName("player1");
        assertEquals("player1", player.getName());
    }
    /** test setter and getter for player money */
    @Test
    public void testSetAndGetMoney(){
        player.setMoney(10.00);
        assertEquals(10.00, player.getMoney());
    }
    /**
    instantiates a new tower
    adds the new tower to the inventory and using getTowerInventory checks if the tower was successfully added
     */
    @Test
    public void testAddTowersToInventory() {
        Tower testTower = new Tower("TestTower", 100, "Resource", 1, 1, 100, "In-Game");
        player.addTowersToInventory(testTower);
        assertEquals(testTower.getTowerCost(), player.getTowerInventory().get(0).getTowerCost());
    }
    /**
    instantiates a new upgrade
    adds the new upgrade to the inventory and using getUpgradeInventory checks if the upgrade was successfully added
     */
    @Test
    public void testAddUpgradesToInventory() {
        Upgrade testUpgrade = new Upgrade("TestUpgrade", 100);
        player.addUpgradesToInventory(testUpgrade);
        assertEquals(testUpgrade.getUpgradeName(), player.getUpgradeInventory().get(0).getUpgradeName());
    }
    /** instantiates a new tower
     * adds the new tower to the inventory
     * removes the added tower from the inventory
     * using getTowerInventory to check if the tower was successfully removed
     */
    @Test
    public void testRemoveTowerFromInventory() {
        Tower testTower = new Tower("TestTower", 100, "Resource", 1, 1, 100, "In-Game");
        player.addTowersToInventory(testTower);
        player.removeTowerFromInventory(testTower);
        assertFalse(player.getTowerInventory().contains(testTower));
    }
    /** instantiates a new upgrade
     * adds the new upgrade to the inventory
     * removes the added upgrade from the inventory
     * using getUpgradeInventory to check if the upgrade was successfully removed
     */
    @Test
    public void testRemoveUpgradeFromInventory() {
        Upgrade testUpgrade = new Upgrade("TestUpgrade", 100);
        player.addUpgradesToInventory(testUpgrade);
        player.removeUpgradeFromInventory(testUpgrade);
        assertFalse(player.getUpgradeInventory().contains(testUpgrade));
    }
}
