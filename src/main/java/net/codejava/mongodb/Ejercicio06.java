package net.codejava.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase donde encontramos el ejercicio numero de 4.C de la PEVAL
 *
 * @version 1.0 (28/01/2023)
 * @author Daniel Jesús Doblas Florido.
 */
public class Ejercicio06 {

    /**
     * Metodo para obtener las recetas cuya elaboracion sea inferior a 1 hora y que
     * sea primer plato o segundo plato.
     * @param col -tipo MongoCollection- para saber sobre que coleccion trabajamos.
     */
    public void recetasYTiempoElaboracion(MongoCollection col) {

        //Preparacion de la consulta.
        BasicDBObject consulta = new BasicDBObject();

        //Preparacion del primer parametro.
        List<BasicDBObject> obj = new ArrayList<>();
        obj.add(new BasicDBObject("tiempo.valor",new Document("$lt",60)));
        obj.add(new BasicDBObject("tiempo.unidad","minutos"));

        //Preparacion segundo parametro.
        List<String> list = new ArrayList<>();
        list.add("primer plato");
        list.add(" platos únicos");
        consulta.put("tipo", new BasicDBObject("$in", list));

        consulta.put("$and", obj);

        //Ejecución de la consulta.
        FindIterable<Document> resultDocument = col.find(consulta);

        //Visualizacion de la consulta.
        MongoCursor<Document> cursor = resultDocument.cursor();

        //Bucle para obtener todos los datos solicitados por la PEVAL.
        System.out.print("\n");
        System.out.println("------------------");
        while (cursor.hasNext()) {
            Document miDocument = (Document) cursor.next();
            Document documentTiempo = (Document) miDocument.get("tiempo");

            System.out.println(miDocument.get("nombre") + " Tiempo: " +documentTiempo.get("valor")+" "+documentTiempo.get("unidad"));
        }
        System.out.println("------------------");
        System.out.print("\n");
    }
}
