import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nastya.model.Score;
import org.nastya.service.MatchScoreCalculationService;

import static org.junit.jupiter.api.Assertions.*;

public class MatchScoreCalculationServiceTest {
    private MatchScoreCalculationService matchScoreCalculationService;
    private Score scorePlayer1;
    private Score scorePlayer2;

    @BeforeEach
    public void createMatchScore(){
        matchScoreCalculationService = new MatchScoreCalculationService();
        scorePlayer1 = new Score();
        scorePlayer2 = new Score();
    }

    @Test
    public void gameScoreShouldIncrease(){
        Score expectedScore = new Score(15, 0, 0, false);

        matchScoreCalculationService.compute(scorePlayer1, scorePlayer2);
        assertEquals(15, scorePlayer1.getGame());
    }
}
