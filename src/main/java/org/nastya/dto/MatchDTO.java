package org.nastya.dto;

import org.nastya.model.Player;
import org.nastya.model.MatchScore;

public class MatchDTO {
    private Player player1;
    private Player player2;
    private MatchScore score;

    public MatchDTO(Player player1, Player player2, MatchScore score) {
        this.player1 = player1;
        this.player2 = player2;
        this.score = score;
    }
}
