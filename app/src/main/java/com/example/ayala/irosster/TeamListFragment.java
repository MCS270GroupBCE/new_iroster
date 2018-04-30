package com.example.ayala.irosster;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ayala on 4/30/2018.
 */

public class TeamListFragment extends Fragment{
    private RecyclerView mTeamRecyclerView;


    @Override
    public void onCreat(Bundle savedInstanceState){
        super.OnCreat(savedInstanceState);
        setHasOptionsMenu(ture); // pg 142 in book to be continued
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup cpntatiner,
                             Bundle savedInstanceState){
        View view = inflater.inflate(android.R.layout.fragment_team_list , container, false);
    }

}
