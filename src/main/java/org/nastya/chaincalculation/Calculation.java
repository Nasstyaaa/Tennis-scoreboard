package org.nastya.chaincalculation;

import org.nastya.model.Score;

public abstract class Calculation {
    protected Calculation nextCalculation;
    protected static boolean isTieBreak = false;

    public void setNextCalculation(Calculation nextCalculation) {
        this.nextCalculation = nextCalculation;
    }

    public abstract void calculate(Score score, Score scoreOpponent);
}
