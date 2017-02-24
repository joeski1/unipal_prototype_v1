package com.example.joe.unipal.communities;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.joe.unipal.community.CommunityActivity;
import com.example.joe.unipal.R;
import com.example.joe.unipal.fake_data.Community;

import java.util.ArrayList;

public class CommunitiesFragment extends Fragment {

    private CommunitiesAdapter adapter;
    private ArrayList<Community> myCommunities;
    private static final String LOG_TAG = CommunitiesActivity.class.getSimpleName();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_communities, container, false);

        myCommunities = new ArrayList<Community>();
        for(Community c : Community.values()){
            if (c.isAdded())
                myCommunities.add(c);
        }
        adapter =
                new CommunitiesAdapter(
                        getActivity(), // The current context (this activity)
                        R.layout.listview_communities, // The name of the layout ID.
                        myCommunities);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_communities);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CommunityActivity.class).putExtra("position", myCommunities.get(position));
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_communities, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_search) {
            getActivity().onSearchRequested();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
