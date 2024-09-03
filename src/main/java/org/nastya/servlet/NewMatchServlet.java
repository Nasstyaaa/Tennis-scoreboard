package org.nastya.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.nastya.dao.PlayerDAO;
import org.nastya.model.Player;
import org.nastya.service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = "/new-match")
public class NewMatchServlet extends HttpServlet {

    private final PlayerDAO playerDAO = new PlayerDAO();
    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/new-match.jsp").forward(request, response);
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String namePlayer1 = request.getParameter("player1");
        String namePlayer2 = request.getParameter("player2");

        if(namePlayer1.isBlank() || namePlayer2.isBlank() || namePlayer1.equals(namePlayer2)){
            request.setAttribute("errorMessage", "Incorrect data has been entered");
            request.getRequestDispatcher("/new-match.jsp").forward(request, response);
            return;
        }

        Player player1 = playerDAO.findByPlayerName(namePlayer1)
                .orElseGet(()->playerDAO.save(new Player(namePlayer1)));

        Player player2 =  playerDAO.findByPlayerName(namePlayer2)
                .orElseGet(()-> playerDAO.save(new Player(namePlayer2)));

        UUID matchId = ongoingMatchesService.add(player1, player2);

        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/match-score?uuid=" + matchId);
    }
}
