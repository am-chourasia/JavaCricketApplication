package com.tekion.cricketgame;

import com.tekion.cricketgame.dtos.Player;

public class SecondInnings extends GameInnings {
    private Player striker;
    private Player nonStriker;
    private Player bowler;
    private int totalRuns;
    private final int target;
    public SecondInnings(BattingTeam battingTeam, BowlingTeam bowlingTeam, int target) {
        super(battingTeam, bowlingTeam);
        this.target = target;
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
                    totalRuns += runs;
                    if (runs % 2 == 1)
                        strikeController.changeStrike();
                    if (totalRuns > target)
                        return;
                }

                if (striker == null) {
                    System.out.println("\nALL OUT");
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
