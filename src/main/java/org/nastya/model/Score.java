package org.nastya.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Score {
    private int pointCount;
    private int gameCount;
    private int setCount;
    private boolean hasAdvantage;

    public Score() {
        pointCount = 0;
        gameCount = 0;
        setCount = 0;
        hasAdvantage = false;
    }
}
