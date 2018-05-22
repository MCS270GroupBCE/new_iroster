package com.example.ayala.irosster;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerLab {

    private List<Player> mPlayers;

    private static PlayerLab sPlayerLab;

    public static PlayerLab get(Context context){
        if (sPlayerLab == null){
            sPlayerLab = new PlayerLab(context);
        }
        return sPlayerLab;
    }

    private PlayerLab(Context context){
        mPlayers = new ArrayList<>();
    }

    public void addPlayer(Player player){
        mPlayers.add(player);
    }

    public void deletePlayer(Player player) {mPlayers.remove(player);}

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

