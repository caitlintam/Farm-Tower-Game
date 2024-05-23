package seng201.team0.gui;

import javafx.application.Platform;
import seng201.team0.PlayerManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
/**
 * Controller class for the Game Completion Screen.
 * Manages the UI elements and interactions on the screen displayed when the game is completed.
 */
public class GameCompletionScreenController {
    private final PlayerManager playerManager;
    @FXML
    public Label WinOrLOseLabel;
    @FXML
    private Label roundsWonLabel;
    @FXML
    private Label roundsLostLabel;

    /**
     * Initializes the controller, setting up UI elements with appropriate values and handling interactions with the UI elements.
     */
    public void initialize(){
        System.out.println(playerManager.getWinOrLose());
        roundsLostLabel.setText("You Lost " + playerManager.getNumRoundsLost() + " / " + playerManager.getNumGameRounds() + " Rounds");
        roundsWonLabel.setText("You Won " + playerManager.getNumRoundsWon() + " / " + playerManager.getNumGameRounds() + " Rounds");
        this.WinOrLOseLabel.setText(playerManager.getWinOrLose());
    }
    /**
     * Constructor for GameCompletionScreenController.
     * @param playerManager The PlayerManager instance associated with the game.
     */
    public GameCompletionScreenController(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }
    /**
     * Handles the action when the "Exit" button is clicked.
     * Exits the application.
     */
    public void onExitClicked(){
        Platform.exit();

    }
}
