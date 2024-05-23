package seng201.team0.gui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;
import seng201.team0.PlayerManager;
import javafx.fxml.FXML;
import seng201.team0.models.Player;

/**
 * Controller class for the Main Game Screen.
 * Manages the UI elements and interactions on the screen during the main game.
 */

public class MainGameScreenController {
    private final PlayerManager playerManager;
    private final Player player;
    @FXML
    public Label mainGameTextLabel;
    @FXML
    public ProgressBar progressBar;
    private DoubleProperty progressProperty = new SimpleDoubleProperty();
    /**
     * Constructs a new instance of MainGameScreenController with the specified PlayerManager.
     * This constructor initializes the MainGameScreenController with the given PlayerManager instance.
     * @param playerManager The PlayerManager instance associated with the main game screen controller.
     */
    public MainGameScreenController(PlayerManager playerManager){
        this.playerManager = playerManager;
        this.player = playerManager.getPlayer();
    }
    /**
     * Initializes the main game screen.
     * This method sets up the initial state of the main game screen by setting the main game text,
     * updating the UI elements, binding the progress bar to the progress property,
     * and starting a timeline animation to update the progress bar.
     * It also listens for changes in the progress property and stops the timeline when the progress reaches 100%.
     */
    public void initialize(){
        mainGameTextLabel.setText(" ");
        System.out.println("Game Text " + playerManager.getMainGameScreenRoundText());
        mainGameTextLabel.setText(playerManager.getMainGameScreenRoundText());
        updateUI();
        progressBar.progressProperty().bind(progressProperty);
        Timeline progressTimeLine = new Timeline(
                new KeyFrame(Duration.seconds(0.04), event -> {
                    progressProperty.setValue(progressProperty.getValue() + 0.01);
                })
        );
        progressTimeLine.setCycleCount(Animation.INDEFINITE);
        progressTimeLine.play();
        progressProperty.addListener((observable, value, newValue) -> {
            if (newValue.doubleValue() >= 1.0 ){
                progressTimeLine.stop();
                endRound();
            }
        });
    }
    /**
     * Updates the UI elements of the main game screen.
     * This method calculates the progress of the current round relative to the total number of rounds,
     * and updates the progress bar accordingly.
     */
    public void updateUI(){
        double progress = (double) playerManager.getCurrentRoundNumber()/playerManager.getNumGameRounds();
        progressBar.setProgress(progress);
    }
    /**
     * Ends the current round of the game.
     * This method closes the main game screen and evaluates the success of the current round.
     * It is called when the progress reaches 100%.
     */
    public void endRound(){
        playerManager.closeMainScreen();
        playerManager.evaluateRoundSuccess();

    }
}
