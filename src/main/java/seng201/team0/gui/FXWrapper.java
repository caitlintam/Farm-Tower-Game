package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team0.PlayerManager;
import seng201.team0.TowerManager;
import seng201.team0.UpgradeManager;

import java.io.IOException;

public class FXWrapper {
    @FXML
    private Pane pane;

    private Stage stage;


    public void init(Stage stage) {
        this.stage = stage;
        new PlayerManager(this::launchSetupScreen, this::launchTowerSetupScreen, this::clearPane, this::launchHomeScreen, this::launchShopScreen, this::launchInventoryScreen, this::launchApplyUpgradeScreen, this::launchChooseRoundDifficultyScreen, this::launchMainGameScreen, this::launchWonRoundScreen, this::launchLostRoundScreen, this::launchGameCompletionScreen);
    }



    private void launchInventoryScreen(PlayerManager playerManager) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/inventory.fxml"));
            // provide a custom Controller with parameters
            setupLoader.setControllerFactory(param -> new InventoryController(playerManager));
            Parent setupParent = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Inventory");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchSetupScreen(PlayerManager playerManager) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/set_up_screen.fxml"));
            // provide a custom Controller with parameters
            setupLoader.setControllerFactory(param -> new SetUpScreenController(playerManager));
            Parent setupParent = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Player Manager Setup");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void launchShopScreen(PlayerManager playerManager) {
        try {
            FXMLLoader shopScreenLoader = new FXMLLoader(getClass().getResource("/fxml/shop.fxml"));
            shopScreenLoader.setControllerFactory(param -> new ShopController(playerManager, new TowerManager(), new UpgradeManager()));
            Parent setupParent  = shopScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Shop Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void launchTowerSetupScreen(PlayerManager playerManager) {
        try {
            FXMLLoader towerSetupScreenLoader = new FXMLLoader(getClass().getResource("/fxml/tower_setup_screen.fxml"));
            towerSetupScreenLoader.setControllerFactory(param -> new TowerSetUpController(playerManager, new TowerManager()));
            Parent setupParent = towerSetupScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Tower Setup Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchHomeScreen(PlayerManager playerManager) {
        try {
            FXMLLoader homeScreenLoader = new FXMLLoader(getClass().getResource("/fxml/homepage.fxml"));
            homeScreenLoader.setControllerFactory(param -> new HomePageController(playerManager));
            Parent setupParent = homeScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Home Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void launchApplyUpgradeScreen(PlayerManager playerManager){
        try {
            FXMLLoader applyUpgradeScreenLoader = new FXMLLoader(getClass().getResource("/fxml/apply_upgrades.fxml"));
            applyUpgradeScreenLoader.setControllerFactory(param -> new ApplyUpgradeScreenController(playerManager));
            Parent setupParent  = applyUpgradeScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Apply Upgrade Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void launchChooseRoundDifficultyScreen(PlayerManager playerManager){
        try {
            FXMLLoader chooseRoundDifficultyScreenLoader = new FXMLLoader(getClass().getResource("/fxml/choose_round_difficulty_screen.fxml"));
            chooseRoundDifficultyScreenLoader.setControllerFactory(param -> new ChooseRoundDifficultyScreenController(playerManager));
            Parent setupParent  = chooseRoundDifficultyScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Choose Round Difficulty Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void launchMainGameScreen(PlayerManager playerManager) {
        try {
            FXMLLoader mainGameScreenLoader = new FXMLLoader(getClass().getResource("/fxml/main_game_screen.fxml"));
            mainGameScreenLoader.setControllerFactory(param -> new MainGameScreenController(playerManager));
            Parent setupParent = mainGameScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Main Game Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void launchLostRoundScreen(PlayerManager playerManager) {
        try {
            FXMLLoader lostRoundScreenLoader = new FXMLLoader(getClass().getResource("/fxml/round_lost_screen.fxml"));
            lostRoundScreenLoader.setControllerFactory(param -> new LostRoundScreenController(playerManager));
            Parent setupParent = lostRoundScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Round Lost Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void launchWonRoundScreen(PlayerManager playerManager) {
        try {
            FXMLLoader wonRoundScreenLoader = new FXMLLoader(getClass().getResource("/fxml/round_won_screen.fxml"));
            wonRoundScreenLoader.setControllerFactory(param -> new WonRoundScreenController(playerManager));
            Parent setupParent = wonRoundScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Round Won Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void launchGameCompletionScreen(PlayerManager playerManager) {
        try {
            FXMLLoader gameCompletionScreenLoader = new FXMLLoader(getClass().getResource("/fxml/game_completion_screen.fxml"));
            gameCompletionScreenLoader.setControllerFactory(param -> new GameCompletionScreenController(playerManager));
            Parent setupParent = gameCompletionScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Game Completion Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void clearPane() {
        pane.getChildren().removeAll(pane.getChildren());
    }
}
