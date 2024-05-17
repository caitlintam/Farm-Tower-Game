package seng201.team0.models;

public class RoundThread extends Thread{
    private final RoundTask roundTask;

    public RoundThread(RoundTask roundTask){
        this.roundTask = roundTask;
    }
    @Override
    public void run(){
        roundTask.run();
    }
}
