package seng201.team0;
import java.util.ArrayList;
import seng201.team0.models.Cart;
import seng201.team0.models.Tower;

import java.util.Collections;
import java.util.List;

public class CartManager {
    private List<Cart> carts;
    private List<Cart> cartsInRound;
    private List<String> potentialCartResourceTypes;
    private PlayerManager playerManager;
    private TowerManager towerManager;
    private List<String> cartsInRoundResourceTypes;
    private int numberOfCarts;
    // private int currentCartSize;
    //privcate int num_reloads = 0;


    public List<Cart> getCartsInRound() {
        return cartsInRound;
    }
    public CartManager(List<Cart> cartsInRound){
        generateNewCartsInGame();
        this.cartsInRound = cartsInRound;
    }
    public CartManager(PlayerManager playerManager, TowerManager towerManager) {
        this.playerManager = playerManager;
        this.towerManager = towerManager;
        // this.carts = new ArrayList<>();
        this.cartsInRound = new ArrayList<>();
//        carts.addAll(List.of(
//                new Cart(100, "Pigs", 20),
//                new Cart(70, "Cows", 10),
//                new Cart(100, "Hay", 40),
//                new Cart(90, "Timber", 10),
//                new Cart(120, "Water", 5),
//                new Cart(50, "Steel", 30),
//                new Cart(60, "Corn", 10),
//                new Cart(120, "Wheat", 5),
//                new Cart(100, "Chickens", 40))))
        setPotentialCartResourceTypes();
        this.numberOfCarts = playerManager.getTowersInGame().size();
    }

    // gives each cart an id
    public void setPotentialCartResourceTypes() {
        for (Tower tower : towerManager.getDefaultTowers()) {
            potentialCartResourceTypes.add(tower.getTowerResourceType());
        }
    }

    public int getCartID(Cart cart) {
        return cart.getCartID();
    }


    // call this function when starting game, generates carts based on resource type
    public void generateNewCartsInGame() {
        // Clear existing carts
        cartsInRound.clear();
        shuffleCartsInGameResourceTypes();
        for (int i = 0; i < numberOfCarts; i++) {

            //     Collections.shuffle(tower);
            String resourceType = cartsInRoundResourceTypes.get(i);
            // SET CART SIZES AND SPEEDS( randomise? )
            //     int cartSize = tower.getTowerResourceAmount(); // Assume the cart size is based on the tower resource amount
            //       int cartSpeed =
            int cartID = i;
//            cartsInRound.add(new Cart(cartID, cartSize, resourceType, cartSpeed));
        }
    }

    // call this somewhere need to reset resource types
    // shuffles the resource types, for number of carts
    public void shuffleCartsInGameResourceTypes() {
        Collections.shuffle(potentialCartResourceTypes);
        // gets sublist of potential carts == size of towers/carts in game
        cartsInRoundResourceTypes = new ArrayList<String>(potentialCartResourceTypes.subList(0, numberOfCarts));
    }
}

