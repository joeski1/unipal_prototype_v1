package com.example.joe.unipal;


import com.example.joe.unipal.fake_data.Member;

import java.util.ArrayList;
import java.util.Arrays;

public class Utility {

    static String[] interests = new String[]{"Gaelic Football", "Mountaineering", "Magic", "IT innovation", "Bowling", "Southampton FC"};

    public static int getMutualInterest(Member m){
        int mutual = 0;
        ArrayList<String> theirInterests = new ArrayList<>(Arrays.asList(m.getMutualInterests()));
        for(String interest : interests){
            if(theirInterests.contains(interest))
                mutual++;
        }
        return mutual;
    }

    public static ArrayList<Member> getFriends(){
        ArrayList<Member> friends = new ArrayList<>();
        for(Member m : Member.values()) {
            if (m.isFriend())
                friends.add(m);
        }
        return friends;
    }

    public static ArrayList<Member> getFriendRequests(){
        ArrayList<Member> friends = new ArrayList<>();
        for(Member m : Member.values()) {
            if (m.isRequested())
                friends.add(m);
        }
        return friends;
    }
}
