package seng201.team0.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.PlayerManager;

public class MainGameScreenController {
    private final PlayerManager playerManager;
    public Button seeResultsScreenButton;
    public Label mainGameTextLabel;

    public MainGameScreenController(PlayerManager playerManager){
        this.playerManager = playerManager;
    }

    public void initialize(){
        System.out.println("Game Text " + playerManager.getMainGameScreenRoundText());
        mainGameTextLabel.setText(playerManager.getMainGameScreenRoundText());
    }
    public void onSeeResultsScreenButtonClicked() {
        playerManager.closeMainScreen();
        playerManager.evaluateRoundSuccess();
    }
}
