/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldf;

import Estilos.Colorinchis;
import bbdd.BD_LDF;
import modulos.Cartelera;
import modulos.Descuentos;
import modulos.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 *
 * @author Alvaro.p
 */
public class LDF {

    public static Scanner sc = new Scanner(System.in);
    public static BD_LDF bd = new BD_LDF("LDF");

    public static void main(String[] args) {

        int opc1, opc2;
        String nombre1, apellidos1, nick1, correo1, pass1, resp1;
        LocalDate fh1;
        String nick2, pass2, passRet2;
        Vector<String> v1;
        Boolean flag1, flag2;

        do {

            limpiar();

            System.out.println(Colorinchis.rainbow("--------------------------------------------------"));
            System.out.println(Colorinchis.blue("|                Bienvenido a LDF                |"));
            System.out.println(Colorinchis.rainbow("--------------------------------------------------"));

            System.out.println(Colorinchis.purple("\nIntroduzca opción:    "));
            System.out.println("\n#1. Registrarse\n#2. Iniciar Sesión\n#3. Comprar entradas\n#4. Ver cartelera\n#5. Salir");
            opc1 = sc.nextInt();

            switch (opc1) {

                // REGISTRARSE
                case 1:

                    sc.nextLine();

                    do {
                        limpiar();

                        System.out.println(Colorinchis.blue("Vas a crear una cuenta, si desea parar en cualquier momento introduzca '0'"));

                        // PREGUNTAMOS Y VALIDAMOS EL NOMBRE
                        do {

                            System.out.println("Introduzca su nombre");
                            nombre1 = sc.nextLine();

                            if (validarDatos(nombre1) == false) {

                                System.out.println("El nombre no puede contener numeros");

                            }

                            if (nombre1.equalsIgnoreCase("0")) {
                                break;
                            }

                        } while (validarDatos(nombre1) == false);

                        if (nombre1.equalsIgnoreCase("0")) {
                            break;
                        }

                        // PREGUNTAMOS Y VALIDAMOS LOS APELLIDOS
                        do {

                            System.out.println("Introduce tus apellidos");
                            apellidos1 = sc.nextLine();

                            if (validarDatos(apellidos1) == false) {

                                System.out.println("Los apellidos no pueden contener numeros");

                            }

                            if (apellidos1.equalsIgnoreCase("0")) {
                                break;
                            }

                        } while (validarDatos(apellidos1) == false);

                        if (apellidos1.equalsIgnoreCase("0")) {
                            break;
                        }

                        // COMPROBAMOS QUE EL NICK NO ESTE YA EN LA BASE DE DATOS
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

                        // VALIDAMOS LA FECHA DE NACIMIENTO
                        System.out.println("Introduce la fecha de nacimiento");
                        fh1 = leeFecha("Fecha incorrecta | Formato: 'dd/mm/yyyy'", "dd/LL/yyyy");

                        // VALIDAMOS EL CORREO
                        do {

                            System.out.println("Introduce tu correo");
                            correo1 = sc.nextLine();

                            if (validaEmail(correo1) == false) {
                                System.out.println("El correo introducido no es válido");
                            }

                        } while (validaEmail(correo1) == false);

                        // PREGUNTAMOS POR LA CONTRASEÑA
                        System.out.println("Introduce tu contraseña");
                        pass1 = sc.nextLine();

                        if (pass1.equalsIgnoreCase("0")) {
                            break;
                        }

                        limpiar();

                        Usuario u = new Usuario(nombre1, apellidos1, nick1, fh1, correo1, pass1);

                        System.out.println(u.toString());

                        System.out.println(Colorinchis.green("\nIntroduce 'No' si quieres volver a rellenar los datos"));

                        resp1 = sc.nextLine();

                        try {

                            int filas1 = bd.añadirUsuario(u);

                            switch (filas1) {

                                case 1:

                                    System.out.println(Colorinchis.rainbow("\nUsuario añadido con éxito"));
                                    break;

                                case 2:

                                    System.out.println(Colorinchis.purple("\nNo se ha podido añadir el usuario, contacte con soporte"));

                            }
                        } catch (Exception e) {

                            e.printStackTrace();
                        }

                        limpiar();

                    } while (resp1.equalsIgnoreCase("no"));

                    limpiar();

                    break;

                case 2:

                    sc.nextLine();

                    limpiar();

                    System.out.println(Colorinchis.blue("Vas a iniciar sesión, si desea parar en cualquier momento introduzca '0'"));

                    do {
                        System.out.println("Introduzca su nick");
                        nick2 = sc.nextLine();

                        if (nick2.equalsIgnoreCase("0")) {
                            break;
                        }

                        v1 = bd.listarCampoTablaString("USUARIOS", "nick");

                        flag2 = false;

                        for (int i = 0; i < v1.size(); i++) {
                            if (nick2.equals(v1.get(i)) == true) {
                                flag2 = true;
                            }
                        }

                        if (flag2 == false) {
                            System.out.println("El nick introducido ( " + nick2 + " ) no pertenece a un usuario registrado");
                        }

                    } while (flag2 == false);

                    if (nick2.equalsIgnoreCase("0")) {
                        break;
                    }

                    do {
                        System.out.println("Introduce tu contraseña");
                        pass2 = sc.nextLine();

                        if (pass2.equalsIgnoreCase("0")) {
                            break;
                        }

                        passRet2 = bd.listarPassword(nick2);

                        flag2 = false;

                        if (pass2.equals(passRet2) == true) {
                            flag2 = true;
                        }

                        if (flag2 == false) {
                            System.out.println("La contraseña no es correcta, vuelve a intentarlo");
                        }

                    } while (flag2 == false);

                    if (pass2.equalsIgnoreCase("0")) {
                        break;
                    }

                    limpiar();

                    if (nick2.equalsIgnoreCase("admin")) {
                        int opcAdmin;
                        int opcAdmin2;

                        do {
                            limpiar();
                            m_admin();
                            opcAdmin = sc.nextInt();
                            sc.nextLine();

                            switch (opcAdmin) {
                                case 1:
                                    limpiar();
                                    m_admin_ges();
                                    opcAdmin2 = sc.nextInt();
                                    sc.nextLine();
                                    switch(opcAdmin2){
                                        case 1:
                                            //Alta usuario
                                            do {
                                                limpiar();

                                                System.out.println(Colorinchis.blue("Vas a crear una cuenta, si desea parar en cualquier momento introduzca '0'"));

                                                // PREGUNTAMOS Y VALIDAMOS EL NOMBRE
                                                do {

                                                    System.out.println("Introduzca el nombre");
                                                    nombre1 = sc.nextLine();

                                                    if (validarDatos(nombre1) == false) {

                                                        System.out.println("El nombre no puede contener números");

                                                    }

                                                    if (nombre1.equalsIgnoreCase("0")) {
                                                        break;
                                                    }

                                                } while (validarDatos(nombre1) == false);

                                                if (nombre1.equalsIgnoreCase("0")) {
                                                    break;
                                                }

                                                // PREGUNTAMOS Y VALIDAMOS LOS APELLIDOS
                                                do {

                                                    System.out.println("Introduzca los apellidos");
                                                    apellidos1 = sc.nextLine();

                                                    if (validarDatos(apellidos1) == false) {

                                                        System.out.println("Los apellidos no pueden contener numeros");

                                                    }

                                                    if (apellidos1.equalsIgnoreCase("0")) {
                                                        break;
                                                    }

                                                } while (validarDatos(apellidos1) == false);

                                                if (apellidos1.equalsIgnoreCase("0")) {
                                                    break;
                                                }

                                                // COMPROBAMOS QUE EL NICK NO ESTE YA EN LA BASE DE DATOS
                                                do {
                                                    System.out.println("Introduzca el nick");
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

                                                // VALIDAMOS LA FECHA DE NACIMIENTO
                                                System.out.println("Introduce la fecha de nacimiento");
                                                fh1 = leeFecha("Fecha incorrecta | Formato: 'dd/mm/yyyy'", "dd/LL/yyyy");

                                                // VALIDAMOS EL CORREO
                                                do {

                                                    System.out.println("Introduce tu correo");
                                                    correo1 = sc.nextLine();

                                                    if (validaEmail(correo1) == false) {
                                                        System.out.println("El correo introducido no es válido");
                                                    }

                                                } while (validaEmail(correo1) == false);

                                                // PREGUNTAMOS POR LA CONTRASEÑA
                                                System.out.println("Introduce tu contraseña");
                                                pass1 = sc.nextLine();

                                                if (pass1.equalsIgnoreCase("0")) {
                                                    break;
                                                }

                                                limpiar();

                                                Usuario u = new Usuario(nombre1, apellidos1, nick1, fh1, correo1, pass1);

                                                System.out.println(u.toString());

                                                System.out.println(Colorinchis.green("\nIntroduce 'No' si quieres volver a rellenar los datos"));

                                                resp1 = sc.nextLine();

                                                try {

                                                    int filas1 = bd.añadirUsuario(u);

                                                    switch (filas1) {

                                                        case 1:

                                                            System.out.println(Colorinchis.rainbow("\nUsuario añadido con éxito"));
                                                            break;

                                                        case 2:

                                                            System.out.println(Colorinchis.purple("\nNo se ha podido añadir el usuario, contacte con soporte"));

                                                    }
                                                } catch (Exception e) {

                                                    e.printStackTrace();
                                                }

                                                limpiar();

                                            } while (resp1.equalsIgnoreCase("no"));

                                            limpiar();

                                            break;

                                        case 2:
                                            //BAJA USUARIO
                                            String nick;
                                            boolean existe = false;
                                            System.out.println(Colorinchis.red("Anota el nick del usuario que deseas borrar: "));
                                            do {
                                                nick = sc.nextLine();
                                                existe = bd.Admin_existeUsuario(nick);
                                                if (!existe) {
                                                    System.out.println(Colorinchis.red("No existe ese nick, prueba otra vez: "));
                                                }
                                            }while(!existe);

                                            int filas = bd.Admin_borrarUsuario(nick);

                                            if (filas == 1) {
                                                System.out.println(Colorinchis.green("Usuario borrado correctamente"));
                                            } else {
                                                System.out.println(Colorinchis.red("Usuario no se ha podido borrar correctamente"));
                                            }
                                            System.out.println("Pulsa enter para continuar");
                                            sc.nextLine();

                                            break;
                                        case 3:
                                            //MODIFICAR USUARIO
                                            limpiar();
                                            String Admin_Usuario;
                                            bd.Admin_listarUsuarios();
                                            System.out.print("Anota el nombre del usuario que desea modificar" + Colorinchis.red(" -> "));
                                            Admin_Usuario = sc.nextLine();
                                            break;
                                        case 4:
                                            break;
                                    }
                                    break;

                                case 2:
                                    //Modificar entradas
                                    break;
                                case 3:
                                    //Modificar cartelera
                                    break;
                                case 4:
                                    limpiar();
                                    m_admin_prom();
                                    opcAdmin2 = sc.nextInt();
                                    String codDescuento;
                                    int porcentajeDescuento = 0;
                                    boolean correcto = false;

                                    sc.nextLine();
                                    switch (opcAdmin2) {
                                        case 1:
                                            System.out.println(Colorinchis.red("Vamos a añadir un descuento: "));
                                            System.out.println("Anota el nombre del descuento: ");
                                            codDescuento = sc.nextLine();

                                            do {
                                                try {
                                                    System.out.println("Cantidad de descuento del 0 al 100");
                                                    porcentajeDescuento = sc.nextInt();
                                                    sc.nextLine();
                                                    correcto = true;
                                                }catch (InputMismatchException imm) {
                                                    System.out.println("Error introduce un número");
                                                }
                                            }while (!correcto);

                                            Descuentos.Admin_addDescuento(codDescuento, porcentajeDescuento);
                                            break;

                                        case 2:
                                            System.out.println(Colorinchis.red("Anota el código del descuento que deseas borrar: "));
                                            codDescuento = sc.nextLine();

                                            Descuentos.Admin_deleteDescuento(codDescuento);
                                            break;

                                        case 3:
                                            System.out.println(Colorinchis.red("Anota el descuento que desea modificar: "));
                                            codDescuento = sc.nextLine();
                                            break;
                                        case 4:
                                            break;
                                    }
                                    break;
                            }
                        }while(opcAdmin != 5);


                    } else {

                        do {
                            limpiar();
                            System.out.println("#1. Ver perfil\n#2. Mirar entradas\n#3. Ver promociones\n#4. Ver cartelera\n#5. Comprar entradas\n#6. Mejorar a premium\n#7. Cerrar sesión");
                            opc2 = sc.nextInt();

                            switch (opc2) {
                                case 1:

                                    do {

                                        sc.nextLine();
                                        limpiar();

                                        bd.listarUser(nick2);

                                        System.out.println("#1. Modificar información\n#2. Salir");
                                        opc2 = sc.nextInt();

                                        sc.nextLine();
                                        limpiar();

                                        switch (opc2) {

                                            case 1:

                                                do {

                                                    bd.listarUser(nick2);
                                                    System.out.println(Colorinchis.blue("¿Que desea modificar?\n") + "#1. Modificar Nick\n#2. Modificar Contraseñas\n#3. Volver");
                                                    opc2 = sc.nextInt();

                                                    switch (opc2) {
                                                        case 1:

                                                            limpiar();
                                                            sc.nextLine();

                                                            do {
                                                                System.out.println("Introduce un nuevo nick");
                                                                String newNick = sc.nextLine();

                                                                v1 = bd.listarCampoTablaString("USUARIOS", "nick");

                                                                flag1 = false;

                                                                for (int i = 0; i < v1.size(); i++) {
                                                                    if (newNick.equalsIgnoreCase(v1.get(i)) == true) {
                                                                        flag1 = true;
                                                                        System.out.println("El nick introducido ( " + newNick + " ) ya pertenece a un usuario registrado");
                                                                        break;
                                                                    }
                                                                }

                                                                if (flag1 == true) {
                                                                    continue;
                                                                }

                                                                try {

                                                                    int filas1 = bd.cambiarNick(newNick, nick2);

                                                                    switch (filas1) {

                                                                        case 1:

                                                                            System.out.println("\nUsuario modificado con éxito");
                                                                            break;

                                                                        case 2:

                                                                            System.out.println("\nNo se ha podido añadir el usuario, contacte con soporte");

                                                                    }
                                                                } catch (Exception e) {

                                                                    e.printStackTrace();
                                                                }

                                                                limpiar();

                                                                nick2 = newNick;

                                                            } while (flag1 == true);

                                                            break;

                                                        case 2:

                                                            limpiar();
                                                            sc.nextLine();

                                                            System.out.println("Introduce una nueva contraseña");
                                                            String newPass = sc.nextLine();

                                                            try {

                                                                int filas1 = bd.cambiarContrasena(newPass, nick2);

                                                                switch (filas1) {

                                                                    case 1:

                                                                        System.out.println("\nUsuario modificado con éxito");
                                                                        break;

                                                                    case 2:

                                                                        System.out.println("\nNo se ha podido añadir el usuario, contacte con soporte");

                                                                }
                                                            } catch (Exception e) {

                                                                e.printStackTrace();
                                                            }

                                                            limpiar();

                                                            break;

                                                    }

                                                } while (opc2 != 3);

                                        }

                                    } while (opc2 != 2);
                                    break;

                                case 2:

                                    limpiar();

                                    System.out.println("Listando las entradas del usuario:  " + nick2 + "\n");

                                    bd.listarEntradasUsuario(nick2);

                                    break;

                                case 3:
                                    Descuentos.mostrarDescuentosActuales();
                                    System.out.println(Colorinchis.green("Pulse cualquier tecla para continuar."));
                                    sc.nextLine();
                                    sc.nextLine();
                                    break;

                                case 4:
                                    limpiar();
                                    cartelera();
                                    break;

                                case 6:
                                    limpiar();
                                    //VENTAJAS DE SER PREMIUM
                                    System.out.println(Colorinchis.rainbow("Ventajas de ser premium:    "));
                                    System.out.println("#1. Si no te gusta la película, te devolvemos la entrada");
                                    System.out.println("#2. Descuentos de un 20% en el precio de tus entradas");
                                    System.out.println("#3. Trato exclusivo y personalizado\n");
                                    System.out.println("#4. Sistema de puntos (No implementado todavía)");
                                    System.out.println(Colorinchis.black("Pago domicilado (SEPA) de 5€ mensuales", true, true));

                                    System.out.println(Colorinchis.green("Pulsa cualquier tecla para continuar"));
                                    sc.nextLine();
                                    sc.nextLine();
                                    break;

                                //HACER CONSULTA DE PREMIUM
                            }

                        } while (opc2 != 7);

                    }

                    break;

                case 3:

                    break;
                case 4:

                    limpiar();
                    cartelera();

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
    public static boolean validarNumTarjeta(String dato) {

        Pattern pattern = Pattern.compile("\\d{16}");
        java.util.regex.Matcher matcher = pattern.matcher(dato);
        return matcher.matches();
    }
    /* @author Alvaro.p*/

    public static boolean validarFechaTarjeta(String dato) {

        Pattern pattern = Pattern.compile("\\d{1,2}/\\d{1,2}");
        java.util.regex.Matcher matcher = pattern.matcher(dato);
        return matcher.matches();
    }
    /* @author Alvaro.p*/

    public static boolean validarCvTarjeta(String dato) {

        Pattern pattern = Pattern.compile("\\d{3}");
        java.util.regex.Matcher matcher = pattern.matcher(dato);
        return matcher.matches();
    }

    /* @author Alvaro.p*/
    public static boolean validarDatos(String dato) {

        /* String regexp = "\\d+[a-zA-Z]";
         if (Pattern.matches(regexp, dato) == true)
         return true;
         else
         return false;*/
        Pattern pattern = Pattern.compile("[a-zA-Z ]*");
        java.util.regex.Matcher matcher = pattern.matcher(dato);
        return matcher.matches();
    }

    /* @author Alvaro.p*/
    public static boolean validaEmail(String mail) {
        Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
        java.util.regex.Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }

    /* @author Alvaro.p*/
    public static void comprarEntradas() {
        Scanner sc = new Scanner(System.in);
        Vector<Cartelera> v;
        bd.listarCartelera();
        System.out.println("Dime el cine");
        String cines = sc.nextLine();
        v = bd.listarCarteleraFiltrada("cine", cines);

        System.out.println("Dime el nombre de la peli");
        String nombre = sc.nextLine();
        for (int i = 0; i < v.size(); i++) {
            if (v.get(i).getNombre().contains(nombre)== true)
          {
              System.out.println(v.get(i).toString());
            }
        }
        System.out.println(" 1 sesion de mañana \n 2 sesion tarde ");

    }

    public static LocalDate leeFecha(String msgError, String patron) {

        boolean correcto = false;
        String fecha;
        DateTimeFormatter fechaFormateada = DateTimeFormatter.ofPattern(patron);
        LocalDate FechaAnotada = null;

        do {
            try {
                fecha = sc.nextLine();
                FechaAnotada = LocalDate.parse(fecha, fechaFormateada);
                correcto = true;
            } catch (DateTimeParseException e) {
                System.out.println(msgError);
            }

            if (FechaAnotada != null) {
                if (FechaAnotada.isAfter(LocalDate.now()) == true) {
                    correcto = false;
                }
            }

        } while (!correcto);
        return FechaAnotada;
    }

    /**
     * @author Lucía Piñán Barberán
     */
    public static void cartelera() {

        int opc4;
        String nombre4;
        Vector<Cartelera> v4;

        do {
            limpiar();
            System.out.println("#1. Ver cartelera completa\n#2. Filtrar cartelera\n#3. Volver");
            opc4 = sc.nextInt();

            switch (opc4) {

                case 1:

                    sc.nextLine();
                    limpiar();

                    System.err.println(Colorinchis.rainbow("                                                             ---- MOSTRANDO CARTELERA ----\n"));
                    v4 = bd.listarCartelera();

                    for (int i = 0; i < v4.size(); i++) {

                        System.out.println(v4.get(i).toString());

                    }

                    break;

                case 2:
                    sc.nextLine();
                    limpiar();
                    do {
                        System.out.println("#1. Filtrar por NOMBRE\n#2. Filtrar por FECHA\n#3. Filtrar por TIPO\n#4. Filtrar por CINE\n#5. Salir");
                        opc4 = sc.nextInt();

                        switch (opc4) {

                            case 1:

                                sc.nextLine();
                                limpiar();

                                System.out.println("Introduce el nombre de la película");
                                nombre4 = sc.nextLine();

                                v4 = bd.listarCarteleraFiltrada(nombre4, "nombre");

                                for (int i = 0; i < v4.size(); i++) {

                                    System.out.println(v4.get(i).toString());

                                }

                                break;
                            case 2:

                                sc.nextLine();
                                limpiar();

                                System.out.println("Introduce la fecha de la película");
                                nombre4 = sc.nextLine();

                                v4 = bd.listarCarteleraFiltrada(nombre4, "fecha_hora");

                                for (int i = 0; i < v4.size(); i++) {

                                    System.out.println(v4.get(i).toString());

                                }

                                break;
                            case 3:

                                sc.nextLine();
                                limpiar();

                                System.out.println("Introduce la versión de la película");
                                nombre4 = sc.nextLine();

                                v4 = bd.listarCarteleraFiltrada(nombre4, "tipo");

                                for (int i = 0; i < v4.size(); i++) {

                                    System.out.println(v4.get(i).toString());

                                }

                                break;

                            case 4:

                                sc.nextLine();
                                limpiar();

                                System.out.println("CINES: \n");
                                bd.listarCines();

                                System.out.println("Introduce el ID del cine donde quieres ver la película");
                                nombre4 = sc.nextLine();

                                limpiar();

                                v4 = bd.listarCarteleraFiltrada(nombre4, "id_cine");

                                for (int i = 0; i < v4.size(); i++) {

                                    System.out.println(v4.get(i).toString());

                                }

                                break;
                        }
                    } while (opc4 != 5);
            }
        } while (opc4 != 3);

        limpiar();

    }


    /* MENUS ADMINISTRADOR */
    /* Autor : Fer */
    public static void m_admin() {
        System.out.println(Colorinchis.purple("Bienvenido Bruce:\n") +
                Colorinchis.red("#1. ") + "Gestionar usuarios\n" +
                Colorinchis.red("#2. ") + "Modificar entradas\n" +
                Colorinchis.red("#3. ") + "Modificar cartelera\n" +
                Colorinchis.red("#4. ") + "Modificar promociones\n" +
                Colorinchis.red("#5. ") + "Volver");
    }

    public static void m_admin_ges() {
        System.out.println(Colorinchis.purple("Gestión de usuarios:\n") +
                Colorinchis.red("#1. ") + "Alta usuario\n" +
                Colorinchis.red("#2. ") + "Baja usuario\n" +
                Colorinchis.red("#3. ") + "Modificar usuario\n" +
                Colorinchis.red("#4. ") + "Volver");
    }

    public static void m_admin_cart() {
        System.out.println(Colorinchis.purple("Gestión de cartelera:\n") +
                Colorinchis.red("#1. ") + "Añadir datos\n" +
                Colorinchis.red("#2. ") + "Borrar datos\n" +
                Colorinchis.red("#3. ") + "Modificar datos\n" +
                Colorinchis.red("#4. ") + "Volver");
    }

    public static void m_admin_prom() {
        System.out.println(Colorinchis.purple("Gestión de promociones:\n") +
                Colorinchis.red("#1. ") + "Añadir promoción\n" +
                Colorinchis.red("#2. ") + "Borrar promoción\n" +
                Colorinchis.red("#3. ") + "Modificar promoción\n" +
                Colorinchis.red("#4. ") + "Volver");
    }






}
