package seng201.team0.gui;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import seng201.team0.PlayerManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import seng201.team0.models.Player;

import javax.sound.midi.SysexMessage;


public class SetUpScreenController {
    @FXML
    public Slider numRoundsSlider;
    @FXML
    public Slider gameDifficultySlider;
    @FXML
    public TextArea nameInputTextArea;
    @FXML
    public Button beginGameButton;
    private PlayerManager playerManager;

    public SetUpScreenController(PlayerManager playerManager){
        this.playerManager = playerManager;

    }
    public void initialize(){
        DoubleProperty sliderValue = numRoundsSlider.valueProperty();
        //playerManager.

    }
    public void onBeginClicked(){
        System.out.println("Begin Clicked!");

        playerManager.setName(nameInputTextArea.getText());
        playerManager.setNumGameRounds((int)numRoundsSlider.getValue());
        playerManager.setGameDifficulty((int) gameDifficultySlider.getValue());
        System.out.println("Player name: " + playerManager.getName());
        System.out.println("Number of game rounds: " + playerManager.getNumGameRounds());
        System.out.println("Game difficulty: " + playerManager.getGameDifficulty());
        playerManager.closeSetupScreen();
        playerManager.launchTowerSetUpScreen();
    }



}
