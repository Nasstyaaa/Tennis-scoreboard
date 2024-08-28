package org.nastya.chaincalculation;

import org.nastya.dto.MatchDTO;
import org.nastya.model.Score;

public class PointCalculationHandler extends CalculationHandler {

    @Override
    public void calculate(MatchDTO matchDTO) {
        Score score = matchDTO.getMatchScore().getScorePlayer1();
        Score scoreOpponent = matchDTO.getMatchScore().getScorePlayer2();

        if (!matchDTO.isTieBreak()) {
            switch (score.getPointCount()) {
                case 0:
                    score.setPointCount(15);
                    break;
                case 15:
                    score.setPointCount(30);
                    break;
                case 30:
                    score.setPointCount(40);
                    break;
                case 40:
                    if (scoreOpponent.getPointCount() <= 30 || score.isHasAdvantage()) {
                        nextCalculationHandler.calculate(matchDTO);
                        break;
                    } else if (!scoreOpponent.isHasAdvantage()) {
                        score.setHasAdvantage(true);
                        break;
                    } else {
                        scoreOpponent.setHasAdvantage(false);
                        break;
                    }
            }
        } else {
            score.setPointCount(score.getPointCount() + 1);
            nextCalculationHandler.calculate(matchDTO);
        }
    }
}
