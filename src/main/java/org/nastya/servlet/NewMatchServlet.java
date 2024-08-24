package org.nastya.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.nastya.DAO.PlayerDAO;
import org.nastya.model.Player;
import org.nastya.service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = "/new-match")
public class NewMatchServlet extends HttpServlet {
    private final PlayerDAO playerDAO = new PlayerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/new-match.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String namePlayer1 = request.getParameter("player1");
        String namePlayer2 = request.getParameter("player2");

        if(namePlayer1.trim().isEmpty() || namePlayer2.trim().isEmpty() || namePlayer1.equals(namePlayer2)){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print("Incorrect data has been entered");
            return;
        }

        Player player1 = playerDAO.find(namePlayer1).orElse(playerDAO.save(namePlayer1));
        Player player2 = playerDAO.find(namePlayer2).orElse(playerDAO.save(namePlayer2));

        UUID matchId = OngoingMatchesService.add(player1, player2);
        //TODO проверить ссылку, поправить ответ ошибки, добавить проверки
        response.sendRedirect("/match-score?uuid=" + matchId);
    }
}
