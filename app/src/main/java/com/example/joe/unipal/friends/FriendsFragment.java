package com.example.joe.unipal.friends;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.joe.unipal.R;
import com.example.joe.unipal.Utility;
import com.example.joe.unipal.community.CommunityMembersFragment;
import com.example.joe.unipal.fake_data.Member;
import com.example.joe.unipal.messages.MessageActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FriendsFragment extends Fragment {

    private FriendsAdapter friendAdapter;
    private FriendRequestAdapter friendRequestAdapter;

    private static final String LOG_TAG = CommunityMembersFragment.class.getSimpleName();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);

        ArrayList<Member> friends = Utility.getFriends();
        ArrayList<Member> friendRequests = Utility.getFriendRequests();

        if(friendRequests == null || friendRequests.isEmpty()) {
            TextView friendRequestTitle = (TextView) rootView.findViewById(R.id.text_view_friend_requests_title);
            friendRequestTitle.setVisibility(View.GONE);
        } else {
            friendRequestAdapter =
                    new FriendRequestAdapter(
                            getActivity(), // The current context (this activity)
                            R.layout.fragment_friends, // The name of the layout ID.
                            friendRequests, rootView, this);

            ListView flistView = (ListView) rootView.findViewById(R.id.listview_friend_requests);
            flistView.setAdapter(friendRequestAdapter);

        }

        friendAdapter =
                new FriendsAdapter(
                        getActivity(), // The current context (this activity)
                        R.layout.fragment_friends, // The name of the layout ID.
                        new ArrayList<>(friends));

        ListView listView = (ListView) rootView.findViewById(R.id.listview_friends);
        listView.setAdapter(friendAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Intent intent = new Intent(getActivity(), MessageActivity.class).putExtra("member", members.get(position));
//                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);            }
//        });
        return rootView;
    }

    public FriendsAdapter getFriendsAdapter() {
        return friendAdapter;
    }
}

class FriendRequestAdapter extends ArrayAdapter<Member> {

    private List<Member> members;
    private Member m;
    private ViewGroup parent;
    private FriendsFragment ff;
    private View parentView;

    public FriendRequestAdapter(Context context, int resource, List<Member> objects, View v, FriendsFragment ff) {
        super(context, resource, objects);
        this.members = objects;
        this.parentView = v;
        this.ff = ff;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View v = convertView;
        this.parent = parent;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item_friend_pending, null);
        }

        m = members.get(position);

        ImageView image = (ImageView) v.findViewById(R.id.list_item_friend_request_picture);
        image.setImageResource(m.getPicId());

        TextView name = (TextView) v.findViewById(R.id.list_item_friend_request_name);
        name.setText(m.getName());

        TextView mutuals = (TextView) v.findViewById(R.id.list_item_friend_request_mutual_interests);
        mutuals.setText(Arrays.toString(m.getMutualInterests()).replaceAll("\\[|\\]", ""));

        Button accept = (Button) v.findViewById(R.id.list_item_friend_request_accept);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m.setFriend(true);
                m.setRequested(false);
                members.remove(m);
                notifyDataSetChanged();
                if(members.isEmpty()) {
                    TextView title = (TextView) parentView.findViewById(R.id.text_view_friend_requests_title);
                    if (title != null) {
                        title.setVisibility(View.GONE);
                        ff.getFriendsAdapter().addMember(m);
                    }
                }
            }
        });

        Button decline = (Button) v.findViewById(R.id.list_item_friend_request_decline);
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m.setRequested(false);
                members.remove(m);
                notifyDataSetChanged();
                if(members.isEmpty()) {
                    TextView title = (TextView) parentView.findViewById(R.id.text_view_friend_requests_title);
                    if(title != null)
                        title.setVisibility(View.GONE);
                    else
                        Log.e("Request Adapter", "decline");
                }
            }
        });

        return v;
    }
}

class FriendsAdapter extends ArrayAdapter<Member> {
    private List<Member> members;
    private Member m;

    public FriendsAdapter(Context context, int resource, List<Member> objects) {
        super(context, resource, objects);
        this.members = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item_friend, null);
        }

        m = getItem(position);

        ImageView image = (ImageView) v.findViewById(R.id.list_item_friend_picture);
        image.setImageResource(m.getPicId());

        TextView name = (TextView) v.findViewById(R.id.list_item_friend_name);
        name.setText(m.getName());

        ImageView newMessage = (ImageView) v.findViewById(R.id.list_item_friend_new_message);
        newMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MessageActivity.class).putExtra("member", m);
                getContext().startActivity(intent);
                ((Activity) getContext()).overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

        return v;
    }

    public void addMember(Member m){
        members.add(m);
        notifyDataSetChanged();
    }
}
