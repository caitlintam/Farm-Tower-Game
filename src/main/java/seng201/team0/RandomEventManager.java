package seng201.team0;

import java.util.List;
import java.util.Random;

public class RandomEventManager {
    private List<Integer> randomEventRounds;
    private Random random;
    public RandomEventManager(List<Integer> randomEventRounds){
        this.randomEventRounds = randomEventRounds;
        this.random = new Random();
    }
    public boolean isRandomEvent(int currentRound){
        return randomEventRounds.contains(currentRound);
    }
    public void generateRandomEvent(){
        int eventType = random.nextInt(3);
        switch (eventType){
            case 0:
                // executeEvent1(); method for in/decreasing stats
                break;
            case 1:
            // executeEvent2()
                break;
            case 2:
                // executeEvent3(); break a tower
                break;
            }
        }
    private void executeEvent1() {
        // Logic for executing event 1
    }

    private void executeEvent2() {
        // Logic for executing event 2
    }

    private void executeEvent3() {
        // Logic for executing event 3
    }
    private List<Integer> getRandomEventRounds(){
        return randomEventRounds;
    }
    }


