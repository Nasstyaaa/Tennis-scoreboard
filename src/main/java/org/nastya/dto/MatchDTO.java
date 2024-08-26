package org.nastya.dto;

import org.nastya.model.Player;
import org.nastya.model.MatchScore;

public class MatchDTO {
    private Player player1;
    private Player player2;
    private MatchScore matchScore;
    private Player winner;

    public MatchDTO(Player player1, Player player2, MatchScore matchScore) {
        this.player1 = player1;
        this.player2 = player2;
        this.matchScore = matchScore;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public MatchScore getMatchScore() {
        return matchScore;
    }

    public Player getPlayerById(String id) {
        if (player1.getId().equals(Integer.valueOf(id))) {
            return player1;
        }
        return player2;
    }
}
