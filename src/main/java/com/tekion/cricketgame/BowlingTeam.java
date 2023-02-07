package com.tekion.cricketgame;

import com.tekion.cricketgame.dtos.Player;
import com.tekion.cricketgame.dtos.Team;

import java.util.ArrayList;

public class BowlingTeam {
    private final ArrayList<Player> bestBowlers;
    private final String teamName;
    public BowlingTeam(Team teamToBowl) {
        // TODO: select 6 best bowlers from the team;
        bestBowlers = new ArrayList<>(teamToBowl.getPlayers());
        teamName = teamToBowl.getName();
    }

    public Player getNewBowler() {
        int index = (int) (Math.random() * bestBowlers.size());
        return bestBowlers.get(index);
    }

    public String getName() {
        return teamName;
    }
}
