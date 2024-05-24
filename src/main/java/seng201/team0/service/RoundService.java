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
    private List<Cart> cartsInRound;
    private String mainGameScreenRoundText;
    private boolean isSuccess;

    /**
     * constructs a RoundService with a given Round.
     * @param round the round
     */
    public RoundService(Round round){
        this.cartsInRound = round.getCartsInRound();
        this.mainGameScreenRoundText = "";
        this.isSuccess = false;
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
        cartsInRound = round.getCartsInRound();
        List<Tower> towersInGame = player.getTowersInGame();
        List<Integer> successfullyFilledCarts = new ArrayList<>();
        System.out.println("Track Distance: " + round.getTrackDistance());
        StringBuilder strBuilder = new StringBuilder(mainGameScreenRoundText);

        strBuilder.append("\n Number of carts in round: ").append(cartsInRound.size());

        for (Cart cart : cartsInRound) {
            int cartTimeOnTrack = (int) (round.getTrackDistance() / cart.getCartSpeed());
            int currentCartSize = 0;
            boolean isMatched = false;
            strBuilder.append("\n\n----------------------------------------------------------- Cart ").append(cart.getCartID() + 1).append(" -----------------------------------------------------------\n Resource Type 1: ").append(cart.getPrimaryCartResourceType()).append(" ------- Resource Type 2: ").append(cart.getSecondaryCartResourceType()).append(" ------- Size: ").append(cart.getCartSize()).append(" ------- Cart Speed: ").append(cart.getCartSpeed()).append("m/s  ............is going round the track");

            for (Tower tower : towersInGame) {
                if (!isMatched&&(Objects.equals(cart.getPrimaryCartResourceType() , tower.getTowerResourceType())) || (Objects.equals(cart.getSecondaryCartResourceType() , tower.getTowerResourceType()))) {
                    int numTowerReloads = Math.floorDiv(cartTimeOnTrack, tower.getTowerReloadSpeed());

                    strBuilder.append("\n").append(tower.getTowerName()).append(" tower with reload speed of ").append(tower.getTowerReloadSpeed()).append("m/s ------- Matches with cart ").append(cart.getCartID()).append("! The cart is on the track for ").append(cartTimeOnTrack).append("s");
                    strBuilder.append("\nCart is being filled: ").append(currentCartSize).append("kg ");
                    for (int i = 0; i <= numTowerReloads; i++) {
                        currentCartSize += tower.getTowerResourceAmount();

                        strBuilder.append("--------> ").append(currentCartSize).append("kg. ");
                    }
                    isMatched = true;

                    strBuilder.append(" Cart is filled to ").append(currentCartSize).append(" kgs after ").append(numTowerReloads + 1).append(" reload/s");
                }
            }
            if (currentCartSize >= cart.getCartSize()) {
                successfullyFilledCarts.add(cart.getCartID());

                strBuilder.append(" You successfully filled Cart ").append(cart.getCartID() + 1).append("!");
            } else{
                strBuilder.append("\nOh no, You didn't manage to fill cart ").append(cart.getCartID() + 1);
            }
        }
        mainGameScreenRoundText = strBuilder.toString();
        round.setMainGameScreenText(mainGameScreenRoundText);
        round.setNumCartsFilled( successfullyFilledCarts.size() );
        if (successfullyFilledCarts.size() >= ((cartsInRound.size() /2 ))){
            player.increaseNumRoundsWon();
            round.setRoundSuccess(true);
            isSuccess = true;
        }else{
            player.increaseNumRoundsLost();
            round.setRoundSuccess(false);
            isSuccess = false;
        }
    }
    /**
     * stores boolean of round success
     * @return boolean true if success
     */
    public boolean isSuccess() {
        return isSuccess;
    }
}
