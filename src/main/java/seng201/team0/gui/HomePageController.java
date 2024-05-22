package seng201.team0.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.PlayerManager;
import seng201.team0.models.Tower;

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
        System.out.println("---------- Home Page ---------");
        System.out.println("Current Money: " + playerManager.getMoney());
        System.out.println("Rounds Completed: " + playerManager.getCurrentRoundNumber() +" / " + playerManager.getNumGameRounds());
        errorNoTowersInGameLabel.setVisible(false);
        playerNameLabel.setText("Hello: " + playerManager.getName());
        homepageMoney.setText("Money: $ " + playerManager.getMoney()); // need to find a way get money from player class
        homepageCurrentRoundLabel.setText("Round: " + playerManager.getCurrentRoundNumber() + " / " + playerManager.getNumGameRounds()); // need to get current round from player class
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
        System.out.println("Play Round Clicked!");
        List<Tower> towersInGame = playerManager.getTowerInventory().stream()
                .filter(tower -> tower.getTowerStatus().equals("In-Game"))
                .toList();
        if (towersInGame.size() < 3){
            errorNoTowersInGameLabel.setVisible(true);
            System.out.println("Error: Must have 3-5 towers in-game"); // relabel this better
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

    public void onGiveUpClicked(ActionEvent actionEvent) {
        Platform.exit();
    }


}