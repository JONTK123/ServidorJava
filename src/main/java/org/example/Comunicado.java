package org.example;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

import java.io.*;

//Objetos da classe Comuinicacao são utilizados para comunicação entre o servidor e o cliente, herdar de Serializable
@BsonDiscriminator
public class Comunicado implements Serializable {

}
