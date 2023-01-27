package net.codejava.mongodb;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio01 {

    private ArrayList<String> tipo = new ArrayList<>();
    private ArrayList<Document> ingredientes = new ArrayList<>();
    private ArrayList<Document> pasos = new ArrayList<>();
    private static boolean condicion = true;
    private String respuesta;
    private int cantidad;
    private String dificultad;
    private String nombre;
    private String electrodomestico;

    private int valorTiempo;
    private String unidadTiempo;

    private int calorias;

    public void insertar(MongoCollection col){

        Scanner sc = new Scanner(System.in);
        Scanner scInt = new Scanner(System.in);

        System.out.println("Introduzca el tipo de su receta: ");
        tipo.add(sc.nextLine());

        while(condicion){

            System.out.println("Quiere añadirle otro tipo a su receta?");
            respuesta = sc.nextLine();
            respuesta.toLowerCase();

            if(respuesta.equals("si")){

                System.out.println("Indiquelo: ");
                tipo.add(sc.nextLine());

            }else if(respuesta.equals("no")){

                condicion = false;
            }
        }
        System.out.println("La dificultad de su receta puede ser ['Facil','Media','Dificil'], Indique cual es la suya: ");
        dificultad = sc.nextLine();

        System.out.println("Introduzca el nombre de la receta");
        nombre = sc.nextLine();

        System.out.println("Introduzca la cantidad de ingredientes necesita: ");
        cantidad = scInt.nextInt();

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

        System.out.println("Introduzca la unidad de tiempo: ");
        unidadTiempo = sc.nextLine();

        System.out.println("Introduzca el electrodomestico: ");
        electrodomestico = sc.nextLine();


        Document miDocumento = new Document();
        Document documentoIngredientes = new Document();
        ArrayList<Document> objetosIngredientes = new ArrayList<>();
        Document documentoPasos = new Document();
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

    }
}
