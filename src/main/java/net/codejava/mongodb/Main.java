package net.codejava.mongodb;

import java.util.Scanner;
import com.mongodb.client.*;
import org.bson.Document;

/**
 * Clase main donde encontramos las distintas opciones del programa
 *
 * @version 1.0 (28/01/2023)
 * @author Daniel Jesús Doblas Florido.
 */
public class Main {
    private static boolean condicion = true; // Variable para poder salir de Menu.
    /**
     * Creo un objeto de la clase Ejercicio01 para poder llamar a los métodos que contiene esa clase,
     * que correponde con los distintos ejercicios de la PEVAL.
     */
    private static Ejercicio01 ej1;
    /**
     * Creo un objeto de la clase Ejercicio02 para poder llamar a los métodos que contiene esa clase,
     * que correponde con los distintos ejercicios de la PEVAL.
     */
    private static Ejercicio02 ej2;
    /**
     * Creo un objeto de la clase Ejercicio03 para poder llamar a los métodos que contiene esa clase,
     * que correponde con los distintos ejercicios de la PEVAL.
     */
    private static Ejercicio03 ej3;
    /**
     * Creo un objeto de la clase Ejercicio04 para poder llamar a los métodos que contiene esa clase,
     * que correponde con los distintos ejercicios de la PEVAL.
     */
    private static Ejercicio04 ej4;
    /**
     * Creo un objeto de la clase Ejercicio05 para poder llamar a los métodos que contiene esa clase,
     * que correponde con los distintos ejercicios de la PEVAL.
     */
    private static Ejercicio05 ej5;
    /**
     * Creo un objeto de la clase Ejercicio06 para poder llamar a los métodos que contiene esa clase,
     * que correponde con los distintos ejercicios de la PEVAL.
     */
    private static Ejercicio06 ej6;
    /**
     * Creo un objeto de la clase Ejercicio07 para poder llamar a los métodos que contiene esa clase,
     * que correponde con los distintos ejercicios de la PEVAL.
     */
    private static Ejercicio07 ej7;
    /**
     * Creo un objeto de la clase Ejercicio08 para poder llamar a los métodos que contiene esa clase,
     * que correponde con los distintos ejercicios de la PEVAL.
     */
    private static Ejercicio08 ej8;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); //Creo un objeto Scanner que me servira para introducir la opcion del menu que desea ejecutar.

        int opcion; // Variable tipo entero para compararla en switch.

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
            System.out.println("/----------------------------------------MENU----------------------------------------/");
            System.out.print("\n");
            System.out.println("1.  Insertar una receta nueva");
            System.out.print("\n");
            System.out.println("2.  Eliminar una receta introduciendo su nombre por teclado");
            System.out.print("\n");
            System.out.println("3.  Modificar los datos del tiempo de elaboracion, introduciendolos por teclado");
            System.out.print("\n");
            System.out.println("4.  Visualizar todos los ingredientes de una receta introduciendo por teclado su nombre");
            System.out.print("\n");
            System.out.println("5.  Dar el nombre de todas las recetas que contengan huevos de ingredientes y tengan \n" +
                    "más de 500 calorías.");
            System.out.print("\n");
            System.out.println("6.  Dar el nombre de todas las recetas y el tiempo de elaboración, cuya elaboración sea \n" +
                    "inferior a 1 hora y que sean primer plato o segundo plato");
            System.out.print("\n");
            System.out.println("7.  Dar el nombre de las recetas y los pasos a seguir en su elaboración, que haya que \n" +
                    "dejarlas reposar y que sean platos únicos o primeros platos.");
            System.out.print("\n");
            System.out.println("8.  Visualizar todos los datos de las recetas que se realicen en 5 o menos pasos que sean\n" +
                    "de una dificultad alta (difícil)");
            System.out.print("\n");
            System.out.println("0.  SALIR");
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

                default:
                    System.out.println("Por favor introduzca un numero entre el 1 y el 8. [0 para salir]");
            }

        }


    }

    /**
     * Método para listar todas las recetas por su nombre.
     * @param col - tipo MongoCollection - contiene la coleccion de la BBDD.
     */
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

    /**
     * Método public utilizado para comprobar que las cadenas introducidas por el usuario
     * sean texto y no haya numeros.
     * @param cadena -tipo String- utilizado para recoger la cadena de texto.
     * @param condicion -tipo boolean- utilizado para la condicion del bucle.
     * @return la condicion pasada anteriormente para poder salir del bucle
     * o mantenerse en él.
     */
    public static boolean comprobarCadenaTexto (String cadena, boolean condicion){

        try {

            Integer.parseInt(cadena);
            System.out.println("/--LA CADENA INTRODUCIDA ES UN NUMERO--/");
            System.out.print("\n");

        }catch (NumberFormatException e){

            condicion = false;
        }

        return condicion;
    }

}