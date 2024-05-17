package seng201.team0;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import seng201.team0.models.Player;
import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;

public class PlayerManager implements toStringOverride {
    private String name;
    private int currentNumRounds = 0;
    private int numGameRounds = 0;
    private  int gameDifficulty;
    private final Consumer<PlayerManager> setupScreenLauncher;
    private final Consumer<PlayerManager> towerSetUpScreenLauncher;
    private final Consumer<PlayerManager> homeScreenLauncher;
    private final Consumer<PlayerManager> shopScreenLauncher;
    private final Consumer<PlayerManager> inventoryScreenLauncher;
    private final Consumer<PlayerManager> applyUpgradeScreenLauncher;
    private final Runnable clearScreen;
    private double money = 1000.00;
    private List<Tower> towerInventory;
    private List<Upgrade> upgradeInventory;
    private List<Tower> towersInGame;

    //private DoubleProperty numRounds;

    public PlayerManager(Consumer<PlayerManager> setupScreenLauncher, Consumer<PlayerManager> towerSetUpScreenLauncher, Runnable clearScreen, Consumer<PlayerManager> homeScreenLauncher, Consumer<PlayerManager> shopScreenLauncher, Consumer<PlayerManager> inventoryScreenLauncher, Consumer<PlayerManager> applyUpgradeScreenLauncher) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.towerSetUpScreenLauncher = towerSetUpScreenLauncher;
        this.homeScreenLauncher = homeScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.clearScreen = clearScreen;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.applyUpgradeScreenLauncher = applyUpgradeScreenLauncher;
        this.towerInventory = new ArrayList<Tower>();
        this.upgradeInventory = new ArrayList<Upgrade>();
        this.towersInGame = new ArrayList<Tower>();
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
    public List<Tower> getTowerInventory(){
        return towerInventory;
    }
    public List<Upgrade> getUpgradeInventory(){
        return upgradeInventory;
    }

    public int getNumGameRounds(){ return numGameRounds;}
    public void setNumGameRounds(int gameRounds){ this.numGameRounds = gameRounds;}
    public int getCurrentNumRounds(){ return currentNumRounds;}
    public void setCurrentNumRounds(int currentNumRounds){ this.currentNumRounds = currentNumRounds;}
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
    public void launchApplyUpgradeScreen(){applyUpgradeScreenLauncher.accept(this);}
    public void closeShopScreen(){
        clearScreen.run();
    }
    public void closeApplyUpgradeScreen(){clearScreen.run();}

    public void addTowersToInventory(Tower tower) {
        Tower newTower = new Tower(tower.getTowerName(), tower.getTowerResourceAmount(), tower.getTowerResourceType(),
                tower.getTowerReloadSpeed(), tower.getTowerLevel(), tower.getTowerCost(),
                tower.getTowerStatus());
        // Add the new tower object to the inventory
        towerInventory.add(newTower);

        //towerInventory.add(tower);
    }
    public void addUpgradesToInventory(Upgrade upgrade){
        Upgrade newUpgrade = new Upgrade(upgrade.getUpgradeName(), upgrade.getUpgradeCost());
        upgradeInventory.add(newUpgrade);
    }
    public void closeInventoryScreen(){
        clearScreen.run();
    }

    public void removeTowerFromInventory(Tower selectedTower) {
        towerInventory.remove(selectedTower);
    }

    public void removeUpgradeFromInventory(Upgrade selectedUpgrade) {
        upgradeInventory.remove(selectedUpgrade);
    }

    public void setTowersInGame() {
        List<Tower> towersInGame = towerInventory.stream()
                .filter(tower -> tower.getTowerStatus().equals("In-Game"))
                .toList();
        this.towersInGame = towersInGame;
    }
}
