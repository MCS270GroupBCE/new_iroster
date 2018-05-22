package com.example.ayala.irosster;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.example.ayala.irosster.PlayerLab;

public class Team {

    private UUID mId;
    private String mTeamName;
    public List<Player> mPlayers;


    public Team(){
        mId = UUID.randomUUID();
        mPlayers = new ArrayList<>();
    }

    public UUID getId(){
        return mId;
    }

    public void addPlayer(Player player){mPlayers.add(player); }

    public List<Player> getPlayers(){
        return mPlayers;
    }

    public Player getPlayer(UUID id){
        for (Player player : mPlayers) {
            if (player.getId().equals(id)){
                return player;
            }
        }
        return null;
    }

    public String getTeamName(){
        return mTeamName;
    }

    public void setTeamName(String teamName){
        mTeamName = teamName;
    }

}
