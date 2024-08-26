package org.nastya.chaincalculation;

import org.nastya.model.Score;

public class GameCalculation extends Calculation {

    @Override
    public void calculate(Score score, Score scoreOpponent) {
        if (!isTieBreak) {
            score.setGame(score.getGame() + 1);
            score.setPoint(0);
            scoreOpponent.setPoint(0);

            if (score.getGame() >= 6) {
                nextCalculation.calculate(score, scoreOpponent);
            }

        } else {
            nextCalculation.calculate(score, scoreOpponent);
        }
    }
}