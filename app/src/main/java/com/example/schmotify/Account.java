package com.example.schmotify;

public class Account {

    private final long ID;
    private String username;
    private String password;
    private String linkedSpotify;

    public Account(int ID, String username, String password) {
        this.ID = ID;
        this.username = username;
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

    public long getID() {
        return ID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLinkedSpotify() {
        return linkedSpotify;
    }
}
