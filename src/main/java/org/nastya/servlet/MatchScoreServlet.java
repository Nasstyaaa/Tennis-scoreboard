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
public class MatchScoreServlet extends HttpServlet {

    private final MatchDAO matchDAO = new MatchDAO();
    private final MatchScoreCalculationService matchCalculationService = new MatchScoreCalculationService();
    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UUID uuid = UUID.fromString(request.getParameter("uuid"));
        MatchDTO currentMatch = ongoingMatchesService.get(uuid);

        request.setAttribute("uuid", uuid);
        request.setAttribute("match", currentMatch);
        request.getRequestDispatcher("/match-score.jsp").include(request, response);
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UUID uuid = UUID.fromString(request.getParameter("uuid"));
        MatchDTO currentMatch = ongoingMatchesService.get(uuid);

        int id = Integer.parseInt(request.getParameter("idPlayer"));
        //TODO id NumberFormatException
        if (id == 1) {
            currentMatch = matchCalculationService.compute(currentMatch);
        } else if (id == 2) {
            currentMatch.getMatchScore().changeScorePlayers();
            currentMatch = matchCalculationService.compute(currentMatch);
            currentMatch.getMatchScore().changeScorePlayers();
        }

        if (currentMatch.getWinner() != null) {
            request.setAttribute("uuid", uuid);
            request.setAttribute("match", currentMatch);
            ongoingMatchesService.delete(uuid);
            Match match = new Match(currentMatch.getPlayer1(), currentMatch.getPlayer2(), currentMatch.getWinner());
            matchDAO.save(match);
        }
        request.setAttribute("uuid", uuid);
        request.setAttribute("match", currentMatch);
        this.doGet(request, response);
    }
}
