package seng201.team0;

import java.beans.beancontext.BeanContextServiceAvailableEvent;
import java.util.function.Consumer;

public class PlayerManager {
    private String name;
    private int numGameRounds;
    private  int gameDifficulty;
    private final Consumer<PlayerManager> setupScreenLauncher;
    private final Consumer<PlayerManager> towerSetUpScreenLauncher;
    private final Consumer<PlayerManager> homeScreenLauncher;
    private final Runnable clearScreen;
    private double money = 0;
    //private DoubleProperty numRounds;

    public PlayerManager(Consumer<PlayerManager> setupScreenLauncher, Consumer<PlayerManager> towerSetUpScreenLauncher, Runnable clearScreen, Consumer<PlayerManager> homeScreenLauncher) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.towerSetUpScreenLauncher = towerSetUpScreenLauncher;
        this.homeScreenLauncher = homeScreenLauncher;
        this.clearScreen = clearScreen;
        launchSetupScreen();
    }
    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }
    public void closeSetupScreen(){
        clearScreen.run();
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;}
    public double getMoney(){return money;}

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
    public void launchHomeScreen(){homeScreenLauncher.accept(this);}
}
