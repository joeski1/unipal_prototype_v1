package com.example.joe.unipal.community;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joe.unipal.R;
import com.example.joe.unipal.fake_data.Member;
import com.example.joe.unipal.messages.MessageActivity;

public class MemberPopupFragment extends Fragment {

    private Member member;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_member_popup, container, false);

        member = ((MemberPopupActivity) getActivity()).getMember();

        TextView nameTextView = (TextView) rootView.findViewById(R.id.member_textView_name);
        nameTextView.setText(member.getName());

        TextView courseTextView = (TextView) rootView.findViewById(R.id.member_textView_course);
        courseTextView.setText(member.getCourse());

        StringBuilder builder = new StringBuilder();
        String[] mutuals = member.getMutualInterests();
        for (int i = 0; i < mutuals.length; i++) {
            builder.append(mutuals[i] + (i == mutuals.length - 1 ? "" : ", "));
        }

        TextView mutualTextView = (TextView) rootView.findViewById(R.id.member_textView_mutual_interests);
        mutualTextView.setText(builder.toString());

        ImageView profilePic = (ImageView) rootView.findViewById(R.id.member_imageView_picture);
        profilePic.setImageResource(member.getPicId());

        Button addFriend = (Button) rootView.findViewById(R.id.member_button_add_friend);
        addFriend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        Button messageButton = (Button) rootView.findViewById(R.id.member_button_message);
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MessageActivity.class).putExtra("member", member);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });

        return rootView;
    }
}
