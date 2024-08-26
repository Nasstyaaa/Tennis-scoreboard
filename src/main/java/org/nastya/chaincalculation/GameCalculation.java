package org.nastya.chaincalculation;

import org.nastya.model.Score;

public class GameCalculation extends Calculation{

    @Override
    public void calculate(Score score, Score scoreOpponent) {
        if (score.getGame() >= 6) {
            if (scoreOpponent.getGame() < 5 || score.getGame() == 7) {
                score.setSet(score.getSet() + 1);
                score.setGame(0);
                scoreOpponent.setGame(0);
            } else if (scoreOpponent.getGame() == 6) {
                nextCalculation.calculate(score, scoreOpponent);
            }
        }
    }
}
