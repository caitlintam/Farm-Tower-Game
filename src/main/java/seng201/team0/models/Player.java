package seng201.team0.models;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int numRounds;
    private int gameDifficulty;
    private double money;

    //private List<> inventory;
    // private list inventory (maybe 2 one of type tower )
    public Player(String name, int numRounds, int gameDifficulty, double money) {
        this.name = name;
        this.numRounds = numRounds;
        this.gameDifficulty = gameDifficulty;
        this.money = money;
        //this.inventory = new ArrayList<>();
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getNumRounds() {
        return numRounds;
    }

    public void setNumRounds(int numRounds) {
        this.numRounds = numRounds;
    }

    public int getGameDifficulty() {
        return gameDifficulty;
    }

    public void setGameDifficulty(int gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }
}

//        public List<Item> getInventory() {
//            return inventory;
//        }

//        public void addItemToInventory(Item item) {
//              inventory.add(item);
//      }
        // public add stuff for rounds and difficulty



