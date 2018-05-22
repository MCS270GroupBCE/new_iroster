package com.example.ayala.irosster;
import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.ayala.irosster.database.TeamDbSchema;

import java.util.UUID;

public class TeamCursorWrapper extends CursorWrapper {
    public TeamCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Team getTeam(){
        String uuidString  = getString(getColumnIndex(TeamDbSchema.teamTable.Cols.UUID));
        String title = getString(getColumnIndex(TeamDbSchema.teamTable.Cols.TITLE));

        Team team = new Team(UUID.fromString(uuidString));
        team.setTeamName(title);
        return team;
    }
}