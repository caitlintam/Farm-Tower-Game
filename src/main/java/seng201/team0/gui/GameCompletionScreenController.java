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
    public Label WinOrLOseLabel;
    @FXML
    private Label roundsWonLabel;
    @FXML
    private Label roundsLostLabel;


    public void initialize(){
        System.out.println(playerManager.getWinOrLose());
        roundsLostLabel.setText("You Lost " + playerManager.getNumRoundsLost() + " / " + playerManager.getNumGameRounds() + "Rounds");
        roundsWonLabel.setText("You Won " + playerManager.getNumRoundsWon() + " / " + playerManager.getNumGameRounds() + " Rounds");
        this.WinOrLOseLabel.setText(playerManager.getWinOrLose());
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
