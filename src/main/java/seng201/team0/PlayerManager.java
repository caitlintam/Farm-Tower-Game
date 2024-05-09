package seng201.team0;

import java.util.function.Consumer;

public class PlayerManager {
    //DONT KNOW WHATS MEANT TO BE HERE, is same as player atm
    private String name;
    private int numGameRounds;
    private  int gameDifficulty;
    private final Consumer<PlayerManager> setupScreenLauncher;
    private final Consumer<PlayerManager> towerSetUpScreenLauncher;
    private final Runnable clearScreen;
    //private DoubleProperty numRounds;

    public PlayerManager(Consumer<PlayerManager> setupScreenLauncher, Consumer<PlayerManager> towerSetUpScreenLauncher, Runnable clearScreen) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.towerSetUpScreenLauncher = towerSetUpScreenLauncher;
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
}
