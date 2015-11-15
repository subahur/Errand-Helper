package com.example.snr.errand_helper;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Leifeng on 2015-10-20.
 */
public class Task {

    private String name, type, description;
    private Date creationTime, dueTime;
    private int creatorID, workerID;

    public Task() {
        this.creationTime = Calendar.getInstance().getTime();
    }

    public Date getCreationTime() {
        return this.creationTime;
    }

    public void setDueTime(Date due) {
        this.dueTime = due;
    }

    public Date getDueTime() {
        return this.dueTime;
    }

    public void setCreator(int creatorID){
        this.creatorID = creatorID;
    }

    public int getCreator(){
        return this.creatorID;
    }

    public void setWorker(int workerID){
        this.workerID = workerID;
    }

    public int getWorker(){
        return this.workerID;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getNmae(){
        return this.name;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public void setDesc(String description){
        this.description= description;
    }

    public String getDesc(){
        return this.description;
    }

}
