package com.example.joe.unipal.fake_data;

import java.io.Serializable;

public enum Community implements Serializable {
    FLAT("Mason Flat 129", 6, 3, true),
    COMPSCI("Computer Science 2016/2017", 103, 0, true),
    SOUTHAMPTONFC("Southampton FC", 14, 12, true),
    GAELIC("Gaelic Football", 30, 7, true),
    MAGIC("Magic", 16, 0, true);


    private String name;
    private int newMessages, members;
    private Boolean added = true;

    Community(String name, int members, int newMessages, boolean added) {
        this.name = name;
        this.members = members;
        this.newMessages = newMessages;
        this.added = added;
    }

    public void setAdded(Boolean b) {
        added = b;
    }

    public String getName() {
        return name;
    }

    public int getNewMessages() {
        return newMessages;
    }

    public Boolean isAdded() {
        return added;
    }
}
