package com.example.snr.errand_helper;


public class User  {

    private String email, password;

    int userID;

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public void setUserID(int userID){
        this.userID = userID;
    }

    public int getUserID(){
        return this.userID;
    }
}
