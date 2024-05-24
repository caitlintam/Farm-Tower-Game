package seng201.team0.gui;

import javafx.scene.control.Label;
import seng201.team0.PlayerManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team0.models.Player;
import seng201.team0.models.Shop;

import seng201.team0.models.Upgrade;
import seng201.team0.models.Tower;

import java.util.List;

public class ShopController {
    @FXML
    public Button upgradeButton3;
    @FXML
    public Button upgradeButton2;
    @FXML
    public Button upgradeButton1;
    @FXML
    public Button buyTowerButton3;
    @FXML
    public Button buyTowerButton2;
    @FXML
    public Button buyTowerButton1;
    @FXML
    public Label errorNoMoneyLabel;
    @FXML
    public Label errorInventoryFullLabel;
    @FXML
    public Label itemBoughtLabel;
    @FXML
    public Button buyButton;
    @FXML
    public Button shopHomeButton;
    @FXML
    private Label shopMoneyLabel;
    @FXML
    private Label shopNameLabel;
    @FXML
    private Label shopCostLabel;
    @FXML
    private Label shopInfoLabel;
    @FXML
    private Label loadLabel;
    @FXML
    private Label speedLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Button selectedButton = null;
    private final PlayerManager playerManager;
    private final Player player;
    private final Upgrade upgrade;
    private final Shop shop;
    private int selectedUpgradeIndex = -1;
    private int selectedTowerIndex = -1;

    /**
     * Constructs a new ShopController.
     * @param playerManager  The PlayerManager instance responsible for managing player-related operations.
     *
     */
    public ShopController(PlayerManager playerManager){
        this.playerManager = playerManager;
        this.upgrade = playerManager.getUpgradeManager();
        this.shop = playerManager.getShopManager();
        this.player = playerManager.getPlayer();
    }
    /**
     * Initializes the shop interface by updating the money label, hiding certain labels, and setting up event handlers
     * for buy buttons associated with upgrades and towers.
     * The method populates the buy buttons with the names of upgrades and towers available for purchase,
     * sets up event handlers to update information labels when a button is clicked, and highlights the selected button.
     */
    @FXML
    public void initialize() {
        updateMoneyLabel();
        itemBoughtLabel.setVisible(false);
        errorInventoryFullLabel.setVisible(false);
        errorNoMoneyLabel.setVisible(false);
        List<Button> buyUpgradeButtons = List.of(upgradeButton1, upgradeButton2, upgradeButton3);
        List<Button> buyTowerButtons = List.of(buyTowerButton1, buyTowerButton2, buyTowerButton3);
        List<Tower> newPurchasableTowers = shop.generateNewPurchasableTowers();

        for (int i = 0; i < buyUpgradeButtons.size(); i++) {
            buyUpgradeButtons.get(i).setText(upgrade.getUpgradeList().get(i).getUpgradeName());
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
                updateUpgradeInfo(upgrade.getUpgradeList().get(finalI));
                selectedUpgradeIndex = finalI;
                selectedButton = buyUpgradeButtons.get(finalI);
                selectedButton.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
            });
        }
    }
    /**
     * Handles the event when the buy button is clicked.
     * This method checks if a button is selected, and then performs appropriate actions based on the selected button.
     * If a tower buy button is clicked, it attempts to buy the tower using the TowerManager.
     * If an upgrade buy button is clicked, it attempts to buy the upgrade using the Upgrade.
     * Depending on the success or failure of the purchase, it updates visibility of labels and the money label accordingly.
     */
    @FXML
    private void onBuyButtonClicked() {
        System.out.println("Buy Button Clicked");
        if (selectedButton != null) {
            if (selectedButton == buyTowerButton1 || selectedButton == buyTowerButton2 || selectedButton == buyTowerButton3) {
                errorNoMoneyLabel.setVisible(false);
                errorInventoryFullLabel.setVisible(false);
                boolean canBuy = shop.tryBuyTower(selectedTowerIndex);
                if (canBuy){
                    itemBoughtLabel.setText("Tower Bought! Added to Inventory as " + player.getTowerInventory().getLast() + " status");
                    itemBoughtLabel.setVisible(true);
                    updateMoneyLabel();
                } else {
                    if (player.getTowerInventory().size() ==10){
                        itemBoughtLabel.setVisible(false);
                        errorNoMoneyLabel.setVisible(false);
                        errorInventoryFullLabel.setVisible(true);
                        System.out.println("Your Tower Inventory is Full");
                    }else{
                        itemBoughtLabel.setVisible(false);
                        errorInventoryFullLabel.setVisible(false);
                        errorNoMoneyLabel.setVisible(true);
                        System.out.println("You don't have enough money");
                    }
                }
            } else if (selectedButton == upgradeButton1 || selectedButton == upgradeButton2 || selectedButton == upgradeButton3) {
                errorNoMoneyLabel.setVisible(false);
                errorInventoryFullLabel.setVisible(false);
                boolean hasEnoughMoney = shop.tryBuyUpgrade(selectedUpgradeIndex);
                if (hasEnoughMoney){
                    itemBoughtLabel.setText("Upgrade Bought! Added to Inventory");
                    itemBoughtLabel.setVisible(true);
                    updateMoneyLabel();
                } else {
                    itemBoughtLabel.setVisible(false);
                    errorNoMoneyLabel.setVisible(true);
                    System.out.println("You don't have enough money");
                }
            }
        }
    }
    /**
     * Handles the event when the home button in the shop screen is clicked.
     * Closes the current shop screen and launches the home screen.
     */
    @FXML
    private void onShopHomeButtonClicked() {
        System.out.println("Home Button Clicked");
        playerManager.closeShopScreen();
        playerManager.launchHomeScreen();
    }
    /**
     * Updates the information displayed in the shop UI based on the selected tower.
     * Sets the name, cost, level, load, reload speed, and type of the tower.
     * @param tower The tower whose information is to be displayed.
     */
    @FXML
    private void updateTowerInfo(Tower tower) {
        shopNameLabel.setText("Name: " + tower.getTowerName());
        shopCostLabel.setText("Cost: $" + tower.getTowerCost());
        shopInfoLabel.setText("Level: " + tower.getTowerLevel());
        loadLabel.setText("Load: " + tower.getTowerResourceAmount());
        speedLabel.setText("Reload Speed: " + tower.getTowerReloadSpeed());
        typeLabel.setText("Type: " + tower.getTowerResourceType());
    }
    /**
     * Updates the money label in the shop UI with the current amount of money owned by the player.
     */
    @FXML
    private void updateMoneyLabel(){
        shopMoneyLabel.setText("Money: $"+ playerManager.getMoney());
    }
    /**
     * Updates the information displayed in the shop UI based on the selected upgrade.
     * Sets the name and cost of the upgrade.
     * @param upgrade The upgrade whose information is to be displayed.
     */
    @FXML
    private void updateUpgradeInfo(Upgrade upgrade) {
        shopNameLabel.setText("Upgrade: " + upgrade.getUpgradeName());
        shopCostLabel.setText("Cost: $" + upgrade.getUpgradeCost());
        shopInfoLabel.setText("");
        loadLabel.setText("");
        speedLabel.setText("");
        typeLabel.setText("");
    }
}
