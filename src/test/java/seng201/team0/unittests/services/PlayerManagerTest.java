//package seng201.team0.unittests.services;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import seng201.team0.PlayerManager;
//import seng201.team0.TowerManager;
//import seng201.team0.models.Tower;
//import seng201.team0.models.Upgrade;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class PlayerManagerTest {
//    private PlayerManager playerManager;
//    @BeforeEach
//    public void setup(){
//        playerManager = new PlayerManager(null, null, null, null, null, null, null, null, null, null, null, null);
//    }
//    }
//    @Test
//    /* tests the setName method using getName to ensure setName is functioning correctly*/
//    public void testSetName() {
//        playerManager.setName("TestPlayer");
//        assertEquals("TestPlayer", playerManager.getName());
//    }
//
//    @Test
//    /* tests the setMoney method and the getMoney method*/
//    public void testSetAndGetMoney() {
//        playerManager.setMoney(2000.00);
//        assertEquals(2000.00, playerManager.getMoney());
//    }
//
//    @Test
//    /*
//    instantiates a new tower
//    adds the new tower to the inventory and using getTowerInventory checks if the tower was successfully added
//     */
//    public void testAddTowersToInventory() {
//        Tower testTower = new Tower("TestTower", 100, "Resource", 1, 1, 100, "In-Game");
//        playerManager.addTowersToInventory(testTower);
//        assertTrue(playerManager.getTowerInventory().contains(testTower));
//    }
//
//    @Test
//    /*
//    instantiates a new upgrade
//    adds the new upgrade to the inventory and using getUpgradeInventory checks if the upgrade was successfully added
//     */
//    public void testAddUpgradesToInventory() {
//        Upgrade testUpgrade = new Upgrade("TestUpgrade", 100);
//        playerManager.addUpgradesToInventory(testUpgrade);
//        assertTrue(playerManager.getUpgradeInventory().contains(testUpgrade));
//    }
//
//    @Test
//    /*
//    instantiates a new tower
//    adds the new tower to the inventory
//    removes the added tower from the inventory
//    using getTowerInventory to check if the tower was successfully removed
//     */
//    public void testRemoveTowerFromInventory() {
//        Tower testTower = new Tower("TestTower", 100, "Resource", 1, 1, 100, "In-Game");
//        playerManager.addTowersToInventory(testTower);
//        playerManager.removeTowerFromInventory(testTower);
//        assertFalse(playerManager.getTowerInventory().contains(testTower));
//    }
//
//    @Test
//    /*
//    instantiates a new upgrade
//    adds the new upgrade to the inventory
//    removes the added upgrade from the inventory
//    using getUpgradeInventory to check if the upgrade was successfully removed
//     */
//    public void testRemoveUpgradeFromInventory() {
//        Upgrade testUpgrade = new Upgrade("TestUpgrade", 100);
//        playerManager.addUpgradesToInventory(testUpgrade);
//        playerManager.removeUpgradeFromInventory(testUpgrade);
//        assertFalse(playerManager.getUpgradeInventory().contains(testUpgrade));
//    }
//
//    @Test
//    /* tests the setGameDifficulty method and the getDifficulty method */
//    public void testSetAndGetGameDifficulty() {
//        playerManager.setGameDifficulty(2);
//        assertEquals(2, playerManager.getGameDifficulty());
//    }
//
//
//}
//
//
