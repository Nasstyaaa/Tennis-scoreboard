package org.nastya.service;

import org.nastya.dto.MatchDTO;
import org.nastya.model.MatchScore;
import org.nastya.model.Player;

import java.util.HashMap;
import java.util.UUID;

public class OngoingMatchesService {
    private static OngoingMatchesService instance;
    private static HashMap<UUID, MatchDTO> matches = new HashMap<>();

    private OngoingMatchesService() {}

    public static OngoingMatchesService getInstance(){
        if(instance == null){
            instance = new OngoingMatchesService();
        }
        return instance;
    }

    public UUID add(Player player1, Player player2){
        MatchDTO matchDTO = new MatchDTO(player1, player2, new MatchScore());
        UUID matchId = UUID.randomUUID();

        matches.put(matchId, matchDTO);
        return matchId;
    }

    public MatchDTO get(UUID uuid){
        return matches.get(uuid);
    }

    public void delete(UUID uuid){
        matches.remove(uuid);
    }
}
