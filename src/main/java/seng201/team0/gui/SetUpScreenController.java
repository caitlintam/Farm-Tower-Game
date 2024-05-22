package seng201.team0.gui;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.*;
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
    public TextField nameInputTextField;
    @FXML
    public Button beginGameButton;
    public Label invalidNameLabel;
    public Label invalidLengthNameLabel;
    public Label invalidCharsNameLabel;
    private PlayerManager playerManager;

    public SetUpScreenController(PlayerManager playerManager){
        this.playerManager = playerManager;

    }
    public void initialize(){
        System.out.println("----- Set Up Screen ----");
        invalidLengthNameLabel.setVisible(false);
        invalidCharsNameLabel.setVisible(false);


    }
    public boolean validNameInput(String name){
        return name.matches("^[a-zA-Z0-9]{3,15}$");
    }
    public void onBeginClicked(){
        try {
            System.out.println("Begin Clicked!");
//            if (nameInputTextField == null || playerManager == null) {
//                throw new IllegalStateException("UI components are not properly initialized.");
//            }
            String name = nameInputTextField.getText();
            invalidCharsNameLabel.setVisible(false);
            invalidLengthNameLabel.setVisible(false);
//            if (name == null || name.isEmpty()) {
//                throw new IllegalArgumentException("Name input cannot be null or empty.");
//            }

            if (!validNameInput(nameInputTextField.getText())) {
                if (!nameInputTextField.getText().matches("^.{3,15}$")) {
                    invalidLengthNameLabel.setVisible(true);
                    //throw new Exception("Name must be between 3 and 15 characters long");
                    //System.out.println("Error: Name must be between 3 and 15 chgvjharacters long");


                } else {
                   // System.out.println("Error: Name must not contain special characters");
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
        }catch(Exception e){
            System.err.println("Error: Please Input a Valid Name");

            }
      //


    }}
