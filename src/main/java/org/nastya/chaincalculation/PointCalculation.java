package org.nastya.chaincalculation;

import org.nastya.model.Score;

public class PointCalculation extends Calculation {

    @Override
    public void calculate(Score score, Score scoreOpponent) {
        switch (score.getPoint()) {
            case 0:
                score.setPoint(15);
                break;
            case 15:
                score.setPoint(30);
                break;
            case 30:
                if (scoreOpponent.getPoint() <= 30) {
                    nextCalculation.calculate(score, scoreOpponent);
                    break;
                }
                score.setPoint(40);
                break;
            case 40:
                if (!score.isHasAdvantage()) {
                    score.setHasAdvantage(true);
                    scoreOpponent.setHasAdvantage(false);
                    break;
                }
                nextCalculation.calculate(score, scoreOpponent);
                break;
        }
    }
}
