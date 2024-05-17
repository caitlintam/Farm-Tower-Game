package seng201.team0.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import seng201.team0.PlayerManager;
import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ApplyUpgradeScreenController {
    private final PlayerManager playerManager;
    public TableView<Tower> prepTowerTable;
    public TableView<Upgrade> prepUpgradeTable;
    public Button applyUpgradeTowerButton;
    public Button nextScreenButton;
    public Button toHomeButton;
    public TableColumn<Upgrade, String> prepUpgradeColumn;
    public TableColumn<Tower, String> prepTowerColumn;
    public Label errorNoTowerSelectedLabel;
    public Label errorNoUpgradeSelectedLabel;

    public ApplyUpgradeScreenController(PlayerManager playerManager){this.playerManager = playerManager;}
    public void initialize(){
        initializeTables();
        errorNoUpgradeSelectedLabel.setVisible(false);
        errorNoTowerSelectedLabel.setVisible(false);
    }

    private void initializeTables() {
        // only load towers in game!
//        gameList<Tower> towersInGame = playerManager.getTowerInventory().stream()
//                .filter(tower -> tower.getTowerStatus().equals("In-Game"))
//                .toList();

        System.out.println();

        ArrayList<Upgrade> upgradeInventory = (ArrayList<Upgrade>) playerManager.getUpgradeInventory();
        System.out.println(upgradeInventory);

        // Convert ArrayList to ObservableList
        ObservableList<Tower> inGameTowersData = FXCollections.observableArrayList(towersInGame);
        ObservableList<Upgrade> upgradeData = FXCollections.observableArrayList(upgradeInventory);
// Bind tower inventory data to table columns
        prepTowerColumn.setCellValueFactory(new PropertyValueFactory<Tower, String>("towerName"));
        prepUpgradeColumn.setCellValueFactory(new PropertyValueFactory<Upgrade, String>("upgradeName"));

        // Set tower inventory data to the table
        prepTowerTable.setItems(inGameTowersData);
        prepUpgradeTable.setItems(upgradeData);
    }

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
            // upgrade the tower level?
            applyUpgrade(selectedTower,selectedUpgrade);

        }
    }

    private void applyUpgrade(Tower selectedTower, Upgrade selectedUpgrade) {
        System.out.println(selectedTower.getTowerName() + " stats before upgrade: \n Reload Speed: "+ selectedTower.getTowerReloadSpeed());
        System.out.println("Level: "+ selectedTower.getTowerLevel() + "\nResource Amount: "+selectedTower.getTowerResourceAmount());
        if (Objects.equals(selectedUpgrade.getUpgradeName(), "Tower Reload Speed Boost!")){
            selectedTower.updateTowerReloadSpeed(selectedTower);
            System.out.println("New Reload Speed: "+selectedTower.getTowerReloadSpeed());
        }else if (Objects.equals(selectedUpgrade.getUpgradeName(), "Tower Resource Amount Boost!")){
            selectedTower.updateTowerResourceAmount(selectedTower);
            System.out.println("New Resource Amount: "+selectedTower.getTowerResourceAmount());
        } else{ // upgrade is a level upgrade
            selectedTower.updateTowerLevel(selectedTower);
            System.out.println("New Level: "+selectedTower.getTowerLevel());
        }
        //remove upgrade from upgrade Inventory
        playerManager.removeUpgradeFromInventory(selectedUpgrade);
        // reinitialise upgrade tbale
        initializeTables();
    }
    public void onHomeButtonClicked() {
        System.out.println("Home Button Clicked");
        playerManager.closeApplyUpgradeScreen();
        playerManager.launchHomeScreen();
    }
}



