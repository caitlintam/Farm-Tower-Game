package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.PlayerManager;
import seng201.team0.TowerManager;
import seng201.team0.models.Tower;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TowerSetUpController {
    public Button nextButton;
    public Button towerOption2;
    public Button towerOption1;
    public Button towerOption6;
    public Button towerOption5;
    public Button towerOption3;
    public Button towerOption4;
    public Label towerNameLabel;
    public Label resTypeLabel;
    public Label resAmountLabel;
    public Label reloadSpeedLabel;
    public Button selectedTower3;
    public Button selectedTower2;
    public Button selectedTower1;
    public Label errorTowerNumLabel;
    private PlayerManager playerManager;
    private TowerManager towerManager;
    private int selectedTowerIndex = -1;
    private final Tower[] selectedTowers = new Tower[3];
    private Button selectedButton;
    public TowerSetUpController(PlayerManager playerManager, TowerManager towerManager){
        this.playerManager = playerManager;
        this.towerManager = towerManager;

    }

    public void initialize(){
        System.out.println("----- Tower Set Up Screen ------");
        errorTowerNumLabel.setVisible(false);
        List<Button> selectedTowerButtons = List.of(selectedTower1,selectedTower2,selectedTower3);
        List<Button> towerButtons = List.of(towerOption1,towerOption2,towerOption3,towerOption4,towerOption5,towerOption6);

        // changes stats, and shows selected button
        for (int i =0; i< towerButtons.size(); i++){
            int finalI = i;
            towerButtons.get(i).setOnAction(actionEvent -> {
                updateStats(towerManager.getDefaultTowers().get(finalI)); // updates text of my selected rockets
                selectedTowerIndex = finalI;
                towerButtons.forEach(button -> { // when button selected, change border to highlight selected
                    if (button == towerButtons.get(finalI)){
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                    } else{
                        button.setStyle(""); // if not selected, remove highlight
                    }
                });
            });
        }
        // sets selected towers
        for (int i = 0; i < selectedTowerButtons.size(); i++){
            int finalI = i;
            selectedTowerButtons.get(i).setOnAction(event -> {
                if (selectedTowerIndex != -1){
                    selectedTowerButtons.get(finalI).setText(towerManager.getDefaultTowers().get(selectedTowerIndex).getTowerName());
                    selectedTowers[finalI] = towerManager.getDefaultTowers().get(selectedTowerIndex);
                }
            });
        }
    }

    private void updateStats(Tower tower){
        towerNameLabel.setText(tower.getTowerName());
        resTypeLabel.setText(tower.getTowerResourceType());
        resAmountLabel.setText(String.valueOf(tower.getTowerResourceAmount()));
        reloadSpeedLabel.setText(String.valueOf(tower.getTowerReloadSpeed()));
    }
    @FXML
    public void onNextClicked(){
        System.out.println("Next Clicked! ");
        towerManager.setTowerList(Arrays.stream(selectedTowers).filter((Objects::nonNull)).toList());
        if (towerManager.getTowerList().size() < 3){
            System.out.println("Error: not enough towers selected");
            errorTowerNumLabel.setVisible(true);
        }else{
            System.out.println("Towers Added to Inventory: ");
            int i = 1;
            for(Tower tower: selectedTowers){
                // HERE NEED TO UPDATE STATUS TO INGAME
                tower.setTowerStatus("In-Game");
                playerManager.addTowersToInventory(tower);
                System.out.println("      Tower " + (i)+ " " + tower.getTowerName());
                towerManager.setTowerStatus(tower);
                i+=1;
            }
            playerManager.setTowersInGame();
            playerManager.closeTowerSetUpScreen();
            playerManager.launchHomeScreen();

        }

    }
}
