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

    public void upgradeReloadSpeed(Tower selectedTower) {
        if (selectedTower.getTowerReloadSpeed()>1){
            selectedTower.setTowerReloadSpeed(selectedTower.getTowerReloadSpeed()-1);
        }else{
            System.out.println("Tower Reload Speed is 1, can't decrease lower");
        }

    }
    public void upgradeTowerResourceAmount(Tower selectedTower){
        selectedTower.setTowerResourceAmount(towerResourceAmount+2);
    }
    //////////////CHANGE THIS, TO INCREASE TOWER LEVEL//////////
    //// add 6 to res amt, -4 off reload speed //// , level +1
    public void increaseTowerLevel(Tower selectedTower){
        selectedTower.setTowerResourceAmount(selectedTower.getTowerLevel()*6);
        selectedTower.setTowerReloadSpeed(30 - ((selectedTower.getTowerLevel()+1)*2));
        selectedTower.setTowerLevel(selectedTower.getTowerLevel()+1);
        //////// increases res amount and
    }
    public void assessTowerLevel(Tower towerToAssess){
        if (towerToAssess.getTowerResourceAmount() > (6*towerToAssess.getTowerLevel())){ //  if res amt above given level threshold,
            towerToAssess.setTowerReloadSpeed(towerToAssess.getTowerReloadSpeed() - ((towerToAssess.getTowerLevel()-1)*2));
            towerToAssess.setTowerLevel(towerToAssess.getTowerLevel()+1);
            // set reload speed to level specified
            System.out.println("Tower Stats Assessed, leveled up");
        }
    }
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
