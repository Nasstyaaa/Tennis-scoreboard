package org.nastya.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.nastya.dto.PaginationRequestDTO;
import org.nastya.dto.PaginationResponseDTO;
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

        PaginationResponseDTO paginationResponseDTO = null;
        try {
            PaginationRequestDTO paginationRequestDTO = new PaginationRequestDTO(namePlayer, page);
            paginationResponseDTO = matchPaginationService.getPaginatedMatches(paginationRequestDTO);
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("/exception.jsp").forward(request, response);
        }

        request.setAttribute("matchList", paginationResponseDTO.getMatchList());
        request.setAttribute("page_number", paginationResponseDTO.getPageNumber());
        request.setAttribute("total_pages", paginationResponseDTO.getTotalPages());
        request.getRequestDispatcher("/completed-matches.jsp").forward(request, response);
    }
}