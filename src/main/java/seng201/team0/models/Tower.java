package seng201.team0.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Represents a tower in the game.
 * Each tower has a name, resource amount, resource type, reload speed, level, cost, and status.
 */
public class Tower {
    private String towerName;
    private int towerResourceAmount;
    private String towerResourceType;
    private int towerReloadSpeed;
    private int towerLevel;
    private double towerCost;
    private String towerStatus;

    private static final String[] RESOURCE_TYPES = {"Hay", "Water", "Cows", "Pigs", "Timber", "Chickens"};
    private List<Tower> towerList;
    private final List<Tower> defaultTowers = new ArrayList<>();
    /**
     * Constructs a Tower object with specified parameters.
     * @param towerName The name of the tower.
     * @param towerResourceAmount The current resource amount of the tower.
     * @param towerResourceType The type of resource the tower generates.
     * @param towerReloadSpeed The speed at which the tower reloads its resource.
     * @param towerLevel The level of the tower.
     * @param towerCost The cost of building the tower.
     * @param towerStatus The status of the tower.
     */
    public Tower(String towerName, int towerResourceAmount, String towerResourceType, int towerReloadSpeed, int towerLevel, double towerCost, String towerStatus){
        this.towerName = towerName;
        this.towerResourceAmount = towerResourceAmount;
        this.towerResourceType = towerResourceType;
        this.towerReloadSpeed = towerReloadSpeed;
        this.towerLevel = towerLevel;
        this.towerCost = towerCost;
        this.towerStatus = towerStatus;
    }
    /**
     * Constructs a Tower object with default values.
     */
    public Tower(){
        defaultTowers.addAll(List.of(
                new Tower("Piglet Palace", 10, "Pigs", 30, 1, 120.00, "Reserve"),
                new Tower("Cowtopia Castle", 8, "Cows", 25, 1,140.00, "Reserve"),
                new Tower("Haybale Haven", 12, "Hay", 27, 1, 135.00,"Reserve"),
                new Tower("Timber Turret", 7, "Timber", 24, 1, 140.00,"Reserve"),
                new Tower("Water Tower ", 6, "Water", 25, 1, 130.00,"Reserve"),
                new Tower("Steel Steeple", 11, "Steel", 32, 1, 125.00,"Reserve"),
                new Tower("Corn Castle", 7, "Corn", 29, 1, 150.00,"Reserve"),
                new Tower("Wheat Tower", 9, "Wheat", 28, 1, 130.00,"Reserve"),
                new Tower("Chicken Coop", 12, "Chickens", 27, 1, 130.00,"Reserve")));
    }
    /**
     * Returns the list of towers.
     * @return The list of towers.
     */
    public List<Tower> getTowerList(){
        return towerList;
    }
    /**
     * Sets the list of towers.
     * @param towerList The list of towers to set.
     */
    public void setTowerList(List<Tower> towerList){
        this.towerList = towerList;
    }
    /**
     * Returns the list of default towers.
     * @return The list of default towers.
     */
    public List<Tower> getDefaultTowers(){
        return defaultTowers;
    }
    /**
     * Sets the status of a tower.
     * If the tower is in the tower list, its status is set to "In-Game"; otherwise, it's set to "Reserve".
     * @param tower The tower whose status is to be set.
     */
    public void setTowerStatus(Tower tower) {
        if (towerList.contains(tower)){
            tower.setTowerStatus("In-Game");
        }else{
            tower.setTowerStatus("Reserve");
        }
    }
    /**
     * Returns the name of the tower.
     * @return The name of the tower.
     */
    public String getTowerName(){
        return towerName;
    }
    /**
     * Returns the current resource amount of the tower.
     * @return The current resource amount of the tower.
     */
    public int getTowerResourceAmount(){
        return towerResourceAmount;
    }
    /**
     * Sets the current resource amount of the tower.
     * @param newResourceAmount The new resource amount to set.
     */
    private void setTowerResourceAmount(int newResourceAmount) {
        this.towerResourceAmount = newResourceAmount;
    }
    /**
     * Returns the type of resource the tower generates.
     * @return The type of resource the tower generates.
     */
    public String getTowerResourceType(){
        return towerResourceType;
    }
    /**
     * Returns the reload speed of the tower.
     * @return The reload speed of the tower.
     */
    public int getTowerReloadSpeed(){
        return towerReloadSpeed;
    }
    /**
     * Sets the reload speed of the tower.
     * @param newTowerReloadSpeed The new reload speed to set.
     */
    public void setTowerReloadSpeed(int newTowerReloadSpeed){
        this.towerReloadSpeed = newTowerReloadSpeed;
    }
    /**
     * Returns the level of the tower.
     * @return The level of the tower.
     */
    public int getTowerLevel(){
        return towerLevel;
    }
    /**
     * Sets the level of the tower.
     * @param newTowerLevel The new level to set.
     */
    public void setTowerLevel(int newTowerLevel) {
        this.towerLevel = newTowerLevel;
    }
    /**
     * Returns the cost of building the tower.
     * @return The cost of building the tower.
     */
    public double getTowerCost(){
        return towerCost;
    }
    /**
     * Sets the status of the tower.
     * @param towerStatus The new status to set.
     */
    public void setTowerStatus(String towerStatus){
        this.towerStatus = towerStatus;
    }
    /**
     * Returns the status of the tower.
     * @return The status of the tower.
     */
    public String getTowerStatus(){
        return towerStatus;
    }
    /**
     * Updates the status of the selected tower between "In-Game" and "Reserve".
     * @param selectedTower The tower whose status is to be updated.
     */
    public void updateTowerStatus(Tower selectedTower) {
        if (Objects.equals(selectedTower.getTowerStatus(), "In-Game")) {
            selectedTower.setTowerStatus("Reserve");
        } else {
            selectedTower.setTowerStatus("In-Game");
        }
    }
    /**
     * Upgrades the reload speed of the selected tower.
     * @param selectedTower The tower whose reload speed is to be upgraded.
     */
    public void upgradeReloadSpeed(Tower selectedTower) {
        if (selectedTower.getTowerReloadSpeed()>1){
            selectedTower.setTowerReloadSpeed(selectedTower.getTowerReloadSpeed()-1);
        }else{
            System.out.println("Tower Reload Speed is 1, can't decrease lower");
        }
    }
    /**
     * Upgrades the resource amount of the selected tower by 2.
     * @param selectedTower The tower whose resource amount is to be upgraded.
     */
    public void upgradeTowerResourceAmount(Tower selectedTower){
        selectedTower.setTowerResourceAmount(towerResourceAmount+2);
    }
    /**
     * Increases the level of the selected tower.
     * @param selectedTower The tower whose level is to be increased.
     */
    public void increaseTowerLevel(Tower selectedTower){
        selectedTower.setTowerResourceAmount(selectedTower.getTowerLevel()*6);
        selectedTower.setTowerReloadSpeed(30 - ((selectedTower.getTowerLevel()+1)*2));
        selectedTower.setTowerLevel(selectedTower.getTowerLevel()+1);
    }
    /**
     * Assesses the level of the tower and upgrades it if necessary.
     * @param towerToAssess The tower to be assessed and potentially leveled up.
     */
    public void assessTowerLevel(Tower towerToAssess){
        if (towerToAssess.getTowerResourceAmount() > (6*towerToAssess.getTowerLevel())){
            towerToAssess.setTowerReloadSpeed(towerToAssess.getTowerReloadSpeed() - ((towerToAssess.getTowerLevel()-1)*2));
            towerToAssess.setTowerLevel(towerToAssess.getTowerLevel()+1);
            System.out.println("Tower Stats Assessed, leveled up");
        }
    }
    /**
     * Decreases the level of the selected tower if its level is greater than 1.
     * @param selectedTower The tower whose level is to be decreased.
     */
    public void decreaseTowerLevel(Tower selectedTower){
        if (selectedTower.getTowerLevel() >1){
            selectedTower.setTowerResourceAmount((selectedTower.getTowerLevel()-1)*6);
            selectedTower.setTowerReloadSpeed(30 - ((selectedTower.getTowerLevel()-1)*2));
            selectedTower.setTowerLevel(selectedTower.getTowerLevel()-1);
            System.out.println("Tower Level Decreased");
        }else{
            System.out.println("Tower Level = 1, cannot decrease");
        }

    }
}
