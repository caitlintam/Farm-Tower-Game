package seng201.team0.models;

import seng201.team0.PlayerManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Constructs a shop where players can buy towers and upgrades
 */
public class Shop {
    private final PlayerManager playerManager;
    private final Tower towerManager;
    private final Upgrade upgrade;
    private final Player player;

    /**
     * creates a shop with the specified player manager, tower manager, and upgrade
     * @param playerManager the player manager
     * @param towerManager  the tower manager
     * @param upgrade       the upgrade
     */
    public Shop(PlayerManager playerManager, Tower towerManager, Upgrade upgrade){
        this.playerManager = playerManager;
        this.towerManager = towerManager;
        this.upgrade = upgrade;
        this.player = playerManager.getPlayer();
    }

    /**
     * Generates a list of new purchasable towers by shuffling the default towers by shuffling a sublist of the default towers.
     * @return the list of new purchasable towers
     */
    public List<Tower> generateNewPurchasableTowers() {
        System.out.println("------ Shuffling and Generating New Purchasable Towers-------");
        List<Tower> allTowers = towerManager.getDefaultTowers();
        Collections.shuffle(allTowers);
        List<Tower> newPurchasableTowers = new ArrayList<>(allTowers.subList(0,3));
        System.out.println("New Purchasable Towers: " + newPurchasableTowers);
        return newPurchasableTowers;
    }
    /**
     * tries to buy a tower at the selected index
     * @param selectedTowerIndex the index of the selected tower
     * @return true if the tower was successfully bought, false otherwise
     */
    public boolean tryBuyTower(int selectedTowerIndex) {
        Tower selectedTower = towerManager.getDefaultTowers().get(selectedTowerIndex);
        double cost = selectedTower.getTowerCost();
        if (cost <= playerManager.getMoney()){
            if (playerManager.getTowerInventory().size() < 10){
                if (player.getTowersInGame().size() < 5){
                    selectedTower.setTowerStatus("In-Game");
                }else{
                    selectedTower.setTowerStatus("Reserve");
                }
                System.out.println(selectedTower.getTowerName() + " Bought, Set to Status " + selectedTower.getTowerStatus());
                playerManager.setMoney(playerManager.getMoney() - cost);
                player.addTowersToInventory(selectedTower);
                player.setTowersInGame();
                return true;
            }
        }return false;
    }

    /**
     * tries to buy an upgrade at the selected index
     * @param selectedUpgradeIndex the index of the selected upgrade
     * @return true if the upgrade was bought successfully, false otherwise
     */
    public boolean tryBuyUpgrade(int selectedUpgradeIndex) {
        Upgrade selectedUpgrade = upgrade.getUpgradeList().get(selectedUpgradeIndex);
        double cost = selectedUpgrade.getUpgradeCost();
        if (cost <= playerManager.getMoney()){
            System.out.println(selectedUpgrade.getUpgradeName() + " Bought");
            playerManager.setMoney(playerManager.getMoney() - cost);
            player.addUpgradesToInventory(selectedUpgrade);
            return true;
        } else {
            return false;
        }
    }
}
