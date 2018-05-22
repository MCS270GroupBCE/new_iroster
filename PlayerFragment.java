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

    private static final String ARG_PLAYER_ID = "player_id";

    private Player mPlayer;
    private EditText mPlayerName;

    public static PlayerFragment newInstance(UUID playerId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_PLAYER_ID, playerId);

        PlayerFragment fragment = new PlayerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID playerId = (UUID) getArguments().getSerializable(ARG_PLAYER_ID);
        mPlayer = PlayerLab.get(getActivity()).getPlayer(playerId);
    }

    @Override
    public void onPause(){
        super.onPause();

        PlayerLab.get(getActivity()).updatePLayer(mPlayer);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_player, container, false);

        mPlayerName = (EditText) v.findViewById(R.id.player_name_edit);
        mPlayerName.setText(mPlayer.getPlayerName());
        mPlayerName.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
                //left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                mPlayer.setPlayerName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s){
                //left blank
            }
        });
        return v;
    }
}