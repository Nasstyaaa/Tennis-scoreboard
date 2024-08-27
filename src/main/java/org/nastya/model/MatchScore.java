package org.nastya.model;

public class MatchScore {
    Score scorePlayer1;
    Score scorePlayer2;

    public MatchScore() {
        scorePlayer1 = new Score();
        scorePlayer2 = new Score();
    }

    public Score getScorePlayer1() {
        return scorePlayer1;
    }

    public Score getScorePlayer2() {
        return scorePlayer2;
    }

    public void changeScorePlayers(){
        Score scorePlayer = scorePlayer1;
        scorePlayer1 = scorePlayer2;
        scorePlayer2 = scorePlayer;
    }
}
