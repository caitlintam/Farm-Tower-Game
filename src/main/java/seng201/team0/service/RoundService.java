package seng201.team0.service;

import seng201.team0.models.Cart;
import seng201.team0.models.Player;
import seng201.team0.models.Round;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Service class to run a round of the game. Handles calculation of round success.
 */
public class RoundService {
    private final List<Cart> cartsInRound;
    private String mainGameScreenRoundText;

    /**
     * constructs a RoundService with a given Round.
     * @param round the round
     */
    public RoundService(Round round){
        this.cartsInRound = round.getCartsInRound();
        this.mainGameScreenRoundText = "";
    }
    /**
     * Runs a round of the game by executing game logic.
     * Creates running string of what's happening in game with calculation of the cart Time on track, dependent on the rounds track distance and carts speed for each cart.
     * Tries to find a match of resource type between carts and towers in game.
     * If a match found, cart is filled for a number of reloads depending on tower reload speed and cart speed on the rounds track distance.
     * If cart is a match  and is filled by the end of the track distance, round is won.
     * If cart not filled by end of round, or match not found, round lost.
     * Sets the round attribute of number of carts filled.
     * In/decreases the players count of number of rounds won/lost
     * @param round the round
     * @param player the player
     */
    public void runRound(Round round, Player player) {
        mainGameScreenRoundText += "------- Running Round " + (round.getRoundNumber()+1) +  " ------";

        List<Integer> successfullyFilledCarts = new ArrayList<Integer>();
        System.out.println("Track Distance: " + round.getTrackDistance());
        mainGameScreenRoundText += "\n Number of carts in round: " + cartsInRound.size();

        for (Cart cart : cartsInRound) {
            int cartTimeOnTrack = (int) (round.getTrackDistance() / cart.getCartSpeed());
            int currentCartSize = 0;
            boolean isMatched = false;
            mainGameScreenRoundText += "\n\n----------------------------------------------------------- Cart " + (cart.getCartID()+1) + " -----------------------------------------------------------\n Resource Type 1: "+ cart.getPrimaryCartResourceType() + " ------- Resource Type 2: " + cart.getSecondaryCartResourceType() + " ------- Size: "+ cart.getCartSize() + " ------- Cart Speed: " + cart.getCartSpeed()+  "m/s  ............is going round the track";

            for (Tower tower : player.getTowersInGame()) {
                if (!isMatched&&(Objects.equals(cart.getPrimaryCartResourceType() , tower.getTowerResourceType())) || (Objects.equals(cart.getSecondaryCartResourceType() , tower.getTowerResourceType()))) {
                    int numTowerReloads = Math.floorDiv(cartTimeOnTrack, tower.getTowerReloadSpeed());
                    mainGameScreenRoundText+= "\n"+ tower.getTowerName()+ " tower with reload speed of " + tower.getTowerReloadSpeed() + "m/s ------- Matches with cart " + cart.getCartID() +"! The cart is on the track for " + cartTimeOnTrack+"s";
                    mainGameScreenRoundText += "\nCart is being filled: " + currentCartSize + "kg " ;
                    for (int i = 0; i <= numTowerReloads; i++) {
                        currentCartSize += tower.getTowerResourceAmount();
                        mainGameScreenRoundText += "--------> " + currentCartSize + "kg. ";
                    }
                    isMatched = true;
                    mainGameScreenRoundText += " Cart is filled to "+ currentCartSize + " kgs after " + (numTowerReloads+1) + " reload/s";
                }
            }
            if (currentCartSize >= cart.getCartSize()) {
                mainGameScreenRoundText += " You successfully filled Cart " + (cart.getCartID()+1) + "!";
                successfullyFilledCarts.add(cart.getCartID());
            } else{
                mainGameScreenRoundText += "\nOh no, You didn't manage to fill cart " + (cart.getCartID()+1);
            }


        }
        round.setMainGameScreenText(mainGameScreenRoundText);
        round.setNumCartsFilled( successfullyFilledCarts.size() );
        if (successfullyFilledCarts.size() >= ((cartsInRound.size() /2 ))){
            player.increaseNumRoundsWon();
            round.setRoundSuccess(true);
        }else{
            player.increaseNumRoundsLost();
            round.setRoundSuccess(false);
        }
    }
}
