package com.example.joe.unipal.community;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.joe.unipal.R;
import com.example.joe.unipal.fake_data.Member;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommunityMembersFragment extends Fragment {

    private MembersAdapter adapter;
    private static final String LOG_TAG = CommunityMembersFragment.class.getSimpleName();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_community_member_list, container, false);

        adapter =
                new MembersAdapter(
                        getActivity(), // The current context (this activity)
                        R.layout.fragment_community_member_list, // The name of the layout ID.
                        new ArrayList<Member>(Arrays.asList(Member.values())));

        ListView listView = (ListView) rootView.findViewById(R.id.listview_community_members);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getActivity(), MemberPopupActivity.class).putExtra("member", Member.values()[position]);
                startActivity(intent);
            }
        });
        return rootView;
    }

}

class MembersAdapter extends ArrayAdapter<Member> {
    private List<Member> members;

    public MembersAdapter(Context context, int resource, List<Member> objects) {
        super(context, resource, objects);
        this.members = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item_member, null);
        }

        Member m = getItem(position);

        ImageView image = (ImageView) v.findViewById(R.id.list_item_member_picture);
        image.setImageResource(m.getPicId());

        TextView name = (TextView) v.findViewById(R.id.list_item_member_name);
        name.setText(m.getName());

        TextView mutualInterests = (TextView) v.findViewById(R.id.list_item_member_mutual_interests);
        int mutuals = m.getMutualInterests().length;
        mutualInterests.setText(mutuals > 0 ? mutuals + " mutual interests!" : "");

        return v;
    }
}
