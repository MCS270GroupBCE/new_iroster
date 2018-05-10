package com.example.ayala.irosster;

import java.util.UUID;

public class Team {

    private UUID mId;
    private String mTeamName;

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


}
