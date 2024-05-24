package seng201.team0.gui;

import javafx.application.Platform;
import javafx.scene.control.Button;
import seng201.team0.PlayerManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.models.Player;
/**
 * Controller class for the Game Completion Screen.
 * Manages the UI elements and interactions on the screen displayed when the game is completed.
 */


public class GameCompletionScreenController {
    @FXML
    public Label winOrLoseLabel;
    @FXML
    public Button exitButton;
    @FXML
    private Label roundsWonLabel;
    @FXML
    private Label roundsLostLabel;
    private final PlayerManager playerManager;
    private final Player player;
    /**
     * Constructs a new instance of GameCompletionScreenController with the specified PlayerManager.
     * This constructor initializes the GameCompletionScreenController with the given PlayerManager instance.
     * @param playerManager The PlayerManager instance associated with the game completion screen controller.
     */
    public GameCompletionScreenController(PlayerManager playerManager) {
        this.playerManager = playerManager;
        this.player = playerManager.getPlayer();
    }

    /**
     * Initializes the game completion screen with relevant information.
     * This method sets the text of labels to display the win or lose status, the number of rounds won and lost,
     * and updates the win or lose label accordingly based on information retrieved from the PlayerManager.
     */
    @FXML
    public void initialize(){
        roundsLostLabel.setText("You Lost " + player.getNumRoundsLost() + " / " + playerManager.getNumGameRounds() + " Rounds");
        roundsWonLabel.setText("You Won " + player.getNumRoundsWon() + " / " + playerManager.getNumGameRounds() + " Rounds");
        this.winOrLoseLabel.setText(playerManager.getWinOrLose());
    }

    /**
     * Handles the action when the "Exit" button is clicked.
     * Exits the application.
     */
    @FXML
    public void onExitClicked(){
        Platform.exit();

    }
}
