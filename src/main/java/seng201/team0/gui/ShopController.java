package seng201.team0.gui;

import javafx.scene.control.Label;
import seng201.team0.PlayerManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team0.ShopManager;
import seng201.team0.TowerManager;
import seng201.team0.UpgradeManager;
import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;

import java.util.ArrayList;
import java.util.List;

public class ShopController {
    public Button upgradeButton3;
    public Button upgradeButton2;
    public Button upgradeButton1;
    public Button buyTowerbutton3;
    public Button buyTowerButton2;
    public Button buyTowerButton1;
    public Label noMoneyLabel;
    public Label errorNoMoneyLabel;
    @FXML
    private Label ShopMoneyLabel;
    @FXML
    private Label ShopNameLabel;
    @FXML
    private Label ShopCostLabel;
    @FXML
    private Label ShopInfoLabel;
    @FXML
    private Label LoadLabel;
    @FXML
    private Label SpeedLabel;
    @FXML
    private Label TypeLabel;
    @FXML
    private Button ShopHomeButton;
    @FXML
    private Button BuyButton;
    private PlayerManager playerManager;
    private TowerManager towerManager;
    private UpgradeManager upgradeManager;
    private int selectedUpgradeIndex = -1;
    private int selectedTowerIndex = -1;
    private Button selectedButton = null;
    private ShopManager shopManager;


    public ShopController(PlayerManager playerManager, TowerManager towerManager, UpgradeManager upgradeManager, ShopManager shopManager){
        this.playerManager = playerManager;
        this.towerManager = towerManager;
        this.upgradeManager = upgradeManager;
        this.shopManager = shopManager;
    }

    public void initialize() {
        updateMoneyLabel();
        errorNoMoneyLabel.setVisible(false);
        List<Button> buyUpgradeButtons = List.of(upgradeButton1, upgradeButton2, upgradeButton3);
        List<Button> buyTowerButtons = List.of(buyTowerButton1, buyTowerButton2, buyTowerbutton3);
        List<Button> allButtons = new ArrayList<>();
        allButtons.addAll(buyTowerButtons);
        allButtons.addAll(buyUpgradeButtons);
        shopManager.generateNewPurchasableTowers();
        // generate new purchasable towers/ upgrades call method
        // purchasable upgrades
// neeed to fix this. make new list for purchasable towers - should be the ones player doesnt click at beginning
        for (int i = 0; i < buyUpgradeButtons.size(); i++) {
            // change this to label purchasbel i
            buyUpgradeButtons.get(i).setText(upgradeManager.getUpgradeList().get(i).getUpgradeName());
            buyTowerButtons.get(i).setText(towerManager.getDefaultTowers().get(i+6).getTowerName());
            int finalI = i;
            buyTowerButtons.get(i).setOnAction(actionEvent -> {
                if (selectedButton != null) {
                    selectedButton.setStyle("");
                }
                updateTowerInfo(towerManager.getDefaultTowers().get(finalI + 6));
                selectedTowerIndex = finalI + 6;
                selectedButton = buyTowerButtons.get(finalI);
                selectedButton.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
            });
            buyUpgradeButtons.get(i).setOnAction(actionEvent -> {
                if (selectedButton != null) {
                    selectedButton.setStyle("");
                }
                updateUpgradeInfo(upgradeManager.getUpgradeList().get(finalI));
                selectedUpgradeIndex = finalI;
                selectedButton = buyUpgradeButtons.get(finalI);
                selectedButton.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
            });
        }
    }
    @FXML
    private void onBuyButtonClicked() {
        System.out.println("Buy Button Clicked");
        if (selectedButton != null) {
            // Check which manager to access based on the selected button's properties
            if (selectedButton == buyTowerButton1 || selectedButton == buyTowerButton2 || selectedButton == buyTowerbutton3) {
                errorNoMoneyLabel.setVisible(false);
                // Access TowerManager
                Tower selectedTower = towerManager.getDefaultTowers().get(selectedTowerIndex);
                double cost = selectedTower.getTowerCost();
                if (cost <= playerManager.getMoney()){
                    System.out.println(selectedTower.getTowerName() + " Bought");
                    playerManager.setMoney(playerManager.getMoney() - cost);
                    playerManager.addTowersToInventory(selectedTower);
                    updateMoneyLabel();
                } else {
                    errorNoMoneyLabel.setVisible(true);
                    System.out.println("You don't have enough money");
                }

                // Perform operations with selected tower
            } else if (selectedButton == upgradeButton1 || selectedButton == upgradeButton2 || selectedButton == upgradeButton3) {
                errorNoMoneyLabel.setVisible(false);
                // Access UpgradeManager
                Upgrade selectedUpgrade = upgradeManager.getUpgradeList().get(selectedUpgradeIndex);
                double cost = selectedUpgrade.getUpgradeCost();
                if (cost <= playerManager.getMoney()){
                    System.out.println(selectedUpgrade.getUpgradeName() + " Bought");
                    playerManager.setMoney(playerManager.getMoney() - cost);
                    playerManager.addUpgradesToInventory(selectedUpgrade);
                    updateMoneyLabel();
                } else {
                    errorNoMoneyLabel.setVisible(true);
                    System.out.println("You don't have enough money");
                }
                // Perform operations with selected upgrade
            }
        } else {
            // No button is selected
        }
    }
    @FXML
    private void onShopHomeButtonClicked() {
        System.out.println("Home Button Clicked");
        playerManager.closeShopScreen();
        playerManager.launchHomeScreen();
    }
    private void updateTowerInfo(Tower tower) {
        ShopNameLabel.setText("Name: " + tower.getTowerName());
        ShopCostLabel.setText("Cost: $" + tower.getTowerCost());
        ShopInfoLabel.setText("Level: " + tower.getTowerLevel());
        LoadLabel.setText("Load: " + tower.getTowerResourceAmount());
        SpeedLabel.setText("Load: " + tower.getTowerReloadSpeed());
        TypeLabel.setText("Type: " + tower.getTowerResourceType());
    }
    private void updateMoneyLabel(){
        ShopMoneyLabel.setText("Money: $"+ playerManager.getMoney());
    }
    private void updateUpgradeInfo(Upgrade upgrade) {
        ShopNameLabel.setText("Upgrade: " + upgrade.getUpgradeName());
        ShopCostLabel.setText("Cost: $" + upgrade.getUpgradeCost());
        ShopInfoLabel.setText("");
        LoadLabel.setText("");
        SpeedLabel.setText("");
        TypeLabel.setText("");
        // You can similarly update other labels with upgrade information
    }
}
