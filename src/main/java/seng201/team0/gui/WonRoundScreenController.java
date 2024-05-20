package seng201.team0.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.PlayerManager;

public class WonRoundScreenController {
    private final PlayerManager playerManager;
    @FXML
    private Label distanceTravelledLabel;
    @FXML
    private Label cartsFilledLabel;
    @FXML
    private Label MoneyEarnedLabel;
    @FXML
    private  Label YouWonRoundLabel;
    public void initialize(){
        distanceTravelledLabel.setText("Distance Travelled: " + playerManager.getCurrentTrackDistance());
        YouWonRoundLabel.setText("Round: " + playerManager.getCurrentRoundNumber());
        cartsFilledLabel.setText("Carts Filled: " + playerManager.getNumCartsFilled() + " / " + playerManager.getTowersInGame().size());
        MoneyEarnedLabel.setText("Money Earned: $ " + playerManager.getEarnedMoney());
    }
    public WonRoundScreenController(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }
    public void onHomeButtonClicked() {
        playerManager.closeWonRoundScreen();
        playerManager.toHomeOrRandomEventOrGameFinish();
    }
    public void onQuitButtonClicked(){
        Platform.exit();
    }
}


