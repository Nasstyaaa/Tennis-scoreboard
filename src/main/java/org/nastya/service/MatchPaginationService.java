package org.nastya.service;

import org.nastya.dao.MatchDAO;
import org.nastya.dao.PlayerDAO;
import org.nastya.dto.PaginationDTO;
import org.nastya.model.Match;

import java.util.List;

public class MatchPaginationService {
    private final MatchDAO matchDAO = new MatchDAO();
    private final PlayerDAO playerDAO = new PlayerDAO();

    public PaginationDTO getPaginatedMatches(String namePlayer, String page){
        try {
            int pageSize = 4;
            int pageNumber = ((page != null && !page.trim().isEmpty())? Integer.parseInt(page) : 1);
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

            if (pageNumber > totalPages){
                throw new NumberFormatException();
            }

            return new PaginationDTO(matchList, totalPages, pageNumber);
        } catch (NumberFormatException e){
            throw new NumberFormatException();
        }
    }
}
