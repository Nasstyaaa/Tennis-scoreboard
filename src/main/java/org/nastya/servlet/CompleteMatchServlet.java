package org.nastya.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.nastya.DAO.MatchDAO;
import org.nastya.DAO.PlayerDAO;
import org.nastya.model.Match;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/matches")
public class CompleteMatchServlet extends HttpServlet {
    private final MatchDAO matchDAO = new MatchDAO();
    private final PlayerDAO playerDAO = new PlayerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String namePlayer = request.getParameter("filter_by_player_name");
        String pagenumber = request.getParameter("page");

        int pageSize = 4;
        int pageNumber = ((pagenumber != null && !pagenumber.trim().isEmpty())? Integer.parseInt(pagenumber) : 1);
        int offset = (pageNumber - 1) * pageSize;
        int totalPages;

        List<Match> matchList;
        if (namePlayer == null || namePlayer.trim().isEmpty() || playerDAO.find(namePlayer).isEmpty()) {
            matchList = matchDAO.findAllWithPagination(offset, pageSize);
            totalPages = (int) Math.ceil(matchDAO.countAll() / pageSize);
        } else {
            matchList = matchDAO.findByPlayerNameWithPagination(namePlayer, offset, pageSize);
            totalPages = (int) Math.ceil(matchDAO.countByPlayerName(namePlayer) / pageSize);
        }

        request.setAttribute("matchList", matchList);
        request.setAttribute("page_number", pageNumber);
        request.setAttribute("total_pages", totalPages);
        request.getRequestDispatcher("/completed-matches.jsp").forward(request, response);
    }
}