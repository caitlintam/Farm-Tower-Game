package seng201.team0.gui;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import seng201.team0.PlayerManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import seng201.team0.models.Player;


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


    }
    public void onBeginClicked(){
        playerManager.setName(nameInputTextArea.getText());
        //playerManager.
        playerManager.closeSetupScreen();
        playerManager.launchTowerSetUpScreen();
    }



}
