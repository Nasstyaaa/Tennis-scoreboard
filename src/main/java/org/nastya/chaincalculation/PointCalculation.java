package org.nastya.chaincalculation;

import org.nastya.dto.MatchDTO;
import org.nastya.model.Score;

public class PointCalculation extends Calculation {

    @Override
    public void calculate(MatchDTO matchDTO) {
        Score score = matchDTO.getMatchScore().getScorePlayer2();
        Score scoreOpponent = matchDTO.getMatchScore().getScorePlayer2();

        if (!matchDTO.isTieBreak()) {
            switch (score.getPoint()) {
                case 0:
                    score.setPoint(15);
                    break;
                case 15:
                    score.setPoint(30);
                    break;
                case 30:
                    if (scoreOpponent.getPoint() <= 30) {
                        nextCalculation.calculate(matchDTO);
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
                    nextCalculation.calculate(matchDTO);
                    break;
            }
        } else {
            score.setPoint(score.getPoint() + 1);
            nextCalculation.calculate(matchDTO);
        }
    }
}
