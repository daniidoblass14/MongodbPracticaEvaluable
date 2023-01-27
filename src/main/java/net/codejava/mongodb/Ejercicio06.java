package net.codejava.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Ejercicio06 {

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

        while (cursor.hasNext()) {
            Document miDocument = (Document) cursor.next();
            Document documentTiempo = (Document) miDocument.get("tiempo");

            System.out.println(miDocument.get("nombre") + " Tiempo: " +documentTiempo.get("valor")+" "+documentTiempo.get("unidad"));
        }
    }
}
