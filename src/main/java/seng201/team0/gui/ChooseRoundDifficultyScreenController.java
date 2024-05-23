package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.PlayerManager;
import java.util.List;

/**Controller class for the Choose Round Difficulty Screen
 * Manages the UI elements and interactions on the screen related to choosing a difficulty level before each round
 */
public class ChooseRoundDifficultyScreenController {
    private final PlayerManager playerManager;
    @FXML
    public Button easyDifficultyButton;
    @FXML
    public Button medDifficultyButton;
    @FXML
    public Button hardDifficultyButton;
    @FXML
    public Button nextButton;
    @FXML
    public Label errorNoDiffSelected;
    private int selectedDifficultyIndex = -1;

    /**
     * Constructs a new instance of ChooseRoundDifficultyScreenController with the specified PlayerManager.
     * This constructor initializes the ChooseRoundDifficultyScreenController with the given PlayerManager instance.
     * @param playerManager The PlayerManager instance associated with the choose round difficulty screen controller.
     */
    public ChooseRoundDifficultyScreenController(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }
    /**
     * Initializes the choose round difficulty screen with UI elements and interactions.
     * This method updates the track distance options list in the PlayerManager,
     * hides the error label for no difficulty selected, and sets actions for difficulty buttons.
     * When a difficulty button is clicked, it sets the selected difficulty index, updates the current track distance,
     * and changes the style of the clicked button to indicate selection.
     */
    public void initialize() {
        playerManager.updateTrackDistanceOptionsList();
        errorNoDiffSelected.setVisible(false);
        List<Button> difficultyButtons = List.of(easyDifficultyButton, medDifficultyButton, hardDifficultyButton);
        for (int i = 0; i < difficultyButtons.size(); i++) {
            int finalI = i;
            difficultyButtons.get(i).setOnAction(actionEvent -> {
                selectedDifficultyIndex = finalI;
                playerManager.setCurrentTrackDistance(selectedDifficultyIndex);
                difficultyButtons.forEach(button -> {
                    if (button == difficultyButtons.get(finalI)) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });

            });
        }
    }
    /**
     * Handles the action when the "Next" button is clicked.
     * Validates if a difficulty level is selected, then proceeds to start the round.
     * Also catches the exception error when a user trys to start round without selecting a round difficulty
     */
    public void onNextButtonClicked() {
        try {
            if (selectedDifficultyIndex == -1) {
                errorNoDiffSelected.setVisible(true);
            } else {
                playerManager.resetMainGameText();
                errorNoDiffSelected.setVisible(false);
                System.out.println("Difficulty selected: " + (selectedDifficultyIndex));
                playerManager.setCurrentTrackDistance(selectedDifficultyIndex);
                playerManager.closeChooseRoundDifficultyScreen();
                playerManager.startRound();
            }
        }catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}


