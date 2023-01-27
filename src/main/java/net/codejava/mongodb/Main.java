package net.codejava.mongodb;

import java.util.Scanner;
import com.mongodb.client.*;
import org.bson.Document;

public class Main {

    private static boolean condicion = true;
    private static Ejercicio01 ej1;
    private static Ejercicio02 ej2;
    private static Ejercicio03 ej3;
    private static Ejercicio04 ej4;
    private static Ejercicio05 ej5;
    private static Ejercicio06 ej6;
    private static Ejercicio07 ej7;
    private static Ejercicio08 ej8;


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        /**
         * Creamos la conexion con la base de datos.
         */
        MongoClient client = MongoClients.create(
                "mongodb+srv://daniidoblass:Daniel11@cluster0.zckdds6.mongodb.net/?retryWrites=true&w=majority");
        System.out.println("Cliente creado.");
        MongoDatabase db = client.getDatabase("practica04");//Conectar con la BBDD deseada.

        System.out.println("ACCEDE A BD " + db.getName());
        MongoCollection col = db.getCollection("recetas"); // Conectar con la coleccion deseada.

        while(condicion){

            System.out.println("1.Insertar una receta nueva");
            System.out.println("2.Eliminar una receta introduciendo su nombre por teclado");
            System.out.println("3.Modificar los datos del tiempo de elaboracion, introduciendolos por teclado");
            System.out.println("4.Visualizar todos los ingredientes de una receta introduciendo por teclado su nombre");
            System.out.println("5. Dar el nombre de todas las recetas que contengan huevos de ingredientes y tengan \n" +
                    "más de 500 calorías.");
            System.out.println("6.Dar el nombre de todas las recetas y el tiempo de elaboración, cuya elaboración sea \n" +
                    "inferior a 1 hora y que sean primer plato o segundo plato");
            System.out.println("7. Dar el nombre de las recetas y los pasos a seguir en su elaboración, que haya que \n" +
                    "dejarlas reposar y que sean platos únicos o primeros platos \n");
            System.out.println("SALIR");

            opcion = sc.nextInt();

            switch (opcion){

                case 1:
                    ej1 = new Ejercicio01();
                    ej1.insertar(col);
                    break;
                case 2:
                    ej2 = new Ejercicio02();
                    ej2.eliminar(col);

                    break;
                case 3:
                    ej3 = new Ejercicio03();
                    ej3.modificarTiempo(col);

                    break;
                case 4:
                    ej4 = new Ejercicio04();
                    ej4.obtenerIngredientes(col);

                    break;
                case 5:
                    ej5 = new Ejercicio05();
                    ej5.obtenerRecetesHuevosCalorias(col);

                    break;

                case 6:
                    ej6 = new Ejercicio06();
                    ej6.recetasYTiempoElaboracion(col);

                    break;

                case 7:
                    ej7 = new Ejercicio07();
                    ej7.recetasReposando(col);

                    break;

                case 8:
                    ej8 = new Ejercicio08();
                    ej8.recetasEnMenosDeCincoPaso(col);

                    break;

                case 0:
                    condicion = false;
                    break;
            }

        }
    }

    public static void listadoRecetas (MongoCollection col){

        //Ejecución de la consulta.
        FindIterable<Document> resultDocument = col.find();

        //Visualizacion de la consulta.
        MongoCursor<Document> cursor = resultDocument.cursor();

        while (cursor.hasNext()){
            Document miDocument = (Document) cursor.next();
            System.out.println(miDocument.get("nombre"));
        }

    }
}