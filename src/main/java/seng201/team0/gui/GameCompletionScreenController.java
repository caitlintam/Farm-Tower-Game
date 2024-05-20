package seng201.team0.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import seng201.team0.App;
import seng201.team0.PlayerManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class GameCompletionScreenController {
    private final PlayerManager playerManager;
    @FXML
    private Label roundsWonLabel;
    @FXML
    private Label roundsLostLabel;
    @FXML
    private Label WinOrLoseLabel;

    public void initialize(){
        roundsLostLabel.setText("You Lost " + playerManager.getNumRoundsLost() + " / " + playerManager.getNumGameRounds());
        roundsWonLabel.setText("You Won " + playerManager.getNumRoundsWon() + " / " + playerManager.getNumGameRounds());
        WinOrLoseLabel.setText(playerManager.getWinOrLose());
    }

    public GameCompletionScreenController(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }
    public void onPlayAgainClicked(){
        Platform.exit();

    }


    public void onPlayAgainClicked(ActionEvent actionEvent) {

    }
}
