package seng201.team0.models;

import java.util.List;

public class RoundTask implements Runnable {
    private Round round;
    public RoundTask(Round round){
        this.round = round;
    }
    public void run(){
        System.out.println("Round Number " + round.getRoundNumber() + " started.");
        List<Cart> carts = round.getCarts();
        for (Cart cart: carts){
            // do something / call method to do something to carts
            System.out.println("FILL CART");
        }
        if (round.isRoundComplete()){
            System.out.println("Round " + round.getRoundNumber() + " completed.");
        }else{
            System.out.println("Round "+round.getRoundNumber() + "terminated.");
        }

    }
}
