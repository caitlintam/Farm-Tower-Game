package seng201.team0.models;



import java.util.List;

public interface Player {
    String getName();
    void setName(String name);
    double getMoney();
    void setMoney(double money);
    void addTowersToInventory(Tower tower);
    void addUpgradesToInventory(Upgrade upgrade);
    void removeTowerFromInventory(Tower selectedTower);
    void removeUpgradeFromInventory(Upgrade selectedUpgrade);
    List<Tower> getTowerInventory();
    List<Upgrade> getUpgradeInventory();
}

