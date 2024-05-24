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
import java.util.Objects;

/**
 * Controller class for the Apply Upgrade Screen.
 * Manages the UI elements and interactions on the screen related to applying upgrades to towers.
 */
public class ApplyUpgradeScreenController {
    private final PlayerManager playerManager;
    private final Player player;
    @FXML
    public TableView<Tower> prepTowerTable;
    @FXML
    public TableView<Upgrade> prepUpgradeTable;
    @FXML
    public Button applyUpgradeTowerButton;
    @FXML
    public Button nextScreenButton;
    @FXML
    public Button toHomeButton;
    @FXML
    public TableColumn<Upgrade, String> prepUpgradeColumn;
    @FXML
    public TableColumn<Tower, String> prepTowerColumn;
    @FXML
    public Label errorNoTowerSelectedLabel;
    @FXML
    public Label errorNoUpgradeSelectedLabel;

    /**
     * Constructs a new instance of ApplyUpgradeScreenController with the specified PlayerManager.
     * This constructor initializes the ApplyUpgradeScreenController with the given PlayerManager instance.
     * @param playerManager The PlayerManager instance associated with the apply upgrade screen controller.
     */
    public ApplyUpgradeScreenController(PlayerManager playerManager){
        this.playerManager = playerManager;
        this.player = playerManager.getPlayer();
    }
    /**
     * Initializes the apply upgrade screen by initializing tables and hiding error labels.
     * This method calls the initializeTables method to initialize the tables displaying towers and upgrades,
     * and hides the error labels for no upgrade or tower selected initially.
     */
    public void initialize(){
        initializeTables();
        errorNoUpgradeSelectedLabel.setVisible(false);
        errorNoTowerSelectedLabel.setVisible(false);
    }
    /**
     * Initializes the tables displaying towers and upgrades.
     * This method populates the tower table with data of towers currently in game
     * and the upgrade table with data of upgrades available in the player's inventory.
     * It also binds the tower inventory data to table columns and sets the data to the tables.
     */
    private void initializeTables() {
        System.out.println();

        ArrayList<Upgrade> upgradeInventory = (ArrayList<Upgrade>) player.getUpgradeInventory();
        System.out.println(upgradeInventory);
        ObservableList<Tower> inGameTowersData = FXCollections.observableArrayList(player.getTowersInGame());
        ObservableList<Upgrade> upgradeData = FXCollections.observableArrayList(upgradeInventory);
        prepTowerColumn.setCellValueFactory(new PropertyValueFactory<>("towerName"));
        prepUpgradeColumn.setCellValueFactory(new PropertyValueFactory<>("upgradeName"));
        prepTowerTable.setItems(inGameTowersData);
        prepUpgradeTable.setItems(upgradeData);
    }
    /**
     * Handles the action when the "Apply Upgrade" button is clicked.
     * Checks for selected tower and upgrade, then applies the upgrade to the tower.
     *  After applying the upgrade, in PlayerManager class it removes the upgrade from the player's inventory
     *  and re-initialises the tables displaying towers and upgrades.
     */
    @FXML
    public void onApplyUpgradeButtonClicked() {
        System.out.println("Apply Upgrade Button Clicked");

        Upgrade selectedUpgrade = prepUpgradeTable.getSelectionModel().getSelectedItem();
        Tower selectedTower = prepTowerTable.getSelectionModel().getSelectedItem();
        if (selectedUpgrade == null | selectedTower == null) {
            if (selectedUpgrade == null && selectedTower == null){
                errorNoTowerSelectedLabel.setVisible(true);
                errorNoUpgradeSelectedLabel.setVisible(true);
                System.out.println("Error: No Upgrade or Tower Selected");
            } else if (selectedUpgrade == null){
                errorNoUpgradeSelectedLabel.setVisible(true);
                System.out.println("Error: No Upgrade Selected");
            } else{
                errorNoTowerSelectedLabel.setVisible(true);
                System.out.println("Error: No Tower Selected");
            }
        } else {
            errorNoTowerSelectedLabel.setVisible(false);
            errorNoUpgradeSelectedLabel.setVisible(false);
            playerManager.applyUpgrade(selectedTower,selectedUpgrade);

            initializeTables();
        }
    }

    /**
     * Handles the action when the "Home" button is clicked.
     * Closes the current screen and returns to the home screen.
     */
    @FXML
    public void onHomeButtonClicked() {
        System.out.println("Home Button Clicked");
        playerManager.closeApplyUpgradeScreen();
        playerManager.launchHomeScreen();
    }
    /**
     * Handles the event when the "Next" button is clicked.
     * This method sets the initial track distance in the PlayerManager,
     * prints the current track distance for debugging purposes,
     * closes the apply upgrade screen, and launches the choose round difficulty screen.
     */
    @FXML
    public void onNextClicked() {
        playerManager.setInitialTrackDistance();
        System.out.println("Current track dist" + playerManager.getCurrentTrackDistance());
        System.out.println("Next Button Clicked");
        playerManager.closeApplyUpgradeScreen();
        playerManager.launchChooseRoundDifficultyScreen();
    }
}



