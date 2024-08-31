package org.nastya.dto;

import org.nastya.model.Match;

import java.util.List;

public class PaginationDTO {
    private List<Match> matchList;
    private int totalPages;
    private int pageNumber;

    public PaginationDTO(List<Match> matchList, int totalPages, int pageNumber) {
        this.matchList = matchList;
        this.totalPages = totalPages;
        this.pageNumber = pageNumber;
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
