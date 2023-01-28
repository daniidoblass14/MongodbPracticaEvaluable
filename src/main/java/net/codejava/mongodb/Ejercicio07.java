package net.codejava.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase donde encontramos el ejercicio numero de 4.D de la PEVAL
 *
 * @version 1.0 (28/01/2023)
 * @author Daniel Jesús Doblas Florido.
 */
public class Ejercicio07 {

    /**
     * Metodo para obtener las recetas que hayan que dejarlas y que
     * sean platos unicos o primer plato.
     * @param col -tipo MongoCollection- para saber sobre que coleccion trabajamos.
     */
    public void recetasReposando(MongoCollection col){

        try {
            //Preparacion de la consulta.
            BasicDBObject consulta = new BasicDBObject();

            //Preparacion del primer parametro.
            List<BasicDBObject> obj = new ArrayList<>();
            obj.add(new BasicDBObject("pasos",new Document("$elemMatch",new Document("elaboracion", new BasicDBObject("$regex","Reposar").append("$options","i")))));

            //Preparacion del segundo parametro.
            List<String> list = new ArrayList<>();
            list.add("primer plato");
            list.add("segundo plato");
            consulta.put("tipo",new BasicDBObject("$in",list));

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
                List<Document> pasos = miDocument.getList("pasos",Document.class);

                System.out.print("\n");
                System.out.println("Nombre: "+miDocument.get("nombre").toString().toUpperCase());
                System.out.print("\n");

                System.out.println("/--PASOS--/");
                System.out.print("\n");
                for(int i = 0;i< pasos.size();i++) {

                    System.out.println("Orden: "+pasos.get(i).get("orden"));
                    System.out.println("Elaboracion: "+pasos.get(i).get("elaboracion"));

                }
                System.out.print("\n");
                System.out.println("/--FIN PASOS--/");
                System.out.print("\n");
                System.out.println("------------------");
                System.out.print("\n");
            }

        }catch (Exception e){

            e.printStackTrace();
        }

    }
}
