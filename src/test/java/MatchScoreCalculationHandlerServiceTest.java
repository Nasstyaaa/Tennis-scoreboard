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
    private Player player1;

    @BeforeEach
    public void createMatchScore() {
        matchScoreCalculationService = new MatchScoreCalculationService();
        player1 = new Player();
        matchDTO = new MatchDTO(player1, new Player(), new MatchScore());
        score = matchDTO.getMatchScore().getScorePlayer1();
        scoreOpponent = matchDTO.getMatchScore().getScorePlayer2();
    }

    @Test
    public void pointScored_pointCountShouldIncrease() {
        score.setPointCount(15);

        matchScoreCalculationService.compute(matchDTO);

        assertEquals(30, score.getPointCount());
    }

    @Test
    public void pointScoredAtScore40_40_playerShouldGetAdvantage() {
        score.setPointCount(40);
        scoreOpponent.setPointCount(40);

        matchScoreCalculationService.compute(matchDTO);

        assertTrue(score.isHasAdvantage());
    }

    @Test
    public void pointScoredAtScoreA_40gameCountShouldIncrease() {
        score.setPointCount(40);
        score.setHasAdvantage(true);
        scoreOpponent.setPointCount(40);

        matchScoreCalculationService.compute(matchDTO);

        assertAll(
                () -> assertEquals(1, score.getGameCount()),
                () -> assertEquals(0, score.getPointCount()),
                () -> assertEquals(0, scoreOpponent.getPointCount()),
                () -> assertFalse(score.isHasAdvantage())
        );
    }

    @Test
    public void pointScoredAtScore40_30_gameCountShouldIncrease() {
        score.setPointCount(40);
        scoreOpponent.setPointCount(30);

        matchScoreCalculationService.compute(matchDTO);

        assertAll(
                () -> assertEquals(1, score.getGameCount()),
                () -> assertEquals(0, score.getPointCount()),
                () -> assertEquals(0, scoreOpponent.getPointCount())
        );
    }

    @Test
    public void pointScoredAtScore40_A_opponentShouldLoseAdvantage() {
        score.setPointCount(40);
        scoreOpponent.setPointCount(40);
        scoreOpponent.setHasAdvantage(true);

        matchScoreCalculationService.compute(matchDTO);

        assertAll(
                () -> assertEquals(0, score.getGameCount()),
                () -> assertEquals(40, score.getPointCount()),
                () -> assertEquals(40, scoreOpponent.getPointCount()),
                () -> assertFalse(scoreOpponent.isHasAdvantage())
        );
    }


    @Test
    public void pointScoredAtScoreA_40WithGames5_4_setCountShouldIncrease() {
        score.setPointCount(40);
        score.setHasAdvantage(true);
        scoreOpponent.setPointCount(40);
        score.setGameCount(5);
        scoreOpponent.setGameCount(4);

        matchScoreCalculationService.compute(matchDTO);

        assertAll(
                () -> assertEquals(0, score.getGameCount()),
                () -> assertEquals(0, scoreOpponent.getGameCount()),
                () -> assertEquals(1, score.getSetCount())
        );
    }

    @Test
    public void pointScoredAtScoreA_40WithGames6_5_setCountShouldIncrease() {
        score.setPointCount(40);
        score.setHasAdvantage(true);
        scoreOpponent.setPointCount(40);
        score.setGameCount(6);
        scoreOpponent.setGameCount(5);

        matchScoreCalculationService.compute(matchDTO);

        assertAll(
                () -> assertEquals(0, score.getGameCount()),
                () -> assertEquals(0, scoreOpponent.getGameCount()),
                () -> assertEquals(1, score.getSetCount())
        );
    }

    @Test
    public void pointScoredAtScoreA_40WithGames5_6_tieBreakShouldStart() {
        score.setPointCount(40);
        score.setHasAdvantage(true);
        scoreOpponent.setPointCount(40);
        score.setGameCount(5);
        scoreOpponent.setGameCount(6);

        matchScoreCalculationService.compute(matchDTO);

        assertTrue(matchDTO.isTieBreak());
    }

    @Test
    public void pointScoredAtScore0_0WithTieBreak_pointCountShouldIncrease() {
        matchDTO.setTieBreak(true);

        matchScoreCalculationService.compute(matchDTO);

        assertEquals(1, score.getPointCount());
    }

    @Test
    public void pointScoredAtScore7_6WithTieBreak_setCountShouldIncrease() {
        matchDTO.setTieBreak(true);
        score.setPointCount(7);
        scoreOpponent.setPointCount(6);

        matchScoreCalculationService.compute(matchDTO);

        assertFalse(matchDTO.isTieBreak());
        assertEquals(1, score.getSetCount());
    }

    @Test
    public void pointScoredAtScore40_30WithGames5_4ithSets1_1_winnerDefined() {
        score.setSetCount(1);
        scoreOpponent.setSetCount(1);
        score.setGameCount(5);
        scoreOpponent.setGameCount(4);
        score.setPointCount(40);
        scoreOpponent.setPointCount(30);

        matchScoreCalculationService.compute(matchDTO);

        assertEquals(player1, matchDTO.getWinner());
    }
}