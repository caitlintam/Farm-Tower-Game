package seng201.team0.gui;

import javafx.event.ActionEvent;
import seng201.team0.PlayerManager;
import seng201.team0.models.Player;

public class wonRoundScreenController {
    private final PlayerManager playerManager;

    public wonRoundScreenController(PlayerManager playerManager) {this.playerManager = playerManager;
    }

    public void onHomeButtonClicked() {
        playerManager.closeWonRoundScreen();
        playerManager.toHomeOrRandomEvent();
    }
}
