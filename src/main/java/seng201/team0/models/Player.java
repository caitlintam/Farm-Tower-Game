package seng201.team0.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Represents a player in the game with attributes such as name, money, inventories for towers and upgrades,
 * as well as statistics on rounds won and lost.
 */
public class Player {
    // inventory, getts and setters
    private String name;
    private double money;
    private final List<Tower> towerInventory;
    private final List<Upgrade> upgradeInventory;
    private List<Tower> towersInGame;
    private List<Tower> reserveTowers;
    private int numRoundsWon;
    private int numRoundsLost;
    /**
     * Constructs a new player with the specified name and initial money.
     *
     * @param name the name of the player
     * @param initialMoney the initial amount of money the player has
     */
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
    /**
     * Gets the name of the player.
     *
     * @return the name of the player
     */
    public String getName(){
        return name;
    }
    /**
     * Sets the name of the player.
     *
     * @param name the name to set for the player
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets the amount of money the player has.
     *
     * @return the amount of money the player has
     */
    public double getMoney(){
        return money;
    }
    /**
     * Sets the amount of money the player has.
     *
     * @param money the amount of money to set for the player
     */
    public void setMoney(double money){
        this.money = money;
    }
    /**
     * Gets the inventory of towers the player possesses.
     *
     * @return the tower inventory of the player
     */
    public List<Tower> getTowerInventory(){
        return towerInventory;
    }
    /**
     * Gets the inventory of upgrades the player possesses.
     *
     * @return the upgrade inventory of the player
     */
    public List<Upgrade> getUpgradeInventory(){
        return upgradeInventory;
    }
    /**
     * Gets the list of towers the player has in the current game.
     *
     * @return the list of towers in the game
     */
    public List<Tower> getTowersInGame(){
        towersInGame = towerInventory.stream()
                .filter(tower -> tower.getTowerStatus().equals("In-Game"))
                .collect(Collectors.toList());
        return towersInGame;}
    /**
     * Adds a tower to the player's tower inventory.
     *
     * @param tower the tower to be added to the inventory
     */
    public void addTowersToInventory(Tower tower) {
        Tower newTower = new Tower(tower.getTowerName(), tower.getTowerResourceAmount(), tower.getTowerResourceType(),
                tower.getTowerReloadSpeed(), tower.getTowerLevel(), tower.getTowerCost(),
                tower.getTowerStatus());
        // Add the new tower object to the inventory
        towerInventory.add(newTower);
    }
    /**
     * Adds an upgrade to the player's upgrade inventory.
     *
     * @param upgrade the upgrade to be added to the inventory
     */
    public void addUpgradesToInventory(Upgrade upgrade){
        Upgrade newUpgrade = new Upgrade(upgrade.getUpgradeName(), upgrade.getUpgradeCost());
        upgradeInventory.add(newUpgrade);
    }
    /**
     * Removes a tower from the player's tower inventory.
     *
     * @param selectedTower the tower to be removed from the inventory
     */
    public void removeTowerFromInventory(Tower selectedTower) {
        towerInventory.remove(selectedTower);
    }
    /**
     * Removes an upgrade from the player's upgrade inventory.
     *
     * @param selectedUpgrade the upgrade to be removed from the inventory
     */
    public void removeUpgradeFromInventory(Upgrade selectedUpgrade) {
        upgradeInventory.remove(selectedUpgrade);
    }
    /**
     * Gets the resource types of towers currently in the game.
     *
     * @return a list of resource types of towers in the game
     */
    public List<String> getTowersResTypeInGame(){
        return towersInGame.stream().map(Tower::getTowerResourceType).collect(Collectors.toList());

    }
    /**
     * Sets the list of towers the player has in the current game.
     * Retrieves towers from the player's tower inventory based on their status and updates the list of towers in-game.
     */
    public void setTowersInGame() {
        // Filter towerInventory to get only the towers that are in-game
        towersInGame = towerInventory.stream()
                .filter(tower -> tower.getTowerStatus().equals("In-Game"))
                .collect(Collectors.toList());
    }
    // for testing
    public void setTowersInGame(List<Tower> towersInGame){
        this.towersInGame = towersInGame;
    }
    public void setReserveTowers() {
        // Filter towerInventory to get only the towers that are in-game
        reserveTowers = towerInventory.stream()
                .filter(tower -> tower.getTowerStatus().equals("Reserve"))
                .collect(Collectors.toList());
    }
    /**
     * Gets the list of reserve towers the player has for future use.
     *
     * @return the list of reserve towers
     */
    public List<Tower> getReserveTowers() {
        return reserveTowers;
    }
    /**
     * Gets the number of rounds the player has won.
     *
     * @return the number of rounds won by the player
     */
    public int getNumRoundsWon(){
        return numRoundsWon;
    }
    /**
     * Gets the number of rounds the player has lost.
     *
     * @return the number of rounds lost by the player
     */
    public int getNumRoundsLost(){
        return numRoundsLost;
    }
    /**
     * Increases the number of rounds won by the player by 1.
     */
    public void increaseNumRoundsWon(){
        numRoundsWon +=1;
    }
    /**
     * Increases the number of rounds lost by the player by 1.
     */
    public void increaseNumRoundsLost(){
        numRoundsLost +=1;
    }


}
