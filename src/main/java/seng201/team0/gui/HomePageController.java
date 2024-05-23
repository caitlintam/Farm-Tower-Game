package seng201.team0.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.PlayerManager;
import seng201.team0.models.Tower;

import java.util.List;
/**
 * Controller class for the Home Page Screen.
 * Manages the UI elements and interactions on the screen displayed when navigating the homepage.
 */
public class HomePageController {
    @FXML
    public Label errorNoTowersInGameLabel;
    private PlayerManager playerManager;
    @FXML
    private Label playerNameLabel;
    @FXML
    private Label homepageMoney;
    @FXML
    private Label homepageCurrentRoundLabel;
    /**
     * Initializes the controller, setting up UI elements with appropriate values and handling interactions with the UI elements.
     */
    public void initialize() {
        System.out.println("---------- Home Page ---------");
        System.out.println("Current Money: " + playerManager.getMoney());
        System.out.println("Rounds Completed: " + playerManager.getCurrentRoundNumber() +" / " + playerManager.getNumGameRounds());
        errorNoTowersInGameLabel.setVisible(false);
        playerNameLabel.setText("Hello: " + playerManager.getName());
        homepageMoney.setText("Money: $ " + playerManager.getMoney()); // need to find a way get money from player class
        homepageCurrentRoundLabel.setText("Round: " + playerManager.getCurrentRoundNumber() + " / " + playerManager.getNumGameRounds()); // need to get current round from player class
    }
    /**
     * Constructor for HomePageController.
     * @param playerManager The PlayerManager instance associated with the game.
     */
    public HomePageController(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }
    /** handles the action when the "Shop" button is clicked
     * it closes the home screen and opens/launches the shop screen*/
    public void onGoToShopButtonClicked() {
        // link to shop screen
        System.out.println("Shop clicked");
        playerManager.closeMainScreen();
        playerManager.launchShopScreen();
    }
    /**handles the action when "Play round" is clicked
     * it filters the tower inventory list, creating a new list of towers with status "In-Game"
     * it handles the potential error of if there are less than 2 towers with status "In-Game"
     * and loads the round screen*/
    public void onPlayRoundButtonClicked() {
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
    /** handles the action when the "Inventory" button is clicked
     * it closes the home screen and opens/launches the Inventory screen*/
    public void onViewInventoryButtonClicked() {
        System.out.println("Inventory Clicked");
        playerManager.closeMainScreen();
        playerManager.launchInventoryScreen();

        // link to inventory screen
    }
    /** handles the action when the "Give up" button is clicked
     * exiting the platform*/
    public void onGiveUpClicked(ActionEvent actionEvent) {
        Platform.exit();
    }


}