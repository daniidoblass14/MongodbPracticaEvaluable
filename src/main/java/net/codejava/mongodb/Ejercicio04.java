package net.codejava.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio04 {

    private String nombre;
    public void obtenerIngredientes(MongoCollection col){

        Scanner sc = new Scanner(System.in);

        System.out.println("--LISTADO DE TODAS LAS RECETAS--");

        listadoRecetas(col);

        System.out.println("Introduzca el nombre de la receta de la cual quiere revisar sus ingredientes: ");
        nombre = sc.nextLine();

        //Preparacion de la consulta.
        BasicDBObject consulta = new BasicDBObject();
        consulta.put("nombre",nombre);

        //Ejecución de la consulta.
        FindIterable<Document> resultDocument = col.find(consulta);

        //Visualizacion de la consulta.
        MongoCursor<Document> cursor = resultDocument.cursor();

        while (cursor.hasNext()){
            Document miDocument = (Document) cursor.next();
            List<Document> listaIngredientes =  miDocument.getList("ingredientes",Document.class);

            System.out.println(miDocument.get("nombre"));
            System.out.println("/--INGREDIENTES--/");
            for(int i = 0;i< listaIngredientes.size();i++){

                System.out.println("Nombre: "+listaIngredientes.get(i).get("nombre")+"\n Cantidad: "+listaIngredientes.get(i).get("cantidad")+
                        "\n Unidad: "+listaIngredientes.get(i).get("unidades"));
                System.out.println("----------------------");
            }
            System.out.println("/--FIN INGREDIENTES--/");

        }
    }

    private void listadoRecetas (MongoCollection col){

        //Ejecución de la consulta.
        FindIterable<Document> resultDocument = col.find();

        //Visualizacion de la consulta.
        MongoCursor<Document> cursor = resultDocument.cursor();

        while (cursor.hasNext()){
            Document miDocument = (Document) cursor.next();
            System.out.println(miDocument.get("nombre"));
        }

    }
}
