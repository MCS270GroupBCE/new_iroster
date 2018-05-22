package com.example.ayala.irosster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ayala.irosster.database.PlayerBaseHelper;
import com.example.ayala.irosster.database.PlayerDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerLab {
    private static PlayerLab sPlayerLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static PlayerLab get(Context context){
        if (sPlayerLab == null){
            sPlayerLab = new PlayerLab(context);
        }
        return sPlayerLab;
    }

    private PlayerLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new PlayerBaseHelper(mContext).getWritableDatabase();

    }

    public void newPlayer(Player player){
        ContentValues values = getContentValues(player);

        mDatabase.insert(PlayerDbSchema.PlayerTable.NAME, null, values);

    }

    public List<Player> getPlayers(){
        List<Player> players = new ArrayList<>();

        PlayerCursorWrapper cursor = queryPlayer(null, null);

        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                players.add(cursor.getPlayer());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return players;
    }

    public Player getPlayer(UUID id){
        PlayerCursorWrapper cursor = queryPlayer(
                PlayerDbSchema.PlayerTable.Cols.UUID + " = ?",
                new String[] { id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getPlayer();
        } finally {
            cursor.close();
        }

    }


    public void updatePLayer(Player player){
        String uuidString = player.getId().toString();
        ContentValues values = getContentValues(player);

        mDatabase.update(PlayerDbSchema.PlayerTable.NAME, values,
                PlayerDbSchema.PlayerTable.Cols.UUID +
                        " = ?", new String[] {uuidString});
    }

    private PlayerCursorWrapper queryPlayer(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                PlayerDbSchema.PlayerTable.NAME,
                null, //columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having,
                null, // orderBy
                null //limit
        );
        return new PlayerCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Player player) {
        ContentValues values = new ContentValues();
        values.put(PlayerDbSchema.PlayerTable.Cols.UUID, player.getId().toString());
        values.put(PlayerDbSchema.PlayerTable.Cols.TITLE, player.getPlayerName());

        return values;
    }
}

