package seng201.team0;

import seng201.team0.models.Cart;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoundService {
    private final CartManager cartManager;
    private final PlayerManager playerManager;
    private int currentCartSize;
    public RoundService(PlayerManager playerManager, CartManager cartManager){
        this.playerManager = playerManager;
        this.cartManager = cartManager;
    }
    private String mainGameScreenRoundText;

    public void runRound(int trackDistance) {
        mainGameScreenRoundText = " ";
        mainGameScreenRoundText += "------- Running Round " + (playerManager.getCurrentRoundNumber()+1) +  " ------";

        List<Integer> successfullyFilledCarts = new ArrayList<Integer>();
        List<Integer> failedFilledCarts = new ArrayList<Integer>();
        cartManager.generateNewCartsInGame();
        mainGameScreenRoundText += "\n num of carts in round" + cartManager.getCartsInRound().size();
 //       System.out.println("cartsinRound" + cartManager.getCartsInRound());
        // for each cart;

        for (Cart cart : cartManager.getCartsInRound()) {
            currentCartSize = 0;
            mainGameScreenRoundText += "\n\n----------------------------------------------------------- Cart " + (cart.getCartID()+1) + " -----------------------------------------------------------\n Resource Type 1: "+ cart.getPrimaryCartResourceType() + " ------- Resource Type 2: " + cart.getSecondaryCartResourceType() + " ------- Size: "+ cart.getCartSize() + " ------- Cart Speed: " + cart.getCartSpeed()+  "  ............is going round the track ";
            // for each tower
            boolean isMatched = false;
            for (Tower tower : playerManager.getTowersInGame()) {
                // if the resources types match
                if (!isMatched&&(Objects.equals(cart.getPrimaryCartResourceType() , tower.getTowerResourceType())) | (Objects.equals(cart.getSecondaryCartResourceType() , tower.getTowerResourceType()))) {
                    // calculate the carts time on the track..  turn time to integer
                    int cartTimeOnTrack = (int) (trackDistance / cart.getCartSpeed());
                    mainGameScreenRoundText+= "\n"+ tower.getTowerName()+ " tower with reload speed of " + tower.getTowerReloadSpeed() + "m/s ------- Matches with cart: " + cart.getCartID() +"! The cart is on the track for " + cartTimeOnTrack+"s";
                    int numTowerReloads = (int) (Math.floorDiv(cartTimeOnTrack, tower.getTowerReloadSpeed()));
                    // for each reload of cart
                    mainGameScreenRoundText += "\nCart is being filled: " + currentCartSize + "kg " ;

                    for (int i = 0; i <= numTowerReloads; i++) {
                        currentCartSize += tower.getTowerResourceAmount();
                        mainGameScreenRoundText += "--------> " + currentCartSize + "kg ";
                    }
                    mainGameScreenRoundText += ". Cart is filled to "+ currentCartSize + "kgs after " + (numTowerReloads+1) + " reload/s";
                    isMatched = true;
                }
            }
            // once done all possible tower reloads, check if filled capacity (>=size) or not ( <size)
            if (currentCartSize >= cart.getCartSize()) {
                mainGameScreenRoundText += " You successfully filled Cart " + (cart.getCartID()+1) + "!";
             //   System.out.println("You successfully filled cart " + cart.getCartID()  );
                // adds succesfully filled cart to list
                successfullyFilledCarts.add(cart.getCartID());
                // increase money
                // ////////////////////////////////////////////////// DOTHIS          setMoney(money *= numReloads);///////////////////////////////////////////////////////
                // launch round win screen

            } else if (currentCartSize < cart.getCartSize()) {
                mainGameScreenRoundText += "\nOh no, none of your towers matched cart " + (cart.getCartID()+1) + " resource type.";
                mainGameScreenRoundText += " You didn't manage to fill cart " + (cart.getCartID()+1);
      //          System.out.println("Uh Oh, you didn't manage to fill cart " + cart.getCartID() );
                // adds unsucesfily filled cart to list
                failedFilledCarts.add(cart.getCartID());
                //launch round lose screen
            }
            playerManager.setNumCartsFilled( successfullyFilledCarts.size() );
            playerManager.setMainGameScreenText(mainGameScreenRoundText);
      //      System.out.println("---------------------------------------------");

        }
        // once all carts have been through round
        // if all carts filled ( failed is empty == true ) won, otherwise false, have a cart not filled

        int numCarts = cartManager.getCartsInRound().size();
        if (successfullyFilledCarts.size() >= ((numCarts/2 ))){
            playerManager.increaseNumRoundsWon();
            playerManager.setRoundSuccess(true);
        }else{
            playerManager.increaseNumRoundsLost();
            playerManager.setRoundSuccess(false);
         
        }

    }
}
