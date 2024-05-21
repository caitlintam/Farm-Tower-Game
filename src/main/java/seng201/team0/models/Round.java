package seng201.team0.models;

//import java.util.List;
//
//public class Round {
//    private int roundNumber;
//    private int roundDifficulty;
//    private double trackDistance;
//    private int numberOfCarts;
//    private List<Cart> carts;
//    private boolean roundComplete;
//
//    public Round(int roundNumber, List<Cart> carts, int roundDifficulty, double trackDistance, int numberOfCarts){
//        this.roundDifficulty = roundDifficulty;
//        this.trackDistance = trackDistance;
//        this.numberOfCarts = numberOfCarts;
//        this.roundNumber = roundNumber;
//        this.carts = carts;
//        this.roundComplete = false;
//    }
//    public int getRoundNumber(){
//        return roundNumber;
//    }
//    public List<Cart> getCarts(){
//        return carts;
//    }
//    public boolean isRoundComplete(){
//        return roundComplete;
//    }
//    public void setRoundComplete(boolean roundComplete) {
//        this.roundComplete = roundComplete;
//    }
//    public int getRoundDifficulty(){
//        return roundDifficulty;
//    }
//    public double getTrackDistance(){
//        return trackDistance;
//    }
//    public int getNumberOfCarts(){
//        return numberOfCarts;
//    }
//
//}


import java.util.List;

public interface Round {
    void startRound();
    void evaluateRoundSuccess();
    List<Integer> getRandomEventsRoundList();
    void runRound(int trackDistance);

}
