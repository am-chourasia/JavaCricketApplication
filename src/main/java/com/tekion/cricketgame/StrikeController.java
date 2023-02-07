package com.tekion.cricketgame;

import com.tekion.cricketgame.dtos.Player;

public class StrikeController {
    private Player striker;
    private Player nonStriker;
//    private Player bowler;
    public StrikeController(Player striker, Player nonStriker) {
        this.striker = striker;
        this.nonStriker = nonStriker;
    }

    public int makeRuns() {
        int randomRun = (int)(Math.random() * 8);
        // TODO: Implement and Update ScoreBoard;
        if (randomRun == 7)
            return -1;
        return randomRun;
    }

    public void changeStrike() {
        Player temp = striker;
        striker = nonStriker;
        nonStriker = temp;
    }

    public void setStriker(Player striker) {
        this.striker = striker;
    }

    public Player getNonStriker() {
        return nonStriker;
    }

    public Player getStriker() {
        return striker;
    }
}
