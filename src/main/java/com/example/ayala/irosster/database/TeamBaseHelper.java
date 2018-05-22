package com.example.ayala.irosster.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ayala.irosster.database.TeamDbSchema.teamTable;

/**
 * Created by ayala on 5/4/2018.
 */

public class TeamBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "teamBase.db";

    public TeamBaseHelper(Context context) {
        super (context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + teamTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                teamTable.Cols.UUID + ", " +
                teamTable.Cols.TITLE +
                ")"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}