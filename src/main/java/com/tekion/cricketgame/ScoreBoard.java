package com.tekion.cricketgame;

import com.tekion.cricketgame.dtos.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class ScoreBoard {
    private final LinkedHashMap<String, BattingStatsRow> battingStats;
    private final LinkedHashMap<String, BowlingStatsRow> bowlingStats;
    private final BattingTeam battingTeam;
    private final BowlingTeam bowlingTeam;

    public ScoreBoard(BattingTeam battingTeam, BowlingTeam bowlingTeam) {
        battingStats = new LinkedHashMap<>();
        bowlingStats = new LinkedHashMap<>();
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
    }

    public void updateBattingStats(Player batsman, int runs) {
        if (!battingStats.containsKey(batsman.getId())) {
            battingStats.put(batsman.getId(), new BattingStatsRow());
        }
        battingStats.get(batsman.getId()).updateStats(runs);
    }

    public void updateBowlingStats(Player bowler, int runs) {
        if (!bowlingStats.containsKey(bowler.getId())) {
            bowlingStats.put(bowler.getId(), new BowlingStatsRow());
        }
        bowlingStats.get(bowler.getId()).updateStats(runs);
    }

    public void display() {
        System.out.println("\nBatting ScoreBoard for team " + battingTeam.getName() + ":");
        BattingStatsRow.printHeader();
        for (Map.Entry<String, BattingStatsRow> entry : battingStats.entrySet()) {
            System.out.printf("%-10s : \t ", entry.getKey());
            entry.getValue().printStats();
        }
        System.out.println("\nBowling Scorecard for team " + bowlingTeam.getName() + ":");
        BowlingStatsRow.printHeader();
        for (Map.Entry<String, BowlingStatsRow> entry : bowlingStats.entrySet()) {
            System.out.printf("%-10s : \t ", entry.getKey());
            entry.getValue().printStats();
        }
        System.out.println();
    }

    private static class BattingStatsRow {
        private int runs;
        private int balls;
        private int fours;
        private int sixes;
        private float strikeRate;

        public void updateStats(int runsAdded) {
            if (runsAdded != -1) runs += runsAdded;
            balls += 1;
            if (runsAdded == 4) fours += 1;
            if (runsAdded == 6) sixes += 1;
            strikeRate = (float) (runs) / balls * 100;
        }

        public static void printHeader() {
            System.out.printf("%-10s \t\t %-5s \t %-5s \t %-5s \t %-5s \t %-15s\n", "Name", "Runs", "Balls", "4s", "6s", "Strike Rate");
        }

        public void printStats() {
            System.out.printf("%-5d \t %-5d \t %-5d \t %-5d \t %-5.2f\n", runs, balls, fours, sixes, strikeRate);
        }
    }

    private static class BowlingStatsRow {
        private float overs;
        private int runsGiven;
        private int wicketsTaken;
        private float economy;

        public void updateStats(int runsAdded) {
            overs += 0.1;
            float overFormat = overs - (float) Math.floor(overs);
            if (Math.abs(overFormat - 0.6f) < 1e-6f) overs += 0.4f;

            if (runsAdded == -1) {
                wicketsTaken += 1;
            } else {
                runsGiven += runsAdded;
            }

            economy = runsGiven / overs;
        }

        public static void printHeader() {
            System.out.printf("%-10s \t\t %-5s \t %-5s \t %-8s \t %-5s\n", "Name", "Overs", "Runs", "Wickets", "Economy");
        }

        public void printStats() {
            System.out.printf("%-5.1f \t %-5d \t %-8d \t %-5.2f\n", overs, runsGiven, wicketsTaken, economy);
        }
    }
}
