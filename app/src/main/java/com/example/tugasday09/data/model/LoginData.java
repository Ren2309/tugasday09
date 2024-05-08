package com.example.tugasday09.data.model;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @SerializedName("name")
    private String name;

    @SerializedName("user_id")
    private String id;

    @SerializedName("username")
    private String username;

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }
}