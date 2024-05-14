package seng201.team0.models;

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
    public String getTowerResourceType(){
        return towerResourceType;
    }
    public int getTowerReloadSpeed(){
        return towerReloadSpeed;
    }
    public int getTowerLevel(){
        return towerLevel;
    }
    public double getTowerCost(){
        return towerCost;
    }
    public void setTowerStatus(String towerStatus){
        this.towerStatus = towerStatus;
    }
    public String getTowerStatus(){return towerStatus;}

}
