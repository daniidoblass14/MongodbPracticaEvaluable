package net.codejava.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Ejercicio05 {

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

        while (cursor.hasNext()){
            Document miDocument = (Document) cursor.next();
            List<Document> listaIngredientes =  miDocument.getList("ingredientes",Document.class);

            System.out.println(miDocument.get("nombre")+" Calorias: "+miDocument.get("calorias"));
            System.out.println("/--INGREDIENTES--/");

            for(int i = 0;i< listaIngredientes.size();i++){

                System.out.println("Nombre: "+listaIngredientes.get(i).get("nombre")+"\n Cantidad: "+listaIngredientes.get(i).get("cantidad")+
                        "\n Unidad: "+listaIngredientes.get(i).get("unidades"));
                System.out.println("----------------------");
            }
            System.out.println("/--FIN INGREDIENTES--/");
            System.out.println("\n");
        }

    }
}
