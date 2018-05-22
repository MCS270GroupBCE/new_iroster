package com.example.ayala.irosster;

import android.support.v4.app.Fragment;

public class TeamListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new TeamListFragment();
    }
}
