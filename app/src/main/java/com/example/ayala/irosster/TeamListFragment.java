package com.example.ayala.irosster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TeamListFragment extends Fragment{


    private RecyclerView mTeamRecyclerView;
    private TeamAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_team_list, container, false);

        mTeamRecyclerView = (RecyclerView) view.findViewById(R.id.team_recycler_view);
        mTeamRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI(){
        TeamLab teamLab = TeamLab.get(getActivity());
        List<Team> teams = teamLab.getTeams();

        mAdapter = new TeamAdapter(teams);
        mTeamRecyclerView.setAdapter(mAdapter);
    }

    private class TeamHolder extends RecyclerView.ViewHolder implements OnClickListener {

        private TextView mTitleTextView;
        private Team mTeam;

        public TeamHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_team, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.team_name);
        }

        public void bind(Team team){
            mTeam = team;
            mTitleTextView.setText(mTeam.getTeamName());
        }

        @Override
        public void onClick(View view) {
            Intent intent = TeamActivity.newIntent(getActivity(), mTeam.getId());
            startActivity(intent);
        }
    }

    private class TeamAdapter extends RecyclerView.Adapter<TeamHolder> {

        private List<Team> mTeams;

        public TeamAdapter(List<Team> teams){
            mTeams = teams;
        }

        @Override
        public TeamHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new TeamHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(TeamHolder holder, int position){
            Team team = mTeams.get(position);
            holder.bind(team);
        }

        @Override
        public int getItemCount(){
            return mTeams.size();
        }
    }

}
