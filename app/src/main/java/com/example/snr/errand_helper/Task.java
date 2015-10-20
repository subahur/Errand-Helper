package com.example.snr.errand_helper;

/**
 * Created by Leifeng on 2015-10-20.
 */
public class Task {
    String creator, type, description;

    public void setCreator(String creator){
        this.creator = creator;
    }

    public String getCreator(){
        return this.creator;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

}
