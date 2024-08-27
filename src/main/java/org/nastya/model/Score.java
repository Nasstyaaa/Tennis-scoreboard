package org.nastya.model;

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

    public Score(int pointCount, int gameCount, int setCount, boolean hasAdvantage) {
        this.pointCount = pointCount;
        this.gameCount = gameCount;
        this.setCount = setCount;
        this.hasAdvantage = hasAdvantage;
    }

    public int getPointCount() {
        return pointCount;
    }

    public int getGameCount() {
        return gameCount;
    }

    public int getSetsCount() {
        return setCount;
    }

    public void setPointCount(int pointCount) {
        this.pointCount = pointCount;
    }

    public void setGameCount(int gameCount) {
        this.gameCount = gameCount;
    }

    public void setSetsCount(int setNumber) {
        this.setCount = setNumber;
    }

    public boolean isHasAdvantage() {
        return hasAdvantage;
    }

    public void setHasAdvantage(boolean hasAdvantage) {
        this.hasAdvantage = hasAdvantage;
    }
}
