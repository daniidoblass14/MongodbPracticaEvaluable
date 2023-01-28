package net.codejava.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Clase donde encontramos el ejercicio numero de 4.E de la PEVAL
 *
 * @version 1.0 (28/01/2023)
 * @author Daniel Jesús Doblas Florido.
 */
public class Ejercicio08 {
    /**
     * Metodo para visualizar las recetas cuyos pasos sean menor que 5 y su dificultad sea 'dificil'
     * @param col
     */
    public void recetasEnMenosDeCincoPaso(MongoCollection col) {

        //Preparacion de la consulta.
        BasicDBObject consulta = new BasicDBObject();

        //Preparacion del primer parametro.
        List<BasicDBObject> obj = new ArrayList<>();
        obj.add(new BasicDBObject("$expr",new Document("$lte", Arrays.asList(new Document("$size","$pasos"),5))));
        obj.add(new BasicDBObject("dificultad","Difícil"));
        consulta.put("$and",obj);

        //Ejecución de la consulta.
        FindIterable<Document> resultDocument = col.find(consulta);

        //Visualizacion de la consulta.
        MongoCursor<Document> cursor = resultDocument.cursor();

        //Bucle para visualizar los datos solicitados por la PEVAL
        System.out.print("\n");
        System.out.println("------------------");
        while (cursor.hasNext()){

            Document miDocument = (Document) cursor.next();
            List<Document> pasos = miDocument.getList("pasos",Document.class);

            System.out.println("--------REGISTRO NUEVO--------");
            System.out.print("\n");
            System.out.println("Nombre: "+miDocument.get("nombre").toString().toUpperCase());
            System.out.print("\n");

            System.out.println("/--PASOS--/");
            System.out.print("\n");
            for(int i = 0;i< pasos.size();i++) {

                System.out.println("Orden: "+pasos.get(i).get("orden"));
                System.out.println("Elaboracion: "+pasos.get(i).get("elaboracion"));
                System.out.print("\n");

            }
            System.out.println("/--FIN PASOS--/");
            System.out.print("\n");
        }
        System.out.println("------------------");
        System.out.print("\n");
    }
}
