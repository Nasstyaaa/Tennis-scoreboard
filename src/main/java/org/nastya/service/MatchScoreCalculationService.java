package org.nastya.service;

import org.nastya.chaincalculation.*;
import org.nastya.dto.MatchDTO;


public class MatchScoreCalculationService {
    private final Calculation calculation = ChainCalculationBuilder.buildChain();

    public MatchDTO compute(MatchDTO matchDTO) {
        calculation.calculate(matchDTO);
        return matchDTO;
    }
}

