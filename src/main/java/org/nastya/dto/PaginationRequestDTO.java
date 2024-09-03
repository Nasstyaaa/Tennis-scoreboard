package org.nastya.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class PaginationRequestDTO {
    String playerName;
    String page;
}
