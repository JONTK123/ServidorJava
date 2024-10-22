package org.example.models;

import java.io.Serializable;

public class Avaliacao implements Serializable {
    private String user;
    private String company;
    private String comment;
    private  int grade;

    public Avaliacao(String username, String company, String comment, int grade){
        this.user = username;
        this.company = company;
        this.comment = comment;
        this.grade = grade;
    }

    public String getUser(){
        return this.user;
    }
    public String getCompany(){
        return this.company;
    }
    public String getComment(){
        return this.comment;
    }
    public int getGrade(){
        return this.grade;
    }

    @Override
    public boolean equals(Object b){
        if(b == null) return false;
        if(b == this) return true;
        if(b.getClass()!= this.getClass()) return false;

        Avaliacao other = (Avaliacao) b;
        if(!this.user.equals(other.user)) return false;
        if(!this.company.equals(other.company)) return false;
        if(!this.comment.equals(other.comment)) return false;
        if(this.grade != other.grade) return false;
        return true;
    }

    @Override
    public int hashCode(){
        int ret = 1;
        ret = ret * 7 + this.user.hashCode();
        ret = ret * 7 + this.company.hashCode();
        ret = ret * 7 + this.comment.hashCode();
        ret = ret * 7 + Integer.valueOf(this.grade).hashCode();
        return ret;
    }

    @Override
    public String toString(){
        return(this.user+"/"+this.company+"/"+this.comment+"/"+this.grade);
    }
}
