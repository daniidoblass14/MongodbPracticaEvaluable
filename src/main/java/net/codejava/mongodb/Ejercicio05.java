package net.codejava.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase donde encontramos el ejercicio numero de 4.B de la PEVAL
 *
 * @version 1.0 (28/01/2023)
 * @author Daniel Jesús Doblas Florido.
 */

public class Ejercicio05 {

    /**
     * Metodo para obtener el nombre y el tiempo de elaboracion de las recetas,
     * que en los ingrdeintes contengan huevos y tengan mas de 500 calorias.
     * @param col -tipo MongoCollection- para saber sobre que coleccion trabajamos.
     */
    public void obtenerRecetesHuevosCalorias(MongoCollection col){

        //Preparacion de la consulta.
        BasicDBObject consulta = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<>();
        obj.add(new BasicDBObject("ingredientes",new Document("$elemMatch",new Document("nombre","huevos"))));
        obj.add(new BasicDBObject("calorias",new BasicDBObject("$gt",500)));

        consulta.put("$and",obj);

        //Ejecución de la consulta.
        FindIterable<Document> resultDocument = col.find(consulta);

        //Visualizacion de la consulta.
        MongoCursor<Document> cursor = resultDocument.cursor();

        //Bucle para imprimir los datos solicitados por la PEVAL.
        System.out.print("\n");
        System.out.println("------------------");
        while (cursor.hasNext()){
            Document miDocument = (Document) cursor.next();
            List<Document> listaIngredientes =  miDocument.getList("ingredientes",Document.class);

            System.out.println(miDocument.get("nombre")+" Calorias: "+miDocument.get("calorias"));
        }
        System.out.println("------------------");
        System.out.print("\n");
    }
}
