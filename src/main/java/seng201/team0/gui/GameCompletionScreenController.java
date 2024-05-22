package seng201.team0.gui;

import javafx.application.Platform;
import seng201.team0.PlayerManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class GameCompletionScreenController {
    private final PlayerManager playerManager;
    public Label WinOrLOseLabel;
    public Label moneyGainedLabel;
    @FXML
    private Label roundsWonLabel;
    @FXML
    private Label roundsLostLabel;


    public void initialize(){
        moneyGainedLabel.setText("Money Gained: $"+playerManager.getMoney());
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


}
