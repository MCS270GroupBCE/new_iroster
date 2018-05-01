package com.example.ayala.irosster;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ayala on 4/30/2018.
 */

public class TeamListFragment extends Fragment{

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    private RecyclerView mTeamRecyclerView;
    private boolean mSubtitleVisable;


    @Override
    public void onCreat(Bundle savedInstanceState){
        super.OnCreat(savedInstanceState);
        setHasOptionsMenu(true); // pg 142 in book to be continued
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup cpntatiner,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(android.R.layout.fragment_team_list, container, false);

        mTeamRecyclerView = (RecyclerView) view.findViewById(android.R.id.team_recycler_view);

        mTeamRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (savedInstanceState != null) {
            mSubtitleVisable = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }
        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public void onSavedInstanceState(Bundle outsState) {
        super.onSaveInstanceState(outsState);
        outsState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisable);
    }

   /* @Override (this code is for a menu may use later)
    public void onCreateOptionMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(android.R.menu.fragment_team_list, menu);

        MenuItem subtitleItem = memu.findItem(R.id.show_)
    } */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_team:
                Team team = new team();
                teamLab.get(getActivity()).addteam(team);
                Intent intent = TeamPagerActivity
                        .newIntent(getActivity(), team.getId());
                startActivity(intent);
                return true;
            case R.id.show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {
        TeamLab teamLab = TeamLab.get(getActivity());
        int teamCount = teamLab.getTeams().size();
        String subtitle = getString(R.string.subtitle_format, teamCount);

        if (!mSubtitleVisible) {
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateUI() {
        TeamLab teamLab = TeamLab.get(getActivity());
        List<Team> teams = TeamLab.getCrimes();

        if (mAdapter == null) {
            mAdapter = new TeamAdapter(teams;
            mTeamRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setTeams(teams);
            mAdapter.notifyDataSetChanged();
        }

        updateSubtitle();
    }



    private class TeamHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Team mTeam;

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;

        public TeamHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_team, parent, false));
            itemView.setOnClickListener(this);
            // this is code to display teams. make more classes for things!
            mTitleTextView = (TextView) itemView.findViewById(R.id.team_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.crime_solved);
        }

        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View view) {
            Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(intent);
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

        public void setCrimes(List<Crime> crimes) {
            mCrimes = crimes;
        }
    }

}
