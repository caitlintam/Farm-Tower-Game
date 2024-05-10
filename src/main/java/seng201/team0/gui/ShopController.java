package seng201.team0.gui;

import javafx.scene.control.Label;
import seng201.team0.PlayerManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team0.TowerManager;
import seng201.team0.UpgradeManager;
import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;

import java.util.List;

public class ShopController {
    public Button upgradeButton3;
    public Button upgradeButton2;
    public Button upgradeButton1;
    public Button buyTowerbutton3;
    public Button buyTowerButton2;
    public Button buyTowerButton1;
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
    private PlayerManager playerManager;
    private TowerManager towerManager;
    private UpgradeManager upgradeManager;
    private int selectedUpgradeIndex = -1;
    private int selectedTowerIndex = -1;
    private Button selectedButton;


    public ShopController(PlayerManager playerManager, TowerManager towerManager, UpgradeManager upgradeManager){
        this.playerManager = playerManager;
        this.towerManager = towerManager;
        this.upgradeManager = upgradeManager;
    }
    public void initialize(){
        List<Button> buyUpgradeButtons = List.of(upgradeButton1,upgradeButton2,upgradeButton3);
        List<Button> buyTowerButtons = List.of(buyTowerButton1,buyTowerButton2,buyTowerbutton3);
        List<Button> allButtons = List.of(buyTowerButton1,buyTowerButton2,buyTowerbutton3,upgradeButton1,upgradeButton2,upgradeButton3);

        for (int i=0; i<buyUpgradeButtons.size(); i++){
            int finalI = i;
            buyTowerButtons.get(i).setOnAction(actionEvent ->{
                updateTowerInfo(towerManager.getDefaultTowers().get(finalI+6)); // +6 indexes to end of  default towerlist
                selectedTowerIndex = finalI+ 6;
                buyTowerButtons.forEach(button -> {
                    if (button != selectedButton){
                        selectedButton.setStyle("");
                        selectedButton = button;
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                    }
                });
            });
            buyUpgradeButtons.get(i).setOnAction(actionEvent -> {
                updateUpgradeInfo(upgradeManager.getUpgradeList().get(finalI));
                selectedUpgradeIndex = finalI;
                buyUpgradeButtons.forEach(button -> {
                    if (button != selectedButton){
                        selectedButton.setStyle("");
                        selectedButton = button;
                        selectedButton.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                    }
                });
            });
        }
    }
    @FXML
    private void onBuyButtonClicked() {
        // Handle Buy button click event
        // adds to inventory
        // decreases money
    }
    @FXML
    private void onShopHomeButtonClicked() {
        playerManager.closeShopScreen();
        playerManager.launchHomeScreen();
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
