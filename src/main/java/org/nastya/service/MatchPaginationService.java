package org.nastya.service;

import org.nastya.dao.MatchDAO;
import org.nastya.dao.PlayerDAO;
import org.nastya.dto.PaginationRequestDTO;
import org.nastya.dto.PaginationResponseDTO;
import org.nastya.model.Match;

import java.util.List;

public class MatchPaginationService {
    private final MatchDAO matchDAO = new MatchDAO();
    private final PlayerDAO playerDAO = new PlayerDAO();

    public PaginationResponseDTO getPaginatedMatches(PaginationRequestDTO paginationRequestDTO) {
        String playerName = paginationRequestDTO.getPlayerName();
        String page = paginationRequestDTO.getPage();
        int pageSize = 4;
        int pageNumber = ((page != null && !page.trim().isEmpty()) ? Integer.parseInt(page) : 1);
        int offset = (pageNumber - 1) * pageSize;
        int totalPages;

        List<Match> matchList;
        if (playerName == null || playerName.trim().isEmpty() || playerDAO.findByPlayerName(playerName).isEmpty()) {
            matchList = matchDAO.findAllWithPagination(offset, pageSize);
            totalPages = (int) Math.ceil((double) matchDAO.countAll() / pageSize);
        } else {
            matchList = matchDAO.findByPlayerNameWithPagination(playerName, offset, pageSize);
            totalPages = (int) Math.ceil((double) matchDAO.countByPlayerName(playerName) / pageSize);
        }

        if (pageNumber > totalPages) {
            throw new NumberFormatException(); //TODO exception
        }
        return new PaginationResponseDTO(matchList, totalPages, pageNumber);
    }
}
