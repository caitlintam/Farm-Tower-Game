package seng201.team0.gui;
import javafx.fxml.FXML;
import seng201.team0.PlayerManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Controller class for the Random Event Screen.
 * Manages the UI elements and interactions on the screen related to random events.
 */
public class RandomEventController {
    @FXML
    public Button nextButtonRandomEventPage;
    @FXML
    public Label randomEventLabel;
    private final PlayerManager playerManager;
    /**
     * Constructs instance of Random Eventwith specified PlayerManager instance
     * @param playerManager
     */
    public RandomEventController(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }
    /**
     * Initializes the random event screen.
     * Sets the initial text of the random event label with the text retrieved from the PlayerManager.
     */
    @FXML
    public void initialize(){
        System.out.println("test " + playerManager.getRandomText());
        randomEventLabel.setText(playerManager.getRandomText());
    }
    /**
     * Handles the action when the next button is clicked.
     * Closes the random event screen and returns to the home screen.
     */
    @FXML
    public void onNextClicked() {
        playerManager.closeRandomEventScreen();
        playerManager.launchHomeScreen();
    }
}
