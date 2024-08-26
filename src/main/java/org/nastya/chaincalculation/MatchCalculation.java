package org.nastya.chaincalculation;

import org.nastya.model.Score;

public class MatchCalculation extends Calculation{
    private boolean matchOver = false;

    @Override
    public void calculate(Score score, Score scoreOpponent) {
        matchOver = true;
    }

    public boolean isMatchOver(){
        return matchOver;
    }
}
