package seng201.team0;

import java.util.List;
import java.util.Random;
import seng201.team0.models.Tower;
import seng201.team0.PlayerManager;

public class RandomEventManager {
    private PlayerManager playerManager;
    private List<Integer> randomEventRounds;
    private Random random;
    private List<Tower> reserveTowers;

    public RandomEventManager(List<Integer> randomEventRounds) {
        this.randomEventRounds = randomEventRounds;
        this.random = new Random();
    }

    public boolean isRandomEvent(int currentRound) {
        return randomEventRounds.contains(currentRound);
    }

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
                // set text in round controller to " tower name has broken "
                break;
        }
    }

    private void executeLevelIncrease() {
        Tower tower = getRandomTower();
        if (tower != null) {
            tower.setTowerLevel(tower.getTowerLevel() + 1);
            System.out.println("Random Event: Tower " + tower.getTowerName() + "increased levels");
            String levelIncreaseText = "Tower " + tower.getTowerName() + "has increased levels to level: " + tower.getTowerLevel();
            playerManager.setRandomEventText(levelIncreaseText);
        }
    }

    private void executeLevelDecrease() {
        Tower tower = getRandomTower();
        if (tower != null) {
            tower.setTowerLevel(tower.getTowerLevel() - 1);
            System.out.println("Random Event: Tower " + tower.getTowerName() + "decreased levels");
            String levelDecreaseText = "Tower " + tower.getTowerName() + "has decreased levels to level: " + tower.getTowerLevel();
            playerManager.setRandomEventText(levelDecreaseText);
        }
    }

    private void executeBreakTower() {
        Tower tower = getRandomTower();
        if (tower != null) {
            playerManager.removeTowerFromInventory(tower);
            System.out.println("Random Event: Tower " + tower.getTowerName() + " broke and was removed.");
            // set text for random event fxml
            String brokenTowerText = "Tower " + tower.getTowerName() + "broke! it has now been removed from your inventory";
            playerManager.setRandomEventText(brokenTowerText);
        }
    }

    private List<Integer> getRandomEventRounds() {
        return randomEventRounds;
    }

    private Tower getRandomTower() {
        if (playerManager.getTowerInventory().isEmpty()) {
            return null;
        } else {
            double usedTowerProbability = 0.8;
            double regTowerProbability = 0.2;
            if (random.nextDouble() < usedTowerProbability) {
                return playerManager.getTowersInGame().get(random.nextInt(playerManager.getTowersInGame().size()));
            } else {
                return playerManager.getReserveTowers().get(random.nextInt(playerManager.getReserveTowers().size()));

            }
        }
    }
}


