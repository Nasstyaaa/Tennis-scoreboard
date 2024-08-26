package org.nastya.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.nastya.DAO.MatchDAO;
import org.nastya.dto.MatchDTO;
import org.nastya.model.MatchScore;
import org.nastya.service.MatchScoreCalculationService;
import org.nastya.service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = "/match-score")
//TODO /match-score?uuid=$match_id, в полях отправленной формы содержится айди выигравшего очко игрока
public class MatchScoreServlet extends HttpServlet {

    private final MatchDAO matchDAO = new MatchDAO();
    private final MatchScoreCalculationService matchCalculationService = new MatchScoreCalculationService();
    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/match-score.jsp").forward(request, response);
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        boolean isGameOver = false;
        UUID uuid = UUID.fromString(request.getParameter("uuid"));
        MatchDTO currentMatch = ongoingMatchesService.get(uuid);
        MatchScore currentMatchScore = currentMatch.getMatchScore();

        String id = request.getParameter("idPlayer");
        if (Integer.getInteger(id) == 1) {
            isGameOver = matchCalculationService.compute(currentMatchScore.getScorePlayer1(), currentMatchScore.getScorePlayer2());
        } else if (Integer.getInteger(id) == 2) {
            isGameOver = matchCalculationService.compute(currentMatchScore.getScorePlayer2(), currentMatchScore.getScorePlayer1());
        }

        if (!isGameOver) {
            //TODO рендерится таблица счёта матча с кнопками, описанными выше
        } else {
            ongoingMatchesService.delete(uuid);
            matchDAO.save(currentMatch.getPlayer1(), currentMatch.getPlayer2(), currentMatch.getPlayerById(id));
            //TODO рендерим финальный счёт
        }
    }
}
