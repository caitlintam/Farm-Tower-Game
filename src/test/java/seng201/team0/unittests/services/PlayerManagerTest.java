package seng201.team0.unittests.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.PlayerManager;
import seng201.team0.models.Player;
import seng201.team0.models.Round;
import java.util.function.Consumer;

public class PlayerManagerTest {
    private PlayerManager playerManager;
    private Consumer<PlayerManager> testConsumer;
    private Runnable testRunnable;
    private Round testRound;
    private boolean consumerCalled;
    private boolean runnableCalled;

    @BeforeEach
    public void setup() {
        consumerCalled = false;
        runnableCalled = false;

        testConsumer = (pm) -> consumerCalled = true;
        testRunnable = () -> runnableCalled = true;
        testRound = new Round(new Player("Test Player", 0), 0, 0);

        playerManager = new PlayerManager(
                testConsumer, testConsumer, testRunnable, testConsumer,
                testConsumer, testConsumer, testConsumer, testConsumer,
                testConsumer, testConsumer, testConsumer, testConsumer) {
            @Override
            public void launchRandomEventScreen() {
                testConsumer.accept(this);
            }
            @Override
            public void launchHomeScreen() {
                testConsumer.accept(this);
            }
            @Override
            public void launchWonRoundScreen() {
                testConsumer.accept(this);
            }
            @Override
            public void launchMainGameScreen() {
                testConsumer.accept(this);
            }
        };
        playerManager.getPlayer().setMoney(0);
    }

    @Test
    public void testToHomeOrRandomEventOrGameFinish_GameComplete() {
        playerManager.setNumGameRounds(1);
        playerManager.toHomeOrRandomEventOrGameFinish();
        assertTrue(consumerCalled);
    }
    @Test
    public void testToHomeOrRandomEventOrGameFinish_NormalRound() {
        playerManager.setNumGameRounds(5);
        playerManager.toHomeOrRandomEventOrGameFinish();
        assertTrue(consumerCalled);
    }

    @Test
    public void testEvaluateRoundSuccess_Success() {
        playerManager.setRoundSuccess(true);
        playerManager.setEarnedMoney(5);

        double initialMoney = playerManager.getPlayer().getMoney();
        playerManager.evaluateRoundSuccess();
        double expectedMoney = initialMoney + playerManager.getEarnedMoney();

        assertEquals(expectedMoney, playerManager.getPlayer().getMoney());
        assertTrue(consumerCalled);
    }

    @Test
    public void testEvaluateRoundSuccess_Failure() {
        playerManager.setRoundSuccess(false);

        double initialMoney = playerManager.getPlayer().getMoney();
        playerManager.evaluateRoundSuccess();

        assertEquals(initialMoney, playerManager.getPlayer().getMoney());
        assertTrue(consumerCalled);
    }
    @Test
    public void testLaunchScreens() {
        consumerCalled = false;

        playerManager.launchSetupScreen();
        assertTrue(consumerCalled);

        consumerCalled = false;
        playerManager.launchTowerSetUpScreen();
        assertTrue(consumerCalled);

        consumerCalled = false;
        playerManager.launchHomeScreen();
        assertTrue(consumerCalled);

        consumerCalled = false;
        playerManager.launchMainGameScreen();
        assertTrue(consumerCalled);

        consumerCalled = false;
        playerManager.launchRandomEventScreen();
        assertTrue(consumerCalled);

        consumerCalled = false;
        playerManager.launchWonRoundScreen();
        assertTrue(consumerCalled);

        consumerCalled = false;
        playerManager.launchShopScreen();
        assertTrue(consumerCalled);

        consumerCalled = false;
        playerManager.launchInventoryScreen();
        assertTrue(consumerCalled);

        consumerCalled = false;
        playerManager.launchApplyUpgradeScreen();
        assertTrue(consumerCalled);

        consumerCalled = false;
        playerManager.launchChooseRoundDifficultyScreen();
        assertTrue(consumerCalled);
    }

    @Test
    public void testCloseScreens() {
        runnableCalled = false;

        playerManager.closeSetupScreen();
        assertTrue(runnableCalled);

        runnableCalled = false;
        playerManager.closeTowerSetUpScreen();
        assertTrue(runnableCalled);

        runnableCalled = false;
        playerManager.closeMainScreen();
        assertTrue(runnableCalled);

        runnableCalled = false;
        playerManager.closeChooseRoundDifficultyScreen();
        assertTrue(runnableCalled);

        runnableCalled = false;
        playerManager.closeShopScreen();
        assertTrue(runnableCalled);

        runnableCalled = false;
        playerManager.closeApplyUpgradeScreen();
        assertTrue(runnableCalled);

        runnableCalled = false;
        playerManager.closeWonRoundScreen();
        assertTrue(runnableCalled);

        runnableCalled = false;
        playerManager.closeRandomEventScreen();
        assertTrue(runnableCalled);

        runnableCalled = false;
        playerManager.closeInventoryScreen();
        assertTrue(runnableCalled);
    }
}