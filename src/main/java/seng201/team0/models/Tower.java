package seng201.team0.models;

import java.util.Objects;

public class Tower {
    private String towerName;
    private int towerResourceAmount;
    private String towerResourceType;
    private int towerReloadSpeed;
    private int towerLevel;
    private double towerCost;
    private String towerStatus;

    private static final String[] RESOURCE_TYPES = {"Hay", "Water", "Cows", "Pigs", "Timber", "Chickens"};

    public Tower(String towerName, int towerResourceAmount, String towerResourceType, int towerReloadSpeed, int towerLevel, double towerCost, String towerStatus){
        this.towerName = towerName;
        this.towerResourceAmount = towerResourceAmount;
        this.towerResourceType = towerResourceType;
        this.towerReloadSpeed = towerReloadSpeed;
        this.towerLevel = towerLevel;
        this.towerCost = towerCost;
        this.towerStatus = towerStatus;
    }

    public String getTowerName(){
        return towerName;
    }
    public void setTowerName(String towerName){
        this.towerName = towerName;
    }
    public int getTowerResourceAmount(){
        return towerResourceAmount;
    }
    private void setTowerResourceAmount(int newResourceAmount) {this.towerResourceAmount = newResourceAmount;}
    public String getTowerResourceType(){
        return towerResourceType;
    }
    public int getTowerReloadSpeed(){
        return towerReloadSpeed;
    }
    public void setTowerReloadSpeed(int newTowerReloadSpeed){this.towerReloadSpeed= newTowerReloadSpeed;}
    public int getTowerLevel(){
        return towerLevel;
    }
    public void setTowerLevel(int newTowerLevel) {this.towerLevel = newTowerLevel;}
    public double getTowerCost(){
        return towerCost;
    }
    public void setTowerStatus(String towerStatus){
        this.towerStatus = towerStatus;
    }
    public String getTowerStatus(){return towerStatus;}

    public void updateTowerStatus(Tower selectedTower) {
        if (Objects.equals(selectedTower.getTowerStatus(), "In-Game")) {
            selectedTower.setTowerStatus("Reserve"); // Assuming setTowerStatus() method exists
        } else {
            selectedTower.setTowerStatus("In-Game"); // Assuming setTowerStatus() method exists
        }
    }

    public void updateTowerReloadSpeed(Tower selectedTower) {
        selectedTower.setTowerReloadSpeed(towerReloadSpeed+1);
    }
    public void updateTowerResourceAmount(Tower selectedTower){
        selectedTower.setTowerResourceAmount(towerResourceAmount+1);
    }
    public void updateTowerLevel(Tower selectedTower){
        selectedTower.setTowerLevel(towerLevel+1);
    }
}
