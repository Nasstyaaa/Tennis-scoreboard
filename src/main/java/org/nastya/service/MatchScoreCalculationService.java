package org.nastya.service;

import org.nastya.chaincalculation.GameCalculation;
import org.nastya.chaincalculation.MatchCalculation;
import org.nastya.chaincalculation.PointCalculation;
import org.nastya.chaincalculation.SetCalculation;
import org.nastya.model.Score;


public class MatchScoreCalculationService {
    private final PointCalculation pointCalculation;
    private final GameCalculation gameCalculation;
    private final SetCalculation setCalculation;
    private final MatchCalculation matchCalculation;

    public MatchScoreCalculationService() {
        pointCalculation = new PointCalculation();
        gameCalculation = new GameCalculation();
        setCalculation = new SetCalculation();
        matchCalculation = new MatchCalculation();

        pointCalculation.setNextCalculation(gameCalculation);
        gameCalculation.setNextCalculation(setCalculation);
        setCalculation.setNextCalculation(matchCalculation);
    }

    public boolean compute(Score score, Score scoreOpponent) {
        pointCalculation.calculate(score, scoreOpponent);
        return matchCalculation.isMatchOver();
    }
}

