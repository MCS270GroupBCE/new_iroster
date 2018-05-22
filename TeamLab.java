package com.example.ayala.irosster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ayala.irosster.database.TeamBaseHelper;
import com.example.ayala.irosster.database.TeamDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeamLab {
     private static TeamLab sTeamLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;


     public static TeamLab get(Context context){
         if (sTeamLab == null){
             sTeamLab = new TeamLab(context);
         }
         return sTeamLab;
     }

     private TeamLab(Context context){
         mContext = context.getApplicationContext();
         mDatabase = new TeamBaseHelper(mContext).getWritableDatabase();


     }

    public void addTeam(Team t){
        ContentValues values = getContentValues(t);
        mDatabase.insert(TeamDbSchema.teamTable.NAME, null, values);

    }

     public List<Team> getTeams(){
         List<Team> teams = new ArrayList<>();

         TeamCursorWrapper cursor = queryTeams(null, null);

         try {
             cursor.moveToFirst();
             while(!cursor.isAfterLast()){
                 teams.add(cursor.getTeam());
                 cursor.moveToNext();
             }
         }
         finally {
             cursor.close();
         }
         return teams;
     }

     public Team getTeam(UUID id){
         TeamCursorWrapper cursor = queryTeams(
                 TeamDbSchema.teamTable.Cols.UUID + " = ?",
                 new String[] { id.toString()}
         );

         try {
             if (cursor.getCount() == 0) {
                 return null;
             }

             cursor.moveToFirst();
             return cursor.getTeam();
         } finally {
             cursor.close();
         }

     }

    public void updateTeam(Team team){
        String uuidString = team.getId().toString();
        ContentValues values = getContentValues(team);

        mDatabase.update(TeamDbSchema.teamTable.NAME, values,
                TeamDbSchema.teamTable.Cols.UUID +
                        " = ?", new String[] {uuidString});
    }


    private TeamCursorWrapper queryTeams(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                TeamDbSchema.teamTable.NAME,
                null, //columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having,
                null, // orderBy
                null //limit
        );
        return new TeamCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Team team) {
        ContentValues values = new ContentValues();
        values.put(TeamDbSchema.teamTable.Cols.UUID, team.getId().toString());
        values.put(TeamDbSchema.teamTable.Cols.TITLE, team.getTeamName());

        return values;
    }
}
