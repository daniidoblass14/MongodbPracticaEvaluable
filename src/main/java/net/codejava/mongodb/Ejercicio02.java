package net.codejava.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Scanner;


public class Ejercicio02 {

    private String nombre;
    public void eliminar(MongoCollection col){
        Scanner sc = new Scanner(System.in);

        System.out.println("--LISTADO DEL NOMBRE DE TODAS LAS RECETAS--");

        //Ejecución de la consulta.
        FindIterable<Document> resultDocument = col.find();

        //Visualizacion de la consulta.
        MongoCursor<Document> cursor = resultDocument.cursor();

        while (cursor.hasNext()){
            Document miDocument = (Document) cursor.next();
            System.out.println(miDocument.get("nombre"));
        }

        System.out.println("Introduzca el nombre del plato que quiera eliminar: ");
        nombre = sc.nextLine();

        //Eliminacion de la receta.
        col.deleteOne(Filters.eq("nombre",nombre));

        System.out.println("--RECETA ELIMINADA--");
    }
}
