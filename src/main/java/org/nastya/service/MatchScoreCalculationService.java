package org.nastya.service;

import org.nastya.chaincalculation.*;
import org.nastya.dto.MatchDTO;


public class MatchScoreCalculationService {
    private final CalculationHandler calculationHandler = ChainCalculationBuilder.buildChain();

    public MatchDTO compute(MatchDTO matchDTO) {
        calculationHandler.calculate(matchDTO);
        return matchDTO;
    }
}

