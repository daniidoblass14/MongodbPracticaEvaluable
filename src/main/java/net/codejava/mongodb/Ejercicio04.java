package net.codejava.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Clase donde encontramos el ejercicio numero de 4.A de la PEVAL
 *
 * @version 1.0 (28/01/2023)
 * @author Daniel Jesús Doblas Florido.
 */
public class Ejercicio04 {
    /**
     * nombre -tipo String- utilizado para acceder a la receta la cual queremos ver sus ingredientes.
     */
    private String nombre;
    /**
     * condicion -tipo boolean-  para salir del bucle.
     */
    private boolean condicion = true;

    /**
     * Método para obtener los ingredientes de una receta introducida por teclado.
     * @param col -tipo MongoCollection- para saber sobre que coleccion trabajamos.
     */
    public void obtenerIngredientes(MongoCollection col){

        Scanner sc = new Scanner(System.in);

        System.out.println("--LISTADO DE TODAS LAS RECETAS--");
        System.out.print("\n");

        Main.listadoRecetas(col);

        System.out.print("\n");
        while (condicion){

            System.out.println("Introduzca el nombre de la receta de la cual quiere revisar sus ingredientes: ");
            nombre = sc.nextLine();

            condicion = Main.comprobarCadenaTexto(nombre,condicion);
        }


        //Preparacion de la consulta.
        BasicDBObject consulta = new BasicDBObject();
        consulta.put("nombre",nombre);

        //Ejecución de la consulta.
        FindIterable<Document> resultDocument = col.find(consulta);

        //Visualizacion de la consulta.
        MongoCursor<Document> cursor = resultDocument.cursor();

        //Bucle para imprimir los datos pedido en el ejercicio.
        if(!cursor.hasNext()){

            System.out.print("\n");
            System.out.println("/--No hay registro para este nombre o el nombre introducido es incorrecto.--/");
            System.out.print("\n");

        }else {

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

    }
}
