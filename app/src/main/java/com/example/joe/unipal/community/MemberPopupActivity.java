package com.example.joe.unipal.community;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.joe.unipal.R;
import com.example.joe.unipal.fake_data.Member;

public class MemberPopupActivity extends Activity {

    private Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        member = (Member) intent.getSerializableExtra("member");

        setContentView(R.layout.content_member_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8), (int) (height*.3));
    }

    public Member getMember() {
        return member;
    }
}
