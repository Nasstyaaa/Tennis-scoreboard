package org.nastya.chaincalculation;

import org.nastya.dto.MatchDTO;
import org.nastya.model.Score;

public abstract class Calculation {
    protected Calculation nextCalculation;

    public void setNextCalculation(Calculation nextCalculation) {
        this.nextCalculation = nextCalculation;
    }

    public abstract void calculate(MatchDTO matchDTO);
}
