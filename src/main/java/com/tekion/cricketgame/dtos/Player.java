package com.tekion.cricketgame.dtos;

public class Player {
    String id;
    String name;
    public Player(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }
}
