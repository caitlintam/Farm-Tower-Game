package seng201.team0.service;

import seng201.team0.models.Cart;
import seng201.team0.models.Player;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
/**
 * Service class to generate random carts for a round.
 */
public class CartService {
    private final Player player;
    private final List<Cart> cartsInRound;
    private List<String> potentialCartResourceTypes;
    private final Tower towerManager;
    private List<String> cartsInRoundResourceTypes;
    private final Random random;
    private int numberOfCarts;
    public List<Cart> getCartsInRound() {
        return cartsInRound;
    }

    /**
     * Constructs a CartService object with the specified player.
     * Initializes lists for carts in the current round and potential cart resource types.
     * Sets up a Tower manager and initializes a random number generator.
     * @param player The player associated with this CartService.
     */
    public CartService(Player player) {
        this.towerManager = new Tower();
        this.cartsInRound = new ArrayList<Cart>();
        this.potentialCartResourceTypes = new ArrayList<>();
        this.random = new Random(201);
        this.player = player;
        setPotentialCartResourceTypes();
    }
    /**
     * Sets the potential cart resource types based on the resource types of default towers.
     * This method populates the list of potential cart resource types by iterating over the resource types
     * of default towers
     */
    public void setPotentialCartResourceTypes() {
        this.potentialCartResourceTypes = new ArrayList<>();
        for (Tower tower : towerManager.getDefaultTowers()) {
            potentialCartResourceTypes.add(tower.getTowerResourceType());
        }
    }
    /**
     * Sets the number of carts based on the number of towers owned by the player.
     * This method retrieves the number of towers currently in-game for the player and sets it as the
     * number of carts for the current round.
     */
    public void setNumberOfCarts(){
        this.numberOfCarts = player.getTowersInGame().size();
    }
    /**
     * Generates new carts for the game round based on player's towers and their resource types.
     * This method generates new carts for the game round by clearing existing carts, determining the number of carts
     * based on the number of towers owned by the player, shuffling cart resource types, and creating new carts
     * with random sizes, speeds, and primary and secondary resource types.
     * The primary resource type is based on the carts in round resource types list, and the secondary resource type is
     * randomly selected from available resource types different from the primary type.
     * Cart size and speed are assumed to be based on the corresponding tower's resource amount and reload speed.
     */
    public void generateNewCartsInGame() {
        System.out.println("------ Generating New Carts -------");
        setNumberOfCarts();
        shuffleCartsInGameResourceTypes();
        List<Tower> towersInGame = player.getTowersInGame();
        cartsInRound.clear();
        for (int i = 0; i < numberOfCarts; i++) {
            String primaryResourceType = cartsInRoundResourceTypes.get(i);
            String secondaryResourceType = getRandomResTypeDiffFromPrimary(primaryResourceType);
            int cartSize = generateRandomCartSize(towersInGame.get(i).getTowerResourceAmount()) ;
            int cartSpeed = generateRandomCartSpeed(towersInGame.get(i).getTowerReloadSpeed());
            System.out.println("Cart "+i + " -- Size: " + cartSize + " -- Primary Res Type: " + primaryResourceType + " -- Secondary Res Type: " + secondaryResourceType + " -- Cart Speed: "+cartSpeed);
            cartsInRound.add(new Cart(i, cartSize, primaryResourceType, secondaryResourceType, cartSpeed));
        }
        System.out.println("My Towers: " + player.getTowersResTypeInGame());
        System.out.println("-------------------");
    }
    /**
     * Retrieves a random resource type different from the primary resource type.
     * @param primaryResourceType The primary resource type.
     * @return A random resource type different from the primary resource type.
     */
    private String getRandomResTypeDiffFromPrimary(String primaryResourceType) {
        List<String> otherTypes = new ArrayList<String>(potentialCartResourceTypes);
        otherTypes.remove(primaryResourceType);
        return otherTypes.get(random.nextInt(otherTypes.size()));
    }
    /**
     * Generates a random cart size based on the tower's resource amount.
     * @param towerResourceAmount The resource amount of the tower.
     * @return A random cart size.
     */
    private int generateRandomCartSize(int towerResourceAmount) {
        int minSize = (int) (towerResourceAmount * 0.8);
        int maxSize = (int) (towerResourceAmount * 1.2);
        return random.nextInt(maxSize - minSize + 1) + minSize;
    }
    /**
     * Generates a random cart speed based on the tower's reload speed.
     * @param towerReloadSpeed The reload speed of the tower.
     * @return A random cart speed.
     */
    private int generateRandomCartSpeed(int towerReloadSpeed) {
        int baseSpeed = towerReloadSpeed / 10;
        int minSpeed = (int) (baseSpeed * 0.7);
        int maxSpeed = (int) (baseSpeed * 1.3);
        return random.nextInt(maxSpeed - minSpeed + 1) + minSpeed;
    }
    /**
     * Shuffles the potential cart resource types and selects a sublist for the number of carts.
     * This method shuffles the potential cart resource types and selects a sublist with the size equal to the number
     * of carts in the current round. This ensures that each cart in the round has a unique resource type.
     */
    public void shuffleCartsInGameResourceTypes() {
        System.out.println("Shuffling Potential Cart Resource Types");
        Collections.shuffle(potentialCartResourceTypes);
        System.out.println("Number of carts:  " + numberOfCarts);
        cartsInRoundResourceTypes = new ArrayList<String>(potentialCartResourceTypes.subList(0, numberOfCarts));
        System.out.println("New  Carts in Round Resource Types: " + cartsInRoundResourceTypes);
    }
}

