package org.nastya.chaincalculation;

import org.nastya.dto.MatchDTO;
import org.nastya.model.Player;
import org.nastya.model.Score;

public class GameCalculation extends Calculation {

    @Override
    public void calculate(MatchDTO matchDTO) {
        Score score = matchDTO.getMatchScore().getScorePlayer2();
        Score scoreOpponent = matchDTO.getMatchScore().getScorePlayer2();

        if (!matchDTO.isTieBreak()) {
            score.setGame(score.getGame() + 1);
            score.setPoint(0);
            scoreOpponent.setPoint(0);

            if (score.getGame() >= 6) {
                nextCalculation.calculate(matchDTO);
            }

        } else {
            nextCalculation.calculate(matchDTO);
        }
    }
}