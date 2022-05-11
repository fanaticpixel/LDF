/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldf;

import bbdd.BD_LDF;
import modulos.Usuario;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 *
 * @author Alvaro.p
 */
public class LDF {
    public static HashMap <String, Integer> descuentos = new HashMap<>();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // TODO code application logic here
        int opc1;
        String nombre1, apellidos1, nick1, correo1, pass1, resp1;
        String nick2, pass2, passRet2;
        Vector<String> v1;
        Boolean flag1, flag2;

        BD_LDF bd = new BD_LDF("LDF");

        System.err.println("Bienvenido a LDF");

        do {

            System.out.println("\nIntroduzca opción:    ");
            System.out.println("\n#1. Registrarse\n#2. Iniciar Sesión\n#3. Comprar entradas\n#4. Ver cartelera\n#5. Salir");
            opc1 = sc.nextInt();

            switch (opc1) {

                case 1:

                    sc.nextLine();

                    do {
                        limpiar();

                        System.out.println("Vas a crear una cuenta, si desea parar en cualquier momento introduzca '0'");

                        do {
                            System.out.println("Introduzca su nombre");
                            nombre1 = sc.nextLine();
                        } while (validarDatos(nombre1) == false);//bucle de validacion del nombre
                        do {
                            System.out.println("Introduce tus apellidos");
                            apellidos1 = sc.nextLine();
                        } while (validarDatos(apellidos1) == false);//bucle de validacion del apellido
                        do {
                            System.out.println("Introduce tu nick");
                            nick1 = sc.nextLine();

                            if (nick1.equalsIgnoreCase("0")) {
                                break;
                            }

                            v1 = bd.listarCampoTablaString("USUARIOS", "nick");

                            flag1 = false;

                            for (int i = 0; i < v1.size(); i++) {
                                if (nick1.equalsIgnoreCase(v1.get(i)) == true) {
                                    flag1 = true;
                                    System.out.println("El nick introducido ( " + nick1 + " ) ya pertenece a un usuario registrado");
                                }
                            }

                        } while (flag1 == true);

                        if (nick1.equalsIgnoreCase("0")) {
                            break;
                        }
                        do {
                            System.out.println("Introduce tu correo");
                            correo1 = sc.nextLine();
                        } while (validaEmail(correo1) == false);//Bucle de validadcion de email
                        System.out.println("Introduce tu contraseña");
                        pass1 = sc.nextLine();

                        limpiar();
                        Usuario u = new Usuario(nombre1, apellidos1, nick1, null, correo1, pass1);

                        System.out.println(u.toString());

                        System.out.println("Introduce 'No' si quieres volver a rellenar los datos");

                        resp1 = sc.nextLine();
                        limpiar();

                    } while (resp1.equalsIgnoreCase("no"));

                    limpiar();

                    break;

                case 2:

                    sc.nextLine();

                    limpiar();

                    System.out.println("Vas a iniciar sesión, si desea parar en cualquier momento introduzca '0'");

                    do {
                        System.out.println("Introduzca su nick");
                        nick2 = sc.nextLine();

                        v1 = bd.listarCampoTablaString("USUARIOS", "nick");

                        flag2 = false;

                        for (int i = 0; i < v1.size(); i++) {
                            if (nick2.equalsIgnoreCase(v1.get(i)) == true) {
                                flag1 = true;
                            }
                        }

                        if (flag2 == false) {
                            System.out.println("El nick introducido ( " + nick2 + " ) no pertenece a un usuario registrado");
                        }

                    } while (flag2 == false);

                    do {
                        System.out.println("Introduce tu contraseña");
                        pass2 = sc.nextLine();

                        passRet2 = bd.listarPassword(nick2);

                        flag2 = false;

                        if (pass2.equalsIgnoreCase(passRet2) == true) {
                            flag1 = true;
                        }

                        if (flag2 == false) {
                            System.out.println("La contraseña no es correcta, vuelve a intentarlo");
                        }

                    } while (flag2 == false);

                    limpiar();

                    break;

                case 3:

                    break;
                case 4:
                    break;
                case 5:
                    System.err.println("El programa se cerrará");
                    System.exit(0);
                default:
                    System.out.println("La opción introducida no es correcta");
            }

        } while (opc1 != 5);

    }

    public static void limpiar() {

        for (int i = 0; i < 60; i++) {
            System.out.println("");
        }

    }

    /* @author Alvaro.p*/
    public static boolean validarDatos(String dato) {

        /* String regexp = "\\d+[a-zA-Z]";
        if (Pattern.matches(regexp, dato) == true)
                return true;
        else
            return false;*/
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        java.util.regex.Matcher matcher = pattern.matcher(dato);
        return matcher.matches();
    }

    /* @author Alvaro.p*/
    public static boolean validaEmail(String mail) {
        Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
        java.util.regex.Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }


    /**
     * Carga en un HashMap los descuentos actuales, desde un fichero TXT
     * @return Éxito o no en función de si la información se ha cargado correctamente
     * Autor: Fer
     */
    public static boolean cargarDescuentosHashMap() {

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
    public static boolean cargarDescuentosTXT() {

        try {
            // Asigno el archivo que será actualizado
            FileWriter salida = new FileWriter("./descuentos.txt");

            // Objeto BufferedWriter para escribir en dicho fichero asignado como salida
            BufferedWriter bfw = new BufferedWriter(salida);

            for (Map.Entry<String, Integer> elemento : descuentos.entrySet()) {
                // Escribo en el fichero código de descuento "," y valor del descuento en formato CSV("CÓDIGO,VALOR")
                bfw.write(elemento.getKey() + "," + elemento.getValue().toString()+ "\n");
            }

            bfw.close();
            salida.close();
            return true;

        }catch (IOException ioe) {
            System.out.println("InputOutput exception");
        }
        return false;
    }

    /**
     * Método que busca la cantidad de descuento (Integer) correspondiente a un código pasado por parámetro
     * @param codDescuento Código de descuento en formato String
     * @return Cantidad de descuento a aplicar o -1 en caso de no existir descuento
     * Autor: Fer
     */
    public static int cantidadDescuento(String codDescuento) {

        // Recorrer con bucle for each todos los elementos que se encuentran en el mapa
        for (Map.Entry<String, Integer> elemento : descuentos.entrySet()) {
            if (elemento.getKey().equalsIgnoreCase(codDescuento)) {
                // En caso de existir match entre la Key y el código de descuento, devolvemos el valor
                return elemento.getValue();
            }
        }
        // Si no encuentra match ...
        return -1;
    }

}
