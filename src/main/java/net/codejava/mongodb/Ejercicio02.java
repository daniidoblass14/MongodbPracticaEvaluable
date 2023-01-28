package net.codejava.mongodb;


import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Scanner;
/**
 * Clase donde encontramos el ejercicio numero de 2 de la PEVAL
 *
 * @version 1.0 (28/01/2023)
 * @author Daniel Jesús Doblas Florido.
 */

public class Ejercicio02 {
    /**
     * nombre -tipo String- para almacenar el nombre de la receta.
     */
    private String nombre;
    /**
     * condicion -tipo boolean-  para salir del bucle.
     */
    private boolean condicion = true;

    /**
     * respuesta -tipo String- para almacenar la repsuesta a la preguntar
     * si desea eliminar la receta.
     */
    private String respuesta;

    /**
     * Método para eliminar una receta introducida por teclado.
     * @param col -tipo MongoCollection - para saber con que coleccion trabajamos.
     */
    public void eliminar(MongoCollection col){
        Scanner sc = new Scanner(System.in);

        System.out.println("--LISTADO DEL NOMBRE DE TODAS LAS RECETAS--");
        System.out.print("\n");

        //Ejecución de la consulta.
        FindIterable<Document> resultDocument = col.find();

        //Visualizacion de la consulta.
        MongoCursor<Document> cursor = resultDocument.cursor();

        while (cursor.hasNext()){
            Document miDocument = (Document) cursor.next();
            System.out.println(miDocument.get("nombre"));
        }

        System.out.print("\n");
        while (condicion){

            System.out.println("Introduzca el nombre del plato que quiera eliminar: ");
            nombre = sc.nextLine();

            System.out.println("Estas seguro de que quieres borrar esta receta? [SI o NO]");
            respuesta = sc.nextLine();

            if (respuesta.equals("si") || respuesta.equals("SI")){

                condicion = Main.comprobarCadenaTexto(nombre,condicion);

            }

        }


        //Eliminacion de la receta.
        col.deleteOne(Filters.eq("nombre",nombre));

        System.out.print("\n");
        System.out.println("--RECETA ELIMINADA--");
        System.out.print("\n");
    }
}
