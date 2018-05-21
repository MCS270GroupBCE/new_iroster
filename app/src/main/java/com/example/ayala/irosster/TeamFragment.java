package com.example.ayala.irosster;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private static final String HAS_TEAM_CHANGED = "com.example.ayala.irosster.has_team_changed";

    private RecyclerView mPlayerRecyclerView;
    private PlayerAdapter mAdapter;
    private EditText mTeamName;
    private Team mTeam;
    private boolean mHasTeamChanged = false;

    public static TeamFragment newInstance(UUID teamId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_TEAM_ID, teamId);

        TeamFragment fragment = new TeamFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID teamId = (UUID) getArguments().getSerializable(ARG_TEAM_ID);
        mTeam = TeamLab.get(getActivity()).getTeam(teamId);
        setHasOptionsMenu(true);
    }

    private void returnResult() {
        Intent data = new Intent();
        data.putExtra(HAS_TEAM_CHANGED, mHasTeamChanged);
        data.putExtra(ARG_TEAM_ID, mTeam.getId());
        getActivity().setResult(Activity.RESULT_OK, data);
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
                    CharSequence s, int start, int count, int after){

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                mTeam.setTeamName(s.toString());
                mHasTeamChanged = true;
            }
            @Override
            public void afterTextChanged(Editable s){
                returnResult();
            }
        });

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_team, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.new_player:
                Player player = new Player();
                PlayerLab.get(getActivity()).newPlayer(player);
                Intent intent = PlayerActivity.newIntent(getActivity(), player.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
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