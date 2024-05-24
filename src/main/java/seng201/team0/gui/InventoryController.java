package seng201.team0.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import seng201.team0.PlayerManager;
import seng201.team0.models.Upgrade;
import seng201.team0.models.Player;
import seng201.team0.models.Tower;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Controller class for the Inventory Screen.
 * Manages the UI elements and interactions related to buying, selling and editing the inventory.
 */
public class InventoryController {
    @FXML
    public TableView<Tower> towerTable;
    @FXML
    public TableView<Upgrade> upgradeTable;
    @FXML
    public TableColumn<Tower, String> towerNameColumn;
    @FXML
    public TableColumn<Tower, String> towerResTypeColumn;
    @FXML
    public TableColumn<Tower, Integer> towerResAmountColumn;
    @FXML
    public TableColumn<Tower, Integer> towerReloadColumn;
    @FXML
    public TableColumn<Tower, Integer> towerLevelColumn;
    @FXML
    public TableColumn<Tower, Integer> towerCostColumn;
    @FXML
    public Button inventoryHomeButton;
    @FXML
    public TableColumn<Tower, String> towerStatusColumn;
    @FXML
    public Button sellSelectedTowerButton;
    @FXML
    public Label moneyLabel;
    @FXML
    public Button changeTowerStatusButton;
    @FXML
    public Label errorChangeStatusLabel;
    @FXML
    public Label errorNoTowerSelectedLabel;
    @FXML
    public Button sellSelectedUpgradeButton;
    @FXML
    public Label errorNoUpgradeSelectedLabel;
    @FXML
    public TableColumn<Upgrade,Double> upgradeCostColumn;
    @FXML
    public TableColumn<Upgrade,String> upgradeNameColumn;
    private final PlayerManager playerManager;
    private final Player player;

    /**
     * Constructs new instance of Inventory Controller class with Playermanager
     * @param playerManager the PlayerManager instance assoicated with inventory
     */
    public InventoryController(PlayerManager playerManager) {
        this.playerManager = playerManager;
        this.player = playerManager.getPlayer();
    }
    /**
     * Initializes the controller,
     * populates the inventory tables
     * updates the players money
     * sets error checking labels to not visible
     */
    @FXML
    public void initialize() {
        updateMoneyLabel();
        initializeTowerTable();
        errorChangeStatusLabel.setVisible(false);
        errorNoTowerSelectedLabel.setVisible(false);
        errorNoUpgradeSelectedLabel.setVisible(false);
        initializeUpgradeTable();
    }
    /**
     * Initializes the tower inventory table with data.
     * Retrieves tower inventory data from the PlayerManager, converts it into ObservableList,
     * and binds the data to table columns. Finally, sets the tower inventory data to the table.
     */
    @FXML
    private void initializeTowerTable() {
        ArrayList<Tower> towerInventory = (ArrayList<Tower>) player.getTowerInventory();
        ObservableList<Tower> towerData = FXCollections.observableArrayList(towerInventory);
        towerNameColumn.setCellValueFactory(new PropertyValueFactory<>("towerName"));
        towerResTypeColumn.setCellValueFactory(new PropertyValueFactory<>("towerResourceType"));
        towerResAmountColumn.setCellValueFactory(new PropertyValueFactory<>("towerResourceAmount"));
        towerReloadColumn.setCellValueFactory(new PropertyValueFactory<>("towerReloadSpeed"));
        towerLevelColumn.setCellValueFactory(new PropertyValueFactory<>("towerLevel"));
        towerCostColumn.setCellValueFactory(new PropertyValueFactory<>("towerCost"));
        towerStatusColumn.setCellValueFactory(new PropertyValueFactory<>("towerStatus"));
        towerTable.setItems(towerData);
    }
    /**
     * Handles the action when the "Sell Selected Tower" button is clicked.
     * Removes the selected tower from the tower inventory, updates the tower table view,
     * adds the tower's cost back to the player's money, and updates the money label in the UI.
     * If no tower is selected, displays an error message.
     */
    @FXML
    private void onSellSelectedTowerButtonClicked(){
        System.out.println("Sell Tower Button Clicked");
        Tower selectedTower = towerTable.getSelectionModel().getSelectedItem();
        if (selectedTower == null){
            errorNoTowerSelectedLabel.setVisible(true);
        }else{
            errorNoTowerSelectedLabel.setVisible(false);
            System.out.println("Tower Inventory before" + playerManager.getTowerInventory().size());
            player.removeTowerFromInventory(selectedTower);
            System.out.println("Tower removed from Inventory :" + playerManager.getTowerInventory().size());
            initializeTowerTable();
            double cost = selectedTower.getTowerCost();
            playerManager.setMoney(playerManager.getMoney() + cost);
            updateMoneyLabel();
        }
    }
    /**
     * Handles the action when the "Change Tower Status" button is clicked.
     * Changes the status of the selected tower, updates the tower table view, and refreshes the tower table.
     * If no tower is selected, displays an error message.
     * If the selected tower's status is "Reserve" and there are already 5 towers in-game, displays an error message.
     * Uses streams to filter the tower inventory list to get in-game towers
     */
    @FXML
    public void onChangeTowerStatusButtonClicked() {
        System.out.println("Change Tower Status Clicked");
        Tower selectedTower = towerTable.getSelectionModel().getSelectedItem();
        List<Tower> towerInventory = playerManager.getTowerInventory();
        long countInGame = towerInventory.stream()
                .filter(tower -> tower.getTowerStatus().equals("In-Game"))
                .count();
        if (selectedTower == null){
            errorNoTowerSelectedLabel.setVisible(true);
        }else {
            errorNoTowerSelectedLabel.setVisible(false);
            if (Objects.equals(selectedTower.getTowerStatus(), "Reserve") && countInGame >= 5) {
                errorChangeStatusLabel.setVisible(true);
            } else {
                errorChangeStatusLabel.setVisible(false);
                selectedTower.updateTowerStatus(selectedTower);
                System.out.println(selectedTower.getTowerName() + " status changed to " + selectedTower.getTowerStatus());
                player.setTowersInGame();
                clearTowerTable();
                initializeTowerTable();
            }
        }
    }
    /**
     * Initializes the upgrade inventory table with data.
     * Retrieves upgrade inventory data from the PlayerManager, converts it into ObservableList,
     * and binds the data to table columns. Finally, sets the upgrade inventory data to the table.
     */
    @FXML
    private void initializeUpgradeTable() {
        ArrayList<Upgrade> upgradeInventory = (ArrayList<Upgrade>) player.getUpgradeInventory();
        ObservableList<Upgrade> upgradeData = FXCollections.observableArrayList(upgradeInventory);
        upgradeNameColumn.setCellValueFactory(new PropertyValueFactory<>("upgradeName"));
        upgradeCostColumn.setCellValueFactory(new PropertyValueFactory<>("upgradeCost"));
        upgradeTable.setItems(upgradeData);
    }
    /**
     * Handles the action when the "Sell Selected Upgrade" button is clicked.
     * Removes the selected upgrade from the upgrade inventory, updates the upgrade table view,
     * adds the upgrade's cost back to the player's money, and updates the money label in the UI.
     * If no upgrade is selected, displays an error message.
     */
    @FXML
    private void onSellSelectedUpgradeButtonClicked() {
        System.out.println("Sell Upgrade Button Clicked");
        Upgrade selectedUpgrade = upgradeTable.getSelectionModel().getSelectedItem();
        if (selectedUpgrade == null) {
            errorNoUpgradeSelectedLabel.setVisible(true);
        } else {
            errorNoUpgradeSelectedLabel.setVisible(false);
            System.out.println("Upgrade Inventory before" + playerManager.getUpgradeInventory().size());
            player.removeUpgradeFromInventory(selectedUpgrade);
            System.out.println("Upgrade removed from Inventory :" + playerManager.getUpgradeInventory().size());
            initializeUpgradeTable();
            double cost = selectedUpgrade.getUpgradeCost();
            playerManager.setMoney(playerManager.getMoney() + cost);
            updateMoneyLabel();
        }
    }
    /**
     * Clears the tower table by removing all items from it.
     * This method removes all items from the ObservableList associated with the tower table.
     */
    @FXML
    public void clearTowerTable(){
        ObservableList<Tower> items = towerTable.getItems();
        items.clear();
    }
    /**
     * Updates the money label in the UI with the current amount of money the player has.
     * This method retrieves the current amount of money from the PlayerManager and sets it as the text of the money label.
     */
    @FXML
    public void updateMoneyLabel(){
        moneyLabel.setText("Money: $"+playerManager.getMoney());
    }
    /** Handles the action of "Home" button being clicked
     * closes the inventory screen and launches the home page screen*/
    @FXML
    private void onInventoryHomeButtonClicked() {
        System.out.println("Home Button Clicked");
        playerManager.closeInventoryScreen();
        playerManager.launchHomeScreen();
    }



}
