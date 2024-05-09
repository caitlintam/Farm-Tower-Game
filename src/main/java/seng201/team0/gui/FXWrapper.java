package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team0.PlayerManager;
import seng201.team0.TowerManager;

import java.io.IOException;

public class FXWrapper {
    @FXML
    private Pane pane;

    private Stage stage;


    public void init(Stage stage) {
        this.stage = stage;
        new PlayerManager(this::launchSetupScreen, this::launchTowerSetupScreen, this::clearPane, this::launchHomeScreen, this::launchShopScreen);
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
            shopScreenLoader.setControllerFactory(param -> new TowerSetUpController(playerManager, new TowerManager()));
            Parent setupParent  = shopScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Shop Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void clearPane() {
        pane.getChildren().removeAll(pane.getChildren());
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
}
