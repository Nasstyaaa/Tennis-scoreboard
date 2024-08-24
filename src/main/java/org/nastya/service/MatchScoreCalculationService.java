package org.nastya.service;

import org.nastya.model.Score;


public class MatchScoreCalculationService {

    public void compute(Score score, Score scoreOpponent) {
        playGame(score, scoreOpponent);
        playSet(score, scoreOpponent);
    }

    private void playSet(Score score, Score scoreOpponent) {
        if (score.getGame() >= 6) {
            if (scoreOpponent.getGame() < 5 || score.getGame() == 7) {
                score.setSet(score.getSet() + 1);
                score.setGame(0);
                scoreOpponent.setGame(0);
            } else if (scoreOpponent.getGame() == 6) {
                playTieBreak(score, scoreOpponent);
            }
        }
    }

    private void playGame(Score score, Score scoreOpponent) {
        switch (score.getPoint()) {
            case 0:
                score.setPoint(15);
                break;
            case 15:
                score.setPoint(30);
                break;
            case 30:
                score.setPoint(40);
            case 40:
                if (scoreOpponent.getPoint() == 40) {
                    break; //TODO Игрок, имеющий преимущество и выигравший следующую подачу, побеждает в гейме.
                }
                score.setPoint(0);
                scoreOpponent.setPoint(0);
                score.setGame(score.getGame() + 1);
                break;
        }
    }

    private void playTieBreak(Score score, Score scoreOpponent) {
        //TODO прописать
    }
}

