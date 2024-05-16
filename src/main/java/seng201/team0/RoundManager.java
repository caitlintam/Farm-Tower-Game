package seng201.team0;

import seng201.team0.models.Round;
import seng201.team0.models.RoundTask;

import java.util.ArrayList;
import java.util.List;

public class RoundManager {
    //
    private List<Round> rounds;
    private int currentRoundIndex;
    public RoundManager(List<Round> rounds){
        this.rounds = rounds;
    }
    public void startRounds() {
        for (Round round : rounds) {
            RoundTask roundTask = new RoundTask(round);
            RoundThread roundThread = new RoundThread(roundTask);
            roundThread.run();
        }
    }

//    public void startRoundThreads() {
//        for (Thread roundThread : roundThreads) {
//            roundThread.start();
//        }
//    }

//    public void endRoundThreads() {
//        for (Round round : rounds) {
//            round.interrupt();
//        }
//    }


}

