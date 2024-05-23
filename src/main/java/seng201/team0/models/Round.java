package seng201.team0.models;

import seng201.team0.service.CartService;

import java.util.List;

public class Round {
   // private final CartService cartService;
    private int roundNumber;
    private int trackDistance;
    private boolean success;
    private int earnedMoney;
    private List<Integer> trackDistanceOptionsList;
    private int numCartsFilled;
    private String mainGameScreenText;

    private int currentCartSize;

    private List<Cart> cartsInRound;
    private Player player;

    public Round(Player player,int roundNumber, int trackDistance){
        this.roundNumber = roundNumber;
        this.trackDistance = trackDistance;
        this.success = false;
        this.earnedMoney = calculateEarnedMoney();
        CartService cartService = new CartService(player);
        cartService.generateNewCartsInGame();
        this.cartsInRound = cartService.getCartsInRound();
        this.player = player;
    //    this.cartService = cartService;
    }
    public Player getPlayer(){
        return player;
    }
    public int getRoundNumber(){
        return roundNumber;
    }
    public int getTrackDistance(){
        return trackDistance;
    }
    public void setTrackDistance(int trackDistance){
        this.trackDistance= trackDistance;
    }
    public int getNumCartsFilled(){
        return numCartsFilled;
    }
    public void setNumCartsFilled(int numCartsFilled){
        this.numCartsFilled = numCartsFilled;
    }
    public void setEarnedMoney(int earnedMoney){
        this.earnedMoney = earnedMoney;
    }
    public boolean isSuccess(){
        return success;
    }
    public void setRoundSuccess(boolean success){
        this.success = success;
    }
    public int getEarnedMoney(){
        return earnedMoney;
    }
    private int calculateEarnedMoney(){
        return (roundNumber+1)*12;
    }
    public void setMainGameScreenText(String text) {
        this.mainGameScreenText = text;
    }
    public String getMainGameScreenText(){
        return mainGameScreenText;
    }
    public List<Cart> getCartsInRound(){
        return cartsInRound;
    }
    // for testing
    public void  setCartsInRound(List<Cart> carts){
        this.cartsInRound = carts;
    }



}
