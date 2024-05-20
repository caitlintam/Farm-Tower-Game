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
    public Label errorInventoryFullLabel;
    public Label itemBoughtLabel;
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
        errorInventoryFullLabel.setVisible(false);
        errorNoMoneyLabel.setVisible(false);
        List<Button> buyUpgradeButtons = List.of(upgradeButton1, upgradeButton2, upgradeButton3);
        List<Button> buyTowerButtons = List.of(buyTowerButton1, buyTowerButton2, buyTowerbutton3);
        List<Button> allButtons = new ArrayList<>();
        allButtons.addAll(buyTowerButtons);
        allButtons.addAll(buyUpgradeButtons);

        // makes list of new purchasable towers
        List<Tower> newPurchasableTowers = shopManager.generateNewPurchasableTowers();

        for (int i = 0; i < buyUpgradeButtons.size(); i++) {
            // change this to label purchasbel i //////////////////////
            buyUpgradeButtons.get(i).setText(upgradeManager.getUpgradeList().get(i).getUpgradeName());
            buyTowerButtons.get(i).setText(newPurchasableTowers.get(i).getTowerName());

            int finalI = i;
            buyTowerButtons.get(i).setOnAction(actionEvent -> {
                if (selectedButton != null) {
                    selectedButton.setStyle("");
                }
                updateTowerInfo(newPurchasableTowers.get(finalI));

                selectedTowerIndex = finalI ;
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
                errorInventoryFullLabel.setVisible(false);
                // Access TowerManager
                boolean canBuy = shopManager.tryBuyTower(selectedTowerIndex);
                //////////////////////////// do this all in shop manager ///////////
                // call new boolean method tryBuyTower();
                /// if can buy tower, change attributes, return true
                /// otherwise return false, change visible labels

                if (canBuy){
                    updateMoneyLabel();
                } else {
                    if (playerManager.getTowerInventory().size() ==10){
                        errorNoMoneyLabel.setVisible(false);
                        errorInventoryFullLabel.setVisible(true);
                        System.out.println("Your Tower Inventory is Full");
                    }else{
                        errorInventoryFullLabel.setVisible(false);
                        errorNoMoneyLabel.setVisible(true);
                        System.out.println("You don't have enough money");
                    }
                }

                // Perform operations with selected tower
            } else if (selectedButton == upgradeButton1 || selectedButton == upgradeButton2 || selectedButton == upgradeButton3) {
                errorNoMoneyLabel.setVisible(false);
                errorInventoryFullLabel.setVisible(false);
                //////////  Access UpgradeManager///////////////
                /// do same boolean method above, move code of attributes into manager, not fxml class
                boolean hasEnoughMoney = shopManager.tryBuyUpgrade(selectedUpgradeIndex);
                if (hasEnoughMoney){
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
