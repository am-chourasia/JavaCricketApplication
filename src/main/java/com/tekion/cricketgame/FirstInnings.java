package com.tekion.cricketgame;

import com.tekion.cricketgame.dtos.Player;

import java.util.Objects;

public class FirstInnings extends GameInnings {
    private Player striker;
    private Player nonStriker;
    private Player bowler;
    private int totalRuns;

    public FirstInnings(BattingTeam battingTeam, BowlingTeam bowlingTeam) {
        super(battingTeam, bowlingTeam);
        striker = battingTeam.getNewBatsman();
        nonStriker = battingTeam.getNewBatsman();
        bowler = bowlingTeam.getNewBowler();
        strikeController = new StrikeController(striker, nonStriker);
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void displayScoreboard() {
        scoreBoard.display();
    }
    @Override
    protected void play() {
        for (int over = 1; over <= totalOvers; over++) {
            for (int ball = 1; ball <= 6; ball++) {
                striker = strikeController.getStriker();
                nonStriker = strikeController.getNonStriker();

                int runs = strikeController.makeRuns();
                scoreBoard.updateBattingStats(striker, runs);
                scoreBoard.updateBowlingStats(bowler, runs);
                if (runs == -1) { // wicket
                    System.out.print(" W ");
                    striker = battingTeam.getNewBatsman();
                    strikeController.setStriker(striker);
                }
                else {
                    System.out.print(" " + runs + " ");
                    if (runs % 2 == 1)
                        strikeController.changeStrike();
                    totalRuns += runs;
                }

                if (Objects.isNull(striker)) {
                    System.out.println("\nAll Out");
                    return;
                }
            }

            // end of over
            System.out.println("\t Over End ");
            bowler = bowlingTeam.getNewBowler();
            strikeController.changeStrike();
        }
    }
}
