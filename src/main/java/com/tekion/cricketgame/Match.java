package com.tekion.cricketgame;

import com.tekion.cricketgame.dtos.Team;

import java.util.Scanner;

public class Match {
    private final Team teamA;
    private final Team teamB;

    public Match() {
        teamA = new Team("India");
        teamB = new Team("Australia");
    }

    public void start() {
        System.out.println("You are playing as " + teamA.getName() + " against " + teamB.getName());
        Team tossWinningTeam = toss();
        Team teamToBat = decideBattingTeam(tossWinningTeam);
        Team teamToBowl = (teamToBat == teamA) ? teamB : teamA;

        BattingTeam battingTeam = new BattingTeam(teamToBat);
        BowlingTeam bowlingTeam = new BowlingTeam(teamToBowl);
        System.out.println(battingTeam.getName() + " Batting: ");

        FirstInnings firstInnings = new FirstInnings(battingTeam, bowlingTeam);
        firstInnings.play();
        firstInnings.displayScoreboard();
        int target = firstInnings.getTotalRuns() + 1;

        System.out.println(battingTeam.getName() + " scored " + firstInnings.getTotalRuns() + "\n");

        battingTeam = new BattingTeam(teamToBowl);
        bowlingTeam = new BowlingTeam(teamToBat);
        System.out.println(battingTeam.getName() + " Batting: ");
        SecondInnings secondInnings = new SecondInnings(battingTeam, bowlingTeam, target);
        secondInnings.play();
        secondInnings.displayScoreboard();

        System.out.println(battingTeam.getName() + " scored " + secondInnings.getTotalRuns());
        showResult(firstInnings, secondInnings);
    }

    private Team toss() {
        System.out.println("Enter 1 for Heads, 2 for Tails");
        Scanner consoleInput = new Scanner(System.in);
        int tossChoice = consoleInput.nextInt();
        int coinToss = (int) (Math.random() * 2);

        if (coinToss == tossChoice) {
            System.out.println(teamA.getName() + " won the toss.");
            return teamA;
        }
        System.out.println(teamB.getName() + " won the toss.");
        return teamB;
    }

    private Team decideBattingTeam(Team team) {
        if (team == teamA) {
            // the operator won the toss
            System.out.println("Enter 0 for Bowling, 1 for Batting");
            Scanner consoleInput = new Scanner(System.in);
            int battingChoice = consoleInput.nextInt();
            if (battingChoice == 1) {
                System.out.println(teamA.getName() + " decided to bat first.");
                return teamA;
            }
            System.out.println(teamA.getName() + " decided to bowl first.");
            return teamB;
        }

        // make random Batting or Bowling Decision for the computer:
        int battingChoice = (int) (Math.random() * 2);
        if (battingChoice == 1) {
            System.out.println(teamB.getName() + " decided to bat first.");
            return teamB;
        }
        System.out.println(teamB.getName() + " decided to bowl first.");
        return teamA;

    }

    public void showResult(FirstInnings firstInnings, SecondInnings secondInnings) {
        int firstInningsRuns = firstInnings.getTotalRuns();
        int secondInningsRuns = secondInnings.getTotalRuns();
        if (firstInningsRuns == secondInningsRuns) {
            System.out.println("Tie between " + firstInnings.getBattingTeam().getName() + " and " + secondInnings.getBattingTeam().getName());
        } else if (firstInningsRuns >= secondInningsRuns) {
            int difference = firstInningsRuns - secondInningsRuns;
            System.out.println(firstInnings.getBattingTeam().getName() + " won the match by " + difference + " runs.");
        } else {
            // TODO: won by how much?
//            int wicketsRemaining = secondInnings.getWicketsRemaining();
//            System.out.println(secondInnings.getBattingTeam().getName() + " won the match by " + wicketsRemaining + " wickets.");
            System.out.println(secondInnings.getBattingTeam().getName() + " won the match.");
        }
    }
}
