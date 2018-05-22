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

    public static final String EXTRA_PLAYER_ID = "com.example.ayala.irosster.player_id";

    public static Intent newIntent(Context packageContext, UUID playerId){
        Intent intent = new Intent (packageContext, PlayerActivity.class);
        intent.putExtra(EXTRA_PLAYER_ID, playerId);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        UUID playerId = (UUID) getIntent().getSerializableExtra(EXTRA_PLAYER_ID);
        return PlayerFragment.newInstance(playerId);
    }
}
