package seng201.team0.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.PlayerManager;

import java.util.List;

public class ChooseRoundDifficultyScreenController {
    private final PlayerManager playerManager;
    public Button easyDifficultyButton;
    public Button medDifficultyButton;
    public Button hardDifficultyButton;
    public Button nextButton;
    public Label errorNoDiffSelected;
    private int selectedDifficultyIndex = -1;
    public ChooseRoundDifficultyScreenController(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }
    public void initialize() {
        playerManager.updateTrackDistanceOptionsList();
        errorNoDiffSelected.setVisible(false);
        List<Button> difficultyButtons = List.of(easyDifficultyButton, medDifficultyButton, hardDifficultyButton);
        for (int i = 0; i < difficultyButtons.size(); i++) {
            int finalI = i;
            //when clicked
            difficultyButtons.get(i).setOnAction(actionEvent -> {
                selectedDifficultyIndex = finalI;
                playerManager.setCurrentTrackDistance(selectedDifficultyIndex);
                difficultyButtons.forEach(button -> {
                    if (button == difficultyButtons.get(finalI)) {
                        // when button selected, change border to highlight selected
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                        // sets the track dist
                    } else {
                        button.setStyle(""); // if not selected, remove highlight
                    }
                });

            });
        }
    }

    public void onNextButtonClicked() {
        if (selectedDifficultyIndex == -1) { // no difficulty selected, error
            errorNoDiffSelected.setVisible(true);
            System.out.println("Error: No difficulty selected");
        }else{
            errorNoDiffSelected.setVisible(false);
            System.out.println("Difficulty selected:" + (selectedDifficultyIndex));
            System.out.println(playerManager.getCurrentTrackDistance());
            //sets track distance
            playerManager.setCurrentTrackDistance(selectedDifficultyIndex);
   //         playerManager.runGame();
            playerManager.closeChooseRoundDifficultyScreen();
            //playerManager.launchMainGameScreen();
            // launches main screen to start round
            playerManager.startRound();




            //show error

        // if the
        //playerManager. close this screen
        // playerManager.launch next screen
    }
}

    // on button clicked{
    // determine what track distance
    // call method setInitialTrackDistance();
}
