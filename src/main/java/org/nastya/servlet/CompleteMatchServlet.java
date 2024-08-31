package org.nastya.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.nastya.dto.PaginationDTO;
import org.nastya.service.MatchPaginationService;

import java.io.IOException;

@WebServlet(urlPatterns = "/matches")
public class CompleteMatchServlet extends HttpServlet {
    private final MatchPaginationService matchPaginationService = new MatchPaginationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String namePlayer = request.getParameter("filter_by_player_name");
        String page = request.getParameter("page");

        PaginationDTO paginationDTO = null;
        try {
            paginationDTO = matchPaginationService.getPaginatedMatches(namePlayer, page);
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("/completed-matches.jsp").forward(request, response);
        }

        request.setAttribute("matchList", paginationDTO.getMatchList());
        request.setAttribute("page_number", paginationDTO.getPageNumber());
        request.setAttribute("total_pages", paginationDTO.getTotalPages());
        request.getRequestDispatcher("/completed-matches.jsp").forward(request, response);
    }
}