package com.tekion.cricketgame;

public abstract class GameInnings {
    protected final int totalOvers = 10;
    protected BattingTeam battingTeam;
    protected BowlingTeam bowlingTeam;
    protected StrikeController strikeController;
    ScoreBoard scoreBoard;

    protected GameInnings(BattingTeam battingTeam, BowlingTeam bowlingTeam) {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        scoreBoard = new ScoreBoard(battingTeam, bowlingTeam);
    }

    protected abstract void play();

    public BattingTeam getBattingTeam() {
        return battingTeam;
    }
}

