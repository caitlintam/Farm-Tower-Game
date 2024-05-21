package seng201.team0.models;

//import java.util.ArrayList;
//import java.util.List;
//
//public class Player {
//    private String name;
//    private int numRounds;
//    private int gameDifficulty;
//    private double money;
//
//    public Player(String name, int numRounds, int gameDifficulty, double money) {
//        this.name = name;
//        this.numRounds = numRounds;
//        this.gameDifficulty = gameDifficulty;
//        this.money = money;
//        //this.inventory = new ArrayList<>();
//    }
//
//    public Player(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public double getMoney() {
//        return money;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setMoney(double money) {
//        this.money = money;
//    }
//
//}
//
//
//

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

