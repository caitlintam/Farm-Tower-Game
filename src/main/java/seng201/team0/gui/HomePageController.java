package seng201.team0.gui;

import javafx.application.Platform;
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
    @FXML
    private Label playerNameLabel;
    @FXML
    private Label homepageMoney;
    @FXML
    private Label homepageCurrentRoundLabel;
    private final PlayerManager playerManager;
    /**
     * Initializes the home page UI with player information.
     * This method prints information about the player's current money, rounds completed, and sets labels accordingly.
     */
    public void initialize() {
        System.out.println("---------- Home Page ---------");
        System.out.println("Current Money: " + playerManager.getMoney());
        System.out.println("Rounds Completed: " + playerManager.getCurrentRoundNumber() +" / " + playerManager.getNumGameRounds());
        errorNoTowersInGameLabel.setVisible(false);
        playerNameLabel.setText("Hello: " + playerManager.getName());
        homepageMoney.setText("Money: $ " + playerManager.getMoney());
        homepageCurrentRoundLabel.setText("Round: " + playerManager.getCurrentRoundNumber() + " / " + playerManager.getNumGameRounds());
    }
    /**
     * Constructs a new instance of HomePageController with the specified PlayerManager.
     * This constructor initializes the HomePageController with the given PlayerManager instance.
     * @param playerManager The PlayerManager instance associated with the home page controller.
     */
    public HomePageController(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    /** handles the action when the "Shop" button is clicked
     * it closes the home screen and opens/launches the shop screen*/
    public void onGoToShopButtonClicked() {
        System.out.println("Shop Clicked");
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
    }
    /** handles the action when the "Give up" button is clicked
     * exiting the platform*/
    public void onGiveUpClicked() {
        Platform.exit();
    }
}