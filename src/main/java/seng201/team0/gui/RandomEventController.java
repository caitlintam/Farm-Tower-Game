package seng201.team0.gui;
import javafx.fxml.FXML;
import seng201.team0.PlayerManager;
import seng201.team0.RandomEventManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Controller class for the Random Event Screen.
 * Manages the UI elements and interactions on the screen related to random events.
 */
public class
RandomEventController {
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
    /**
     * Initializes the random event screen.
     * Sets the initial text of the random event label with the text retrieved from the PlayerManager.
     */
    public void initialize(){
        System.out.println("test " + playerManager.getRandomText());
        RandomEventLabel.setText(playerManager.getRandomText());
    }
    /**
     * Handles the action when the next button is clicked.
     * Closes the random event screen and returns to the home screen.
     */
    public void onNextClicked() {
        playerManager.closeRandomEventScreen();
        playerManager.launchHomeScreen();
    }
    /**
     * Sets the text of the random event label.
     * @param text The text to be set on the random event label.
     */
    public void setRandomEventText(String text){
        RandomEventLabel.setText(text);
    }
}
