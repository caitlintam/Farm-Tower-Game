package seng201.team0;

import seng201.team0.models.Round;

import java.util.ArrayList;
import java.util.List;

public class RoundManager {
    //
    private final List<Round> roundsList;
    private int currentRoundIndex;
    private PlayerManager playerManager;


    public RoundManager(PlayerManager playerManager){
        //this.roundsList = rounds;
        this.currentRoundIndex = 0;
        this.roundsList = new ArrayList<Round>();
    }
//    public setRoundsList(){
//        for (int i = 0; i <= playerManager.getNumGameRounds(); i++){
//
//        }
//    }

//    public void startRounds() {
//        for (Round round : rounds) {
//            RoundTask roundTask = new RoundTask(round);
//            RoundThread roundThread = new RoundThread(roundTask);
//            roundThread.run();
//        }
    }
//    public boolean allRoundsCompleted() {
//        return currentRoundIndex >= rounds.size();
//    }

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
//
//}

