package seng201.team0.models;

import seng201.team0.PlayerManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shop {
    private PlayerManager playerManager;
    private Tower towerManager;
    private List<Tower> newPurchasableTowers;
    private Upgrade upgrade;
    private Player player;

    public Shop(PlayerManager playerManager, Tower towerManager, Upgrade upgrade){
        this.playerManager = playerManager;
        this.towerManager = towerManager;
        this.upgrade = upgrade;
        this.player = playerManager.getPlayer();
    }
    public List<Tower> generateNewPurchasableTowers() {
        System.out.println("------ Shuffling and Generating New Purchasable Towers-------");
        List<Tower> allTowers = towerManager.getDefaultTowers();
        Collections.shuffle(allTowers);
        newPurchasableTowers = new ArrayList<Tower>(allTowers.subList(0,3));
        System.out.println("New Purchasable Towers: " + newPurchasableTowers);
        return newPurchasableTowers;
    }

    public boolean tryBuyTower(int selectedTowerIndex) {
        Tower selectedTower = towerManager.getDefaultTowers().get(selectedTowerIndex);
        double cost = selectedTower.getTowerCost();
        if (cost <= playerManager.getMoney()){
            // if inventory size <10
            if (playerManager.getTowerInventory().size() < 10){ // can buy, add to inventory with certain status
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
