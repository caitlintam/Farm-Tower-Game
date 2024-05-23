package seng201.team0.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import seng201.team0.PlayerManager;
/**
 * Represents a random event in the game that affects the player.
 */
public class RandomEvent {
    private PlayerManager playerManager;
    private List<Integer> randomEventRounds;
    private Random random;
    private String randomEventText;
    private Player player;
    /**
     * Constructs a new random event with the specified player manager and player.
     *
     * @param playerManager the player manager responsible for managing the player
     * @param player the player affected by the random event
     */
    public RandomEvent(PlayerManager playerManager, Player player) {
        this.playerManager = playerManager;
        this.random = new Random();
        this.randomEventRounds = new ArrayList<Integer>();
        this.player = player;

    }
    /**
     * Generates a random event and executes the corresponding action based on the event type.
     * The event type determines whether to increase tower level, decrease tower level, or break a tower.
     */
    public void generateRandomEvent() {
        int eventType = random.nextInt(3);
        switch (eventType) {
            case 0:
                executeLevelIncrease(); //method for increasing tower level
                break;
            case 1:
                executeLevelDecrease(); // method for decreasing tower level
                break;
            case 2:
                executeBreakTower(); // method to break a tower
                break;
        }
    }
    /**
     * Executes a random event where the level of a randomly selected tower is increased.
     * If no tower is available, a NullPointerException is caught and an error message is displayed.
     */
    private void executeLevelIncrease() {
        Tower tower = getRandomTower();
        try{
            tower.setTowerLevel(tower.getTowerLevel() + 1);
            System.out.println("Random Event: Tower " + tower.getTowerName() + "increased levels");
            String levelIncreaseText = tower.getTowerName() + "has increased levels to level: " + tower.getTowerLevel();
            this.randomEventText = levelIncreaseText;
        }catch(NullPointerException e){
            System.out.println("Uh Oh, error, no random tower generated");
        }
    }
    /**
     * Executes a random event where the level of a randomly selected tower is decreased.
     * If the tower's level is already at the minimum level (1), no action is taken.
     * If no tower is available, a NullPointerException is caught and an error message is displayed.
     */
    private void executeLevelDecrease() {
        Tower tower = getRandomTower();
        try{
            if (tower.getTowerLevel() > 1){
                tower.decreaseTowerLevel(tower);
                System.out.println("Random Event: Tower " + tower.getTowerName() + " decreased levels");
                String levelDecreaseText = "Random Event: " +tower.getTowerName() + " has decreased levels to level: " + tower.getTowerLevel();
                this.randomEventText = levelDecreaseText;
            }else{
                System.out.println("Random Event: Tower can't be decreased below level 1");
                String levelDecreaseText = "Random Event: Tower can't be decreased below level 1";
                this.randomEventText = levelDecreaseText;
            }
        }catch(NullPointerException e){
            System.out.println("Uh Oh, error, no random tower generated");
        }
    }
    /**
     * Executes a random event where a randomly selected tower is broken and removed from the player's inventory.
     * If no tower is available, a NullPointerException is caught and an error message is displayed.
     */
    private void executeBreakTower() {
        Tower tower = getRandomTower();
        try{
            player.removeTowerFromInventory(tower);
            String brokenTowerText = tower.getTowerName() + " broke! it has now been removed from your inventory";
            this.randomEventText = brokenTowerText;
        }catch(NullPointerException e){
            System.out.println("Uh Oh, error, no random tower generated");
        }
    }
    /**
     * Gets the text describing the most recent random event.
     * @return the text describing the random event
     */
    public String getRandomEventText(){
        return randomEventText;
    }
    /**
     * Gets the list of rounds when random events occur.
     *
     * @return the list of rounds when random events occur
     */
    public List<Integer> getRandomEventRounds() {
        return randomEventRounds;
    }
    /**
     * Retrieves a randomly selected tower from the player's tower inventory.
     * If the player's tower inventory is empty, returns null.
     * @return a randomly selected tower, or null if the inventory is empty
     */
    private Tower getRandomTower() {
        if (player.getTowerInventory().isEmpty()) {
            return null;
        } else {
            double usedTowerProbability = 0.8;
            if (random.nextDouble() < usedTowerProbability) {
                return player.getTowersInGame().get(random.nextInt(player.getTowersInGame().size()));
            } else {
                if (player.getReserveTowers().isEmpty()){ //  to stop invocation error or non postiive bound
                    return player.getTowersInGame().get(random.nextInt(player.getTowersInGame().size()));
                }else{
                    return player.getReserveTowers().get(random.nextInt(player.getReserveTowers().size()));
                }
            }
        }
    }
    /**
     * Sets the rounds when random events will occur during the game.
     * Randomly selects rounds based on the total number of game rounds.
     */
    public void setRandomEventRounds(){
        List<Integer> randomEventRoundsList = new ArrayList<Integer>();
        List<Integer> potentialRoundsList = new ArrayList<Integer>();
        // Populate a list with potential round numbers
        for (int i=1; i <= playerManager.getNumGameRounds();i++){
            potentialRoundsList.add(i);
        }
        // Shuffle the list of potential rounds
        Collections.shuffle(potentialRoundsList);
        // Select a subset of potential rounds for random events
        for (int i=0; i <= playerManager.getNumGameRounds()/3; i++){
            randomEventRoundsList.add(potentialRoundsList.get(i));
        }
        // Set the random event rounds
        this.randomEventRounds = randomEventRoundsList;
    }
}


