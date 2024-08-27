package org.nastya.chaincalculation;

import org.nastya.dto.MatchDTO;
import org.nastya.model.Score;

public class SetCalculation extends Calculation {

    @Override
    public void calculate(MatchDTO matchDTO) {
        Score score = matchDTO.getMatchScore().getScorePlayer1();
        Score scoreOpponent = matchDTO.getMatchScore().getScorePlayer2();

        if (!matchDTO.isTieBreak()) {
            if (scoreOpponent.getGameCount() < 5 || score.getGameCount() == 7) {
                score.setSetsCount(score.getSetsCount() + 1);

                score.setGameCount(0);
                scoreOpponent.setGameCount(0);
            } else if (scoreOpponent.getGameCount() == 6) {
                matchDTO.setTieBreak(true);

                score.setGameCount(0);
                scoreOpponent.setGameCount(0);
            }
        } else {
            if (score.getPointCount() - scoreOpponent.getPointCount() >= 2) {
                score.setSetsCount(score.getSetsCount() + 1);
                matchDTO.setTieBreak(false);
                score.setPointCount(0);
                scoreOpponent.setPointCount(0);
            }
        }

        if (score.getSetsCount() == 2) {
            matchDTO.setWinner(matchDTO.getPlayer1());
        }
    }
}
