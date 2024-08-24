package org.nastya.model;

import java.util.HashMap;

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
}
