package com.example.ayala.irosster;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeamLab {
     private static TeamLab sTeamLab;

     private List<Team> mTeams;

     public static TeamLab get(Context context){
         if (sTeamLab == null){
             sTeamLab = new TeamLab(context);
         }
         return sTeamLab;
     }

     private TeamLab(Context context){
         mTeams = new ArrayList<>();
     }

    public void addTeam(Team t){
         mTeams.add(t);
     }

    public void deleteTeam(Team t) {mTeams.remove(t); }

    public List<Team> getTeams(){
         return mTeams;
     }

     public Team getTeam(UUID id){
         for (Team team : mTeams) {
             if (team.getId().equals(id)){
                 return team;
             }
         }
         return null;
     }

//    public void addPlayer(Player player){
//        mPlayers.add(player);
//    }
//
//    public void deletePlayer(Player player) {mPlayers.remove(player);}
//
//    public List<Player> getPlayers(){
//        return mPlayers;
//    }
//
//    public Player getPlayer(UUID id){
//        for (Player player : mPlayers) {
//            if (player.getId().equals(id)){
//                return player;
//            }
//        }
//        return null;
//    }


}





