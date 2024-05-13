package seng201.team0;

import seng201.team0.models.Player;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;

public class TowerManager {
    private String towerName;
    private List<Tower> towerList;
    private final List<Tower> defaultTowers = new ArrayList<>();


    public TowerManager(){
//        towerList.addAll(List.of(new Tower("Piglet Palace", 25, "Pigs", 15, 2, 45.00),
//                new Tower("Cowtopia Castle", 10, "Cows", 30, 1,25.00),
//                new Tower("Haybale Haven", 50, "Hay", 10, 1, 10.00),
//                new Tower("Timber Turret", 20, "Timber", 60, 2, 50.00)));

        defaultTowers.addAll(List.of(
                new Tower("Piglet Palace", 25, "Pigs", 15, 2, 45.00),
                new Tower("Cowtopia Castle", 10, "Cows", 30, 1,25.00),
                new Tower("Haybale Haven", 50, "Hay", 10, 1, 10.00),
                new Tower("Timber Turret", 20, "Timber", 60, 2, 50.00),
                new Tower("Tower 5", 15, "t5", 25, 9, 48.00),
                new Tower("Tower 6", 35, "t6", 45, 4, 40.00),
                new Tower("Tower 7", 35, "t6", 45, 4, 40.00),
                new Tower("Tower 8", 35, "t6", 45, 4, 40.00),
                new Tower("Tower 9", 35, "t6", 45, 4, 40.00)));

    }
    public void addTowerListToPlayer(PlayerManager playerManager){
        playerManager.addTowersToInventory(towerList); // adds tower list to inventory
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


}
