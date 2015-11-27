package com.example.snr.errand_helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Leifeng on 2015-10-20.
 */
public class Task {

    private String name, type, description, status, creatorID,creationTime,dueTime;
    private int taskID, workerID;

    public Task() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd ");//create a format of date
        this.creationTime = dateFormat.format(new Date());//add the time and date when the object is created or initialized
    }

    public String getCreationTime() {
        return this.creationTime;
    }

    public void setDueTime(String due) {
        this.dueTime = due;
    }

    public String getDueTime() {
        return this.dueTime;
    }

    public void setTaskID(int taskID){
        this.taskID = taskID;
    }

    public int getTaskID(){
        return this.taskID;
    }

    public void setCreator(String creatorID){
        this.creatorID = creatorID;
    }

    public String getCreator(){
        return this.creatorID;
    }

    public void setWorker(int workerID){
        this.workerID = workerID;
    }

    public int getWorker(){
        return this.workerID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
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
