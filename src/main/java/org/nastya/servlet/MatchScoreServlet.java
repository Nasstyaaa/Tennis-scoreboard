package org.nastya.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.nastya.DAO.MatchDAO;
import org.nastya.dto.MatchDTO;
import org.nastya.model.Match;
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
        UUID uuid = UUID.fromString(request.getParameter("uuid"));
        MatchDTO currentMatch = ongoingMatchesService.get(uuid);

        String id = request.getParameter("idPlayer");
        //TODO проверять id
        if (Integer.getInteger(id) == 1) {
            currentMatch = matchCalculationService.compute(currentMatch);
        } else if (Integer.getInteger(id) == 2) {
            currentMatch.getMatchScore().changeScorePlayers();
            currentMatch = matchCalculationService.compute(currentMatch);
            currentMatch.getMatchScore().changeScorePlayers();
        }

        if (currentMatch.getWinner() == null) {
            //TODO рендерится таблица счёта матча с кнопками, описанными выше
        } else {
            ongoingMatchesService.delete(uuid);
            Match match = new Match(currentMatch.getPlayer1(), currentMatch.getPlayer2(), currentMatch.getWinner());
            matchDAO.save(match);
            //TODO рендерим финальный счёт
        }
    }
}
