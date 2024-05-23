package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.PlayerManager;
import seng201.team0.models.Player;
import seng201.team0.models.Tower;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Controller class for the tower setup in a game.
 * This class handles the interactions between the user and the tower selection interface.
 * It manages the selection and configuration of towers before starting the game.
 */
public class TowerSetUpController {
    @FXML
    public Button nextButton;
    @FXML
    public Button towerOption2;
    @FXML
    public Button towerOption1;
    @FXML
    public Button towerOption6;
    @FXML
    public Button towerOption5;
    @FXML
    public Button towerOption3;
    @FXML
    public Button towerOption4;
    @FXML
    public Label towerNameLabel;
    @FXML
    public Label resTypeLabel;
    @FXML
    public Label resAmountLabel;
    @FXML
    public Label reloadSpeedLabel;
    @FXML
    public Button selectedTower3;
    @FXML
    public Button selectedTower2;
    @FXML
    public Button selectedTower1;
    @FXML
    public Label errorTowerNumLabel;
    @FXML
    private final PlayerManager playerManager;
    @FXML
    private final Tower towerManager;
    private int selectedTowerIndex = -1;
    private final Tower[] selectedTowers = new Tower[3];
    private final Player player;
    /**
     * Constructor for the TowerSetUpController.
     * Initializes the controller with a PlayerManager instance.
     *
     * @param playerManager the PlayerManager instance
     */
    public TowerSetUpController(PlayerManager playerManager){
        this.playerManager = playerManager;
        this.towerManager = playerManager.getTowerManager();
        this.player = playerManager.getPlayer();
    }
    /**
     * Initializes the TowerSetUpController.
     * Sets up the initial state of the UI components and event handlers for the tower selection buttons.
     * Highlights the selected tower button and resets the style for non-selected buttons.
     */
    public void initialize(){
        System.out.println("----- Tower Set Up Screen ------");
        errorTowerNumLabel.setVisible(false);
        List<Button> selectedTowerButtons = List.of(selectedTower1,selectedTower2,selectedTower3);
        List<Button> towerButtons = List.of(towerOption1,towerOption2,towerOption3,towerOption4,towerOption5,towerOption6);
        for (int i =0; i< towerButtons.size(); i++){
            int finalI = i;
            towerButtons.get(i).setOnAction(actionEvent -> {
                updateStats(towerManager.getDefaultTowers().get(finalI));
                selectedTowerIndex = finalI;
                towerButtons.forEach(button -> {
                    if (button == towerButtons.get(finalI)){
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                    } else{
                        button.setStyle("");
                    }
                });
            });
        }
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
    /**
     * Updates the tower stats labels with the information of the selected tower.
     * @param tower the selected tower
     */
    private void updateStats(Tower tower){
        towerNameLabel.setText(tower.getTowerName());
        resTypeLabel.setText(tower.getTowerResourceType());
        resAmountLabel.setText(String.valueOf(tower.getTowerResourceAmount()));
        reloadSpeedLabel.setText(String.valueOf(tower.getTowerReloadSpeed()));
    }
    /**
     * Handles the click event for the next button.
     * Validates the selected towers and adds them to the player's inventory if valid.
     * Otherwise, displays an error message.
     */
    @FXML
    public void onNextClicked(){
        System.out.println("Next Clicked! ");
        towerManager.setTowerList(Arrays.stream(selectedTowers).filter((Objects::nonNull)).toList());
        if (towerManager.getTowerList().size() < 3){
            errorTowerNumLabel.setVisible(true);
        }else{
            System.out.println("Towers Added to Inventory: ");
            int i = 1;
            for(Tower tower: selectedTowers){
                tower.setTowerStatus("In-Game");
                player.addTowersToInventory(tower);
                System.out.println("      Tower " + (i)+ " " + tower.getTowerName());
                towerManager.setTowerStatus(tower);
                i+=1;
            }
            player.setTowersInGame();
            playerManager.closeTowerSetUpScreen();
            playerManager.launchHomeScreen();
        }
    }
}
