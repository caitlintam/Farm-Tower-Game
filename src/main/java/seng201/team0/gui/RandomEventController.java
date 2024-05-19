package seng201.team0.gui;
import javafx.fxml.FXML;
import seng201.team0.PlayerManager;
import seng201.team0.RandomEventManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class RandomEventController {
    @FXML
    public Button NextButtonRandomEventPage;
    @FXML
    public Label RandomEventLabel;
    private final PlayerManager playerManager;
    private RandomEventManager randomEventManager;
    public RandomEventController(PlayerManager playerManager, RandomEventManager randomEventManager) {
        this.playerManager = playerManager;
        this.randomEventManager = randomEventManager;
    }
    public void initialize(){
        System.out.println("rtest" + playerManager.getRandomText());
        RandomEventLabel.setText(playerManager.getRandomText());
    }
    public void onNextClicked() {
        playerManager.closeRandomEventScreen();
        playerManager.launchHomeScreen();
    }
    public void setRandomEventText(String text){
        RandomEventLabel.setText(text);
    }
}
