package org.nastya.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.nastya.dao.MatchDAO;
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

        try {
            UUID uuid = UUID.fromString(request.getParameter("uuid"));
            MatchDTO currentMatch = ongoingMatchesService.get(uuid);
            request.setAttribute("uuid", uuid);

            if (currentMatch == null) {
                request.getRequestDispatcher("/not-found-match.jsp").forward(request, response);
            }

            request.setAttribute("match", currentMatch);
            response.setContentType("text/html; charset=UTF-8");
            request.getRequestDispatcher("/match-score.jsp").forward(request, response);

        }catch (IllegalArgumentException e) {
            request.getRequestDispatcher("/exception.jsp").forward(request, response);
        }
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            UUID uuid = UUID.fromString(request.getParameter("uuid"));
            MatchDTO currentMatch = ongoingMatchesService.get(uuid);

            if (currentMatch == null) {
                this.doGet(request, response);
            }

            int id = Integer.parseInt(request.getParameter("idPlayer"));

            if (id == 1) {
                currentMatch = matchCalculationService.compute(currentMatch);
            } else if (id == 2) {
                currentMatch.getMatchScore().swapScores();
                currentMatch = matchCalculationService.compute(currentMatch);
                currentMatch.getMatchScore().swapScores();
            }

            if (currentMatch.getWinner() != null) {
                this.doGet(request, response);

                ongoingMatchesService.delete(uuid);
                Match match = new Match(currentMatch.getPlayer1(), currentMatch.getPlayer2(), currentMatch.getWinner());
                matchDAO.save(match);
            }
            this.doGet(request, response);

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Invalid id in the form field");
        } catch (IllegalArgumentException e) {
            request.getRequestDispatcher("/exception.jsp").forward(request, response);
        }
    }
}
