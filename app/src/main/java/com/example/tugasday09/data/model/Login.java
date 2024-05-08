package com.example.tugasday09.data.model;

import com.google.gson.annotations.SerializedName;

public class Login{

    @SerializedName("data")
    private LoginData loginData;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public LoginData getData(){
        return loginData;
    }

    public void setData(LoginData loginData) {
        this.loginData = loginData;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage(){
        return message;
    }

    public boolean isStatus(){
        return status;
    }
}