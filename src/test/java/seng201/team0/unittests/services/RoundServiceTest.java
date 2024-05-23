package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Cart;
import seng201.team0.models.Player;
import seng201.team0.models.Round;
import seng201.team0.models.Tower;
import seng201.team0.service.RoundService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for the RoundService class.
 */
public class RoundServiceTest {

    private List<Cart> cartsInRound;
    private List<Tower> towersInGame;
    private Player player;
    private Round round;
    private RoundService roundService;
    /**
     * initialises the objects required for testing before each test case.
     */
    @BeforeEach
    void init(){
        player = new Player("testPlayer",0);
        round = new Round(player,1,10);
        cartsInRound = new ArrayList<Cart>();
        towersInGame = new ArrayList<Tower>();
        roundService = new RoundService(round);
    }
    /**
     * Tests the runRound method of the RoundService class, for a mock winning round.
     */

        @Test
    void testRunRoundWon(){
        Tower tower0 = new Tower("tower0", 100, "resType1", 1, 1, 1 , "In-Game");
        Tower tower1 = new Tower("tower1", 100, "resType3", 1, 1,1 , "In-Game");
        Tower tower2 = new Tower("tower2", 100, "resType4", 1, 1,1 , "In-Game");
        player.addTowersToInventory(tower0);
        player.addTowersToInventory(tower1);
        player.addTowersToInventory(tower2);
        player.setTowersInGame();

        Cart cart0 = new Cart(1,3,"resType1","resType2",2);
        Cart cart1 = new Cart(2,1000000,"resType1","resType4",1);
        Cart cart2 = new Cart(3,8,"resType1","resType4",2);
        cartsInRound.add(cart0);
        cartsInRound.add(cart1);
        cartsInRound.add(cart2);
        round.setCartsInRound(cartsInRound);

        roundService.runRound(round, player);

        assertEquals(2, round.getNumCartsFilled());
        assertTrue(roundService.isSuccess());
        assertEquals(1, player.getNumRoundsWon());
        assertEquals(0,player.getNumRoundsLost());
    }
    /**
     * Tests the runRound method of the RoundService class, for a mock losing round.
     */

    @Test
    void testRunRoundLost(){
        Tower tower0 = new Tower("tower0", 10, "resType1", 100, 1, 1 , "In-Game");
        Tower tower1 = new Tower("tower1", 10, "resType3", 200, 1,1 , "In-Game");
        Tower tower2 = new Tower("tower2", 100, "resType4", 100, 1,1 , "In-Game");
        player.addTowersToInventory(tower0);
        player.addTowersToInventory(tower1);
        player.addTowersToInventory(tower2);
        player.setTowersInGame();

        Cart cart0 = new Cart(1,3000,"resType1","resType2",2);
        Cart cart1 = new Cart(2,1000,"resType1","resType4",1);
        Cart cart2 = new Cart(3,800,"resType1","resType4",2);
        cartsInRound.add(cart0);
        cartsInRound.add(cart1);
        cartsInRound.add(cart2);
        round.setCartsInRound(cartsInRound);

        roundService.runRound(round, player);
        assertEquals(0, round.getNumCartsFilled());
        assertFalse(round.isSuccess());
        assertEquals(0, player.getNumRoundsWon());
        assertEquals(1,player.getNumRoundsLost());
    }


}
