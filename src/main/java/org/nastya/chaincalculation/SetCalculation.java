package org.nastya.chaincalculation;

import org.nastya.model.Score;

public class SetCalculation extends Calculation {

    @Override
    public void calculate(Score score, Score scoreOpponent) {

        if (scoreOpponent.getGame() < 5 || score.getGame() == 7) {
            score.setSet(score.getSet() + 1);
            score.setGame(0);
            scoreOpponent.setGame(0);
        } else if (scoreOpponent.getGame() == 6) {
            //tai break
        }

        if (score.getSet() == 2){
            nextCalculation.calculate(score, scoreOpponent);
        }
    }
}
