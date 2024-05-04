package seng201.team0.gui;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import seng201.team0.PlayerManager;

public class SetUpScreenController {
    public Slider numRoundsSlider;
    public Slider gameDifficultySlider;
    public TextArea nameInputTextArea;
    public Button beginGameButton;
    private PlayerManager playerManager;

    public SetUpScreenController(PlayerManager playerManager){
        this.playerManager = playerManager;

    }
    public void initialize(){

    }



}
