package org.nastya.chaincalculation;

import org.nastya.dto.MatchDTO;

public abstract class CalculationHandler {
    protected CalculationHandler nextCalculationHandler;

    public void setNextCalculation(CalculationHandler nextCalculationHandler) {
        this.nextCalculationHandler = nextCalculationHandler;
    }

    public abstract void calculate(MatchDTO matchDTO);
}
