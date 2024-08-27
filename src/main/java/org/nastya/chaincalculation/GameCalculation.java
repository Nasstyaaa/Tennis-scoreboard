package org.nastya.chaincalculation;

import org.nastya.dto.MatchDTO;
import org.nastya.model.Score;

public class GameCalculation extends Calculation {

    @Override
    public void calculate(MatchDTO matchDTO) {
        Score score = matchDTO.getMatchScore().getScorePlayer1();
        Score scoreOpponent = matchDTO.getMatchScore().getScorePlayer2();

        if (!matchDTO.isTieBreak()) {
            score.setGamesNumber(score.getGamesNumber() + 1);
            score.setPointsNumber(0);
            scoreOpponent.setPointsNumber(0);

            if (score.getGamesNumber() >= 6) {
                nextCalculation.calculate(matchDTO);
            }
        } else if (score.getPointsNumber() >= 7) {
            nextCalculation.calculate(matchDTO);
        }
    }
}