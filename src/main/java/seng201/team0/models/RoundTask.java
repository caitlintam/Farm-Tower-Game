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
        try {
            Thread.sleep(2000); // time taken for round (based on track distance)
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        } round.isRoundComplete();
        if (round.isRoundComplete()){
            System.out.println("Round " + round.getRoundNumber() + " completed.");
        }else{
            System.out.println("Round "+round.getRoundNumber() + "terminated.");
        }

    }
}
