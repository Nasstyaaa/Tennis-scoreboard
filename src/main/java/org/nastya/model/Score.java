package org.nastya.model;

public class Score {
    private int point;
    private int game;
    private int set;
    private boolean hasAdvantage;

    public Score() {
        point = 0;
        game = 0;
        set = 0;
        hasAdvantage = false;
    }

    public int getPoint() {
        return point;
    }

    public int getGame() {
        return game;
    }

    public int getSet() {
        return set;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public boolean isHasAdvantage() {
        return hasAdvantage;
    }

    public void setHasAdvantage(boolean hasAdvantage) {
        this.hasAdvantage = hasAdvantage;
    }
}
