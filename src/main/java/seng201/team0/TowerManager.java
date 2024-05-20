package seng201.team0;

import seng201.team0.models.Player;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TowerManager {
    private double towerCost;
    private String towerName;
    private List<Tower> towerList;
    private final List<Tower> defaultTowers = new ArrayList<>();


    public TowerManager(){
//        towerList.addAll(List.of(new Tower("Piglet Palace", 25, "Pigs", 15, 2, 45.00),
//                new Tower("Cowtopia Castle", 10, "Cows", 30, 1,25.00),
//                new Tower("Haybale Haven", 50, "Hay", 10, 1, 10.00),
//                new Tower("Timber Turret", 20, "Timber", 60, 2, 50.00)));

        defaultTowers.addAll(List.of(
                new Tower("Piglet Palace", 10, "Pigs", 30, 1, 45.00, "Reserve"),
                new Tower("Cowtopia Castle", 8, "Cows", 25, 1,25.00, "Reserve"),
                new Tower("Haybale Haven", 12, "Hay", 27, 1, 10.00,"Reserve"),
                new Tower("Timber Turret", 7, "Timber", 24, 1, 50.00,"Reserve"),
                new Tower("Water Tower ", 6, "Water", 25, 1, 48.00,"Reserve"),
                new Tower("Steel Steeple", 11, "Steel", 32, 1, 40.00,"Reserve"),
                new Tower("Corn Castle", 7, "Corn", 29, 1, 20.00,"Reserve"),
                new Tower("Wheat Tower", 9, "Wheat", 28, 1, 30.00,"Reserve"),
                new Tower("Chicken Coop", 12, "Chickens", 27, 1, 40.00,"Reserve")));

    }
    public void addTowerListToPlayer(PlayerManager playerManager){
        //playerManager.addTowersToInventory(towerList); // adds tower list to inventory
    }
    public List<Tower> getTowerList(){
        return towerList;
    }
    public void setTowerList(List<Tower> towerList){
        this.towerList = towerList;
    }
    public String getTowerName(){
        return towerName;
    }
    public void setTowerName(String towerName){
        this.towerName = towerName;
    }
    public List<Tower> getDefaultTowers(){
        return defaultTowers;
    }
    public double getTowerCost(){
        return towerCost;
    }

    public void setTowerStatus(Tower tower) {
        if (towerList.contains(tower)){
            tower.setTowerStatus("In-Game");
        }else{
            tower.setTowerStatus("Reserve");
        }
    }


}
