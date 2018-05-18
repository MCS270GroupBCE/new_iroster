package com.example.ayala.irosster;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.UUID;

public class PlayerFragment extends Fragment {

    private Player mPlayer;
    private EditText mPlayerNameField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID playerId = (UUID) getActivity().getIntent()
                .getSerializableExtra(PlayerActivity.EXTRA_PLAYER_ID);
        mPlayer = PlayerLab.get(getActivity()).getPlayer(playerId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_player, container, false);

        mPlayerNameField = (EditText) v.findViewById(R.id.player_name);
        mPlayerNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayer.setPlayerName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return v;
    }
}