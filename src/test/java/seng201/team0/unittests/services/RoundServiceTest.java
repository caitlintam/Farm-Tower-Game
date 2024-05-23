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

public class RoundServiceTest {

    private List<Cart> cartsInRound;
    private List<Tower> towersInGame;
    private Player player;
    private Round round;
    private RoundService roundService;
    @BeforeEach
    void init(){
        player = new Player("testPlayer",0);
        round = new Round(player,1,10);
        cartsInRound = new ArrayList<Cart>();
        towersInGame = new ArrayList<Tower>();
        round.setCartsInRound(cartsInRound);
        player.setTowersInGame(towersInGame);
        roundService = new RoundService(round);
    }

    @Test
    void testRunRound(){
        Cart cart0 = new Cart(1,100,"resType1","resType2",5);
        Cart cart1 = new Cart(2,200,"resType3","resType4",4);
        Cart cart2 = new Cart(2,10,"resType3","resType4",200);
        cartsInRound.add(cart0);
        cartsInRound.add(cart1);
        cartsInRound.add(cart2);
        Tower tower0 = new Tower("tower0", 1, "resType1", 1, 1, 1 , "In-Game");
        Tower tower1 = new Tower("tower1", 1, "resType3", 1, 1,1 , "In-Game");
        towersInGame.add(tower0);
        towersInGame.add(tower1);
        roundService.runRound(round, player);
        assertEquals(0, round.getNumCartsFilled());
        assertFalse(round.isSuccess());
        assertEquals(0, player.getNumRoundsWon());
        assertEquals(1,player.getNumRoundsLost());
    }
}
