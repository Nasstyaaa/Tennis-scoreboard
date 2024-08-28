package org.nastya.chaincalculation;

import org.nastya.dto.MatchDTO;
import org.nastya.model.Score;

public class GameCalculationHandler extends CalculationHandler {

    @Override
    public void calculate(MatchDTO matchDTO) {
        Score score = matchDTO.getMatchScore().getScorePlayer1();
        Score scoreOpponent = matchDTO.getMatchScore().getScorePlayer2();

        if (!matchDTO.isTieBreak()) {
            score.setGameCount(score.getGameCount() + 1);
            score.setPointCount(0);
            scoreOpponent.setPointCount(0);
            score.setHasAdvantage(false);

            if (score.getGameCount() >= 6) {
                nextCalculationHandler.calculate(matchDTO);
            }
        } else if (score.getPointCount() >= 7) {
            nextCalculationHandler.calculate(matchDTO);
        }
    }
}