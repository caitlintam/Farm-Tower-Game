package seng201.team0.gui;

import javafx.scene.control.Label;
import seng201.team0.PlayerManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team0.TowerManager;
import seng201.team0.UpgradeManager;
import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;

public class ShopController {
    @FXML
    private Label ShopMoneyLabel;
    @FXML
    private Label ShopNameLabel;
    @FXML
    private Label ShopCostLabel;
    @FXML
    private Label ShopInfoLabel;
    @FXML
    private Button ShopHomeButton;
    @FXML
    private Button BuyButton;
    @FXML
    private Button ShopTower1;
    @FXML
    private Button ShopTower2;
    @FXML
    private Button ShopTower3;
    @FXML
    private Button Upgrade1;
    @FXML
    private Button Upgrade2;
    @FXML
    private Button Upgrade3;
    private PlayerManager playerManager;
    private TowerManager towerManager;
    private UpgradeManager upgradeManager;

    public ShopController(PlayerManager playerManager, TowerManager towerManager, UpgradeManager upgradeManager){
        this.playerManager = playerManager;
        this.towerManager = towerManager;
        this.upgradeManager = upgradeManager;
    }
    private void onBuyButtonClicked() {
        // Handle Buy button click event
        // adds to inventory
        // decreases money
    }

    private void onShopTower1Clicked() {
        // Handle Tower 1 button click event
        Tower tower = towerManager.getTowerList().get(0);
        updateTowerInfo(tower);
    }

    private void onShopTower2Clicked() {
        // Handle Tower 2 button click event
        Tower tower = towerManager.getTowerList().get(1);
        updateTowerInfo(tower);
    }

    private void onShopTower3Clicked() {
        // Handle Tower 3 button click event
        Tower tower = towerManager.getTowerList().get(2);
        updateTowerInfo(tower);
    }

    private void onUpgrade1Clicked() {
        // Handle Upgrade 1 button click event
        Upgrade upgrade = upgradeManager.getUpgradeList().get(0);
        updateUpgradeInfo(upgrade);
    }

    private void onUpgrade2Clicked() {
        // Handle Upgrade 2 button click event
        Upgrade upgrade = upgradeManager.getUpgradeList().get(1);
        updateUpgradeInfo(upgrade);
    }

    private void onUpgrade3Clicked() {
        // Handle Upgrade 3 button click event
        Upgrade upgrade = upgradeManager.getUpgradeList().get(2);
        updateUpgradeInfo(upgrade);
    }
    private void onShopHomeButtonClicked() {
        // launch home screen
    }
    private void updateTowerInfo(Tower tower) {
        ShopNameLabel.setText("Name: " + tower.getTowerName());
        ShopCostLabel.setText("Cost: $" + tower.getTowerCost());
        ShopInfoLabel.setText("Info: \nLevel: " + tower.getTowerLevel() + "\nLoad " + tower.getTowerResourceAmount()
        + "\nSpeed: " + tower.getTowerReloadSpeed() + "\nType: " + tower.getTowerResourceType());
    }

    private void updateUpgradeInfo(Upgrade upgrade) {
        ShopNameLabel.setText("Upgrade: " + upgrade.getUpgradeName());
        ShopCostLabel.setText("Cost: $" + upgrade.getUpgradeCost());
        // You can similarly update other labels with upgrade information
    }
}
