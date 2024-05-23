package seng201.team0.models;

import java.util.ArrayList;
import java.util.List;

public class Upgrade {
    private String upgradeName;
    private double upgradeCost;
    private List<Upgrade> upgradeList;
    public Upgrade(String upgradeName, double upgradeCost){
        this.upgradeName = upgradeName;
        this.upgradeCost = upgradeCost;
    }
    public Upgrade(){
        this.upgradeList = new ArrayList<>();
        upgradeList.addAll(List.of(new Upgrade("Tower Level Boost!", 600.00),
                new Upgrade("Tower Resource Amount Boost!", 200.00),
                new Upgrade("Tower Reload Speed Boost!", 200.00)));
    }
    public List<Upgrade> getUpgradeList(){
        return upgradeList;
    }
    public String getUpgradeName(){
        return upgradeName;
    }
    public double getUpgradeCost(){
        return upgradeCost;
    }

}
