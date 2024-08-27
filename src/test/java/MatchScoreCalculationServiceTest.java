import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nastya.dto.MatchDTO;
import org.nastya.model.MatchScore;
import org.nastya.model.Player;
import org.nastya.model.Score;
import org.nastya.service.MatchScoreCalculationService;

import static org.junit.jupiter.api.Assertions.*;

public class MatchScoreCalculationServiceTest {
    private MatchScoreCalculationService matchScoreCalculationService;
    private MatchDTO matchDTO;
    private Score score;
    private Score scoreOpponent;
    Player player1;

    @BeforeEach
    public void createMatchScore(){
        matchScoreCalculationService = new MatchScoreCalculationService();
        player1 = new Player();
        matchDTO = new MatchDTO(player1, new Player(), new MatchScore());
        score = matchDTO.getMatchScore().getScorePlayer1();
        scoreOpponent = matchDTO.getMatchScore().getScorePlayer2();
    }

    @Test
    public void pointsNumberShouldIncrease(){
        score.setPointsNumber(15);

        matchScoreCalculationService.compute(matchDTO);
        assertEquals(30, score.getPointsNumber());
    }

    @Test
    public void playerShouldGetAdvantage(){
        score.setPointsNumber(40);
        scoreOpponent.setPointsNumber(40);

        matchScoreCalculationService.compute(matchDTO);
        assertEquals(true, score.isHasAdvantage());
    }

    @Test
    public void gamesNumberShouldIncreaseAfterGetAdvantage(){
        score.setPointsNumber(40);
        score.setHasAdvantage(true);
        scoreOpponent.setPointsNumber(40);

        matchScoreCalculationService.compute(matchDTO);
        assertEquals(1, score.getGamesNumber());
        assertEquals(0, score.getPointsNumber());
        assertEquals(0, scoreOpponent.getPointsNumber());
    }

    @Test
    public void gamesNumberShouldIncreaseIfOpponentsLess(){
        score.setPointsNumber(40);
        scoreOpponent.setPointsNumber(30);

        matchScoreCalculationService.compute(matchDTO);
        assertEquals(1, score.getGamesNumber());
        assertEquals(0, score.getPointsNumber());
        assertEquals(0, scoreOpponent.getPointsNumber());
    }

    @Test
    public void opponentPlayerShouldLoseAdvantage(){
        score.setPointsNumber(40);
        scoreOpponent.setPointsNumber(40);
        scoreOpponent.setHasAdvantage(true);

        matchScoreCalculationService.compute(matchDTO);
        assertEquals(0, score.getGamesNumber());
        assertEquals(40, score.getPointsNumber());
        assertEquals(40, scoreOpponent.getPointsNumber());
        assertEquals(false, scoreOpponent.isHasAdvantage());
    }


    @Test
    public void setsNumberShouldIncreaseIfOpponentsLess(){
        score.setPointsNumber(40);
        score.setHasAdvantage(true);
        scoreOpponent.setPointsNumber(40);
        score.setGamesNumber(5);
        scoreOpponent.setGamesNumber(4);

        matchScoreCalculationService.compute(matchDTO);
        assertEquals(0, score.getGamesNumber());
        assertEquals(0, scoreOpponent.getGamesNumber());
        assertEquals(1, score.getSetsNumber());
    }

    @Test
    public void setsNumberShouldIncreaseIfOpponentsLessThanTwo(){
        score.setPointsNumber(40);
        score.setHasAdvantage(true);
        scoreOpponent.setPointsNumber(40);
        score.setGamesNumber(6);
        scoreOpponent.setGamesNumber(5);

        matchScoreCalculationService.compute(matchDTO);
        assertEquals(0, score.getGamesNumber());
        assertEquals(0, scoreOpponent.getGamesNumber());
        assertEquals(1, score.getSetsNumber());
    }

    @Test
    public void isTieBreakShouldTrue(){
        score.setPointsNumber(40);
        score.setHasAdvantage(true);
        scoreOpponent.setPointsNumber(40);
        score.setGamesNumber(5);
        scoreOpponent.setGamesNumber(6);

        matchScoreCalculationService.compute(matchDTO);
        assertEquals(true, matchDTO.isTieBreak());
    }

    @Test
    public void pointsNumberShouldIncreaseInTieBreak(){
        score.setPointsNumber(40);
        score.setHasAdvantage(true);
        scoreOpponent.setPointsNumber(40);
        score.setGamesNumber(5);
        scoreOpponent.setGamesNumber(6);
        matchScoreCalculationService.compute(matchDTO);

        matchScoreCalculationService.compute(matchDTO);
        assertEquals(1, score.getPointsNumber());
    }

    @Test
    public void setsNumberShouldIncreaseInTieBreak(){
        matchDTO.setTieBreak(true);
        score.setPointsNumber(7);
        scoreOpponent.setPointsNumber(6);
        matchScoreCalculationService.compute(matchDTO);

        assertEquals(false, matchDTO.isTieBreak());
        assertEquals(1, score.getSetsNumber());
    }

    @Test
    public void winnerShouldNotNull(){
        score.setSetsNumber(1);
        scoreOpponent.setSetsNumber(1);
        matchDTO.setTieBreak(true);
        score.setPointsNumber(7);
        scoreOpponent.setPointsNumber(6);
        matchScoreCalculationService.compute(matchDTO);

        assertEquals(player1, matchDTO.getWinner());
    }
}