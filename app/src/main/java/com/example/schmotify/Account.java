package com.example.schmotify;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private final String ID;
    private String username;
    private String password;
    private final String email;
    private String linkedSpotify;

    private String mAccessToken;

    private String mResetToken;

    private List<Account> friendsList = new ArrayList<>();

    public Account(String ID, String username, String email, String password) {
        this.ID = ID;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLinkedSpotify(String linkedSpotify) {
        this.linkedSpotify = linkedSpotify;
    }

    public void setmAccessToken(String newToken) {mAccessToken = newToken;}

    public void setmResetToken(String newToken) {mResetToken = newToken;}

    public String getID() {
        return ID;
    }

    public String getEmail() { return email; }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLinkedSpotify() {
        return linkedSpotify;
    }

    public String getmAccessToken() {return mAccessToken;}

    public String getmResetToken() {return mResetToken;}

    public void addFriend(Account friend) {
        friendsList.add(friend);
    }

    public void removeFriend(Account friend) {
        friendsList.remove(friend);
    }

}
