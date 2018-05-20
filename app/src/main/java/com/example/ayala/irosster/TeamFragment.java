package com.example.ayala.irosster;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.UUID;

public class TeamFragment extends Fragment {

    private static final String ARG_TEAM_ID = "team_id";

    private RecyclerView mPlayerRecyclerView;
    private PlayerAdapter mAdapter;
    private EditText mTeamName;
    private Team mTeam;

    public static TeamFragment newInstance(UUID teamId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_TEAM_ID, teamId);

        TeamFragment fragment = new TeamFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID teamId = (UUID) getActivity().getIntent().getSerializableExtra(TeamActivity.EXTRA_TEAM_ID);
        mTeam = TeamLab.get(getActivity()).getTeam(teamId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team, container, false);

        mPlayerRecyclerView = (RecyclerView) view.findViewById(R.id.player_recycler_view);
        mPlayerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mTeamName = (EditText) view.findViewById(R.id.team_name_edit);
        mTeamName.setText(mTeam.getTeamName());
        mTeamName.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after){}

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count){
                mTeam.setTeamName(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s){

            }
        });

        updateUI();

        return view;
    }

    private void updateUI() {
        PlayerLab playerLab = PlayerLab.get(getActivity());
        List<Player> players = playerLab.getPlayers();

        if (mAdapter == null) {
            mAdapter = new PlayerAdapter(players);
            mPlayerRecyclerView.setAdapter(mAdapter);
        } else{
            mAdapter.notifyDataSetChanged();
        }
    }

    private class PlayerHolder extends RecyclerView.ViewHolder implements OnClickListener {

        private TextView mPlayerNameTextView;
        private Player mPlayer;

        public PlayerHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_player, parent, false));
            itemView.setOnClickListener(this);

            mPlayerNameTextView = (TextView) itemView.findViewById(R.id.player_name);
        }

        public void bind(Player player) {
            mPlayer = player;
            mPlayerNameTextView.setText(mPlayer.getPlayerName());
        }

        @Override
        public void onClick(View view) {
            Intent intent = PlayerActivity.newIntent(getActivity(), mPlayer.getId());
            startActivity(intent);
        }
    }

    private class PlayerAdapter extends RecyclerView.Adapter<PlayerHolder> {

        private List<Player> mPlayers;

        public PlayerAdapter(List<Player> players) {
            mPlayers = players;
        }

        @Override
        public PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new PlayerHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(PlayerHolder holder, int position) {
            Player player = mPlayers.get(position);
            holder.bind(player);
        }

        @Override
        public int getItemCount() {
            return mPlayers.size();
        }
    }
}