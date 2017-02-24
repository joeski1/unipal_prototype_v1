package com.example.joe.unipal.communities;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joe.unipal.R;
import com.example.joe.unipal.fake_data.Community;

import java.util.List;

public class CommunitiesAdapter extends ArrayAdapter<Community> {
    private static final String LOG_TAG = CommunitiesActivity.class.getSimpleName();
    private ImageView image;
    private List<Community> myCommunities;

    public CommunitiesAdapter(Context context, int resource, List<Community> objects) {
        super(context, resource, objects);
        this.myCommunities = objects;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item_community, null);
        }

        Community c = getItem(position);

        image = (ImageView) v.findViewById(R.id.list_item_options);
        image.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(getContext(), image);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.community_popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favourite_community:
                                break;
                            case R.id.action_leave_community:
                                myCommunities.get(position).setAdded(false);
                                myCommunities.remove(position);
                                notifyDataSetChanged();
                                break;
                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        });

        if (c != null) {
            TextView newMessagesView = (TextView) v.findViewById(R.id.list_item_new_messages_count);
            TextView nameView = (TextView) v.findViewById(R.id.list_item_name);
            TextView recentMessageView = (TextView) v.findViewById(R.id.list_item_recent_message);

            if (newMessagesView != null && c.getNewMessages() != 0)
                newMessagesView.setText("" + c.getNewMessages());

            if (nameView != null)
                nameView.setText(c.getName());

            if (recentMessageView != null)
                recentMessageView.setText("Most recent message goes here");

        }

        return v;
    }
}
