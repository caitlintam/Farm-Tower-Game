package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.PlayerManager;
import seng201.team0.TowerManager;
import seng201.team0.models.Tower;
import javafx.stage.Stage;

public class HomePageController {
    private TowerManager towerManager;
    private PlayerManager playerManager;
    @FXML
    private Label playerNameLabel;

    @FXML
    private Label homepageMoney;
    @FXML
    private Label homepageCurrentRoundLabel;
    private Label remainingRoundsLabel;

    @FXML
    private Button goToShopButton;
    @FXML
    private Button PlayRoundButton;
    @FXML
    private Button viewInventoryButton;


    public void initialize(){
        playerNameLabel.setText(playerManager.getName());
        homepageMoney.setText(PlayerManager.getMoney()); // need to find a way get money from player class
        //homepageCurrentRoundLabel.setText(PlayerManager.getCurrentRound());
        remainingRoundsLabel.setText(PlayerManager.getNumGameRounds());

    }

    public HomePageController(PlayerManager playerManager){
        this.towerManager = towerManager;
        updateHomePage();

    }
    public void updateHomePage(){
        //
    }

}
