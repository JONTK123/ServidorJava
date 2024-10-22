package org.example.models;

import java.io.Serializable;

public class Trajeto implements Serializable {

    private String company;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;

    public Trajeto(String company, String origin, String destination, String dT, String aT){
        this.company = company;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = dT;
        this.arrivalTime = aT;
    }

    public String getCompany(){
        return this.company;
    }
    public String getOrigin(){
        return this.origin;
    }
    public String getDestination(){
        return this.destination;
    }
    public String getDepartureTime(){
        return this.departureTime;
    }
    public String getArrivalTime(){
        return this.arrivalTime;
    }

    @Override
    public boolean equals(Object b){
        if(b == null) return false;
        if(b == this) return true;
        if(b.getClass()!= this.getClass()) return false;

        Trajeto other = (Trajeto) b;
        if(!this.company.equals(other.company)) return false;
        if(!this.origin.equals(other.origin)) return false;
        if(!this.destination.equals(other.destination)) return false;
        if(!this.departureTime.equals(other.departureTime)) return false;
        if(!this.arrivalTime.equals(other.arrivalTime)) return false;
        return true;
    }

    @Override
    public int hashCode(){
        int ret = 1;
        ret = ret * 7 + this.company.hashCode();
        ret = ret * 7 + this.origin.hashCode();
        ret = ret * 7 + this.destination.hashCode();
        ret = ret * 7 + this.departureTime.hashCode();
        ret = ret * 7 + this.arrivalTime.hashCode();
        return ret;
    }

    @Override
    public String toString(){
        return(this.company+"/"+this.origin+"/"+this.destination+"/"+this.departureTime+"/"+this.arrivalTime);
    }

}
