package org.nastya.chaincalculation;

import org.nastya.dto.MatchDTO;
import org.nastya.model.Score;

public class SetCalculation extends Calculation {

    @Override
    public void calculate(MatchDTO matchDTO) {
        Score score = matchDTO.getMatchScore().getScorePlayer1();
        Score scoreOpponent = matchDTO.getMatchScore().getScorePlayer2();

        if (!matchDTO.isTieBreak()) {
            if (scoreOpponent.getGamesNumber() < 5 || score.getGamesNumber() == 7) {
                score.setSetsNumber(score.getSetsNumber() + 1);
            } else if (scoreOpponent.getGamesNumber() == 6) {
                matchDTO.setTieBreak(true);
            }
        } else {
            if (score.getPointsNumber() - scoreOpponent.getPointsNumber() >= 2) {
                score.setSetsNumber(score.getSetsNumber() + 1);
                matchDTO.setTieBreak(false);
            }
        }
        score.setGamesNumber(0);
        scoreOpponent.setGamesNumber(0);


        if (score.getSetsNumber() == 2) {
            matchDTO.setWinner(matchDTO.getPlayer1());
        }
    }
}
