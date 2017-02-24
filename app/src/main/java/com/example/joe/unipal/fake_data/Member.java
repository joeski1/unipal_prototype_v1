package com.example.joe.unipal.fake_data;

import com.example.joe.unipal.R;

import java.io.Serializable;

public enum Member implements Serializable {
    STEVE("Steven Heart", "Medicine", new String[]{"Gaelic Football", "Running", "Bowling"}, R.drawable.user_steve, "I offered, it's fine", "Yesterday", false, true),
    JOE("Joe Bean", "Computer Science", new String[]{"Mountaineering", "Southampton FC"}, R.drawable.user_joe, "Hey are you still up for tonight?", "16:25", true, false),
    LILY("Lily Millar", "Medicine", new String[]{"Mountaineering"}, R.drawable.user_lily, "Yeah, goodluck :)", "13:11", true, false),
    BILLY("Billy Card", "Sport Science", new String[]{"Running"}, R.drawable.user_billy, "See you at the gates at 11", "Yesterday", true, false),
    GEMMA("Gemma Leeder", "Maths", new String[]{}, R.drawable.user_gemma, "My favourites are one to do lossless comp...", "11/03/2017", true, false);

    private String name, course, lastMessage, lastMessageSent;
    private String[] mutualInterests;
    private int picId;
    private boolean friend, requested;

    Member(String name, String course, String[] mutualInterests, int picId, String lastMessage, String lastMessageSent, Boolean friend, Boolean requested) {
        this.name = name;
        this.course = course;
        this.mutualInterests = mutualInterests;
        this.picId = picId;
        this.lastMessage = lastMessage;
        this.lastMessageSent = lastMessageSent;
        this.friend = friend;
        this.requested = requested;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public String[] getMutualInterests() {
        return mutualInterests;
    }

    public int getPicId() {
        return picId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getLastMessageSent() {
        return lastMessageSent;
    }

    public boolean isFriend() {
        return friend;
    }

    public void setFriend(Boolean b) { this.friend = b; }

    public boolean isRequested() {
        return requested;
    }

    public void setRequested(Boolean b) { this.requested = b; }
}
