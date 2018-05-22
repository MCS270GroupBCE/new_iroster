package com.example.ayala.irosster;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Team {

    private UUID mId;
    private String mTeamName;

    public Team() {
        this(UUID.randomUUID());
    }
    public Team(UUID id){
        mId = id;
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
