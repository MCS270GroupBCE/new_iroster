package com.example.ayala.irosster;

import java.util.UUID;

public class Player {

    private UUID mId;
    private String mPlayerName;
    private Team mTeam;

    public Player(Team team){
        mId = UUID.randomUUID();
        mTeam = team;
        team.addPlayer(this);
    }

    public Team getPlayerTeam(){
        return mTeam;
    }

    public Player getPlayer(UUID id){
        return mTeam.getPlayer(id);
    }

    public UUID getId(){
        return mId;
    }

    public String getPlayerName(){
        return mPlayerName;
    }

    public void setPlayerName(String playerName){
        mPlayerName = playerName;
    }

}
