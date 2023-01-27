package net.codejava.mongodb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import javax.print.Doc;
import java.util.Scanner;

public class Ejercicio03 {

    private String nombre;
    private int valor;
    private String unidad;
    public void modificarTiempo(MongoCollection col){

        Scanner sc = new Scanner(System.in);
        Scanner scInt = new Scanner(System.in);

        System.out.println("--LISTADO DE TODAS LAS RECETAS--");

        //Ejecución de la consulta.
        FindIterable<Document> resultDocument = col.find();

        //Visualizacion de la consulta.
        MongoCursor<Document> cursor = resultDocument.cursor();

        while (cursor.hasNext()){
            Document miDocument = (Document) cursor.next();
            Document documentoTiempo = (Document) miDocument.get("tiempo");
            System.out.println(miDocument.get("nombre")+" "+documentoTiempo.get("valor")+" "+documentoTiempo.get("unidad"));
        }

        System.out.println("Indique la receta a la que quiera modificarle el tiempo: ");
        nombre = sc.nextLine();

        System.out.println("Indique el valor del tiempo: ");
        valor = scInt.nextInt();

        System.out.println("Indique la unidad de tiempo: ");
        unidad = sc.nextLine();

        col.updateOne(Filters.eq("nombre",nombre), Updates.set("valor",valor));
        col.updateOne(Filters.eq("nombre",nombre), Updates.set("unidad",unidad));
    }
}
