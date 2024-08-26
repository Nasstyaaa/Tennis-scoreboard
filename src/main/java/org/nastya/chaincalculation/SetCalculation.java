package org.nastya.chaincalculation;

import org.nastya.model.Score;

public class SetCalculation extends Calculation {

    @Override
    public void calculate(Score score, Score scoreOpponent) {
        if (!isTieBreak) {
            if (scoreOpponent.getGame() < 5 || score.getGame() == 7) {
                score.setSet(score.getSet() + 1);
            } else if (scoreOpponent.getGame() == 6) {
                isTieBreak = true;
            }
            score.setGame(0);
            scoreOpponent.setGame(0);

        } else {
            if (score.getPoint() - scoreOpponent.getPoint() >= 2) {
                score.setSet(score.getSet() + 1);
                score.setPoint(0);
                scoreOpponent.setPoint(0);
                isTieBreak = false;
            }
        }

        if (score.getSet() == 2) {
            nextCalculation.calculate(score, scoreOpponent);
        }
    }
}
