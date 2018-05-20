package com.example.ayala.irosster;

import android.app.Activity;
import android.app.FragmentContainer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.UUID;

public class PlayerActivity extends SingleFragmentActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment);
//
//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
//
//        if (fragment == null) {
//            fragment = createFragment();
//            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
//
//        }
//    }

    public static final String EXTRA_PLAYER_ID = "com.example.ayala.irosster.player_id";

    public static Intent newIntent(Context packageContext, UUID playerId){
        Intent intent = new Intent (packageContext, PlayerActivity.class);
        intent.putExtra(EXTRA_PLAYER_ID, playerId);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        return new PlayerFragment();
    }
}
