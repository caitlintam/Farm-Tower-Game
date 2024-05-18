package seng201.team0.gui;

import javafx.scene.control.Button;
import seng201.team0.PlayerManager;

public class MainGameScreenController {
    private final PlayerManager playerManager;
    public Button seeResultsScreenButton;
    
    public MainGameScreenController(PlayerManager playerManager){
        this.playerManager = playerManager;
    }

}
