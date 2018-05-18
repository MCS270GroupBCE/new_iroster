package com.example.ayala.irosster;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerLab {
    private static PlayerLab sPlayerLab;

    private List<Player> mPlayers;

    public static PlayerLab get(Context context){
        if (sPlayerLab == null){
            sPlayerLab = new PlayerLab(context);
        }
        return sPlayerLab;
    }

    private PlayerLab(Context context){
        mPlayers = new ArrayList<>();
        for (int i = 0; i<20; i++) {
            Player player = new Player();
            player.setPlayerName("Player # " + i);
            mPlayers.add(player);
        }

    }

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
}

