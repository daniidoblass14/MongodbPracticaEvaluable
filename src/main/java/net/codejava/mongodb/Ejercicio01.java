package net.codejava.mongodb;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase donde encontramos el ejercicio numero de 1 de la PEVAL
 *
 * @version 1.0 (28/01/2023)
 * @author Daniel Jesús Doblas Florido.
 */
public class Ejercicio01 {
    /**
     * tipo - tipo ArrayList<String> - para almacenar los tipos de que puede ser la nueva receta
     */
    private ArrayList<String> tipo = new ArrayList<>();
    /**
     * ingredientes - tipo ArrayList<Document> - para almacenar los ingredientes que puede tener la nueva receta
     */
    private ArrayList<Document> ingredientes = new ArrayList<>();
    /**
     * pasos - tipo ArrayList<Document> - para almacenar los pasos que puede tener la nueva receta
     */
    private ArrayList<Document> pasos = new ArrayList<>();
    /**
     * tipo - tipo boolean - para salir del bucle cuando no quiera introducirle mas tipos a la receta.
     */
    private static boolean condicion = true;
    /**
     * respuesta - tipo String - para almacenar la respuesta del usuario.
     */
    private String respuesta;
    /**
     * cantidad - tipo int - para almacenar la cantidad tanto de pasos, como de ingredientes.
     */
    private int cantidad;
    /**
     * dificultad - tipo String - para almacenar la dificultad que puede tener la nueva receta
     */
    private String dificultad;
    /**
     * nombre - tipo String - para almacenar el nombre que puede tener la nueva receta
     */
    private String nombre;
    /**
     * electrodomestico - tipo String - para almacenar el electrodomestico que puede tener la nueva receta
     */
    private String electrodomestico;
    /**
     * valorTiempo - tipo int - para almacenar el valor del tiempo que pueda tener la nueva receta
     */
    private int valorTiempo;
    /**
     * unidadTiempo - tipo String - para almacenar la unidad de tiempo que puede tener la nueva receta
     */
    private String unidadTiempo;
    /**
     * calorias - tipo int - para almacenar las calorias que puede tener la nueva receta
     */
    private int calorias;

    /**
     * nombreTipo - tipo String - para almacenar el nombre del tipo que puede ser la nueva receta
     */
    private String nombreTipo;

    /**
     * nombreIngrediente - tipo String - para almacenar el nombre de Ingrediente que puede ser la nueva receta
     */
    private String nombreIngrediente;

    /**
     * nombreUnidad - tipo String - para almacenar el nombre de la unidad que puede ser la nueva receta
     */
    private String nombreUnidad;

    /**
     * Método para insertar la nueva receta
     * @param col - tipo MongoCollection - nos proporciona la coleccion sobre la que estamos trabajando.
     */

    public void insertar(MongoCollection col){

        Scanner sc = new Scanner(System.in); // Scanner utilizado para los STRING'S.

        Scanner scInt = new Scanner(System.in); // Scanner utilizado para los INT'S.

        try {

            while (condicion){

                System.out.println("Introduzca el tipo de su receta: ");
                nombreTipo = sc.nextLine();

                condicion = Main.comprobarCadenaTexto(nombreTipo,condicion);

            }

            condicion = true;
            /**
             * Bucle que utilizamos para saber si el usuario quiere introducirle más de un tipo
             * a la receta mediante una pregunta, y según su respuesta de SI o NO le podrás introducir
             * más o no.
             */
            while(condicion){

                System.out.println("Quiere añadirle otro tipo a su receta? [SI o NO]");
                respuesta = sc.nextLine();
                respuesta.toLowerCase();

                if(respuesta.equals("si") || respuesta.equals("SI") ){

                    System.out.println("Indiquelo: ");
                    tipo.add(sc.nextLine());

                }else if(respuesta.equals("no") || respuesta.equals("NO")){

                    condicion = false;
                }else {
                    System.out.println("Esa respuesta no es válida.");
                }
            }

            condicion = true;

            while (condicion){

                System.out.println("La dificultad de su receta puede ser ['Facil','Media','Dificil'], Indique cual es la suya: ");
                dificultad = sc.nextLine();

                condicion = Main.comprobarCadenaTexto(dificultad,condicion);
            }

            condicion = true;

            while (condicion){

                System.out.println("Introduzca el nombre de la receta");
                nombre = sc.nextLine();

                condicion = Main.comprobarCadenaTexto(nombre,condicion);
            }

            condicion = true;

            System.out.println("Introduzca la cantidad de ingredientes necesita: ");
            cantidad = scInt.nextInt();

            /**
             * Bucle que se repetira tanta veces como hayas introducido anteriormente.
             * En el cual cada vez que demos una vuelta crearemos un nuevo documento de ingredientes,
             * (Esto debbido a que en la BBDD los ingredientes son un OBJ JSON ) una vez introducidos
             * sus parámetros añadimos este documento al arrayList de tipo document de ingredientes (Por qué,
             * en la BBDD aparace como un array de objetos.
             */
            for (int i = 0; i<cantidad;i++){

                Document newIngredientes = new Document();

                System.out.println("Introduzca el nombre del ingrdiente "+i);
                newIngredientes.append("nombre",sc.nextLine());

                System.out.println("Introduza la cantidad del mismo: ");
                newIngredientes.append("cantidad",scInt.nextInt());

                System.out.println("Introduzca la unidaddes de medidas: ");
                newIngredientes.append("unidad",sc.nextLine());


                ingredientes.add(newIngredientes);
            }

            System.out.println("Introduzca la cantidad de calorias que tiene su receta: ");
            calorias = scInt.nextInt();

            System.out.println("Introduzca la cantidad de paso que hay que realizar: ");
            cantidad = scInt.nextInt();

            /**
             * Mismo caso que con los ingredientes.
             */
            for (int i = 0; i<cantidad;i++){

                Document newPaso = new Document();
                System.out.println("Paso numero "+i);
                newPaso.append("orden",i);

                System.out.println("Como se elabora? ");
                newPaso.append("elaboracion",sc.nextLine());

                pasos.add(newPaso);

            }

            System.out.println("Introduzca el valor del tiempo: ");
            valorTiempo =scInt.nextInt();

            while (condicion){

                System.out.println("Introduzca la unidad de tiempo: ");
                unidadTiempo = sc.nextLine();

                condicion = Main.comprobarCadenaTexto(unidadTiempo,condicion);

            }
            condicion = true;

            while (condicion){

                System.out.println("Introduzca el electrodomestico: ");
                electrodomestico = sc.nextLine();

                condicion = Main.comprobarCadenaTexto(electrodomestico,condicion);
            }

            Document miDocumento = new Document();
            Document documentoTiempo = new Document();
            documentoTiempo.append("valor",valorTiempo);
            documentoTiempo.append("unidad",unidadTiempo);

            miDocumento.append("tipo",tipo);
            miDocumento.append("dificultad",dificultad);
            miDocumento.append("nombre",nombre);
            miDocumento.append("ingredientes", ingredientes);
            miDocumento.append("calorias",calorias);
            miDocumento.append("pasos",pasos);
            miDocumento.append("tiempo",documentoTiempo);
            miDocumento.append("electrodomestico",electrodomestico);

            col.insertOne(miDocumento);
            System.out.println("--INSERTADO--");

        }catch (Exception e){

            System.out.println("\n");

            System.out.println("/--NO SE HA PODIDO INTRODUICIR--/");

            System.out.println("\n");
        }



    }
}
