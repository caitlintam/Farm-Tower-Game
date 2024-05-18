package seng201.team0;
import java.util.ArrayList;
import seng201.team0.models.Cart;
import seng201.team0.models.Tower;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CartManager {
    private List<Cart> carts;
    private List<Cart> cartsInRound;
    private List<String> potentialCartResourceTypes;
    private PlayerManager playerManager;
    private TowerManager towerManager;
    private List<String> cartsInRoundResourceTypes;
    private Random random;
    private int numberOfCarts;
    // private int currentCartSize;
    //privcate int num_reloads = 0;


    public List<Cart> getCartsInRound() {
        return cartsInRound;
    }
    public CartManager(List<Cart> cartsInRound){
        this.random = new Random(201);
        generateNewCartsInGame();
        this.cartsInRound = cartsInRound;
    }
    public CartManager(PlayerManager playerManager, TowerManager towerManager) {
        this.playerManager = playerManager;
        this.towerManager = towerManager;
        // this.carts = new ArrayList<>();
        this.cartsInRound = new ArrayList<Cart>();
        this.potentialCartResourceTypes = new ArrayList<String>();
        this.random = new Random(201);
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

        System.out.println("From constructor, no carts " + numberOfCarts);
    }

    // gives each cart an id
    public void setPotentialCartResourceTypes() {
        this.potentialCartResourceTypes = new ArrayList<>();
        for (Tower tower : towerManager.getDefaultTowers()) {
            potentialCartResourceTypes.add(tower.getTowerResourceType());
        }
    }
    public void setNumberOfCarts(){
        this.numberOfCarts = playerManager.getTowersInGame().size();
    }
    public int getCartID(Cart cart) {
        return cart.getCartID();
    }


    // call this function when starting game, generates carts based on resource type
    public void generateNewCartsInGame() {
        System.out.println("------ Generating New Carts -------");
        // Clear existing carts
        setNumberOfCarts();
        shuffleCartsInGameResourceTypes();
        List<Tower> towersInGame = playerManager.getTowersInGame();


        for (int i = 0; i < numberOfCarts; i++) {
            String resourceType = cartsInRoundResourceTypes.get(i);
            int cartSize = generateRandomCartSize(towersInGame.get(i).getTowerResourceAmount()); // Assume the cart size is based on the tower resource amount
            int cartSpeed = generateRandomCartSpeed(towersInGame.get(i).getTowerReloadSpeed());
            int cartID = i;
            System.out.println("Cart "+cartID + " -- Size: " + cartSize + " -- Type: " + resourceType + " -- Speed: " + cartSpeed);
            cartsInRound.add(new Cart(cartID, cartSize, resourceType, cartSpeed));

        }
        System.out.println("-------------------");
    }
    private int generateRandomCartSize(int towerResourceAmount) {
        // Example: Cart size can be between 80% and 120% of tower resource amount
        int minSize = (int) (towerResourceAmount * 0.8);
        int maxSize = (int) (towerResourceAmount * 1.2);
        // gets random integer between 80-120% bounds for cart size
        return random.nextInt(maxSize - minSize + 1) + minSize;
    }

    private int generateRandomCartSpeed(int towerReloadSpeed) {
        // Example: Cart speed can be between 70% and 130% of a base value derived from tower resource amount
        int baseSpeed = towerReloadSpeed / 10; // Arbitrary scaling factor
        int minSpeed = (int) (baseSpeed * 0.7);
        int maxSpeed = (int) (baseSpeed * 1.3);
        // gets random integer between 80-120% bounds for cart speed
        return random.nextInt(maxSpeed - minSpeed + 1) + minSpeed;
    }
    // call this somewhere need to reset resource types
    // shuffles the resource types, for number of carts
    public void shuffleCartsInGameResourceTypes() {
        System.out.println("Shuffling Potential Cart Resource Types");
        Collections.shuffle(potentialCartResourceTypes);
        // gets sublist of potential carts == size of towers/carts in game
        System.out.println("Number of carts:  " + numberOfCarts);
        cartsInRoundResourceTypes = new ArrayList<String>(potentialCartResourceTypes.subList(0, numberOfCarts));
        System.out.println("New Carts in Round Resource Types: " + cartsInRoundResourceTypes);
    }
}

