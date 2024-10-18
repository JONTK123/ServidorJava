package org.example.models;

public class Usuario {

    private String name;
    private String email;
    private Data birthday;
    private String password;

    public Usuario(String name, String email, Data birthday, String password){
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.password = password;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public Data getData(){
        return this.birthday;
    }

    public String getPassword(){
        return this.password;
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
        if(!this.password.equals(other.password)) return false;
        return true;
    }

    @Override
    public int hashCode(){
        int ret = 1;
        ret = ret * 7 + this.name.hashCode();
        ret = ret * 7 + this.email.hashCode();
        ret = ret * 7 + this.birthday.hashCode();
        ret = ret * 7 + this.password.hashCode();
        return ret;
    }

    @Override
    public String toString(){
        return(this.name+"/"+this.email+"/"+this.birthday+"/"+this.password);
    }


}
