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
import modulos.Entrada;

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
        Vector<String> v1;
        

        String nick2, pass2, passRet2;
        Boolean flag1, flag2;

        do {
            limpiar();
            m_cabecera();
            m_base();
            opc1 = esInt();
            switch (opc1) {
                // REGISTRARSE
                case 1:
                    registrarse();
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

                    /*
                     * Usuario Admin
                     * Autor: Fer
                     */
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

                                    switch (opcAdmin2) {
                                        case 1:
                                            // ALTA USUARIO
                                            sc.nextLine();
                                            registrarse();
                                            break;

                                        case 2:
                                            //BAJA USUARIO
                                            limpiar();
                                            sc.nextLine();
                                            Admin_bajaUsusario();
                                            break;

                                        case 3:
                                            // MODIFICAR USUARIO
                                            limpiar();
                                            Admin_modificarUsuario();
                                            break;

                                        case 4:
                                            break;
                                    }
                                    break;

                                case 2:
                                    // MODIFICAR ENTRADAS
                                    break;

                                case 3:
                                    // MODIFICAR CARTELERA
                                    limpiar();
                                    m_admin_cart();
                                    Admin_modificarCartelera();
                                    break;

                                case 4:
                                    // MODIFICAR PROMOCIONES
                                    limpiar();
                                    m_admin_prom();
                                    Admin_modificarPromociones();
                                    break;
                            }
                        } while (opcAdmin != 5);

                    } else {
                        do {
                            limpiar();
                            m_menuUsuario();
                            opc2 = sc.nextInt();

                            switch (opc2) {
                                case 1:

                                    do {
                                        sc.nextLine();
                                        limpiar();

                                        bd.listarUser(nick2);

                                        m_usuarioVerPerfil();
                                        opc2 = sc.nextInt();

                                        sc.nextLine();
                                        limpiar();

                                        switch (opc2) {

                                            case 1:

                                                do {

                                                    bd.listarUser(nick2);
                                                    m_usuarioModificarPerfil();
                                                    opc2 = sc.nextInt();

                                                    switch (opc2) {
                                                        case 1:

                                                            limpiar();
                                                            sc.nextLine();

                                                            nick2 = usuario_ModificarPerfil(nick2);

                                                            break;

                                                        case 2:
                                                            limpiar();
                                                            sc.nextLine();
                                                            usuario_ModificarContraseña(nick2);

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
                                    
                                case 5:
                                    
                                    Double precio = 9.40;
                                    
                                    if(bd.esPremium(nick2) == true){
                                    
                                        precio *= 0.8;
                                        
                                    }
                                    
                                    
                                    limpiar();
                                    sc.nextLine();
                                    System.out.println("Introduce un código de descuento pulsa cualquier tecla");
                                    String desc = sc.nextLine();
                                    
                                    if(Descuentos.existeDescuento(desc) == true){
                                    
                                        precio *= 1 - ((double)Descuentos.porcentajeDescuento(desc))/100;
                                        
                                    }
                                    
                                    comprarEntradas(nick2, precio);
                                    
                                    break;

                                case 6:
                                    limpiar();
                                    premium();
                                    break;

                                //HACER CONSULTA DE PREMIUM
                                //HACER CONSULTA DE PREMIUM
                            }

                        } while (opc2 != 7);

                    }

                    break;

                case 3:
                    limpiar();
                    comprarEntradas("AnonymusLDF", 9.40);
                    break;
                case 4:

                    limpiar();
                    cartelera();

                    break;
                case 5:
                    System.err.println("El programa se cerrará");
                    System.exit(0);
            }

        } while (opc1 != 5);

    }

    public static void premium() {
        //VENTAJAS DE SER PREMIUM
        System.out.println(Colorinchis.rainbow("Ventajas de ser premium:"));
        System.out.println(Colorinchis.rainbow("========================\n"));
        System.out.println(Colorinchis.purple("#1. ")
                + "GARANTÍA DE SATISFACCIÓN:\n"
                + "Si no te gusta la peli, te devolvemos la entrada");
        System.out.println(Colorinchis.purple("#2. ")
                + "COMODIDAD:\n"
                + "Incluyen butaca VIP");
        System.out.println(Colorinchis.purple("#3. ")
                + "PRECIOS:\n"
                + "Especiales para socios (Podrás elegir butacas VIP a precio rebajado)");
        System.out.println(Colorinchis.purple("#4. ")
                + "DESCUENTOS EXCLUSIVOS:\n"
                + "Recibirás en tu e-mail promos exclusivas Cinesacard");
        System.out.println(Colorinchis.purple("#5. ")
                + "PUNTOS:\n"
                + "Canjéalos por entradas o productos de bar\n");

        System.out.println(Colorinchis.black("Pago domicilado (SEPA) de 5€ mensuales", true, true));
        System.out.println(Colorinchis.green("Pulsa cualquier tecla para continuar"));
        sc.nextLine();
        sc.nextLine();
    }

    public static void usuario_ModificarContraseña(String nick2) {
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
    }

    public static String usuario_ModificarPerfil(String nick2) {
        Vector<String> v1;
        Boolean flag1;
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
        return nick2;
    }

    public static void m_usuarioModificarPerfil() {
        System.out.println(Colorinchis.blue("¿Que desea modificar?\n")
                + Colorinchis.red("#1.") + " Modificar Nick\n"
                + Colorinchis.red("#2.") + "Modificar Contraseñas\n"
                + Colorinchis.red("#3.") + "Volver");
    }

    public static void m_usuarioVerPerfil() {
        System.out.println(Colorinchis.red("#1.") + "Modificar información\n"
                + Colorinchis.red("#2.") + "Salir");
    }

    public static void m_menuUsuario() {
        System.out.println(Colorinchis.red("#1.") + "Ver perfil\n"
                + Colorinchis.red("#2.") + "Mirar entradas\n"
                + Colorinchis.red("#3.") + "Ver promociones\n"
                + Colorinchis.red("#4.") + "Ver cartelera\n"
                + Colorinchis.red("#5.") + "Comprar entradas\n"
                + Colorinchis.red("#6.") + "Mejorar a premium\n"
                + Colorinchis.red("#7.") + "Cerrar sesión");
    }

    public static void m_cabecera() {
        System.out.println(Colorinchis.rainbow("--------------------------------------------------"));
        System.out.println(Colorinchis.blue("|                Bienvenido a LDF                |"));
        System.out.println(Colorinchis.rainbow("--------------------------------------------------"));
    }

    public static void m_base() {
        System.out.println(Colorinchis.purple("\nIntroduzca opción:    "));
        System.out.println(Colorinchis.red("#1.") + "Registrarse\n"
                + Colorinchis.red("#2.") + "Iniciar Sesión\n"
                + Colorinchis.red("#3.") + "Comprar entradas\n"
                + Colorinchis.red("#4.") + "Ver cartelera\n"
                + Colorinchis.red("#5.") + "Salir");
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

    public static void comprarEntradas(String nick2, double precio) {
        sc.nextLine();
        boolean flagAux;
        Vector<Cartelera> v;
        v = bd.listarCartelera();
        Entrada e;

        for (int i = 0; i < v.size(); i++) {

            System.out.println(v.get(i).toString());

        }

        System.out.println("Dime el cine");
        String cines = sc.nextLine();
        v = bd.listarCarteleraFiltrada(cines, "id_cine");

        limpiar();

        for (int i = 0; i < v.size(); i++) {

            System.out.println(v.get(i).toString());

        }

        System.out.println("Dime el nombre de la peli");
        String nombre = sc.nextLine();

        limpiar();

        for (int i = 0; i < v.size(); i++) {
            if (v.get(i).getNombre().toUpperCase().contains(nombre.toUpperCase()) == true) {
                System.out.println(v.get(i).toString());
            }
        }

        System.out.println("Dime fecha y hora (aaaa-mm-dd-hh:mi)");
        String fh = sc.nextLine();

        limpiar();

        for (int i = 0; i < v.size(); i++) {
            if (v.get(i).toString().contains(fh) == true && v.get(i).getNombre().toUpperCase().contains(nombre.toUpperCase()) == true) {
                System.out.println(v.get(i).toString());
            }
        }

        System.out.println("Dime la sala");
        int sala = sc.nextInt();

        limpiar();

        for (int i = 0; i < v.size(); i++) {
            if (v.get(i).getId_sala() == sala && v.get(i).toString().contains(fh) == true && v.get(i).getNombre().toUpperCase().contains(nombre.toUpperCase()) == true) {
                System.out.println(v.get(i).toString());
            }
        }

        Cartelera ca = bd.listarPeliculaCartelera(nombre, cines, sala, fh);

        System.out.println("¿Cuántas entradas quiere?");

        int numEntradas = esInt();
        int nF, nB;

        Vector<Integer> f = bd.listarFilaButaca(sala, cines);

        for (int i = 0; i < numEntradas; i++) {

            do {

                flagAux = false;

                do {

                    System.out.println("Introduce la fila (1 - " + f.get(0) + ")");
                    nF = esInt();

                } while (nF < 1 || nF > f.get(0));

                do {

                    System.out.println("Introduce la butaca (1 - " + f.get(1) + ")");
                    nB = esInt();

                } while (nB < 1 || nB > f.get(1));

                Cartelera auxc = bd.listarPeliculaCartelera(nombre, cines, sala, fh);

                e = new Entrada(auxc.getId_cine(), auxc.getId_sala(), auxc.getNombre(), nF, nB, auxc.getFecha_hora());

                flagAux = bd.sitioOcupado(e);

                if (flagAux == true) {
                    System.out.println("El sitio ya está ocupado, prueba a introducir otro sitio");
                }

            } while (flagAux == true);
            
            try {

                int filas1 = bd.añadirEntrada(nick2, e, precio);

                switch (filas1) {

                    case 1:

                        System.out.println(Colorinchis.rainbow("\nEntrada sacada con éxito"));
                        break;

                    case 2:

                        System.out.println(Colorinchis.purple("\nNo se ha podido añadir la entrada, contacte con soporte"));

                }
            } catch (Exception esp) {

                esp.printStackTrace();
            }
        }

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

            System.out.println(Colorinchis.red("#1.") + "Ver cartelera completa\n" + Colorinchis.red("#2.") + "Filtrar cartelera\n" + Colorinchis.red("#3.") + "Volver");
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
                        System.out.println(Colorinchis.red("#1.") + "Filtrar por NOMBRE\n" + Colorinchis.red("#2.") + "Filtrar por FECHA\n"
                                + Colorinchis.red("#3.") + "Filtrar por TIPO\n" + Colorinchis.red("#4.") + "Filtrar por CINE\n" + Colorinchis.red("#5.") + "Salir");
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
                    limpiar();
            }
        } while (opc4 != 3);

        limpiar();

    }

    public static void registrarse() {

        String nombre1, apellidos1, nick1, correo1, pass1, resp1;
        LocalDate fh1;
        Vector<String> v1;
        Boolean flag1;

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

    }

    /**
     * Método para validar que los datos introducidos son números siempre
     *
     * @return un int 100%
     */
    public static int esInt() {
        boolean pasaPasa = false;
        int numero = 0;
        do {
            try {
                numero = sc.nextInt();
                pasaPasa = true;
            } catch (InputMismatchException imme) {
                System.out.println(Colorinchis.red("Error! debes introducir un int"));
                sc.nextLine();
            }
        } while (!pasaPasa);
        return numero;
    }

    /**
     * Método para validar que los datos introducidos son números siempre
     *
     * @return un double 100%
     */
    public static double esDouble() {
        boolean pasaPasa = false;
        double numero = 0;
        do {
            try {
                numero = sc.nextDouble();
                pasaPasa = true;
            } catch (InputMismatchException imme) {
                System.out.println(Colorinchis.red("Error! debes introducir un double"));
                sc.nextLine();
            }
        } while (!pasaPasa);
        return numero;

    }


    /* PARTE ADMINISTRADOR */
 /* Autor : Fer */
    public static void m_admin() {
        System.out.println(Colorinchis.purple("Bienvenido Bruce:\n")
                + Colorinchis.red("#1. ") + "Gestionar usuarios\n"
                + Colorinchis.red("#2. ") + "Modificar entradas\n"
                + Colorinchis.red("#3. ") + "Modificar cartelera\n"
                + Colorinchis.red("#4. ") + "Modificar promociones\n"
                + Colorinchis.red("#5. ") + "Volver");
    }

    public static void m_admin_ges() {
        System.out.println(Colorinchis.purple("Gestión de usuarios:\n")
                + Colorinchis.red("#1. ") + "Alta usuario\n"
                + Colorinchis.red("#2. ") + "Baja usuario\n"
                + Colorinchis.red("#3. ") + "Modificar usuario\n"
                + Colorinchis.red("#4. ") + "Volver");
    }

    public static void m_admin_ges_mod() {
        System.out.println(Colorinchis.purple("Gestión de usuarios:\n")
                + Colorinchis.red("#1. ") + "Modificar nick\n"
                + Colorinchis.red("#2. ") + "Modificar contraseña\n"
                + Colorinchis.red("#3. ") + "Modificar email"
                + Colorinchis.red("#4. ") + "Volver");
    }

    public static void m_admin_cart() {
        System.out.println(Colorinchis.purple("Gestión de cartelera:\n")
                + Colorinchis.red("#1. ") + "Añadir datos\n"
                + Colorinchis.red("#2. ") + "Borrar datos\n"
                + Colorinchis.red("#3. ") + "Modificar datos\n"
                + Colorinchis.red("#4. ") + "Volver");
    }

    public static void m_admin_prom() {
        System.out.println(Colorinchis.purple("Gestión de promociones:\n")
                + Colorinchis.red("#1. ") + "Añadir promoción\n"
                + Colorinchis.red("#2. ") + "Borrar promoción\n"
                + Colorinchis.red("#3. ") + "Modificar promoción\n"
                + Colorinchis.red("#4. ") + "Volver");
    }

    /*ADMIN USUARIO*/
    public static void Admin_bajaUsusario() {
        String Admin_nick;
        boolean existe = false;
        bd.Admin_listarUsuarios();
        System.out.println(Colorinchis.red("Anota el nick del usuario que deseas borrar: "));
        do {
            Admin_nick = sc.nextLine();
            existe = bd.Admin_existeUsuario(Admin_nick);
            if (!existe) {
                System.out.println(Colorinchis.red("No existe ese nick, prueba otra vez: "));
            }
        } while (!existe);

        int Admin_filas = bd.Admin_borrarUsuario(Admin_nick);

        if (Admin_filas == 1) {
            System.out.println(Colorinchis.green("Usuario borrado correctamente"));
        } else {
            System.out.println(Colorinchis.red("Usuario no se ha podido borrar correctamente"));
        }
        System.out.println("Pulsa enter para continuar");
        sc.nextLine();
    }

    public static void Admin_modificarUsuario() {
        String Admin_nick = null;
        String newNick;
        boolean existe = false;
        int modOpc;
        int opcAdmin2;

        bd.Admin_listarUsuarios();
        System.out.println("Salir con '0'");
        System.out.print("Anota el nick del usuario que desea modificar" + Colorinchis.red(" -> "));
        do {
            Admin_nick = sc.nextLine();
            existe = bd.Admin_existeUsuario(Admin_nick);
            if (!existe) {
                System.out.println(Colorinchis.red("Usuario no encontrado, vuelva a introducir un nick valido"));
            }
        } while (!existe || Admin_nick.equalsIgnoreCase("0"));

        do {
            limpiar();
            m_admin_ges_mod();
            opcAdmin2 = esInt();
            sc.nextLine();
            if (opcAdmin2 < 1 || opcAdmin2 > 3) {
                System.out.println(Colorinchis.red("Opción incorrecta"));
            }

            switch (opcAdmin2) {
                case 1:
                    limpiar();
                    System.out.println(Colorinchis.green("Anota el nuevo nick: "));
                    newNick = sc.nextLine();

                    int Admin_filas = bd.Admin_updateUser("nick", newNick, Admin_nick);

                    if (Admin_filas > 0) {
                        System.out.println(Colorinchis.green(Admin_filas + " filas actualizadas"));
                    } else {
                        System.out.println(Colorinchis.red("No se han actualizado filas"));
                    }
                    System.out.println("Pulsa enter para continuar");
                    sc.nextLine();

                    break;
                case 2:
                    limpiar();
                    String password;
                    System.out.println(Colorinchis.red("Anota la nueva contraseña nueva: "));
                    password = sc.nextLine();

                    Admin_filas = bd.Admin_updateUser("contrasena", password, Admin_nick);

                    if (Admin_filas > 0) {
                        System.out.println(Colorinchis.green(Admin_filas + " filas actualizadas"));
                    } else {
                        System.out.println(Colorinchis.red("No se han actualizado filas"));
                    }
                    System.out.println("Pulsa enter para continuar");
                    sc.nextLine();

                    break;

                case 3:
                    String email;
                    boolean Admin_emailB;
                    do {
                        System.out.println(Colorinchis.red("Anota el correo nuevo: "));
                        email = sc.nextLine();

                        Admin_emailB = validaEmail(email);
                        if (!Admin_emailB) {
                            System.out.println("Email con formato incorrecto");
                        }
                    } while (!Admin_emailB);

                    Admin_filas = bd.Admin_updateUser("correo", email, Admin_nick);

                    if (Admin_filas > 0) {
                        System.out.println(Colorinchis.green(Admin_filas + " filas actualizadas"));
                    } else {
                        System.out.println(Colorinchis.red("No se han actualizado filas"));
                    }
                    System.out.println("Pulsa enter para continuar");
                    sc.nextLine();
                    break;
                case 4:
                    break;
            }
        } while (opcAdmin2 < 1 || opcAdmin2 > 4);
    }


    /*ADMIN CARTELERA*/
    public static void Admin_modificarCartelera() {
        int opcAdmin2 = esInt();

        switch (opcAdmin2) {
            case 1:
                limpiar();
                String Admin_nomPelicula,
                 Admin_idCine,
                 Admin_tipoPeli;
                int Admin_idSala = -1;
                LocalDate Admin_fecha_hora;
                boolean idExiste = false,
                 salaNum = false;
                System.out.println("Anote nombre de la película: ");
                Admin_nomPelicula = sc.nextLine();

                do {
                    System.out.println("Anote el id del cine");
                    Admin_idCine = sc.nextLine();
                    Admin_idCine = Admin_idCine.toUpperCase();
                    idExiste = bd.Admin_existeIdCine(Admin_idCine);

                    if (!idExiste) {
                        System.out.println("El cine indicado no existe");
                    }
                } while (!idExiste);

                do {
                    System.out.println("Anota id de sala:");
                    try {
                        Admin_idSala = sc.nextInt();
                        salaNum = true;
                    } catch (InputMismatchException imme) {
                        System.out.println("Introduce un número");
                    }
                } while (!salaNum);

                System.out.println("Anota fecha en formato dd/mm/yyyy: ");
                Admin_fecha_hora = leeFecha("Fecha incorrecta | Formato: 'dd/mm/yyyy'", "dd/LL/yyyy");

                System.out.println("Anota la duración de la película: ");
                int Admin_duracion = sc.nextInt();
                sc.nextLine();

                do {
                    System.out.println("Anota tipo de la película:");
                    Admin_tipoPeli = sc.nextLine();
                    if (!Admin_tipoPeli.equalsIgnoreCase("CASTELLANO") && !Admin_tipoPeli.equalsIgnoreCase("VOSE")) {
                        System.out.println("La película debe ser CASTELLANO o VOSE");
                    }
                } while (!Admin_tipoPeli.equalsIgnoreCase("CASTELLANO") && !Admin_tipoPeli.equalsIgnoreCase("VOSE"));

                Cartelera c1 = new Cartelera(Admin_nomPelicula, Admin_idCine, Admin_idSala, Admin_fecha_hora, Admin_duracion, Admin_tipoPeli);

                if (bd.Admin_insertCartelera(c1)) {
                    System.out.println(Colorinchis.green("Cartelera actualizada"));
                } else {
                    System.out.println(Colorinchis.red("No se ha podido actualizar cartelera"));
                }
                System.out.println("Pulsa enter para continuar");
                sc.nextLine();

                break;
            case 2:

                break;
            case 3:
                limpiar();
                String Admin_campo_mod,
                 Admin_newValue;
                int Admin_valor_num;
                boolean Admin_vali;

                do {
                    limpiar();
                    System.out.println("Anota el campo que deseas modificar: ");
                    Admin_campo_mod = sc.nextLine();

                    if (Admin_campo_mod.equalsIgnoreCase("nombre") || Admin_campo_mod.equalsIgnoreCase("id_cine") || Admin_campo_mod.equalsIgnoreCase("tipo")) {
                        System.out.println("Anote el nuevo valor para " + Admin_campo_mod);
                        Admin_newValue = sc.nextLine();
                        Admin_vali = true;
                    } else if (Admin_campo_mod.equalsIgnoreCase("id_sala") || Admin_campo_mod.equalsIgnoreCase("duración")) {
                        System.out.println("Anote el nuevo valor para " + Admin_campo_mod);
                        Admin_valor_num = sc.nextInt();
                        sc.nextLine();
                        Admin_vali = true;
                    } else if (Admin_campo_mod.equalsIgnoreCase("fecha_hora")) {
                        System.out.println("Anote el nuevo valor para " + Admin_campo_mod + " en formato dd/mm/aaaa");
                        Admin_fecha_hora = leeFecha("Fecha incorrecta | Formato: 'dd/mm/yyyy'", "dd/LL/yyyy");
                        Admin_vali = true;
                    } else {
                        System.out.println("Error, valor se encuentra en la tabla");
                        Admin_vali = false;
                    }
                } while (!Admin_vali);
                break;
            case 4:
                break;
        }
    }


    /*ADMIN PROMOCIONES*/
    public static void Admin_modificarPromociones() {
        int opcAdmin2 = esInt();
        String codDescuento;
        int porcentajeDescuento = 0;
        boolean correcto = false;

        sc.nextLine();
        switch (opcAdmin2) {
            case 1:
                limpiar();
                System.out.println(Colorinchis.red("Vamos a añadir un descuento: "));
                System.out.println("Anota el nombre del descuento: ");
                codDescuento = sc.nextLine();

                do {
                    try {
                        System.out.println("Cantidad de descuento del 0 al 100");
                        porcentajeDescuento = sc.nextInt();
                        sc.nextLine();
                        correcto = true;
                    } catch (InputMismatchException imm) {
                        System.out.println("Error introduce un número");
                    }
                } while (!correcto);

                Descuentos.Admin_addDescuento(codDescuento, porcentajeDescuento);
                System.out.println("Descuento añadido, pulsa enter para continuar");
                sc.nextLine();
                break;

            case 2:
                limpiar();
                System.out.println(Colorinchis.red("Anota el código del descuento que deseas borrar: "));
                codDescuento = sc.nextLine();

                if (Descuentos.Admin_deleteDescuento(codDescuento)) {
                    System.out.println("Descuento borrado con éxito, pulsa enter para continuar");
                } else {
                    System.out.println("Descuento no se ha podido borrar correctamente, pulsa enter para continuar");
                }
                sc.nextLine();
                break;

            case 3:
                limpiar();
                boolean existeDesc = false;
                boolean buenNum = false;
                String newCodDesc;
                int newPercent = -1;

                do {
                    System.out.println(Colorinchis.red("Anota el descuento que desea modificar: "));
                    codDescuento = sc.nextLine();

                    existeDesc = Descuentos.existeDescuento(codDescuento);
                } while (!existeDesc);

                System.out.println(Colorinchis.red("Anota el NUEVO nombre del descuento: "));
                newCodDesc = sc.nextLine();

                do {
                    limpiar();
                    System.out.println("Anota la NUEVA cantidad de descuento");
                    try {
                        newPercent = sc.nextInt();
                        sc.nextLine();
                        buenNum = true;
                    } catch (InputMismatchException imm) {
                        System.out.println("Error introduce un número");
                    }
                } while (!buenNum);

                Descuentos.Admin_deleteDescuento(codDescuento);
                Descuentos.Admin_addDescuento(newCodDesc, newPercent);
                break;
            case 4:
                break;
        }
    }

}
