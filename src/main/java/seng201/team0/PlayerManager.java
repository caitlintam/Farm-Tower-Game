package seng201.team0;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import seng201.team0.service.RoundService;
import seng201.team0.models.*;

/**
 * Manages the game environment and player.
 * This class controls various aspects of the game, including player rounds, screens, events, shops,
 * towers, upgrades, and carts.
 */
public class PlayerManager {

    private final Consumer<PlayerManager> setupScreenLauncher;
    private final Consumer<PlayerManager> towerSetUpScreenLauncher;
    private final Consumer<PlayerManager> homeScreenLauncher;
    private final Consumer<PlayerManager> shopScreenLauncher;
    private final Consumer<PlayerManager> inventoryScreenLauncher;
    private final Consumer<PlayerManager> applyUpgradeScreenLauncher;
    private final Consumer<PlayerManager> chooseRoundDifficultyScreenLauncher;
    private final Consumer<PlayerManager> mainGameScreenLauncher;
    private final Consumer<PlayerManager> wonRoundScreenLauncher;
    private final Consumer<PlayerManager> gameCompletionScreenLauncher;
    private final Consumer<PlayerManager> randomEventScreenLauncher;
    private final Runnable clearScreen;
    private final Player player;
    private final Shop shop;
    private final Tower towerManager;
    private final Upgrade upgrade;
    private final RandomEvent randomEvent;
    private int currentTrackDistance;
    private int currentRoundNumber = 0;
    private int numGameRounds = 0;
    private  int gameDifficulty;
    private final List<Integer> trackDistanceOptionsList;
    private List<Integer> randomEventRoundsList;
    private boolean roundSuccess = false;
    private int earnedMoney;
    private int numCartsFilled;
    private String mainGameScreenText;
    private String winOrLoseGameText;
    private String randomText;
    /**
     * Constructs a PlayerManager object with specified screen launchers and a screen clearer.
     * Initializes various attributes and launches the setup screen.
     * @param setupScreenLauncher Consumer for launching the setup screen
     * @param towerSetUpScreenLauncher Consumer for launching the tower setup screen
     * @param clearScreen Runnable for clearing the screen
     * @param homeScreenLauncher Consumer for launching the home screen
     * @param shopScreenLauncher Consumer for launching the shop screen
     * @param inventoryScreenLauncher Consumer for launching the inventory screen
     * @param applyUpgradeScreenLauncher Consumer for launching the upgrade screen
     * @param chooseRoundDifficultyScreenLauncher Consumer for launching the round difficulty screen
     * @param mainGameScreenLauncher Consumer for launching the main game screen
     * @param wonRoundScreenLauncher Consumer for launching the round won screen
     * @param gameCompletionScreenLauncher Consumer for launching the game completion screen
     * @param randomEventScreenLauncher Consumer for launching the random event screen
     */
    public PlayerManager(Consumer<PlayerManager> setupScreenLauncher, Consumer<PlayerManager> towerSetUpScreenLauncher, Runnable clearScreen, Consumer<PlayerManager> homeScreenLauncher, Consumer<PlayerManager> shopScreenLauncher, Consumer<PlayerManager> inventoryScreenLauncher, Consumer<PlayerManager> applyUpgradeScreenLauncher, Consumer<PlayerManager> chooseRoundDifficultyScreenLauncher, Consumer<PlayerManager> mainGameScreenLauncher, Consumer<PlayerManager> wonRoundScreenLauncher, Consumer<PlayerManager> gameCompletionScreenLauncher, Consumer<PlayerManager> randomEventScreenLauncher) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.towerSetUpScreenLauncher = towerSetUpScreenLauncher;
        this.homeScreenLauncher = homeScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.clearScreen = clearScreen;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.applyUpgradeScreenLauncher = applyUpgradeScreenLauncher;
        this.chooseRoundDifficultyScreenLauncher = chooseRoundDifficultyScreenLauncher;
        this.mainGameScreenLauncher = mainGameScreenLauncher;
        this.wonRoundScreenLauncher = wonRoundScreenLauncher;
        this.gameCompletionScreenLauncher = gameCompletionScreenLauncher;
        trackDistanceOptionsList = new ArrayList<>();
            trackDistanceOptionsList.add(170);
            trackDistanceOptionsList.add(150);
            trackDistanceOptionsList.add(130);
        this.randomEventScreenLauncher = randomEventScreenLauncher;
        this.player = new Player("PlayerName", 0.00);
        this.shop = new Shop(this, new Tower(), new Upgrade());
        this.towerManager = new Tower();
        this.upgrade = new Upgrade();
        this.randomEvent = new RandomEvent(this, player);
        launchSetupScreen();
    }
    /**
     * Retrieves the shop manager.
     * @return The shop manager.
     */
    public Shop getShopManager(){
        return shop;
    }
    /**
     * Retrieves the player object.
     * @return The player object.
     */
    public Player getPlayer(){
        return player;
    }
    /**
     * Retrieves the upgrade manager.
     * @return The upgrade manager.
     */
    public Upgrade getUpgradeManager(){
        return upgrade;
    }
    /**
     * Retrieves the tower manager.
     * @return The tower manager.
     */
    public Tower getTowerManager(){
        return towerManager;
    }

    /**
     * Retrieves the name of the player.
     * @return The name of the player.
     */
    public String getName(){
        return player.getName();
    }
    /**
     * Sets the name of the player.
     * @param name The new name for the player.
     */
    public void setName(String name) {
        player.setName(name);
    }
    /**
     * Retrieves the amount of money the player has.
     * @return The amount of money the player has.
     */
    public double getMoney(){
        return player.getMoney();
    }
    /**
     * Sets the amount of money the player has.
     * @param money The new amount of money for the player.
     */
    public void setMoney(double money){
        player.setMoney(money);
    }
    /**
     * Retrieves the tower inventory of the player.
     * @return The tower inventory of the player.
     */
    public List<Tower> getTowerInventory(){
        return player.getTowerInventory();
    }
    /**
     * Retrieves the upgrade inventory of the player.
     * @return The upgrade inventory of the player.
     */
    public List<Upgrade> getUpgradeInventory(){
        return player.getUpgradeInventory();
    }
    /**
     * Retrieves the total number of game rounds.
     * @return The total number of game rounds.
     */
    public int getNumGameRounds(){
        return numGameRounds;
    }
    /**
     * Sets the total number of game rounds.
     * @param gameRounds The new total number of game rounds.
     */
    public void setNumGameRounds(int gameRounds){
        this.numGameRounds = gameRounds;
    }
    /**
     * Retrieves the current round number.
     * @return The current round number.
     */
    public int getCurrentRoundNumber(){
        return currentRoundNumber;
    }
    /**
     * Retrieves the game difficulty level.
     * @return The game difficulty level.
     */
    public int getGameDifficulty(){
        return gameDifficulty;
    }
    /**
     * Sets the game difficulty level and updates the player's money accordingly.
     * @param gameDifficulty The new game difficulty level.
     */
    public void setGameDifficulty(int gameDifficulty){
        this.gameDifficulty = gameDifficulty;
        player.setMoney((4-gameDifficulty) * 120);
    }
    /**
     * Sets the list of rounds for random events.
     * Calls the RandomEvent object to generate random event rounds.
     * @see RandomEvent
     */
    public void setRandomEventRoundsList(){
        randomEvent.setRandomEventRounds();
        this.randomEventRoundsList = randomEvent.getRandomEventRounds();
        System.out.println(randomEventRoundsList);
    }

    /**
     * Updates the track distance options list based on the current track distance.
     * Decreases each option's value by specific increments.
     */
    public void updateTrackDistanceOptionsList(){
        trackDistanceOptionsList.set(0,currentTrackDistance - 2);
        trackDistanceOptionsList.set(1,currentTrackDistance - 5);
        trackDistanceOptionsList.set(2,currentTrackDistance - 8);
    }
    /**
     * Sets the initial track distance for the round.
     * Calculates the initial track distance based on the number of game rounds.
     */
    public void setInitialTrackDistance(){
        if (currentRoundNumber == 0){
            currentTrackDistance = numGameRounds * 8 + 10;
        }
    }
    /**
     * Sets the current track distance based on the selected distance index.
     * Retrieves the track distance from the track distance options list.
     * @param selectedDistanceIndex The index of the selected distance option.
     */
    public void setCurrentTrackDistance(int selectedDistanceIndex){
        currentTrackDistance = trackDistanceOptionsList.get(selectedDistanceIndex);
    }
    /**
     * Retrieves the current track distance.
     * @return The current track distance.
     */
    public int getCurrentTrackDistance(){
        return currentTrackDistance;
    }

    /**
     * Retrieves the list of rounds for random events.
     * @return The list of rounds for random events.
     */
    public List<Integer> getRandomEventsRoundList(){
        return randomEventRoundsList;
    }

    /**
     * Sets the success status of the current round.
     * @param bool The success status of the current round.
     */
    public void setRoundSuccess(boolean bool){
        roundSuccess = bool;
    }
    /**
     * Retrieves the success status of the current round.
     * @return The success status of the current round.
     */
    public boolean getRoundSuccess(){
        return roundSuccess;
    }
    /**
     * Retrieves the number of carts filled in the last round.
     * @return The number of carts filled in the last round.
     */
    public int getNumCartsFilled(){
        return numCartsFilled;
    }
    /**
     * Retrieves the text associated with the last random event.
     * @return The text associated with the last random event.
     */
    public String getRandomText(){
        return randomText;
    }
    /**
     * Retrieves the text displayed on the main game screen.
     * @return The text displayed on the main game screen.
     */
    public String getMainGameScreenRoundText(){
        return mainGameScreenText;
    }
    /**
     * Resets the text displayed on the main game screen.
     */
    public void resetMainGameText() {
        this.mainGameScreenText = " ";
    }
    /**
     * Retrieves the amount of money earned in the last round.
     * @return The amount of money earned in the last round.
     */
    public int getEarnedMoney(){
        return earnedMoney;
    }
    /**
     * Sets the amount of money earned based on the current round number.
     * @param currentRoundNumber The current round number.
     */
    public void setEarnedMoney(int currentRoundNumber){
        this.earnedMoney = (currentRoundNumber+1) *12;
    }
    /**
     * Sets the text indicating whether the player won or lost the game.
     * Compares the number of rounds won and lost by the player to determine the outcome.
     */
    public void setWinOrLoseGameText(){
        if (player.getNumRoundsWon() >= player.getNumRoundsLost()){
            this.winOrLoseGameText =  "CONGRATULATIONS YOU WON!";
        } else {
            this.winOrLoseGameText = "SORRY YOU LOST";
        }
    }
    /**
     * Retrieves the text indicating whether the player won or lost the game.
     * @return The text indicating the game outcome.
     */
    public String getWinOrLose(){
        return winOrLoseGameText;
    }


    /**
     * Starts a new round in the game.
     * Creates a new round object, runs the round service, updates game attributes, and launches the main game screen.
     */
    public void startRound() {
        Round currentRound = new Round(player, currentRoundNumber, currentTrackDistance);
        RoundService roundService= new RoundService(currentRound);
        roundService.runRound(currentRound, player);
        mainGameScreenText = currentRound.getMainGameScreenText();
        this.numCartsFilled = currentRound.getNumCartsFilled();
        this.earnedMoney = currentRound.getEarnedMoney();
        roundSuccess = currentRound.isSuccess();
        launchMainGameScreen();
    }
    /**
     * Evaluates the success of the last round and updates the player's money accordingly.
     */
    public void evaluateRoundSuccess(){
        if (roundSuccess){
            setEarnedMoney(currentRoundNumber);
            System.out.println("money before " + player.getMoney());
            setMoney((player.getMoney()) + earnedMoney);
            System.out.println("money after " + player.getMoney());
        }else{
            System.out.println("No Money earned");
        }
        launchWonRoundScreen();
    }
    /**
     * Determines whether to go to the home screen, random event screen, or game completion screen based on the current round number.
     */
    public void toHomeOrRandomEventOrGameFinish() {
        List<Integer> randomEventRounds = randomEvent.getRandomEventRounds();
        if ((currentRoundNumber+1) >= numGameRounds){
            setWinOrLoseGameText();
            System.out.println("Here! Game Complete");
            launchGameCompletionScreen();
        }
        else if (randomEventRounds.contains((currentRoundNumber+1))) {
            randomEvent.generateRandomEvent();

            this.randomText = randomEvent.getRandomEventText();
            launchRandomEventScreen();
            currentRoundNumber += 1;
        } else {
            currentRoundNumber += 1;
            launchHomeScreen();
        }
    }


    /**
     * Applies the selected upgrade to the selected tower.
     * This method upgrades the selected tower based on the selected upgrade.
     * It first prints the tower's stats before the upgrade, then applies the upgrade.
     * If the upgrade is "Tower Reload Speed Boost!", it upgrades the tower's reload speed,
     * assesses the tower's level, and prints the new reload speed.
     * If the upgrade is "Tower Resource Amount Boost!", it upgrades the tower's resource amount,
     * assesses the tower's level, and prints the new resource amount.
     * If the upgrade is a level upgrade, it increases the tower's level.
     * @param selectedTower The tower to which the upgrade is applied.
     * @param selectedUpgrade The upgrade to be applied.
     */
    public void applyUpgrade(Tower selectedTower, Upgrade selectedUpgrade) {
            if (Objects.equals(selectedUpgrade.getUpgradeName(), "Tower Reload Speed Boost!")){
                selectedTower.upgradeReloadSpeed(selectedTower);
                selectedTower.assessTowerLevel(selectedTower);
            }else if (Objects.equals(selectedUpgrade.getUpgradeName(), "Tower Resource Amount Boost!")){
                selectedTower.upgradeTowerResourceAmount(selectedTower);
                selectedTower.assessTowerLevel(selectedTower);
            } else{
                selectedTower.increaseTowerLevel(selectedTower);
            }
            player.removeUpgradeFromInventory(selectedUpgrade);
    }


    /**
     * Launches the setup screen.
     */
    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }
    /**
     * Launches the tower setup screen.
     */
    public void launchTowerSetUpScreen(){
        towerSetUpScreenLauncher.accept(this);
    }
    /**
     * Launches the home screen.
     */
    public void launchHomeScreen(){
        homeScreenLauncher.accept(this);
    }
    /**
     * Launches the main game screen.
     */
    public void launchMainGameScreen() {
        mainGameScreenLauncher.accept(this);
    }
    /**
     * Launches the random event screen.
     */
    public void launchRandomEventScreen() {
        randomEventScreenLauncher.accept(this);
    }
    /**
     * Launches the screen displayed when a round is won.
     */
    public void launchWonRoundScreen(){
        wonRoundScreenLauncher.accept(this);
    }
    /**
     * Launches the game completion screen.
     * This method is private, indicating it is called internally.
     */
    private void launchGameCompletionScreen() {
        gameCompletionScreenLauncher.accept(this);
    }
    /**
     * Launches the shop screen.
     */
    public void launchShopScreen() {
        shopScreenLauncher.accept(this);
    }
    /**
     * Launches the inventory screen.
     */
    public void launchInventoryScreen(){
        inventoryScreenLauncher.accept(this);
    }
    /**
     * Launches the apply upgrade screen.
     */
    public void launchApplyUpgradeScreen(){
        applyUpgradeScreenLauncher.accept(this);
    }
    /**
     * Launches the choose round difficulty screen.
     */
    public void launchChooseRoundDifficultyScreen(){
        chooseRoundDifficultyScreenLauncher.accept(this);
    }
    /**
     * Closes the setup screen.
     */
    public void closeSetupScreen(){
        clearScreen.run();
    }
    /**
     * Closes the tower setup screen.
     */
    public void closeTowerSetUpScreen(){
        clearScreen.run();
    }
    /**
     * Closes the main screen.
     */
    public void closeMainScreen() {
        clearScreen.run();
    }
    /**
     * Closes the choose round difficulty screen.
     */
    public void closeChooseRoundDifficultyScreen(){
        clearScreen.run();
    }
    /**
     * Closes the shop screen.
     */
    public void closeShopScreen(){
        clearScreen.run();
    }
    /**
     * Closes the apply upgrade screen.
     */
    public void closeApplyUpgradeScreen(){
        clearScreen.run();
    }
    /**
     * Closes the screen displayed when a round is won.
     */
    public void closeWonRoundScreen(){
        clearScreen.run();
    }
    /**
     * Closes the random event screen.
     */
    public void closeRandomEventScreen(){
        clearScreen.run();
    }
    /**
     * Closes the inventory screen.
     */
    public void closeInventoryScreen(){
        clearScreen.run();
    }

}

