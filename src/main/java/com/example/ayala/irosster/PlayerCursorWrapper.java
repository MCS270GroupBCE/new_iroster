package com.example.ayala.irosster;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.ayala.irosster.database.PlayerDbSchema;
import com.example.ayala.irosster.database.TeamDbSchema;

import java.util.UUID;

public class PlayerCursorWrapper extends CursorWrapper {

    public PlayerCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Player getPlayer() {
        String uuidString = getString(getColumnIndex(PlayerDbSchema.PlayerTable.Cols.UUID));
        String title = getString(getColumnIndex(PlayerDbSchema.PlayerTable.Cols.TITLE));

        Player player = new Player(UUID.fromString(uuidString));
        player.setPlayerName(title);

        return player;
    }
}
