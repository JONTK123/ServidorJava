package org.example.models;

public class Data implements Comparable <Data>, Cloneable {
    private byte  dia;
    private byte  mes;
    private short ano;
    private static int qtd=0;

    public static boolean isBissexto (short ano)
    {
        // Calendario Juliano
        if (ano<1582)
            if (ano%4==0)
                return true;
            else
                return false;

        // Calendario Gregoriano
        if (ano%400==0) return true;
        if (ano%  4==0 && ano%100!=0) return true;
        return false;
    }

    public static boolean isValida (byte dia, byte mes, short ano)
    {
        if (ano<-45) return false; // antes do Calendario Juliano
        if (ano== 0) return false; // nao existiu ano 0; do ano 1ac foi direto para o ano 1dc
        if (ano==1582 && mes==10 && dia>=5 && dia<=14) return false; // dias cortados dos calendario pelo Papa Gregorio

        if (dia<1 || dia>31 || mes<1 || mes>12) return false;

        if (dia>30 && (mes==4 || mes==6 || mes==9 || mes==11)) return false;
        if (dia>29 && mes==2) return false;
        if (dia>28 && mes==2 && !Data.isBissexto(ano)) return false;

        return true;
    }

    public /*void*/ Data (byte dia, byte mes, short ano) throws Exception
    {
        if (!Data.isValida(dia,mes,ano))
            throw new Exception ("Data invalida");

        this.dia=dia;
        this.mes=mes;
        this.ano=ano;

        Data.qtd++;
    }

    public static int getQtd ()
    {
        return Data.qtd;
    }

    public void setDia (byte dia) throws Exception
    {
        if (!Data.isValida(dia,this.mes,this.ano))
            throw new Exception ("Dia invalido");

        this.dia=dia;
    }

    public byte getDia ()
    {
        return this.dia;
    }

    public void setMes (byte mes) throws Exception
    {
        if (!Data.isValida(this.dia,mes,this.ano))
            throw new Exception ("Mes invalido");

        this.mes=mes;
    }

    public byte getMes ()
    {
        return this.mes;
    }

    public void setAno (short ano) throws Exception
    {
        if (!Data.isValida(this.dia,this.mes,ano))
            throw new Exception ("Ano invalido");

        this.ano=ano;
    }

    public short getAno ()
    {
        return this.ano;
    }


    //IMPLEMENTAR MÉTODOS QUE ALTERAM O THIS, SE FOR PRECISO...
    //SE NÃO TIVER MÉTODOS QUE ALTEREM O THIS, REMOVER CLONE E CONSTRUTOR DE CÓPIA!


    @Override
    public String toString ()
    {
        return (this.dia<10?"0":"") +
                this.dia +
                "/" +
                (this.mes<10?"0":"") +
                this.mes +
                "/" +
                (this.ano<0?(-this.ano):this.ano);
    }

    // equals compara this e obj
    @Override
    public boolean equals (Object obj)
    {
        if (obj==this) return true;
        if (obj==null) return false;
        if (obj.getClass()!=this.getClass()) return false;

        //if ((Data)obj.dia!=this.dia) return false;
        //if ((Data)obj.mes!=this.mes) return false;
        //if ((Data)obj.ano!=this.ano) return false;

        Data d = (Data)obj;
        if (d.dia!=this.dia) return false;
        if (d.mes!=this.mes) return false;
        if (d.ano!=this.ano) return false;

        return true;
    }

    @Override
    public int hashCode ()
    {
        int ret=1; // um valor positivo qualquer

        ret = ret *  2+ Byte.valueOf(this.dia).hashCode();
        ret = ret * 2+ Byte.valueOf (this.mes).hashCode();
        ret = ret *  2+ Short.valueOf(this.ano).hashCode();

        if (ret<0) ret=-ret;
        return ret;
    }

    @Override
    public int compareTo (Data d)
    {
        if (this.ano>d.ano) return  1;
        if (this.ano<d.ano) return -1;

        if (this.mes>d.mes) return  1;
        if (this.mes<d.mes) return -1;

        if (this.dia>d.dia) return  1;
        if (this.dia<d.dia) return -1;

        return 0;
    }
    public Data (Data modelo) throws Exception
    {
        if (modelo==null) throw new Exception ("Modelo ausente");

        this.dia=modelo.dia;
        this.mes=modelo.mes;
        this.ano=modelo.ano;
    }

    @Override
    public Object clone ()
    {
        Data ret=null;
        try
        {
            ret=new Data (this);
        }
        catch (Exception erro)
        {} //Não vai dar erro
        return ret;
    }
}