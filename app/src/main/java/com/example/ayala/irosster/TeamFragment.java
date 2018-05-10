package com.example.ayala.irosster;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class TeamFragment extends Fragment {

    private Team mTeam;
    private EditText mTeamNameField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTeam = new Team();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_team, container, false);

        mTeamNameField = (EditText) v.findViewById(R.id.team_name);
        mTeamNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTeam.setTeamName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return v;
    }
}