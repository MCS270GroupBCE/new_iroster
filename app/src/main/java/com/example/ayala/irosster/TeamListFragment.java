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

public class TeamListFragment extends Fragment {

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    private RecyclerView mTeamRecyclerView;
    private boolean mSubtitleVisable;


    /**
     * for teamlab.java
     *
     * private Context mContext;
     * private SQliteDarabase mDatabase;
     *
     * in private CrimeLab()
     * mContext = context.getApplicationContext();
     * mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
     * mTeams = new ArrayList <>();
     *
     * private static ContentValues get ContentValues(Team team){
     *     Contentvalues values = new ContentValues();
     *     values.put(TeamTable.Cols.UUID, team.getID().toString());
     *      values.put(TeamTable.Cols.Title, team.getTitle());
     *
     *      return values;
     *
     * public void AddTeam(Team t){
     *     Contentvalues values = getcontentValues(c);
     *     mDatabase.insert(crimeTable.Name, null, values);
     * }
     *
     * public void updateTeam(Team team){
     *     String uuidString = team.getId().toString();
     *     ContentValues values = getContentValues(team);
     *
     *     mDatabase.update(TeamTable.Name, Values, TeamTable.Cols.UUID + " = ?", new String[] {uuidString});
     * }
     *
     * for TeamBaseHelper.java
     *
     * @override
     * public void oncreate(SQLitedatabase db)
     *  db.execSQl("create table " + teamDBSchema.TeamTable.Name + "(" + // need to import something for TeamTable
     *  "TeamTable.Cols.UUID + ", " +
     *  "TeamTable.Cols.Title + ", " + ");
     *
     * // pg 277 in book makes you delete some stuff on TeamLab
     *
     * In TeamLab.java
     *
     * }
     */


}