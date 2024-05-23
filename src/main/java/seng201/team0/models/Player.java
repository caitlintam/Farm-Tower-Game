package seng201.team0.models;

import seng201.team0.PlayerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    // inventory, getts and setters
    private String name;
    private double money;
    private List<Tower> towerInventory;
    private List<Upgrade> upgradeInventory;
    private List<Tower> towersInGame;
    private List<Tower> reserveTowers;
    private int numRoundsWon;
    private int numRoundsLost;
 //   private String mainGameScreenText;

    public Player(String name, double initialMoney){
        this.name = name;
        this.money = initialMoney;
        this.towerInventory = new ArrayList<Tower>();
        this.upgradeInventory = new ArrayList<Upgrade>();
        this.towersInGame = new ArrayList<Tower>();
        this.reserveTowers = new ArrayList<Tower>();
        this.numRoundsLost = 0;
        this.numRoundsWon = 0;
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
    public List<Tower> getTowersInGame(){
        towersInGame = towerInventory.stream()
                .filter(tower -> tower.getTowerStatus().equals("In-Game"))
                .collect(Collectors.toList());
        return towersInGame;}
    public void addTowersToInventory(Tower tower) {
        Tower newTower = new Tower(tower.getTowerName(), tower.getTowerResourceAmount(), tower.getTowerResourceType(),
                tower.getTowerReloadSpeed(), tower.getTowerLevel(), tower.getTowerCost(),
                tower.getTowerStatus());
        // Add the new tower object to the inventory
        towerInventory.add(newTower);
    }
    public void addUpgradesToInventory(Upgrade upgrade){
        Upgrade newUpgrade = new Upgrade(upgrade.getUpgradeName(), upgrade.getUpgradeCost());
        upgradeInventory.add(newUpgrade);
    }
    public void removeTowerFromInventory(Tower selectedTower) {
        towerInventory.remove(selectedTower);
    }

    public void removeUpgradeFromInventory(Upgrade selectedUpgrade) {
        upgradeInventory.remove(selectedUpgrade);
    }
    public List<String> getTowersResTypeInGame(){
        return towersInGame.stream().map(Tower::getTowerResourceType).collect(Collectors.toList());

    }
    public void setTowersInGame() {
        // Filter towerInventory to get only the towers that are in-game
        towersInGame = towerInventory.stream()
                .filter(tower -> tower.getTowerStatus().equals("In-Game"))
                .collect(Collectors.toList());
    }
    public void setReserveTowers() {
        // Filter towerInventory to get only the towers that are in-game
        reserveTowers = towerInventory.stream()
                .filter(tower -> tower.getTowerStatus().equals("Reserve"))
                .collect(Collectors.toList());
    }
    public List<Tower> getReserveTowers() {
        return reserveTowers;
    }
    public int getNumRoundsWon(){return numRoundsWon;}
    public int getNumRoundsLost(){return numRoundsLost;}
    public void increaseNumRoundsWon(){
        numRoundsWon +=1;
    }
    public void increaseNumRoundsLost(){
        numRoundsLost +=1;
    }


}
