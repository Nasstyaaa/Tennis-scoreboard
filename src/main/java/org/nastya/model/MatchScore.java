package org.nastya.model;

import lombok.Getter;

@Getter
public class MatchScore {
    Score scorePlayer1;
    Score scorePlayer2;

    public MatchScore() {
        scorePlayer1 = new Score();
        scorePlayer2 = new Score();
    }

    public void swapScores(){
        Score scorePlayer = scorePlayer1;
        scorePlayer1 = scorePlayer2;
        scorePlayer2 = scorePlayer;
    }
}
