package seng201.team0.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import seng201.team0.PlayerManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LostRoundScreenController {
    private final PlayerManager playerManager;
    @FXML
    private Label DistanceTravelledLabel;
    @FXML
    private Label cartsFilledLabel;
    @FXML
    private Label moneyEarnedLabel;
    @FXML
    private  Label LosingRoundLabel;

    public void initialize(){
        DistanceTravelledLabel.setText("Distance Travelled: " + playerManager.getCurrentTrackDistance());
        LosingRoundLabel.setText("Round: " + (playerManager.getCurrentRoundNumber()+1));
        cartsFilledLabel.setText("Carts Filled: " + playerManager.getNumCartsFilled() + " / " + playerManager.getTowersInGame().size());
        moneyEarnedLabel.setText("Money Earned: $ 0.00");
    }

    public LostRoundScreenController(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }


    public void onHomeButtonClicked() {
        playerManager.closeLostRoundScreen();
        playerManager.toHomeOrRandomEventOrGameFinish();

    }
    public void onQuitButtonClicked(){
        Platform.exit();
    }
}
