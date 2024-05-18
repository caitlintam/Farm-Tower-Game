package seng201.team0;
import java.util.ArrayList;
import seng201.team0.models.Cart;
import seng201.team0.models.Tower;
import seng201.team0.PlayerManager;

import java.util.Collections;
import java.util.List;

public class CartManager {
    private List<Cart> carts;
    private List<Cart> cartsInGame;
    private List<String> potentialCartResourceTypes;
    private PlayerManager playerManager;
    // private int currentCartSize;
    //privcate int num_reloads = 0;




    public CartManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
       // this.carts = new ArrayList<>();
        this.cartsInGame = new ArrayList<>();
//        carts.addAll(List.of(
//                new Cart(100, "Pigs", 20),
//                new Cart(70, "Cows", 10),
//                new Cart(100, "Hay", 40),
//                new Cart(90, "Timber", 10),
//                new Cart(120, "Water", 5),
//                new Cart(50, "Steel", 30),
//                new Cart(60, "Corn", 10),
//                new Cart(120, "Wheat", 5),
//                new Cart(100, "Chickens", 40)));
        for (Tower towers: TowerManager.getDefaultTowers()){

        }
//

    }
    // gives each cart an id
    private int getCartNumber(Cart cart){return cart.getCartNumber();}
    // call this function when starting game, generates carts based on resource type
    public void generateCartsBasedOnTowers() {
        // Clear existing carts
        int i = 0;
        for (Tower tower : playerManager.getTowersInGame()) {

       //     Collections.shuffle(tower);
       //     String resourceType = tower.getTowerResourceType();
            // SET CART SIZES AND SPEEDS( randomise? )
            //     int cartSize = tower.getTowerResourceAmount(); // Assume the cart size is based on the tower resource amount
            //       int cartSpeed =
            int cartNumber = i;

            cartsInGame.add(new Cart(cartNumber, cartSize, resourceType, cartSpeed));
        }
    }

//    public void addCart(Cart cart) {
//        carts.add(cart);
//    }
//
//    public void selectRandomCarts() {
//        Collections.shuffle(carts);
//        selectedCarts = new ArrayList<>(carts.subList(0, 3));
//    }
//
//    public List<Cart> getSelectedCarts() {
//        return selectedCarts;
//    }

    // methods:
    //fill cart

}

    //new cart
    //round complete