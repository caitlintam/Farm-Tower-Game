package seng201.team0;

import seng201.team0.models.Round;
import seng201.team0.models.RoundTask;

public class RoundThread implements Runnable {
    private RoundTask roundTask;

    public RoundThread(RoundTask roundTask) {
        this.roundTask = roundTask;
    }

    @Override
    public void run() {
        // Logic for processing the round
        roundTask.run();
    }
}
