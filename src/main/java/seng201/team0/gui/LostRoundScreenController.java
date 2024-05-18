package seng201.team0.gui;

import javafx.event.ActionEvent;
import seng201.team0.PlayerManager;

public class LostRoundScreenController {
    private final PlayerManager playerManager;

    public LostRoundScreenController(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }


    public void onHomeButtonClicked() {
        playerManager.closeLostRoundScreen();
        playerManager.toHomeOrRandomEvent();

    }
}
