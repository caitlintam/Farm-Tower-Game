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
import seng201.team0.UpgradeManager;
import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InventoryController {
    private final PlayerManager playerManager;
    //private final UpgradeManager upgradeManager;
    @FXML
    public TableView<Tower> towerTable;
    @FXML
    public TableView<Upgrade> upgradeTable;
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
    public Button sellSelectedUpgradeButton;
    //public Label errornoUpgradeSelectedLabel;
    public Label errorNoUpgradeSelectedLabel;
    public TableColumn<Upgrade,Double> upgradeCostColumn;
    //public TableColumn<Upgrade, String> upgradeDescriptionColumn;
    public TableColumn<Upgrade,String> upgradeNameColumn;


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
        errorNoUpgradeSelectedLabel.setVisible(false);
        initializeUpgradeTable();

    }

    private void initializeTowerTable() {
        ArrayList<Tower> towerInventory = (ArrayList<Tower>) playerManager.getTowerInventory();
        System.out.println("here" + towerInventory.toString());

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
    private void onSellSelectedTowerButtonClicked(){
        System.out.println("Sell Tower Button Clicked");
        // get selected tower, remove from tableview, remove cost
    Tower selectedTower = towerTable.getSelectionModel().getSelectedItem();
    if (selectedTower == null){
        errorNoTowerSelectedLabel.setVisible(true);
    }else{
        errorNoTowerSelectedLabel.setVisible(false);
        // remove from towerInventory
        System.out.println("Tower Inventory before" + playerManager.getTowerInventory().size());
        playerManager.removeTowerFromInventory(selectedTower);
        System.out.println("Tower removed from Inventory :" + playerManager.getTowerInventory().size());
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
        List<Tower> towerInventory = playerManager.getTowerInventory();
        // use of streams, to filter by status = In-Game
        long countInGame = towerInventory.stream()
                .filter(tower -> tower.getTowerStatus().equals("In-Game"))
                .count();
        if (selectedTower == null){
            errorNoTowerSelectedLabel.setVisible(true);
        }else {
            errorNoTowerSelectedLabel.setVisible(false);
            if (Objects.equals(selectedTower.getTowerStatus(), "Reserve") && countInGame >= 5) {
                errorChangeStatusLabel.setVisible(true);
                System.out.println("Cannot Change Status");
            } else {
                errorChangeStatusLabel.setVisible(false);
                selectedTower.updateTowerStatus(selectedTower);
                System.out.println(selectedTower.getTowerName() + " status changed to " + selectedTower.getTowerStatus());
                playerManager.setTowersInGame();
                //refresh tower table
                clearTowerTable();
                initializeTowerTable();
            }
        }
    }
    private void initializeUpgradeTable() {
        ArrayList<Upgrade> upgradeInventory = (ArrayList<Upgrade>) playerManager.getUpgradeInventory();
        System.out.println(upgradeInventory);

        // Convert ArrayList to ObservableList
        ObservableList<Upgrade> upgradeData = FXCollections.observableArrayList(upgradeInventory);

        upgradeNameColumn.setCellValueFactory(new PropertyValueFactory<Upgrade, String>("upgradeName"));
        upgradeCostColumn.setCellValueFactory(new PropertyValueFactory<Upgrade, Double>("upgradeCost"));
        upgradeTable.setItems(upgradeData);
    }
    @FXML
    private void onSellSelectedUpgradeButtonClicked() {
        System.out.println("Sell Upgrade Button Clicked");
        // get selected tower, remove from tableview, remove cost
        Upgrade selectedUpgrade = upgradeTable.getSelectionModel().getSelectedItem();
        if (selectedUpgrade == null) {
            errorNoUpgradeSelectedLabel.setVisible(true);
        } else {
            errorNoUpgradeSelectedLabel.setVisible(false);
            System.out.println("Upgrade Inventory before" + playerManager.getUpgradeInventory().size());
            playerManager.removeUpgradeFromInventory(selectedUpgrade);
            System.out.println("Upgrade removed from Inventory :" + playerManager.getUpgradeInventory().size());
            initializeUpgradeTable();
            double cost = selectedUpgrade.getUpgradeCost();
            playerManager.setMoney(playerManager.getMoney() + cost);
            updateMoneyLabel();
        }
    }

    public void clearTowerTable(){
        ObservableList<Tower> items = towerTable.getItems();

    // Clear the items list
        items.clear();
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
