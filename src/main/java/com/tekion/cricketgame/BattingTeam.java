package com.tekion.cricketgame;

import com.tekion.cricketgame.dtos.Player;
import com.tekion.cricketgame.dtos.Team;

import java.util.ListIterator;

public class BattingTeam {
    private final ListIterator<Player> iter;
    private final String teamName;

    public BattingTeam(Team teamToBat) {
        // TODO : Selecting best batsman from the team;
        iter = teamToBat.getPlayers().listIterator();
        teamName = teamToBat.getName();
    }

    public String getName() {
        return teamName;
    }
    public Player getNewBatsman() {
        if (iter.hasNext())
            return iter.next();
        return null;
    }

//    public int wicketsFallen() {
//        return Integer.max(0, iter.nextIndex() - 2);
//    }

//    public int getWicketsRemaining() {
//        return iter.nextIndex();
//    }
}
