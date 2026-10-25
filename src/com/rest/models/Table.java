package com.rest.models;

public class Table{
    public enum Availability{
        OCCUPIED,FREE
    }

    private int id;
    private int capacity;
    private Availability status;
    
    public Table(int id , int capacity){
        this.id = id;
        this.capacity = capacity;
        this.status = Availability.FREE;
    }

    public int getId(){
        return id;
    }

    public int getCapasity(){
        return capacity;
    }

    public void setStatus(Availability status) {
        this.status = status;
    }

    public Availability getStatus(){
        return status;
    }

    public void booking(){
        status = Availability.OCCUPIED;
    }

    public void freeNow(){
        status = Availability.FREE;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
