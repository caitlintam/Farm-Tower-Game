package seng201.team0.models;

public class Player{
        private String name;
        private int numRounds;
        private int gameDifficulty;
        public Player(String name, int numRounds, int gameDifficulty) {
            this.name = name;
            this.numRounds = numRounds;
            this.gameDifficulty = gameDifficulty;
        }
        public Player(String name){
            this.name = name;
        }
        public String getName(){
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getNumRounds(){
            return numRounds;
        }
        public void setNumRounds(int numRounds) {
            this.numRounds = numRounds;
        }
        public int getGameDifficulty(){
            return gameDifficulty;
        }
        public void setGameDifficulty(int gameDifficulty) {
            this.gameDifficulty = gameDifficulty;
        }
        // public add stuff for rounds and difficulty
    }


