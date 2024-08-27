package org.nastya.chaincalculation;

import org.nastya.dto.MatchDTO;
import org.nastya.model.Score;

public class PointCalculation extends Calculation {

    @Override
    public void calculate(MatchDTO matchDTO) {
        Score score = matchDTO.getMatchScore().getScorePlayer1();
        Score scoreOpponent = matchDTO.getMatchScore().getScorePlayer2();

        if (!matchDTO.isTieBreak()) {
            switch (score.getPointsNumber()) {
                case 0:
                    score.setPointsNumber(15);
                    break;
                case 15:
                    score.setPointsNumber(30);
                    break;
                case 30:
                    score.setPointsNumber(40);
                    break;
                case 40:
                    if (scoreOpponent.getPointsNumber() <= 30 || score.isHasAdvantage()) {
                        nextCalculation.calculate(matchDTO);
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
            score.setPointsNumber(score.getPointsNumber() + 1);
            nextCalculation.calculate(matchDTO);
        }
    }
}
