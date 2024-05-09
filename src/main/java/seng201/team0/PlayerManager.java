package seng201.team0;

import seng201.team0.models.Player;

import java.util.function.Consumer;

public class PlayerManager {
    //DONT KNOW WHATS MEANT TO BE HERE, is same as player atm
    private String name;
    private int numGameRounds;
    private  int gameDifficulty;
    private final Consumer<PlayerManager> setupScreenLauncher;
    private final Consumer<PlayerManager> towerSetUpScreenLauncher;
    private final Consumer<PlayerManager> shopScreenLauncher;
    private final Runnable clearScreen;
    //private DoubleProperty numRounds;

    public PlayerManager(Consumer<PlayerManager> setupScreenLauncher, Consumer<PlayerManager> towerSetUpScreenLauncher, Runnable clearScreen, Consumer<PlayerManager> shopScreenLauncher) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.towerSetUpScreenLauncher = towerSetUpScreenLauncher;
        this.clearScreen = clearScreen;
        this.shopScreenLauncher = shopScreenLauncher;
        launchSetupScreen();
    }
    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }
    public void closeSetupScreen(){
        clearScreen.run();
    }
    public void launchShopScreen() {
        shopScreenLauncher.accept(this);
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;}
    public int getNumGameRounds(){ return numGameRounds;}
    public void setNumGameRounds(int gameRounds){ this.numGameRounds = gameRounds;}
    public int getGameDifficulty(){ return gameDifficulty;}
    public void setGameDifficulty(int gameDifficulty){ this.gameDifficulty = gameDifficulty;}
    public void launchTowerSetUpScreen(){
        towerSetUpScreenLauncher.accept(this);
    }
    public void closeTowerSetUpScreen(){
        System.exit(0);
    }

    public void closeMainScreen() {
    }

}
