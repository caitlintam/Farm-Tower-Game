package seng201.team0.models;

public class Upgrade {
    private String upgradeName;
    private double upgradeCost;
    public Upgrade(String upgradeName, double upgradeCost){
        this.upgradeName = upgradeName;
        this.upgradeCost = upgradeCost;

    }
    public String getUpgradeName(){
        return upgradeName;
    }
    public double getUpgradeCost(){
        return upgradeCost;
    }
}
