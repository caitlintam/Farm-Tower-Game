package seng201.team0.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import seng201.team0.PlayerManager;
import seng201.team0.models.Tower;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public Button inventoryHomeButton;
    public TableColumn<Tower, String> towerStatusColumn;
    public Button sellSelectedTowerButton;
    public Label moneyLabel;
    public Button changeTowerStatusButton;
    public Label errorChangeStatusLabel;
    public Label errorNoTowerSelectedLabel;


    // public TableColumn<PlayerManager, String>
    //''private

    //private TowerManager myTowers;
    // method for tableview. On selected, set selected tower to ..
    InventoryController(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }
    public void initialize() {
        // Populate tower inventory table
        updateMoneyLabel();
        initializeTowerTable();
        errorChangeStatusLabel.setVisible(false);
        errorNoTowerSelectedLabel.setVisible(false);

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
        towerStatusColumn.setCellValueFactory(new PropertyValueFactory<Tower, String>("towerStatus"));

        // Set tower inventory data to the table
        towerTable.setItems(towerData);
    }

    @FXML
    private void onSellSelectedButtonClicked(){
        // get selected tower, remove from tableview, remove cost
    Tower selectedTower = towerTable.getSelectionModel().getSelectedItem();
    if (selectedTower == null){
        errorNoTowerSelectedLabel.setVisible(true);
    }else{
        errorNoTowerSelectedLabel.setVisible(false);
        // remove from towerInventory
        playerManager.removeTowerFromInventory(selectedTower);
        // update fxml - remove from tableview( initialise tower method)
        initializeTowerTable();
        // remove from cost of player
        double cost = selectedTower.getTowerCost();
        playerManager.setMoney(playerManager.getMoney() + cost);
        // update fxml money label
        updateMoneyLabel();
    }
    }

    @FXML
    public void onChangeTowerStatusButtonClicked() {
        System.out.println("Change Tower Status Clicked");
        Tower selectedTower = towerTable.getSelectionModel().getSelectedItem();
        // error cant change tower status: too many towers in game
        // only if trying to change reserve tower status to ingame but already 3 towers inventory in game

        List<Tower> towerInventory = playerManager.getTowerInventory();
        // use of streams, to filter by status = In-Game
        long countInGame = towerInventory.stream()
                .filter(tower -> tower.getTowerStatus().equals("In-Game"))
                .count();

        if (Objects.equals(selectedTower.getTowerStatus(), "Reserve") && countInGame >=3){
            errorChangeStatusLabel.setVisible(true);
            System.out.println("Cannot Change Status");
        } else{
            System.out.println(selectedTower.getTowerName() + "status changed to "+ selectedTower.getTowerStatus());
            selectedTower.updateTowerStatus();
            // FIX THIS< NOT CHANGING INVENOTRY
            initializeTowerTable();
        }
    }
    public void updateMoneyLabel(){
        moneyLabel.setText("Money: $"+playerManager.getMoney());
    }

    @FXML
    private void onInventoryHomeButtonClicked() {
        System.out.println("Home Button Clicked");
        playerManager.closeInventoryScreen();
        playerManager.launchHomeScreen();
    }

}
