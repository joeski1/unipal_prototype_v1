package com.example.joe.unipal.community;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.joe.unipal.R;
import com.example.joe.unipal.fake_data.Community;

import java.util.ArrayList;

public class CommunitySearchActivity extends AppCompatActivity {

    private TextView mTextView;
    private ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTextView = (TextView) findViewById(R.id.text);
        mListView = (ListView) findViewById(R.id.list);

        setTitle("Search All Communities");

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }

    private void doMySearch(String query) {
        String[] communities = getMatchingCommunities(query);
        Boolean noneFound = false;
        if (communities.length == 0) {
            mTextView.setText("No results found for \"" + query + "\"");
            noneFound = true;
            communities = new String[]{"Create a new community"};
        } else
            mTextView.setText(communities.length + " results found!");
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, communities));

    }

    // ArrayAdapter(Context context, int resource, int textViewResourceId, T[] objects)
    private String[] getMatchingCommunities(String query) {
        ArrayList<String> coms = new ArrayList<>();
        for (Community c : Community.values()) {
            if (c.getName().toLowerCase().contains(query.toLowerCase()))
                coms.add(c.getName());
        }
        return coms.toArray(new String[coms.size()]);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

}
