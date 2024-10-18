package org.example.models;

public class Empresa {

    private String name;
    private String email;
    private String CNPJ;
    private String CEP;
    private String address;
    private String phone;
    private String password;
    private int Qtd_reviews;
    private double Av_grade;

    public Empresa(String name, String email, String CNPJ, String CEP, String address, String phone, String password, int Qtd_reviews, double Av_grade){
        this.name = name;
        this.email = email;
        this.CNPJ = CNPJ;
        this.CEP = CEP;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.Qtd_reviews = Qtd_reviews;
        this.Av_grade = Av_grade;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getCNPJ(){
        return this.CNPJ;
    }

    public String getCEP(){
        return this.CEP;
    }

    public String getAddress(){
        return this.address;
    }

    public String getPhone(){
        return this.phone;
    }

    public String getPassword(){
        return this.password;
    }

    public int getQtdeReviews(){
        return this.Qtd_reviews;
    }

    public double getAvGrade(){
        return this.Av_grade;
    }

    @Override
    public boolean equals(Object b){
        if(b == null) return false;
        if(b == this) return true;
        if(b.getClass()!= this.getClass()) return false;

        Empresa other = (Empresa) b;

        if(!this.name.equals(other.name)) return false;
        if(!this.email.equals(other.email)) return false;
        if(!this.CNPJ.equals(other.CNPJ)) return false;
        if(!this.CEP.equals(other.CEP)) return false;
        if(!this.address.equals(other.address)) return false;
        if(!this.phone.equals(other.phone)) return false;
        if(!this.password.equals(other.password)) return false;
        if(this.Qtd_reviews != other.Qtd_reviews) return false;
        if(this.Av_grade != other.Av_grade) return false;
        return true;
    }

    @Override
    public int hashCode(){
        int ret = 1;
        ret = ret * 7 + this.name.hashCode();
        ret = ret * 7 + this.email.hashCode();
        ret = ret * 7 + this.CNPJ.hashCode();
        ret = ret * 7 + this.CEP.hashCode();
        ret = ret * 7 + this.phone.hashCode();
        ret = ret * 7 + this.address.hashCode();
        ret = ret * 7 + this.password.hashCode();
        ret = ret * 7 + Integer.valueOf(this.Qtd_reviews).hashCode();
        ret = ret * 7 + Double.valueOf(this.Av_grade).hashCode();
        return ret;
    }

    @Override
    public String toString(){
        return(this.name+"/"
                +this.email+"/"
                +this.CNPJ+"/"
                +this.CEP+"/"
                +this.phone+"/"
                +this.address+"/"
                +this.password+"/"
                +this.Qtd_reviews+"/"
                +this.Av_grade);
    }

}