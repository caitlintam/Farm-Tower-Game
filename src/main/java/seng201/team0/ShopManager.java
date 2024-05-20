package seng201.team0;

import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ShopManager {
    private PlayerManager playerManager;
    private TowerManager towerManager;
    private List<Tower> newPurchasableTowers;
    private UpgradeManager upgradeManager;

    public ShopManager(PlayerManager playerManager, TowerManager towerManager, UpgradeManager upgradeManager){
        this.playerManager = playerManager;
        this.towerManager = towerManager;
        this.upgradeManager = upgradeManager;
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
                if (playerManager.getTowersInGame().size() < 5){
                    selectedTower.setTowerStatus("In-Game");
                }else{
                    selectedTower.setTowerStatus("Reserve");
                }
                System.out.println(selectedTower.getTowerName() + " Bought, Set to Status " + selectedTower.getTowerStatus());
                playerManager.setMoney(playerManager.getMoney() - cost);
                playerManager.addTowersToInventory(selectedTower);
                playerManager.setTowersInGame();
                return true;
            }
        }return false;

    }

    public boolean tryBuyUpgrade(int selectedUpgradeIndex) {
        Upgrade selectedUpgrade = upgradeManager.getUpgradeList().get(selectedUpgradeIndex);
        double cost = selectedUpgrade.getUpgradeCost();
        if (cost <= playerManager.getMoney()){
            System.out.println(selectedUpgrade.getUpgradeName() + " Bought");
            playerManager.setMoney(playerManager.getMoney() - cost);
            playerManager.addUpgradesToInventory(selectedUpgrade);
            return true;
        } else {
            return false;
        }
    }
}
