package org.nastya.chaincalculation;

public class ChainCalculationBuilder {
    public static CalculationHandler buildChain() {
        CalculationHandler pointCalculationHandler = new PointCalculationHandler();
        CalculationHandler gameCalculationHandler = new GameCalculationHandler();
        CalculationHandler setCalculationHandler = new SetCalculationHandler();

        pointCalculationHandler.setNextCalculation(gameCalculationHandler);
        gameCalculationHandler.setNextCalculation(setCalculationHandler);
        return pointCalculationHandler;
    }
}
