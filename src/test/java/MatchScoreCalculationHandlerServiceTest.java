import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nastya.dto.MatchDTO;
import org.nastya.model.MatchScore;
import org.nastya.model.Player;
import org.nastya.model.Score;
import org.nastya.service.MatchScoreCalculationService;

import static org.junit.jupiter.api.Assertions.*;

public class MatchScoreCalculationHandlerServiceTest {
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
        score.setPointCount(15);

        matchScoreCalculationService.compute(matchDTO);
        assertEquals(30, score.getPointCount());
    }

    @Test
    public void playerShouldGetAdvantage(){
        score.setPointCount(40);
        scoreOpponent.setPointCount(40);

        matchScoreCalculationService.compute(matchDTO);
        assertEquals(true, score.isHasAdvantage());
    }

    @Test
    public void gamesNumberShouldIncreaseAfterGetAdvantage(){
        score.setPointCount(40);
        score.setHasAdvantage(true);
        scoreOpponent.setPointCount(40);

        matchScoreCalculationService.compute(matchDTO);
        assertEquals(1, score.getGameCount());
        assertEquals(0, score.getPointCount());
        assertEquals(0, scoreOpponent.getPointCount());
        assertEquals(false, score.isHasAdvantage());
    }

    @Test
    public void gamesNumberShouldIncreaseIfOpponentsLess(){
        score.setPointCount(40);
        scoreOpponent.setPointCount(30);

        matchScoreCalculationService.compute(matchDTO);
        assertEquals(1, score.getGameCount());
        assertEquals(0, score.getPointCount());
        assertEquals(0, scoreOpponent.getPointCount());
    }

    @Test
    public void opponentPlayerShouldLoseAdvantage(){
        score.setPointCount(40);
        scoreOpponent.setPointCount(40);
        scoreOpponent.setHasAdvantage(true);

        matchScoreCalculationService.compute(matchDTO);
        assertEquals(0, score.getGameCount());
        assertEquals(40, score.getPointCount());
        assertEquals(40, scoreOpponent.getPointCount());
        assertEquals(false, scoreOpponent.isHasAdvantage());
    }


    @Test
    public void setsNumberShouldIncreaseIfOpponentsLess(){
        score.setPointCount(40);
        score.setHasAdvantage(true);
        scoreOpponent.setPointCount(40);
        score.setGameCount(5);
        scoreOpponent.setGameCount(4);

        matchScoreCalculationService.compute(matchDTO);
        assertEquals(0, score.getGameCount());
        assertEquals(0, scoreOpponent.getGameCount());
        assertEquals(1, score.getSetCount());
    }

    @Test
    public void setsNumberShouldIncreaseIfOpponentsLessThanTwo(){
        score.setPointCount(40);
        score.setHasAdvantage(true);
        scoreOpponent.setPointCount(40);
        score.setGameCount(6);
        scoreOpponent.setGameCount(5);

        matchScoreCalculationService.compute(matchDTO);
        assertEquals(0, score.getGameCount());
        assertEquals(0, scoreOpponent.getGameCount());
        assertEquals(1, score.getSetCount());
    }

    @Test
    public void isTieBreakShouldTrue(){
        score.setPointCount(40);
        score.setHasAdvantage(true);
        scoreOpponent.setPointCount(40);
        score.setGameCount(5);
        scoreOpponent.setGameCount(6);

        matchScoreCalculationService.compute(matchDTO);
        assertEquals(true, matchDTO.isTieBreak());
    }

    @Test
    public void pointsNumberShouldIncreaseInTieBreak(){
        score.setPointCount(40);
        score.setHasAdvantage(true);
        scoreOpponent.setPointCount(40);
        score.setGameCount(5);
        scoreOpponent.setGameCount(6);
        matchScoreCalculationService.compute(matchDTO);

        matchScoreCalculationService.compute(matchDTO);
        assertEquals(1, score.getPointCount());
    }

    @Test
    public void setsNumberShouldIncreaseInTieBreak(){
        matchDTO.setTieBreak(true);
        score.setPointCount(7);
        scoreOpponent.setPointCount(6);
        matchScoreCalculationService.compute(matchDTO);

        assertEquals(false, matchDTO.isTieBreak());
        assertEquals(1, score.getSetsCount());
    }

    @Test
    public void winnerShouldNotNull(){
        score.setSetsCount(1);
        scoreOpponent.setSetsCount(1);
        matchDTO.setTieBreak(true);
        score.setPointCount(7);
        scoreOpponent.setPointCount(6);
        matchScoreCalculationService.compute(matchDTO);

        assertEquals(player1, matchDTO.getWinner());
    }
}