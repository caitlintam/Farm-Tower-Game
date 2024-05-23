package seng201.team0.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.PlayerManager;
import seng201.team0.models.Player;

/**
 * controller for the round results screen
 */
public class RoundResultsController {
    private final PlayerManager playerManager;
    @FXML
    public Label roundResultTitleLabel;
    @FXML
    private Label distanceTravelledLabel;
    @FXML
    private Label cartsFilledLabel;
    @FXML
    private Label MoneyEarnedLabel;
    @FXML
    private  Label roundSuccessLabel;
    private final Player player;
    /**
     * Initializes the round results screen with the appropriate data for labels
     */
    public void initialize(){
        System.out.println(playerManager.getRoundSuccess());
        if (playerManager.getRoundSuccess()){
            roundResultTitleLabel.setText("You Won This Round!");
            distanceTravelledLabel.setText("Distance Travelled: " + playerManager.getCurrentTrackDistance());
            roundSuccessLabel.setText("Round: " + (playerManager.getCurrentRoundNumber()+1));
            cartsFilledLabel.setText("Carts Filled: " + playerManager.getNumCartsFilled() + " / " + player.getTowersInGame().size());
            MoneyEarnedLabel.setText("Money Earned: $ " + playerManager.getEarnedMoney());
        }else{
            roundResultTitleLabel.setText("You Lost This Round");
            distanceTravelledLabel.setText("Distance Travelled: " + playerManager.getCurrentTrackDistance());
            roundSuccessLabel.setText("Round: " + (playerManager.getCurrentRoundNumber()+1));
            cartsFilledLabel.setText("Carts Filled: " + playerManager.getNumCartsFilled() + " / " + player.getTowersInGame().size());
            MoneyEarnedLabel.setText("Money Earned: $ 0.00");
        }
    }
    /**
     * Constructs a RoundResultsController with the given PlayerManager
     * @param playerManager the PlayerManager
     */
    public RoundResultsController(PlayerManager playerManager) {
        this.playerManager = playerManager;
        this.player = playerManager.getPlayer();
    }
    /**
     * Handles the event when the home button is clicked. Closes the screen and calls playerManagers next method.
     */
    public void onHomeButtonClicked() {
        playerManager.closeWonRoundScreen();
        playerManager.toHomeOrRandomEventOrGameFinish();
    }

    /**
     * Handles the event when the quit button is clicked. Exits the Window.
     */
    public void onQuitButtonClicked(){
        Platform.exit();
    }
}


