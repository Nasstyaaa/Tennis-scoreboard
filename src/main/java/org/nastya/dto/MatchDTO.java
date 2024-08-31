package org.nastya.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.nastya.model.Player;
import org.nastya.model.MatchScore;


@Getter
@Setter
public class MatchDTO {
    private Player player1;
    private Player player2;
    private MatchScore matchScore;
    private Player winner;
    private boolean isTieBreak;

    public MatchDTO(Player player1, Player player2, MatchScore matchScore) {
        this.player1 = player1;
        this.player2 = player2;
        this.matchScore = matchScore;
        this.winner = null;
        this.isTieBreak = false;
    }
}
