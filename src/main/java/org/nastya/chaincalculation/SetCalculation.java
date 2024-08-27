package org.nastya.chaincalculation;

import org.nastya.dto.MatchDTO;
import org.nastya.model.Score;

public class SetCalculation extends Calculation {

    @Override
    public void calculate(MatchDTO matchDTO) {
        Score score = matchDTO.getMatchScore().getScorePlayer2();
        Score scoreOpponent = matchDTO.getMatchScore().getScorePlayer2();

        if (!matchDTO.isTieBreak()) {
            if (scoreOpponent.getGame() < 5 || score.getGame() == 7) {
                score.setSet(score.getSet() + 1);
            } else if (scoreOpponent.getGame() == 6) {
                matchDTO.setTieBreak(true);
            }
            score.setGame(0);
            scoreOpponent.setGame(0);

        } else {
            if (score.getPoint() - scoreOpponent.getPoint() >= 2) {
                score.setSet(score.getSet() + 1);
                score.setPoint(0);
                scoreOpponent.setPoint(0);
                matchDTO.setTieBreak(false);
            }
        }


        if (score.getSet() == 2) {
            matchDTO.setWinner(matchDTO.getPlayer1());
        }
    }
}
