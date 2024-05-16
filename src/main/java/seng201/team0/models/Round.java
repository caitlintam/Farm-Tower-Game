package seng201.team0.models;

import java.util.List;

public class Round {
    private int roundNumber;
    private int roundDifficulty;
    private double trackDistance;
    private int numberOfCarts;
    private List<Cart> carts;

    public Round(int roundNumber, List<Cart> carts, int roundDifficulty, double trackDistance, int numberOfCarts){
        this.roundDifficulty = roundDifficulty;
        this.trackDistance = trackDistance;
        this.numberOfCarts = numberOfCarts;
        this.roundNumber = roundNumber;
        this.carts = carts;
    }
    public int getRoundNumber(){
        return roundNumber;
    }
    public List<Cart> getCarts(){
        return carts;
    }
    public boolean isRoundComplete(){
        /// implement////
        return true;
    }
    public int getRoundDifficulty(){return roundDifficulty;}
    public double getTrackDistance(){return trackDistance;}
    public int getNumberOfCarts(){return numberOfCarts;}

}
