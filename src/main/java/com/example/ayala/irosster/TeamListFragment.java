package com.example.ayala.irosster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TeamListFragment extends Fragment{

    private RecyclerView mTeamRecyclerView;
    private TeamAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_team_list, container, false);

        mTeamRecyclerView = (RecyclerView) view.findViewById(R.id.team_recycler_view);
        mTeamRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        TeamLab teamLab = TeamLab.get(getActivity());
        List<Team> teams = teamLab.getTeams();

        if (mAdapter == null) {
            mAdapter = new TeamAdapter(teams);
            mTeamRecyclerView.setAdapter(mAdapter);
        } else{
            mAdapter.setTeams(teams);
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_team_list, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.new_team:
                Team team = new Team();
                TeamLab.get(getActivity()).addTeam(team);
                Intent intent = TeamActivity.newIntent(getActivity(), team.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class TeamHolder extends RecyclerView.ViewHolder implements OnClickListener {

        private TextView mTeamNameView;
        private Team mTeam;

        public TeamHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            mTeamNameView = (TextView) itemView.findViewById(R.id.team_name);
        }

        public void bind(Team team){
            mTeam = team;
            mTeamNameView.setText(mTeam.getTeamName());

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
            View view = layoutInflater.inflate(R.layout.list_item_team, parent, false);
            return new TeamHolder(view);
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

        public void setTeams(List<Team> teams){
            mTeams = teams;
        }
    }
}
