package org.nastya.model;

public class Score {
    private int pointsNumber;
    private int gamesNumber;
    private int setNumber;
    private boolean hasAdvantage;

    public Score() {
        pointsNumber = 0;
        gamesNumber = 0;
        setNumber = 0;
        hasAdvantage = false;
    }

    public Score(int pointsNumber, int gamesNumber, int setNumber, boolean hasAdvantage) {
        this.pointsNumber = pointsNumber;
        this.gamesNumber = gamesNumber;
        this.setNumber = setNumber;
        this.hasAdvantage = hasAdvantage;
    }

    public int getPointsNumber() {
        return pointsNumber;
    }

    public int getGamesNumber() {
        return gamesNumber;
    }

    public int getSetsNumber() {
        return setNumber;
    }

    public void setPointsNumber(int pointsNumber) {
        this.pointsNumber = pointsNumber;
    }

    public void setGamesNumber(int gamesNumber) {
        this.gamesNumber = gamesNumber;
    }

    public void setSetsNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public boolean isHasAdvantage() {
        return hasAdvantage;
    }

    public void setHasAdvantage(boolean hasAdvantage) {
        this.hasAdvantage = hasAdvantage;
    }
}
