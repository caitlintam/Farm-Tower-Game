package seng201.team0;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import seng201.team0.gui.service.CartService;
import seng201.team0.gui.service.RoundService;
import seng201.team0.models.*;

public class PlayerManager {
  //  private String name;
    private int currentRoundNumber = 0;
    private int numGameRounds = 0;
    private  int gameDifficulty;
    private final Consumer<PlayerManager> setupScreenLauncher;
    private final Consumer<PlayerManager> towerSetUpScreenLauncher;
    private final Consumer<PlayerManager> homeScreenLauncher;
    private final Consumer<PlayerManager> shopScreenLauncher;
    private final Consumer<PlayerManager> inventoryScreenLauncher;
    private final Consumer<PlayerManager> applyUpgradeScreenLauncher;
    private final Consumer<PlayerManager> chooseRoundDifficultyScreenLauncher;
    private final Consumer<PlayerManager> mainGameScreenLauncher;
    private final Consumer<PlayerManager> wonRoundScreenLauncher;
 //   private final Consumer<PlayerManager> lostRoundScreenLauncher;
    private final Consumer<PlayerManager> gameCompletionScreenLauncher;
    private final Consumer<PlayerManager> randomEventScreenLauncher;
    private final Runnable clearScreen;
  //  private double money = 1000.00;
//    private List<Tower> towerInventory;
//    private List<Upgrade> upgradeInventory;
//    private List<Tower> towersInGame;
//    private List<Tower> reserveTowers;
    private int currentTrackDistance;
    private List<Integer> trackDistanceOptionsList;

    private List<Cart> newCartsInRound;
    private CartService cartService;
    private List<Cart> cartsInRound;
    private RandomEvent randomEvent;
    private List<Integer> randomEventRoundsList;
    private String winOrLose;
    private RoundService roundService;

    private int numRoundsWon = 0;
    private int numRoundsLost = 0;
    private boolean roundSuccess = false;
    private int earnedMoney;
    private int numCartsFilled;
    private String mainGameScreenText;
    private Player player;



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
       // this.lostRoundScreenLauncher = lostRoundScreenLauncher;
        this.wonRoundScreenLauncher = wonRoundScreenLauncher;
        this.gameCompletionScreenLauncher = gameCompletionScreenLauncher;
//        this.towerInventory = new ArrayList<Tower>();
//        this.upgradeInventory = new ArrayList<Upgrade>();
//        this.towersInGame = new ArrayList<Tower>();
//        this.reserveTowers = new ArrayList<Tower>();
        trackDistanceOptionsList = new ArrayList<Integer>();
            trackDistanceOptionsList.add(170);
            trackDistanceOptionsList.add(150);
            trackDistanceOptionsList.add(130);

        this.newCartsInRound = new ArrayList<Cart>();
      //  this.cartService = new CartService(this, new TowerManager(), player);

        this.randomEventScreenLauncher = randomEventScreenLauncher;

        //this.roundService = new RoundService(player, cartService, );
        this.player = new Player("PlayerName", 0.00);
        this.shop = new Shop(this, new Tower(), new Upgrade());
        this.towerManager = new Tower();
        this.upgrade = new Upgrade();
        this.randomEvent = new RandomEvent(this, player);
        launchSetupScreen();
    }

    ///player stuff

    public String getName(){
        return player.getName();
    }
    public void setName(String name) {
        player.setName(name);}
    public double getMoney(){
        return player.getMoney();
    }
    public void setMoney(double money){
        player.setMoney(money);
    }
    public List<Tower> getTowerInventory(){
        return player.getTowerInventory();
    }
    public List<Upgrade> getUpgradeInventory(){
        return player.getUpgradeInventory();
    }
    public List<Tower> getReserveTowers() {
        return player.getReserveTowers();
    }

// round stuff

    public int getNumGameRounds(){ return numGameRounds;}
    public void setNumGameRounds(int gameRounds){ this.numGameRounds = gameRounds;}
    public int getCurrentRoundNumber(){ return currentRoundNumber;}
    public int getGameDifficulty(){ return gameDifficulty;}
    public void setGameDifficulty(int gameDifficulty){
        this.gameDifficulty = gameDifficulty;
        // easier (smaller) gamedifficulty = more initialmoney
        player.setMoney((4-gameDifficulty) * 120);
    }
//    public List<Tower> getTowersInGame(){return towersInGame;}
//    public List<Tower> getTowersInGame(){return towersInGame;}

    public void setRandomEventRoundsList(){
        randomEvent.setRandomEventRounds();
        this.randomEventRoundsList = randomEvent.getRandomEventRounds();
        System.out.println(randomEventRoundsList);
    }

    //////////////////////////////////////////// launching screens ///////////////////////////////////////////////////////
    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }
    public void launchTowerSetUpScreen(){
        towerSetUpScreenLauncher.accept(this);
    }
    public void launchHomeScreen(){
        homeScreenLauncher.accept(this);
        // catch if rounds exceeded
    }
    public void launchMainGameScreen() {
        mainGameScreenLauncher.accept(this);
    }
    public void launchRandomEventScreen() {
        randomEventScreenLauncher.accept(this);
    }
    public void launchWonRoundScreen(){
        wonRoundScreenLauncher.accept(this);
    }
 //   public void launchLostRoundScreen(){lostRoundScreenLauncher.accept(this);
   // }
    private void launchGameCompletionScreen() {gameCompletionScreenLauncher.accept(this);
    }
    public void launchShopScreen() {
        shopScreenLauncher.accept(this);
    }
    public void launchInventoryScreen(){
        inventoryScreenLauncher.accept(this);
    }
    public void launchApplyUpgradeScreen(){applyUpgradeScreenLauncher.accept(this);}
    public void launchChooseRoundDifficultyScreen(){chooseRoundDifficultyScreenLauncher.accept(this);
    }
//////////////////////// Closing Screens ////////////////////////////
    public void closeSetupScreen(){
        clearScreen.run();
    }

    public void closeTowerSetUpScreen(){
        clearScreen.run();
    }
    public void closeMainScreen() {
        clearScreen.run();
    }
    public void closeChooseRoundDifficultyScreen(){clearScreen.run();}
    public void closeShopScreen(){
        clearScreen.run();
    }
    public void closeApplyUpgradeScreen(){clearScreen.run();}
    public void closeWonRoundScreen(){ clearScreen.run();}
   // public void closeLostRoundScreen(){clearScreen.run();}
    public void closeRandomEventScreen(){
        clearScreen.run();
    }

    public void closeInventoryScreen(){clearScreen.run();}
//     resets the distance options list, to +5, +10, +15 each round
//     call this method in the page between rounds when next clicked. to refresh track dist.
    public void updateTrackDistanceOptionsList(){
        trackDistanceOptionsList.set(0,currentTrackDistance - 2);
        trackDistanceOptionsList.set(1,currentTrackDistance - 5);
        trackDistanceOptionsList.set(2,currentTrackDistance - 8);
    }
    // sets the current Track Distance after 'difficulty' selected
    public void setInitialTrackDistance(){
        if (currentRoundNumber == 0){
            currentTrackDistance = numGameRounds * 8 + 10;
        }
    }
    public void setCurrentTrackDistance(int selectedDistanceIndex){
        currentTrackDistance = trackDistanceOptionsList.get(selectedDistanceIndex);}
    public int getCurrentTrackDistance(){return currentTrackDistance;}
    // cbb putting at top now

    public void startRound() {
        Round currentRound = new Round(player, currentRoundNumber, currentTrackDistance);
        this.roundService = new RoundService(currentRound);
        roundService.runRound(currentRound, player);
        mainGameScreenText = currentRound.getMainGameScreenText();
        this.numCartsFilled = currentRound.getNumCartsFilled();
        this.earnedMoney = currentRound.getEarnedMoney();
        roundSuccess = currentRound.isSuccess();
        launchMainGameScreen();
        //unchMainGameScreen();
    }
//    public void updateMainGameScreenText(String text){
//        round.setMainGameScreenText(text);
//    }
//    public String getMainGameScreenText(){
//        return round.getMainGameScreenText();
//    }
    public void runRoundService(Round round){

    }
    public List<Integer> getRandomEventsRoundList(){
        return randomEventRoundsList;
    }

    public int getEarnedMoney(){
        return earnedMoney;
    }
    public void setEarnedMoney(int currentRoundNumber){
        this.earnedMoney = (currentRoundNumber+1) *12;
    }
    private String winOrLoseGameText;
    public void setWinOrLoseGameText(){
        if (player.getNumRoundsWon() >= player.getNumRoundsLost()){
            this.winOrLoseGameText =  "CONGRATULATIONS YOU WON!";
        } else {
            this.winOrLoseGameText = "SORRY YOU LOST";
        }
    }
    public String getWinOrLose(){
        return winOrLoseGameText;
    }

    public void evaluateRoundSuccess(){
        if (roundSuccess == true){
            setEarnedMoney(currentRoundNumber);
            System.out.println("money before " + player.getMoney());
            setMoney((player.getMoney()) + earnedMoney);
            System.out.println("money after " + player.getMoney());
        }else{
            System.out.println("No Money earned");
        }
        launchWonRoundScreen();
    }

    private String randomText;
    public void toHomeOrRandomEventOrGameFinish() {
        List<Integer> randomEventRounds = randomEvent.getRandomEventRounds();
        if ((currentRoundNumber+1) >= numGameRounds){
            setWinOrLoseGameText();
            System.out.println("Here! compelte game");
            launchGameCompletionScreen();
        }
        // if current round is a round of a random event, generate the random event
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
    private Shop shop;
    private Tower towerManager;
    private Upgrade upgrade;

    //getting other classes from playermanager
    public Shop getShopManager(){
        return shop;
    }
    public Player getPlayer(){
        return player;
    }
    public RandomEvent getRandomEventManager(){
        return randomEvent;
    }
    public Upgrade getUpgradeManager(){
        return upgrade;
    }
    public Tower getTowerManager(){
        return towerManager;
    }
    public CartService getCartManager(){
        return cartService;
    }
//    public List<Cart> getCartsInRound(){
//        return cartService.getCartsInRound()
//    }




    public String getRandomText(){
        return randomText;
    }

    public void setNumCartsFilled(int numCartsFilled){
        this.numCartsFilled = numCartsFilled;
    }


    public void setRoundSuccess(boolean bool){
        roundSuccess = bool;
    }
    public boolean getRoundSuccess(){
        return roundSuccess;
    }

    public int getNumCartsFilled(){
        return numCartsFilled;
    }


    public String getMainGameScreenRoundText(){
        return mainGameScreenText;
    }


    public void resetMainGameText() {
        this.mainGameScreenText = " ";
    }


}

