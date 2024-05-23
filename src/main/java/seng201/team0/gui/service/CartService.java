package seng201.team0.gui.service;
import java.util.ArrayList;

import seng201.team0.PlayerManager;
import seng201.team0.TowerManager;
import seng201.team0.models.Cart;
import seng201.team0.models.Player;
import seng201.team0.models.Tower;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CartService {
    private final Player player;
    private List<Cart> carts;
    private List<Cart> cartsInRound;
    private List<String> potentialCartResourceTypes;
    private PlayerManager playerManager;
    private TowerManager towerManager;
    private List<String> cartsInRoundResourceTypes;
    private Random random;
    private int numberOfCarts;

    public List<Cart> getCartsInRound() {
        return cartsInRound;
    }


    public CartService(Player player) {
   //     this.playerManager = p;
        this.towerManager = new TowerManager();
        this.cartsInRound = new ArrayList<Cart>();
        this.potentialCartResourceTypes = new ArrayList<String>();
        this.random = new Random(201);
        this.player = player;
        setPotentialCartResourceTypes();

    }

    // gives each cart an id
    public void setPotentialCartResourceTypes() {
        this.potentialCartResourceTypes = new ArrayList<>();
        for (Tower tower : towerManager.getDefaultTowers()) {
            potentialCartResourceTypes.add(tower.getTowerResourceType());
        }
    }
    public void setNumberOfCarts(){
        this.numberOfCarts = player.getTowersInGame().size();
    }

    // call this function when starting game, generates carts based on resource type
    public void generateNewCartsInGame() {
        System.out.println("------ Generating New Carts -------");
        // Clear existing carts
        setNumberOfCarts();
        shuffleCartsInGameResourceTypes();
        List<Tower> towersInGame = player.getTowersInGame();
        //
        cartsInRound.clear();

        for (int i = 0; i < numberOfCarts; i++) {
            String resourceType = cartsInRoundResourceTypes.get(i);
            String primaryResourceType = cartsInRoundResourceTypes.get(i);
            String secondaryResourceType = getRandomResTypeDiffFromPrimary(primaryResourceType);
            int cartSize = generateRandomCartSize(towersInGame.get(i).getTowerResourceAmount()) ; // Assume the cart size is based on the tower resource amount
            int cartSpeed = generateRandomCartSpeed(towersInGame.get(i).getTowerReloadSpeed());
            int cartID = i;
            System.out.println("Cart "+cartID + " -- Size: " + cartSize + " -- Primary Res Type: " + primaryResourceType + " -- Secondary Res Type: " + secondaryResourceType + " -- Cart Speed: "+cartSpeed);
            cartsInRound.add(new Cart(cartID, cartSize, primaryResourceType, secondaryResourceType, cartSpeed));

        }
        System.out.println("My Towers: " + player.getTowersResTypeInGame());
        System.out.println("-------------------");

    }

    // returns another type different to the first res type
    private String getRandomResTypeDiffFromPrimary(String primaryResourceType) {
        List<String> otherTypes = new ArrayList<String>(potentialCartResourceTypes);
        otherTypes.remove(primaryResourceType);
        return otherTypes.get(random.nextInt(otherTypes.size()));
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
        System.out.println("New  Carts in Round Resource Types: " + cartsInRoundResourceTypes);
    }

}

