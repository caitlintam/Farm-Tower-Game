package seng201.team0.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import seng201.team0.PlayerManager;
import seng201.team0.models.Tower;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;

public class InventoryController {
    private final PlayerManager playerManager;
    @FXML
    public TableView<Tower> towerTable;
    @FXML
    public TableView upgradeTable;
    public TableColumn<Tower, String> towerNameColumn;
    public TableColumn<Tower, String> towerResTypeColumn;
    public TableColumn<Tower, Integer> towerResAmountColumn;
    public TableColumn<Tower, Integer> towerReloadColumn;
    public TableColumn<Tower, Integer> towerLevelColumn;
    public TableColumn<Tower, Integer> towerCostColumn;


    // public TableColumn<PlayerManager, String>
    //''private

    //private TowerManager myTowers;
    // method for tableview. On selected, set selected tower to ..
    InventoryController(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }
    public void initialize() {
        // Populate tower inventory table

        initializeTowerTable();

    }


    private void initializeTowerTable() {
        ArrayList<Tower> towerInventory = (ArrayList<Tower>) playerManager.getTowerInventory();
        System.out.println(towerInventory);

        // Convert ArrayList to ObservableList
        ObservableList<Tower> towerData = FXCollections.observableArrayList(towerInventory);
// Bind tower inventory data to table columns
        towerNameColumn.setCellValueFactory(new PropertyValueFactory<Tower, String>("towerName"));
        towerResTypeColumn.setCellValueFactory(new PropertyValueFactory<Tower, String>("towerResourceType"));
        towerResAmountColumn.setCellValueFactory(new PropertyValueFactory<Tower, Integer>("towerResourceAmount"));
        towerReloadColumn.setCellValueFactory(new PropertyValueFactory<Tower, Integer>("towerReloadSpeed"));
        towerLevelColumn.setCellValueFactory(new PropertyValueFactory<Tower, Integer>("towerLevel"));
        towerCostColumn.setCellValueFactory(new PropertyValueFactory<Tower, Integer>("towerCost"));

        // Set tower inventory data to the table
        towerTable.setItems(towerData);
    }

}
