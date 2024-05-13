package seng201.team0.gui;

import javafx.scene.control.TableView;
import seng201.team0.PlayerManager;
import seng201.team0.TowerManager;
import seng201.team0.UpgradeManager;
import seng201.team0.models.Tower;

public class InventoryController {
    private final TowerManager towerManager;
    private final UpgradeManager upgradeManager;
    public TableView towerTable;
    public TableView upgradeTable;

    private TowerManager myTowers;
    // method for tableview. On selected, set selected tower to ..

    public InventoryController(TowerManager towerManager, UpgradeManager upgradeManager){
        this.towerManager = towerManager;
        this.upgradeManager = upgradeManager;

    }

}
