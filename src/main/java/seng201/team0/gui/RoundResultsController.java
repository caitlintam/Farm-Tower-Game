package seng201.team0.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.PlayerManager;
import seng201.team0.models.Player;

public class RoundResultsController {
    private final PlayerManager playerManager;
    public Label roundResultTitleLabel;
    @FXML
    private Label distanceTravelledLabel;
    @FXML
    private Label cartsFilledLabel;
    @FXML
    private Label MoneyEarnedLabel;
    @FXML
    private  Label roundSuccessLabel;
    private Player player;
    public void initialize(){
        System.out.println(playerManager.getRoundSuccess());
        if (playerManager.getRoundSuccess() == true){
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
    public RoundResultsController(PlayerManager playerManager) {
        this.playerManager = playerManager;
        this.player = playerManager.getPlayer();
    }
    public void onHomeButtonClicked() {
        playerManager.closeWonRoundScreen();
        playerManager.toHomeOrRandomEventOrGameFinish();
    }
    public void onQuitButtonClicked(){
        Platform.exit();
    }
}


