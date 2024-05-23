package seng201.team0.models;

import seng201.team0.service.CartService;
import java.util.List;
/**
 * Represents a round in the game.
 */
public class Round {
    private final int roundNumber;
    private final int trackDistance;
    private boolean success;
    private final int earnedMoney;
    private int numCartsFilled;
    private String mainGameScreenText;
    private List<Cart> cartsInRound;
    private final Player player;
    /**
     * Constructs a new round with the specified player, round number, and track distance.
     * @param player the player participating in the round
     * @param roundNumber the number of the round
     * @param trackDistance the distance of the track in the round
     */
    public Round(Player player,int roundNumber, int trackDistance){
        this.roundNumber = roundNumber;
        this.trackDistance = trackDistance;
        this.success = false;
        this.earnedMoney = calculateEarnedMoney();
        CartService cartService = new CartService(player);
        cartService.generateNewCartsInGame();
        this.cartsInRound = cartService.getCartsInRound();
        this.player = player;
    }
    /**
     * Gets the player participating in the round.
     * @return the player participating in the round
     */
    public Player getPlayer(){
        return player;
    }
    /**
     * Gets the number of the round.
     * @return the number of the round
     */
    public int getRoundNumber(){
        return roundNumber;
    }
    /**
     * Gets the distance of the track in the round.
     * @return the distance of the track in the round
     */
    public int getTrackDistance(){
        return trackDistance;
    }
    /**
     * Gets the number of carts filled in the round.
     * @return the number of carts filled in the round
     */
    public int getNumCartsFilled(){
        return numCartsFilled;
    }
    /**
     * Sets the number of carts filled in the round.
     * @param numCartsFilled the number of carts filled in the round
     */
    public void setNumCartsFilled(int numCartsFilled){
        this.numCartsFilled = numCartsFilled;
    }
    /**
     * Checks if the round was successful.
     * @return true if the round was successful, otherwise false
     */
    public boolean isSuccess(){
        return success;
    }
    /**
     * Sets the success status of the round.
     * @param success the success status of the round
     */
    public void setRoundSuccess(boolean success){
        this.success = success;
    }
    /**
     * Gets the amount of money earned in the round.
     * @return the amount of money earned in the round
     */
    public int getEarnedMoney(){
        return earnedMoney;
    }
    /**
     * Calculates the amount of money earned in the round based on the round number.
     * @return the amount of money earned in the round
     */
    private int calculateEarnedMoney(){
        return (roundNumber+1)*12;
    }
    /**
     * Sets the main game screen text for the round.
     * @param text the main game screen text for the round
     */
    public void setMainGameScreenText(String text) {
        this.mainGameScreenText = text;
    }
    /**
     * Gets the main game screen text for the round.
     * @return the main game screen text for the round
     */
    public String getMainGameScreenText(){
        return mainGameScreenText;
    }
    /**
     * Gets the list of carts in the round.
     * @return the list of carts in the round
     */
    public List<Cart> getCartsInRound(){
        return cartsInRound;
    }
    /**
     * Sets the list of carts in the round.
     * @param carts the carts used in the round
     */
    public void  setCartsInRound(List<Cart> carts){
        this.cartsInRound = carts;
    }



}
