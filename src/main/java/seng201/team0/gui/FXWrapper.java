package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team0.*;

import java.io.IOException;
/** The FXWrapper manages the launching of different UI screens
 * it does this by loading their respective FXML files and initializing their controllers.
 * */
public class FXWrapper {
    @FXML
    private Pane pane;
    private Stage stage;

    /**
     * Initializes the FXWrapper with the primary stage.
     * @param stage The primary stage of the application.
     */
    public void init(Stage stage) {
        this.stage = stage;
        new PlayerManager(this::launchSetupScreen, this::launchTowerSetupScreen, this::clearPane, this::launchHomeScreen, this::launchShopScreen, this::launchInventoryScreen, this::launchApplyUpgradeScreen, this::launchChooseRoundDifficultyScreen, this::launchMainGameScreen, this::launchWonRoundScreen, this::launchGameCompletionScreen, this::launchRandomEventScreen);
    }
    /**
     * Launches the Inventory screen by loading the inventory fxml and inventory controller
     * @param playerManager The PlayerManager instance associated with the game.
     */
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
    /**
     * Launches the SetupScreen by loading the setup screen fxml and set up controller
     * @param playerManager The PlayerManager instance associated with the game.
     */
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
    /**
     * Launches the Shop Screen by loading the shop screen fxml and shop controller
     * @param playerManager The PlayerManager instance associated with the game.
     */
    public void launchShopScreen(PlayerManager playerManager) {
        try {
            FXMLLoader shopScreenLoader = new FXMLLoader(getClass().getResource("/fxml/shop.fxml"));
            shopScreenLoader.setControllerFactory(param -> new ShopController(playerManager));
            Parent setupParent  = shopScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Shop Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Launches the RandomEventScreen by loading the random event screen fxml and random event controller
     * @param playerManager The PlayerManager instance associated with the game.
     */
    public void launchRandomEventScreen(PlayerManager playerManager) {
        try {
            FXMLLoader randomEventScreenLoader = new FXMLLoader(getClass().getResource("/fxml/random_event.fxml"));
            randomEventScreenLoader.setControllerFactory(param -> new RandomEventController(playerManager));
            Parent setupParent = randomEventScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Random Event Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Launches the TowerSetupScreen by loading the tower setup screen fxml and tower set up controller
     * @param playerManager The PlayerManager instance associated with the game.
     */
    public void launchTowerSetupScreen(PlayerManager playerManager) {
        try {
            FXMLLoader towerSetupScreenLoader = new FXMLLoader(getClass().getResource("/fxml/tower_setup_screen.fxml"));
            towerSetupScreenLoader.setControllerFactory(param -> new TowerSetUpController(playerManager));
            Parent setupParent = towerSetupScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Tower Setup Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Launches the home screen by loading the home page fxml and homepage controller
     * @param playerManager The PlayerManager instance associated with the game.
     */
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
    /**
     * Launches the apply Upgrade screen by loading the apply upgrade fxml, and choose apply upgrade screen controller
     * @param playerManager The PlayerManager instance associated with the game.
     */
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
    /**
     * Launches the choose round difficulty screen by loading the choose round difficulty fxml, and choose round difficulty screen controller
     * @param playerManager The PlayerManager instance associated with the game.
     */
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
    /**Launches main screen, by loading the main screen fxml, and main screen controller
     * @param playerManager The PlayerManager instance is associated with the game
     * */
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
    /**Launches win round screen, by loading the round results fxml, and Round Results controller
     * @param playerManager The PlayerManager instance is associated with the game
     * */
    private void launchWonRoundScreen(PlayerManager playerManager) {
        try {
            FXMLLoader wonRoundScreenLoader = new FXMLLoader(getClass().getResource("/fxml/round_results_screen.fxml"));
            wonRoundScreenLoader.setControllerFactory(param -> new RoundResultsController(playerManager));
            Parent setupParent = wonRoundScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Round Results Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**Launches game completion screen, by loading the game complete fxml, and GameCompletionScreen controller
     * @param playerManager The PlayerManager instance is associated with the game
     * */
    private void launchGameCompletionScreen(PlayerManager playerManager) {
        try {
            FXMLLoader gameCompletionScreenLoader = new FXMLLoader(getClass().getResource("/fxml/game_complete_screen.fxml"));
            gameCompletionScreenLoader.setControllerFactory(param -> new GameCompletionScreenController(playerManager));
            Parent setupParent = gameCompletionScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Game Completion Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Clears the content of the pane.
     */
    public void clearPane() {
        pane.getChildren().removeAll(pane.getChildren());
    }
}
