package seng201.team0.gui;

import javafx.scene.control.*;
import seng201.team0.PlayerManager;
import javafx.fxml.FXML;

/**
 * Controller class for the Setup Screen.
 * Manages the UI elements and interactions on the screen related to setting up the game parameters.
 */
public class SetUpScreenController {
    @FXML
    public Slider numRoundsSlider;
    @FXML
    public Slider gameDifficultySlider;
    @FXML
    public TextField nameInputTextField;
    @FXML
    public Button beginGameButton;
    @FXML
    public Label invalidLengthNameLabel;
    @FXML
    public Label invalidCharsNameLabel;
    private PlayerManager playerManager;
    /**
     * Constructs a new instance of SetUpScreenController with the specified PlayerManager.
     * Initializes the SetUpScreenController with the given PlayerManager instance.
     * @param playerManager The PlayerManager associated with the setup screen controller.
     */
    public SetUpScreenController(PlayerManager playerManager) {
        this.playerManager = playerManager;
        /**
         * Initializes the setup screen.
         * Prints a setup screen message to the console and hides error labels.
         */
    }
    public void initialize() {
        System.out.println("----- Set Up Screen ----");
        invalidLengthNameLabel.setVisible(false);
        invalidCharsNameLabel.setVisible(false);
    }
    /**
     * checks that name is valid input
     * must be 3-15 characters
     * can only contain alphanumeric characters*/
    public boolean validNameInput(String name) {
        return name.matches("^[a-zA-Z0-9]{3,15}$");
    }
    /**
     * Handles the action when the "Begin" button is clicked.
     * This method validates the input name and sliders' values, sets the player's name, number of game rounds,
     * and game difficulty based on the input, and closes the setup screen.
     * If the input name is invalid or if there's an error during the process, an error message is printed.
     */
    public void onBeginClicked() {
            System.out.println("Begin Clicked!");
            String name = nameInputTextField.getText();
            invalidCharsNameLabel.setVisible(false);
            invalidLengthNameLabel.setVisible(false);
            if (!validNameInput(nameInputTextField.getText())) {
                if (!nameInputTextField.getText().matches("^.{3,15}$")) {
                    invalidLengthNameLabel.setVisible(true);
                } else {
                    invalidCharsNameLabel.setVisible(true);
                }
            } else {
                System.out.println("valid input");
                playerManager.setName(nameInputTextField.getText());
                playerManager.setNumGameRounds((int) numRoundsSlider.getValue());
                playerManager.setGameDifficulty((int) gameDifficultySlider.getValue());
                System.out.println("Player Name: " + playerManager.getName());
                System.out.println("Number of Game Rounds: " + playerManager.getNumGameRounds());
                System.out.println("Game Difficulty: " + playerManager.getGameDifficulty());
                playerManager.setRandomEventRoundsList();
                System.out.println("random " + playerManager.getRandomEventsRoundList());
                playerManager.closeSetupScreen();
                playerManager.launchTowerSetUpScreen();
            }
        }
}

