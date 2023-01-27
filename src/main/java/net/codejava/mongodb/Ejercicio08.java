package net.codejava.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ejercicio08 {
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

        while (cursor.hasNext()){

            Document miDocument = (Document) cursor.next();
            List<Document> pasos = miDocument.getList("pasos",Document.class);

            System.out.println("Nombre: "+miDocument.get("nombre").toString().toUpperCase());

            System.out.println("/--PASOS--/");
            for(int i = 0;i< pasos.size();i++) {

                System.out.println("Orden: "+pasos.get(i).get("orden"));
                System.out.println("Elaboracion: "+pasos.get(i).get("elaboracion"));

            }
            System.out.println("/--FIN PASOS--/");
        }
    }
}
