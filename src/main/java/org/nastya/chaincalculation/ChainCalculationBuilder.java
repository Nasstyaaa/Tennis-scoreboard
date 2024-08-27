package org.nastya.chaincalculation;

public class ChainCalculationBuilder {
    public static Calculation buildChain() {
        Calculation pointCalculation = new PointCalculation();
        Calculation gameCalculation = new GameCalculation();
        Calculation setCalculation = new SetCalculation();

        pointCalculation.setNextCalculation(gameCalculation);
        gameCalculation.setNextCalculation(setCalculation);
        return pointCalculation;
    }
}
