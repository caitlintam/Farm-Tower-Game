package seng201.team0;

import seng201.team0.models.Tower;

import java.util.List;

public class TowerManager {
    private String towerName;
    private List<Tower> towerList;
    public void setTowerList(List<Tower> towerList){
        this.towerList = towerList;
    }
    public void setTowerName(String towerName){
        this.towerName = towerName;
    }

    public TowerManager(){
        towerList.addAll(List.of(new Tower("Piglet Palace", 25, "Pigs", 15, 2, 45.00),
                new Tower("Cowtopia Castle", 10, "Cows", 30, 1,25.00),
                new Tower("Haybale Haven", 50, "Hay", 10, 1, 10.00),
                new Tower("Timber Turret", 20, "Timber", 60, 2, 50.00)));
    }

    public List<Tower> getTowerList(){
        return towerList;
    }
}
