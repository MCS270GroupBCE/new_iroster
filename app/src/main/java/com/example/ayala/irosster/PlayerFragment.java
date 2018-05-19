package com.example.ayala.irosster;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

public class PlayerFragment extends Fragment {

    private Player mPlayer;
    private TextView mPlayerName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_player, container, false);

        mPlayerName = (TextView) v.findViewById(R.id.player_name_top);
        UUID playerId = (UUID) getActivity().getIntent().getSerializableExtra(PlayerActivity.EXTRA_PLAYER_ID);
        mPlayer = PlayerLab.get(getActivity()).getPlayer(playerId);
        mPlayerName.setText(mPlayer.getPlayerName());




        return v;
    }
}