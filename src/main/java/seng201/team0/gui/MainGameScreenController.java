package seng201.team0.gui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;
import seng201.team0.PlayerManager;
import javafx.fxml.FXML;

public class MainGameScreenController {
    private final PlayerManager playerManager;
    @FXML
    public Button seeResultsScreenButton;
    @FXML
    public Label mainGameTextLabel;
    @FXML
    public ProgressBar progressBar;
    private DoubleProperty progressProperty = new SimpleDoubleProperty();

    public MainGameScreenController(PlayerManager playerManager){
        this.playerManager = playerManager;
    }

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
    public void updateUI(){
        double progress = (double) playerManager.getCurrentRoundNumber()/playerManager.getNumGameRounds();
        progressBar.setProgress(progress);
    }
    public void updateProgressBar(double progress){
        progressBar.setProgress(progress);
        if (progress >= 1.0) {
            endRound();
        }
    }
    public void endRound(){
        playerManager.closeMainScreen();
        playerManager.evaluateRoundSuccess();

    }
}
