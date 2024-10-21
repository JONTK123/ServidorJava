package org.example.models;

import org.example.Comunicado;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String name;
    private String email;
    private String birthday;

    public Usuario(String name, String email, String birthday){
        this.name = name;
        this.email = email;
        this.birthday = birthday;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getData(){
        return this.birthday;
    }


    @Override
    public boolean equals(Object b){
        if(b == null) return false;
        if(b == this) return true;
        if(b.getClass()!= this.getClass()) return false;

        Usuario other = (Usuario) b;
        if(!this.name.equals(other.name)) return false;
        if(!this.email.equals(other.email)) return false;
        if(!this.birthday.equals(other.birthday)) return false;
        return true;
    }

    @Override
    public int hashCode(){
        int ret = 1;
        ret = ret * 7 + this.name.hashCode();
        ret = ret * 7 + this.email.hashCode();
        ret = ret * 7 + this.birthday.hashCode();
        return ret;
    }

    @Override
    public String toString(){
        return(this.name+"/"+this.email+"/"+this.birthday);
    }

}
