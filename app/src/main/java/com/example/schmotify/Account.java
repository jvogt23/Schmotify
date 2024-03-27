package com.example.schmotify;

public class Account {

    private final String ID;
    private String username;
    private String password;
    private String email;
    private String linkedSpotify;

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
}
