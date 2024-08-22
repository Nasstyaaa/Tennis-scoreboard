package org.nastya.servlet;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.nastya.dao.MatchDAO;
import org.nastya.dao.PlayerDAO;
import org.nastya.model.Player;
import org.nastya.service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {
    private final PlayerDAO playerDAO = new PlayerDAO();
    private final MatchDAO matchDAO = new MatchDAO();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String namePlayer1 = request.getParameter("player1");
        String namePlayer2 = request.getParameter("player2");

        if(namePlayer1.trim().isEmpty() || namePlayer2.trim().isEmpty()){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print("Incorrect data has been entered");
        }

        Player player1 = playerDAO.find(namePlayer1);
        Player player2 = playerDAO.find(namePlayer2);

        if (player1.equals(null)){
            player1 = playerDAO.save(namePlayer1);
        } else if (player2.equals(null)) {
            player2 = playerDAO.save(namePlayer2);
        }

        UUID matchId = OngoingMatchesService.add(player1, player2);
        //TODO проверить ссылку
        response.sendRedirect(request.getContextPath() + "/match-score?uuid=" + matchId);
    }
}
