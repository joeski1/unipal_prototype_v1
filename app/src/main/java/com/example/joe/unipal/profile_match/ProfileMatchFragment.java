package com.example.joe.unipal.profile_match;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.joe.unipal.R;
import com.example.joe.unipal.community.CommunityMembersFragment;
import com.example.joe.unipal.fake_data.Member;
import com.example.joe.unipal.messages.MessageActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileMatchFragment extends Fragment {

    private ProfileMatchAdapter adapter;
    private static final String LOG_TAG = CommunityMembersFragment.class.getSimpleName();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_messages, container, false);

        ArrayList<Member> members = new ArrayList<>();
        for(Member m : Member.values()){
            if(m.getMutualInterests().length > 0)
                members.add(m);
        }

        adapter =
                new ProfileMatchAdapter(
                        getActivity(), // The current context (this activity)
                        R.layout.fragment_messages, // The name of the layout ID.
                        members);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_messages);
        listView.setAdapter(adapter);
        return rootView;
    }

}

class ProfileMatchAdapter extends ArrayAdapter<Member> {
    private List<Member> members;

    public ProfileMatchAdapter(Context context, int resource, List<Member> objects) {
        super(context, resource, objects);
        this.members = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item_profile_match, null);
        }

        final Member m = getItem(position);

        ImageView image = (ImageView) v.findViewById(R.id.list_item_profile_match_image);
        image.setImageResource(m.getPicId());

        TextView name = (TextView) v.findViewById(R.id.list_item_profile_match_name);
        name.setText(m.getName());

        TextView lastSentMessage = (TextView) v.findViewById(R.id.list_item_profile_match_course);
        lastSentMessage.setText(m.getCourse());

        TextView lastSentTime = (TextView) v.findViewById(R.id.list_item_profile_match_mutual_interests);
        lastSentTime.setText(Arrays.toString(m.getMutualInterests()).replaceAll("\\[|\\]", ""));

        Button addFriend = (Button) v.findViewById(R.id.list_item_profile_match_button_add_friend);
        addFriend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                members.remove(m);
                notifyDataSetChanged();
            }
        });

        Button message = (Button) v.findViewById(R.id.list_item_profile_match_button_message);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MessageActivity.class).putExtra("member", m);
                getContext().startActivity(intent);
                ((Activity) getContext()).overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

        return v;
    }
}
