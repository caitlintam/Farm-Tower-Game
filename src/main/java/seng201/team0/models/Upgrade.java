package seng201.team0.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Upgrade Class represents an upgrade that can be applied to a tower in game
 */
public class Upgrade {
    private String upgradeName;
    private double upgradeCost;
    private List<Upgrade> upgradeList;

    /**
     * Constructs Upgrade instance each with a name and cost
     * @param upgradeName the name of the upgrade
     * @param upgradeCost the cost of the upgrade
     */
    public Upgrade(String upgradeName, double upgradeCost){
        this.upgradeName = upgradeName;
        this.upgradeCost = upgradeCost;
    }

    /**
     * Constructs an upgrade Object with predefined upgrades:
     * The predefined upgrades are: Tower Level Boost, Tower Resource Amount Boost and Tower Reload Speed Boost
     * Each upgrade has their associated cost
     */
    public Upgrade(){
        this.upgradeList = new ArrayList<>();
        upgradeList.addAll(List.of(new Upgrade("Tower Level Boost!", 600.00),
                new Upgrade("Tower Resource Amount Boost!", 200.00),
                new Upgrade("Tower Reload Speed Boost!", 200.00)));
    }

    /**
     * returns a list of the predefined upgrades
     * @return the list of type Upgrade predefined upgrades
     */
    public List<Upgrade> getUpgradeList(){
        return upgradeList;
    }

    /**
     * Retrieves the name of the upgrade
     * @return String upgrade name
     */
    public String getUpgradeName(){
        return upgradeName;
    }

    /**
     * Retrieves the cost of the upgrade
     * @return double of the upgrade cost
     */
    public double getUpgradeCost(){
        return upgradeCost;
    }

}
