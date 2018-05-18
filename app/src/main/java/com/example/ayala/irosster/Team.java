package com.example.ayala.irosster;

import java.util.List;
import java.util.UUID;

public class Team {

    private UUID mId;
    private String mTeamName;
//    private List<Player> mPlayers;

    public Team(){
        mId = UUID.randomUUID();
    }

    public UUID getId(){
        return mId;
    }

    public String getTeamName(){
        return mTeamName;
    }

    public void setTeamName(String teamName){
        mTeamName = teamName;
    }

//    public void addPlayer(Player player){
//        mPlayers.add(player);
//    }


}
