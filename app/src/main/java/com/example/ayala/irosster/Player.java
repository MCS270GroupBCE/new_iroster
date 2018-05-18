package com.example.ayala.irosster;

import java.util.UUID;

public class Player {

    private UUID mId;
    private String mPlayerName;
    private int mPlayerAge;

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

    public int getPlayerAge() {
        return mPlayerAge;
    }

    public void setPlayerAge(int playerAge) {
        mPlayerAge = playerAge;
    }

}
