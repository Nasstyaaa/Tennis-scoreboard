package org.nastya.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.nastya.DAO.MatchDAO;
import org.nastya.DAO.PlayerDAO;
import org.nastya.model.Match;
import org.nastya.model.Player;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/matches")
public class CompleteMatchServlet extends HttpServlet {
    private final MatchDAO matchDAO = new MatchDAO();
    private final PlayerDAO playerDAO = new PlayerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Match> matchList = matchDAO.findAll();

        request.setAttribute("matchList", matchList);

        request.getRequestDispatcher("/completed-matches.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String namePlayer = request.getParameter("namePlayer");

        System.out.println(namePlayer);
    }
}
