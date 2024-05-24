package seng201.team0.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.PlayerManager;
import seng201.team0.models.Player;

/**
 * controller for the round results screen
 */
public class RoundResultsController {
    @FXML
    public Label roundResultTitleLabel;
    @FXML
    public Button toHomeButton;
    @FXML
    public Button quitButton;
    @FXML
    private Label distanceTravelledLabel;
    @FXML
    private Label cartsFilledLabel;
    @FXML
    private Label moneyEarnedLabel;
    @FXML
    private  Label roundSuccessLabel;
    private final PlayerManager playerManager;
    private final Player player;
    /**
     * Constructs a RoundResultsController with the given PlayerManager
     * @param playerManager the PlayerManager
     */
    public RoundResultsController(PlayerManager playerManager) {
        this.playerManager = playerManager;
        this.player = playerManager.getPlayer();
    }
    /**
     * Initializes the round results screen with the appropriate data for labels
     */
    @FXML
    public void initialize(){
        System.out.println(playerManager.getRoundSuccess());
        if (playerManager.getRoundSuccess()){
            roundResultTitleLabel.setText("You Won This Round!");
            distanceTravelledLabel.setText("Distance Travelled: " + playerManager.getCurrentTrackDistance());
            roundSuccessLabel.setText("Round: " + (playerManager.getCurrentRoundNumber()+1));
            cartsFilledLabel.setText("Carts Filled: " + playerManager.getNumCartsFilled() + " / " + player.getTowersInGame().size());
            moneyEarnedLabel.setText("Money Earned: $ " + playerManager.getEarnedMoney());
        }else{
            roundResultTitleLabel.setText("You Lost This Round");
            distanceTravelledLabel.setText("Distance Travelled: " + playerManager.getCurrentTrackDistance());
            roundSuccessLabel.setText("Round: " + (playerManager.getCurrentRoundNumber()+1));
            cartsFilledLabel.setText("Carts Filled: " + playerManager.getNumCartsFilled() + " / " + player.getTowersInGame().size());
            moneyEarnedLabel.setText("Money Earned: $ 0.00");
        }
    }

    /**
     * Handles the event when the home button is clicked. Closes the screen and calls playerManagers next method.
     */
    @FXML
    public void onHomeButtonClicked() {
        playerManager.closeWonRoundScreen();
        playerManager.toHomeOrRandomEventOrGameFinish();
    }
    /**
     * Handles the event when the quit button is clicked. Exits the Window.
     */
    @FXML
    public void onQuitButtonClicked(){
        Platform.exit();
    }
}


