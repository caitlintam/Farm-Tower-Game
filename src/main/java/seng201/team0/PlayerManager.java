package seng201.team0;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import seng201.team0.models.Cart;
import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;

public class PlayerManager {
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
    private final Consumer<PlayerManager> chooseRoundDifficultyScreenLauncher;
    private final Runnable clearScreen;
    private double money = 1000.00;
    private List<Tower> towerInventory;
    private List<Upgrade> upgradeInventory;
    private List<Tower> towersInGame;
    private int timeOnTrack;
    //private DoubleProperty numRounds;
    private int currentTrackDistance;
    private List<Integer> trackDistanceOptionsList;
    private boolean roundSuccess;
    private boolean gameSuccess;
    private List<Cart> newCartsInRound;
    private CartManager cartManager;
    private List<Cart> cartsInRound;

    public PlayerManager(Consumer<PlayerManager> setupScreenLauncher, Consumer<PlayerManager> towerSetUpScreenLauncher, Runnable clearScreen, Consumer<PlayerManager> homeScreenLauncher, Consumer<PlayerManager> shopScreenLauncher, Consumer<PlayerManager> inventoryScreenLauncher, Consumer<PlayerManager> applyUpgradeScreenLauncher, Consumer<PlayerManager> chooseRoundDifficultyScreenLauncher) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.towerSetUpScreenLauncher = towerSetUpScreenLauncher;
        this.homeScreenLauncher = homeScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.clearScreen = clearScreen;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.applyUpgradeScreenLauncher = applyUpgradeScreenLauncher;
        this.chooseRoundDifficultyScreenLauncher = chooseRoundDifficultyScreenLauncher;
        this.towerInventory = new ArrayList<Tower>();
        this.upgradeInventory = new ArrayList<Upgrade>();
        this.towersInGame = new ArrayList<Tower>();
        trackDistanceOptionsList = new ArrayList<Integer>();
            trackDistanceOptionsList.add(20);
            trackDistanceOptionsList.add(30);
            trackDistanceOptionsList.add(40);

        this.newCartsInRound = new ArrayList<Cart>();
        this.cartManager = new CartManager(this, new TowerManager());
        this.cartsInRound = cartManager.getCartsInRound();

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
    public List<Tower> getTowersInGame(){return towersInGame;}
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
    public void launchChooseRoundDifficultyScreen(){chooseRoundDifficultyScreenLauncher.accept(this);
    }
    public void closeChooseRoundDifficultyScreen(){clearScreen.run();}
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
        // Filter towerInventory to get only the towers that are in-game
        towersInGame = towerInventory.stream()
                .filter(tower -> tower.getTowerStatus().equals("In-Game"))
                .collect(Collectors.toList());
    }
    // resets the distance options list, to +5, +10, +15 each round
    // call this method in the page between rounds when next clicked. to refresh track dist.
    public void updateTrackDistanceOptionsList(){
        trackDistanceOptionsList.set(0,currentTrackDistance + 5);
        trackDistanceOptionsList.set(1,currentTrackDistance + 10);
        trackDistanceOptionsList.set(2,currentTrackDistance + 15);
    }
    // sets the current Track Distance after 'difficulty' selected
    public void setCurrentTrackDistance(int selectedDistanceIndex){currentTrackDistance = trackDistanceOptionsList.get(selectedDistanceIndex);}



    //run the whole game
    public void runGame(){
        gameSuccess = false;
        int numRoundsWon = 0;
        int numRoundsLost = 0;
        // for each round
        for (int i = 0; i <= numGameRounds; i++){
            roundSuccess = false;
            // run round, determines if round success or not

            runRound(currentTrackDistance);
            if (!roundSuccess){
                // stop game? add to counter of failed rounds?  make changes to game success
                numRoundsLost+=1;
            }else{
                numRoundsWon +=1;
            }
            // otherwise loops to next round
        }

    }
    // since each round has different track distance
    public void runRound(int trackDistance) {
        List<Integer> successfullyFilledCarts = new ArrayList<Integer>();
        List<Integer> failedFilledCarts = new ArrayList<Integer>();

        // for each cart
        for (Cart cart : cartsInRound) {
            // for each tower
            for (Tower tower : towersInGame) {
                // if the resources types match
                if (cart.getCartResourceType() == tower.getTowerResourceType()) {
                    // calculate the carts time on the track..  turn time to integer
                    int cartTimeOnTrack = (int) (trackDistance/cart.getCartSpeed());
                    int numTowerReloads = (int) (Math.floorDiv(cartTimeOnTrack, tower.getTowerReloadSpeed()));
                    // for each reload of cart
                    int currentCartSize = 0;
                    for (int i =0; i <= numTowerReloads; i++) {
                        currentCartSize += tower.getTowerResourceAmount();
                        }
                    // once done all possible tower reloads, check if filled capacity (>=size) or not ( <size)
                    if (currentCartSize >= cart.getCartSize()) {
                        System.out.println("You successfully filled cart " + cart.getCartID() + " with " + tower.getTowerResourceType());
                        // adds succesfully filled cart to list
                        successfullyFilledCarts.add(cart.getCartID());
                        // increase money
    //                    setMoney(money *= numReloads);
                        // launch round win screen
                        // playerManager.setNumRoundsWon(getNumRoundsWon + 1));
                    } else if (currentCartSize < cart.getCartSize()) {
                        System.out.println("Uh Oh, you didn't fill cart " + cart.getCartID() + " with enough "+ tower.getTowerResourceType());
                        // adds unsucesfily filled cart to list
                        failedFilledCarts.add(cart.getCartID());
                        //launch round lose screen
                    }
                }
            }
        }
        // once all carts have been through round
        // if all carts filled ( failed is empty == true ), otherwise false, have a cart not filled
        roundSuccess = failedFilledCarts.isEmpty();
    }
}
