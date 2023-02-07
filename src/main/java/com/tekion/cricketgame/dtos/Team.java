package com.tekion.cricketgame.dtos;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private final List<Player> players = new ArrayList<>(11);
    private final String teamName;
    public Team(String teamName) {
        this.teamName = teamName;
        for (int id = 0; id < 11; id++) {
            String playerId = teamName + id + 1;
            String playerName = teamName + id + 1;
            players.add(new Player(playerId, playerName));
        }
    }
    public String getName() {
        return teamName;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
