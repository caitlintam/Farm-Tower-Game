package seng201.team0.gui;
import javafx.fxml.FXML;
import seng201.team0.RandomEventManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class RandomEventController {
    @FXML
    public Button NextButtonRandomEventPage;
    @FXML
    public Label RandomEventLabel;

    public void setRandomEventText(String text){
        RandomEventLabel.setText(text);
    }
}
