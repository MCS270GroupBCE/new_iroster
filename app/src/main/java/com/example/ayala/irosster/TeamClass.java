package com.example.ayala.irosster;

import java.util.UUID;

/**
 * Created by ayala on 5/3/2018.
 */

public class TeamClass {

    private UUID mId;
    private String mTeamName;

    public TeamClass(){
        mId = UUID.randomUUID();

    }

    public UUID getmId(){
        return mId;
    }

    public String getmTeamName(){
        return mTeamName;
    }

    public void teamName(){}
}

