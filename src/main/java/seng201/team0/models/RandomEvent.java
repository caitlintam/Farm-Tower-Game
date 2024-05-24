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
    private final PlayerManager playerManager;
    private List<Integer> randomEventRounds;
    private final Random random;
    private String randomEventText;
    private final Player player;
    /**
     * Constructs a new random event with the specified player manager and player.
     *
     * @param playerManager the player manager responsible for managing the player
     * @param player the player affected by the random event
     */
    public RandomEvent(PlayerManager playerManager, Player player) {
        this.playerManager = playerManager;
        this.random = new Random();
        this.randomEventRounds = new ArrayList<>();
        this.player = player;

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
        List<Tower> towersInGame = player.getTowersInGame();
        List<Tower> reserveTowers = player.getReserveTowers();
        if (player.getTowerInventory().isEmpty()) {
            return null;
        } else {
            double usedTowerProbability = 0.8;
            boolean useInGameTower = random.nextDouble() < usedTowerProbability;
            if (useInGameTower && !towersInGame.isEmpty()) {
                return towersInGame.get(random.nextInt(towersInGame.size()));
            }
            if (!reserveTowers.isEmpty()) {
                return reserveTowers.get(random.nextInt(reserveTowers.size()));
            }
            if (!towersInGame.isEmpty()) {
                return towersInGame.get(random.nextInt(towersInGame.size()));
            }
        }
        return null;
    }

    /**
     * Sets the rounds when random events will occur during the game.
     * Randomly selects rounds based on the total number of game rounds.
     */
    public void setRandomEventRounds(){
        List<Integer> randomEventRoundsList = new ArrayList<>();
        List<Integer> potentialRoundsList = new ArrayList<>();
        for (int i=1; i <= playerManager.getNumGameRounds();i++){
            potentialRoundsList.add(i);
        }
        Collections.shuffle(potentialRoundsList);
        for (int i=0; i <= playerManager.getNumGameRounds()/3; i++){
            randomEventRoundsList.add(potentialRoundsList.get(i));
        }
        this.randomEventRounds = randomEventRoundsList;
    }
    /**
     * Generates a random event and executes the corresponding action based on the event type.
     * The event type determines whether to increase tower level, decrease tower level, or break a tower.
     */
    public void generateRandomEvent() {
        int eventType = random.nextInt(3);
        Tower tower = getRandomTower();
        switch (eventType) {
            case 0:
                executeLevelIncrease(tower);
                break;
            case 1:
                executeLevelDecrease(tower);
                break;
            case 2:
                executeBreakTower(tower);

                break;
        }
    }
    /**
     * Executes a random event where the level of a randomly selected tower is increased.
     * If no tower is available, a NullPointerException is caught and an error message is displayed.
     */
    public void executeLevelIncrease(Tower tower) {
        try{
            tower.setTowerLevel(tower.getTowerLevel() + 1);
            System.out.println("Random Event: Tower " + tower.getTowerName() + "increased levels");
            this.randomEventText = tower.getTowerName() + " has increased levels to level: " + tower.getTowerLevel();
        }catch(NullPointerException e){
            System.out.println("Uh Oh, error, no random tower generated");
        }
    }
    /**
     * Executes a random event where the level of a randomly selected tower is decreased.
     * If the tower's level is already at the minimum level (1), no action is taken.
     * If no tower is available, a NullPointerException is caught and an error message is displayed.
     */
     public void executeLevelDecrease(Tower tower) {
        try{
            if (tower.getTowerLevel() > 1){
                tower.decreaseTowerLevel(tower);
                System.out.println("Random Event: Tower " + tower.getTowerName() + " decreased levels");
                this.randomEventText = "Random Event: " +tower.getTowerName() + " has decreased levels to level: " + tower.getTowerLevel();
            }else{
                System.out.println("Random Event: Tower can't be decreased below level 1");
                this.randomEventText = "Random Event: Tower can't be decreased below level 1";
            }
        }catch(NullPointerException e){
            System.out.println("Uh Oh, error, no random tower generated");
        }
    }
    /**
     * Executes a random event where a randomly selected tower is broken and removed from the player's inventory.
     * If no tower is available, a NullPointerException is caught and an error message is displayed.
     */
     public void executeBreakTower(Tower tower) {
        try{
            System.out.println("invent size before"+ player.getTowerInventory().size());
            player.removeTowerFromInventory(tower);
            System.out.println("invent size after"+ player.getTowerInventory().size());
            System.out.println("Random Event: " + tower.getTowerName() + " broke and was removed.");
            this.randomEventText = tower.getTowerName() + " broke! it has now been removed from your inventory";
        }catch(NullPointerException e){
            System.out.println("Uh Oh, error, no random tower generated");
        }
    }

}


