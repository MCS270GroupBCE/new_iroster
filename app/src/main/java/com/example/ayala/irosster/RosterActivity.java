package com.example.ayala.irosster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RosterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster);
        int number = 0;
    }

    public static int add(int x, int y){
        return x+y;
    }
}
