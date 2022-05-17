package modulos;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Descuentos {

    public static HashMap<String, Integer> descuentos = new HashMap<>();

    /**
     * Carga en un HashMap los descuentos actuales, desde un fichero TXT
     * @return Éxito o no en función de si la información se ha cargado correctamente
     * Autor: Fer
     */
    private static boolean cargarDescuentosHashMap() {

        try {
            // Lee de archivo llamado "descuentos.txt" en la raíz del proyecto
            FileReader entrada = new FileReader("./descuentos.txt");

            //Creación BufferedReader
            BufferedReader bfr = new BufferedReader(entrada);

            String linea;
            while ((linea = bfr.readLine()) != null) {

                // Hago split por "," y almaceno tanto código como descuento en un String[]
                String[] codigoYDescuento = linea.split(",");

                //Lo cargo en un HashMap, parseo valor descuento a Integer para poder operar con él más tarde
                descuentos.put(codigoYDescuento[0], Integer.valueOf(codigoYDescuento[1]));
            }

            bfr.close();
            entrada.close();
            return true;

        } catch (FileNotFoundException fnf) {
            System.out.println("Archivo no encontrado");
        } catch (IOException ioe) {
            System.out.println("InputOutput Exception");
        }
        return false;
    }

    /**
     * Actualiza los descuentos del archivo descuentos.txt
     * @return Éxito o no de la operación
     * Autor: Fer
     */
    private static boolean cargarDescuentosTXT() {

        try {
            // Asigno el archivo que será actualizado
            FileWriter salida = new FileWriter("./descuentos.txt");

            // Objeto BufferedWriter para escribir en dicho fichero asignado como salida
            BufferedWriter bfw = new BufferedWriter(salida);

            for (Map.Entry<String, Integer> elemento : descuentos.entrySet()) {
                // Escribo en el fichero código de descuento "," y valor del descuento en formato CSV("CÓDIGO,VALOR")
                bfw.write(elemento.getKey() + "," + elemento.getValue().toString() + "\n");
            }

            bfw.close();
            salida.close();
            return true;

        } catch (IOException ioe) {
            System.out.println("InputOutput exception");
        }
        return false;
    }

    public static void mostrarDescuentosActuales () {
        descuentos.clear();
        Descuentos.cargarDescuentosHashMap();
        System.out.println("\u001B[31m Los descuentos actuales son:");

        for (Map.Entry<String, Integer> descuento : descuentos.entrySet()) {
            System.out.println("\u001B[34m Código: " + "\u001B[35m" + descuento.getKey() + " => " + "\u001B[33m" +descuento.getValue() + "% \u001B[0m"); // Reset color por defecto
        }
    }

    /**
     * Método que busca la cantidad de descuento (Integer) correspondiente a un código pasado por parámetro
     * @param codDescuento Código de descuento en formato String
     * @return Cantidad de descuento a aplicar o -1 en caso de no existir descuento
     * Autor: Fer
     */
    public static int porcentajeDescuento(String codDescuento) {
        codDescuento = codDescuento.toUpperCase();
        descuentos.clear();
        Descuentos.cargarDescuentosHashMap();
        // Busco en el mapa
        Integer porcentajeDescuento =  descuentos.get(codDescuento);
        // Si no encuentra match ...
        if (porcentajeDescuento != null) {
            return porcentajeDescuento;
        } else {
            return -1;
        }
    }

    /**
     * Método que checkea si existe un descuento en descuentos.txt
     * @param codDescuento
     * @return
     */
    public static boolean existeDescuento(String codDescuento) {
        codDescuento = codDescuento.toUpperCase();
        descuentos.clear();
        Descuentos.cargarDescuentosHashMap();

        if (descuentos.get(codDescuento) == null) {
            return false;
        }
        return true;

    }

    /**
     * Función que permite al usuario administrador añadir códigos de descuento, y actualizar descuentos.txt
     * @param codDescuento
     * @param porcentajeDescuento
     * Autor: Fer
     */
    public static void Admin_addDescuento(String codDescuento, Integer porcentajeDescuento) {
        codDescuento = codDescuento.toUpperCase();
        descuentos.clear();
        Descuentos.cargarDescuentosHashMap();
        descuentos.put(codDescuento, porcentajeDescuento);
        Descuentos.cargarDescuentosTXT();
    }

    /**
     * Función que permite al usuario administrador borrar códigos de descuento del archivo descuentos.txt
     * @param codDescuento
     * Autor: Fer
     */
    public static boolean Admin_deleteDescuento (String codDescuento) {
        codDescuento = codDescuento.toUpperCase();
        descuentos.clear();
        Descuentos.cargarDescuentosHashMap();

        boolean encontrado = descuentos.containsKey(codDescuento);

        if (encontrado) {
            descuentos.remove(codDescuento);
            Descuentos.cargarDescuentosTXT();
            return true;
        } else {
            return false;
        }
    }
}
