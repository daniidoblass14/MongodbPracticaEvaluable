package net.codejava.mongodb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import javax.print.Doc;
import java.util.Scanner;
/**
 * Clase donde encontramos el ejercicio numero de 3 de la PEVAL
 *
 * @version 1.0 (28/01/2023)
 * @author Daniel Jesús Doblas Florido.
 */
public class Ejercicio03 {
    /**
     * nombre - tipo String- para indicar el nombre de la recata.
     */
    private String nombre;
    /**
     * valor -tipo int- para indicar el valor del tiempo.
     */
    private int valor;
    /**
     * unidad -tipo String- para indicar la unidad del tiempo.
     */
    private String unidad;
    /**
     * condicion -tipo boolean-  para salir del bucle.
     */
    private boolean condicion = true;

    /**
     * Métedo para modificar el tiempo de una receta introduciendo su nombre.
     * @param col -tipo MongoCollection- para saber sobre que coleccion trabajamos.
     */
    public void modificarTiempo(MongoCollection col){

        Scanner sc = new Scanner(System.in);
        Scanner scInt = new Scanner(System.in);

        System.out.println("--LISTADO DE TODAS LAS RECETAS--");
        System.out.print("\n");

        //Ejecución de la consulta.
        FindIterable<Document> resultDocument = col.find();

        //Visualizacion de la consulta.
        MongoCursor<Document> cursor = resultDocument.cursor();

        /**
         * Búcle while que nos sirve para imprimir los datos que deseamos, en este como queremos imprimir
         * aparte del nombre, nos hace falta los datos del Tiempo, que es un objeto JSON, por ello aparte
         * de crear el Document 'general', tendriamos que crear otro a partir de este para poder acceder
         * a los datos del mismo.
         */
        while (cursor.hasNext()){
            Document miDocument = (Document) cursor.next();
            Document documentoTiempo = (Document) miDocument.get("tiempo");
            System.out.println(miDocument.get("nombre")+" "+documentoTiempo.get("valor")+" "+documentoTiempo.get("unidad"));
        }

        System.out.print("\n");
        while (condicion){

            System.out.println("Indique la receta a la que quiera modificarle el tiempo: ");
            nombre = sc.nextLine();

            condicion = Main.comprobarCadenaTexto(nombre,condicion);

        }

        System.out.print("\n");
        System.out.println("Indique el valor del tiempo: ");
        valor = scInt.nextInt();

        condicion = true;

        System.out.print("\n");
        while (condicion){

            System.out.println("Indique la unidad de tiempo: ");
            unidad = sc.nextLine();

            condicion = Main.comprobarCadenaTexto(unidad,condicion);
        }

        /**
         * Aqui es donde actualizamos y en el caso de que no exitan los campos lo añade
         * como un camplo simple nuevo
         */
        col.updateOne(Filters.eq("nombre",nombre), Updates.set("tiempo.valor",valor));
        col.updateOne(Filters.eq("nombre",nombre), Updates.set("tiempo.unidad",unidad));
    }
}
