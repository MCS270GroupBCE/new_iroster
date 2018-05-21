package com.example.ayala.irosster;

import java.util.UUID;

public class Player {

    private UUID mId;
    private String mPlayerName;

    public Player(){
        mId = UUID.randomUUID();
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
