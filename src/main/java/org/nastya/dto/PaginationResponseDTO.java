package org.nastya.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.nastya.model.Match;

import java.util.List;


@Getter
@AllArgsConstructor
public class PaginationResponseDTO {
    private List<Match> matchList;
    private int totalPages;
    private int pageNumber;
}
