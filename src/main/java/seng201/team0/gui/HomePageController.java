package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.PlayerManager;
import seng201.team0.models.Tower;
import javafx.stage.Stage;

import java.util.List;

public class HomePageController {
    public Label errorNoTowersInGameLabel;
    private PlayerManager playerManager;
    @FXML
    private Label playerNameLabel;

    @FXML
    private Label homepageMoney;
    @FXML
    private Label homepageCurrentRoundLabel;
    @FXML
    private Label remainingRoundsLabel;

    @FXML
    private Button goToShopButton;
    @FXML
    private Button PlayRoundButton;
    @FXML
    private Button viewInventoryButton;


    public void initialize() {
        errorNoTowersInGameLabel.setVisible(false);
        playerNameLabel.setText("Hello: " + playerManager.getName());
        homepageMoney.setText("Money: $ " + playerManager.getMoney()); // need to find a way get money from player class
        homepageCurrentRoundLabel.setText("Round: " + playerManager.getCurrentNumRounds() + " / " + playerManager.getNumGameRounds()); // need to get current round from player class


    }

    public HomePageController(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public void onGoToShopButtonClicked() {
        // link to shop screen
        System.out.println("Shop clicked");
        playerManager.closeMainScreen();
        playerManager.launchShopScreen();
    }

    public void onPlayRoundButtonClicked() {
        // link to round screen
        // filter list of in game towers
        List<Tower> towersInGame = playerManager.getTowerInventory().stream()
                .filter(tower -> tower.getTowerStatus().equals("In-Game"))
                .toList();
        if (towersInGame.size() < 3){
            errorNoTowersInGameLabel.setVisible(true);
            System.out.println("Error: Must have 3 towers in-game"); // relabel this better
        }else{
            errorNoTowersInGameLabel.setVisible(false);
            System.out.println("Play Round Button Clicked");
            playerManager.closeMainScreen();
            playerManager.launchApplyUpgradeScreen();
        }

    }

    public void onViewInventoryButtonClicked() {
        System.out.println("Inventory Clicked");
        playerManager.closeMainScreen();
        playerManager.launchInventoryScreen();

        // link to inventory screen
    }

}