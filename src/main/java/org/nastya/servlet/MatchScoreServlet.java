package org.nastya.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.nastya.model.MatchScore;
import org.nastya.service.MatchScoreCalculationService;
import org.nastya.service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = "/match-score")
//TODO /match-score?uuid=$match_id, в полях отправленной формы содержится айди выигравшего очко игрока
public class MatchScoreServlet extends HttpServlet {

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
        String uuid = request.getParameter("uuid");
        MatchScore currentMatchScore = ongoingMatchesService.get(UUID.fromString(uuid)).getMatchScore();

        String id = request.getParameter("idPlayer");
        if (Integer.getInteger(id) == 1) {
            matchCalculationService.compute(currentMatchScore.getScorePlayer1(), currentMatchScore.getScorePlayer2());
        } else if (Integer.getInteger(id) == 2) {
            matchCalculationService.compute(currentMatchScore.getScorePlayer2(), currentMatchScore.getScorePlayer1());
        }
    }
}
