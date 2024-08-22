package org.nastya.service;

import org.nastya.dto.MatchDTO;

import java.util.HashMap;
import java.util.UUID;

public class OngoingMatchesService {
    private static HashMap<String, MatchDTO> matches = new HashMap<>();

    public static void add(MatchDTO matchDTO, String uuid){
        matches.put(uuid, matchDTO);
    }
}
