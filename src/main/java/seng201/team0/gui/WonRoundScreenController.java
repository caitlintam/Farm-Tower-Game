package seng201.team0.gui;

import seng201.team0.PlayerManager;

public class WonRoundScreenController {
    private final PlayerManager playerManager;

    public WonRoundScreenController(PlayerManager playerManager) {this.playerManager = playerManager;
    }

    public void onHomeButtonClicked() {
        playerManager.closeWonRoundScreen();
        playerManager.toHomeOrRandomEventOrGameFinish();
    }
}
