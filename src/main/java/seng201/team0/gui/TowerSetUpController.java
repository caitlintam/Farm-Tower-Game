package seng201.team0.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.PlayerManager;

public class TowerSetUpController {
    public Button nextButton;
    public Button towerOption2;
    public Button towerOption1;
    public Button towerOption6;
    public Button towerOption5;
    public Button towerOption3;
    public Button towerOption4;
    public Label towerNameLabel;
    public Label resTypeLabel;
    public Label resAmountLabel;
    public Label reloadSpeedLabel;
    public Button selectedTower3;
    public Button selectedTower2;
    public Button selectedTower1;
    private PlayerManager playerManager;
    public TowerSetUpController(PlayerManager playerManager){
        this.playerManager = playerManager;

    }
    public void initialize(){

    }
    public void onNextClicked(){
        playerManager.closeTowerSetUpScreen();
        playerManager.launchHomeScreen();

    }
}
