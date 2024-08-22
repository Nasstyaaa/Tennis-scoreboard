package org.nastya.dto;

import org.nastya.model.Player;

public class MatchDTO {
    private Player player1;
    private Player player2;
    private int pointPlayer1;
    private int pointPlayer2;

    public MatchDTO(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        pointPlayer1 = 0;
        pointPlayer2 = 0;
    }
}
