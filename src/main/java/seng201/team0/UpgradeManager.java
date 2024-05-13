package seng201.team0;

import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;

import java.util.ArrayList;
import java.util.List;

public class UpgradeManager {
    private String upgradeName;
    private List<Upgrade> upgradeList = new ArrayList<>();
    public UpgradeManager(){
        upgradeList.addAll(List.of(new Upgrade("Increase Tower Level", 200.00),
                new Upgrade("Double Tower Resource Amount", 400.00),
                new Upgrade("Jackpot!", 700.00)));
    }

    public void setUpgradeList(List<Upgrade> upgradeList){
        this.upgradeList = upgradeList;
    }
    public void setUpgradeName(String towerName){
        this.upgradeName = upgradeName;
    }
    public List<Upgrade> getUpgradeList(){
        return upgradeList;
    }
    public void addUpgradesListToPlayer(PlayerManager playerManager){
        playerManager.addUpgradesToInventory(upgradeList); // adds tower list to inventory
    }
}
