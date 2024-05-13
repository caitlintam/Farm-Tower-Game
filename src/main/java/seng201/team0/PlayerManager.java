package seng201.team0;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;

public class PlayerManager {
    private String name;
    private int numGameRounds;
    private  int gameDifficulty;
    private final Consumer<PlayerManager> setupScreenLauncher;
    private final Consumer<PlayerManager> towerSetUpScreenLauncher;
    private final Consumer<PlayerManager> homeScreenLauncher;
    private final Consumer<PlayerManager> shopScreenLauncher;
    private final Consumer<PlayerManager> inventoryScreenLauncher;
    private final Runnable clearScreen;
    private double money = 1000.00;
    private List<Tower> towerInventory;
    private List<Upgrade> upgradeInventory;
    //private DoubleProperty numRounds;

    public PlayerManager(Consumer<PlayerManager> setupScreenLauncher, Consumer<PlayerManager> towerSetUpScreenLauncher, Runnable clearScreen, Consumer<PlayerManager> homeScreenLauncher, Consumer<PlayerManager> shopScreenLauncher, Consumer<PlayerManager> inventoryScreenLauncher) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.towerSetUpScreenLauncher = towerSetUpScreenLauncher;
        this.homeScreenLauncher = homeScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.clearScreen = clearScreen;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.towerInventory = new ArrayList<>();
        this.upgradeInventory = new ArrayList<>();
        launchSetupScreen();
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;}
    public double getMoney(){
        return money;
    }
    public void setMoney(double money){
        this.money = money;
    }
    public List getTowerInventory(){
        return towerInventory;
    }
    public List<Upgrade> getUpgradeInventory(){
        return upgradeInventory;
    }

    public int getNumGameRounds(){ return numGameRounds;}
    public void setNumGameRounds(int gameRounds){ this.numGameRounds = gameRounds;}
    public int getGameDifficulty(){ return gameDifficulty;}
    public void setGameDifficulty(int gameDifficulty){ this.gameDifficulty = gameDifficulty;}
    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }
    public void closeSetupScreen(){
        clearScreen.run();
    }
    public void launchTowerSetUpScreen(){
        towerSetUpScreenLauncher.accept(this);
    }
    public void closeTowerSetUpScreen(){
        clearScreen.run();
    }
    public void closeMainScreen() {
        clearScreen.run();
    }
    public void launchHomeScreen(){homeScreenLauncher.accept(this);}
    public void launchShopScreen() {
        shopScreenLauncher.accept(this);
    }
    public void launchInventoryScreen(){
        inventoryScreenLauncher.accept(this);
    }
    public void closeShopScreen(){
        clearScreen.run();
    }

    public void addTowersToInventory(Tower tower) {
        towerInventory.add(tower);
    }
    public void addUpgradesToInventory(Upgrade upgrade){
        upgradeInventory.add(upgrade);
    }

}
