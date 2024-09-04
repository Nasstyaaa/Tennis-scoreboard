package org.nastya.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class PaginationRequestDTO {
    private String playerName;
    private int page;
}
