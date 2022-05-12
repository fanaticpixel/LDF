/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd;

import java.sql.*;
import java.time.LocalDate;
import java.util.Vector;
import modulos.*;

/**
 *
 * @author Alvaro.p
 */
public class BD_LDF extends BD_Conector {

    private static Statement s;
    private static PreparedStatement ps;
    private static ResultSet reg;

    public BD_LDF(String file) {
        super(file);
    }

    /**
     * @author AlvaroPerez
     *
     *
     * @return
     */
    public Vector<Cartelera> listarCartelera() {

        String cadenaSQL = "SELECT * FROM CARTELERA";
        Vector<Cartelera> v = new Vector<Cartelera>();

        try {

            this.abrir();
            s = c.createStatement();
            reg = s.executeQuery(cadenaSQL);

            while (reg.next()) {
                java.sql.Date f = reg.getDate("fecha_hora");
                LocalDate fBuena = f.toLocalDate();
                v.add(new Cartelera(reg.getString("nombre"), reg.getString("id_cine"), reg.getInt("id_sala"), fBuena, reg.getInt("duracion"), reg.getString("tipo")));
            }

            this.cerrar();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return v;

    }

    /**
     * @author AlvaroPerez
     *
     * @param tabla
     * @param campo
     *
     * @return
     */
    public Vector<Cartelera> listarCarteleraFiltrada(String dato, String campo) {

        String data = "%" + dato + "%";
        String cadenaSQL = "SELECT * FROM CARTELERA WHERE " + campo + " LIKE  ?";
        Vector<Cartelera> v = new Vector<Cartelera>();

        try {

            this.abrir();
            ps = c.prepareStatement(cadenaSQL);

            ps.setString(1, data);

            reg = ps.executeQuery();

            while (reg.next()) {
                java.sql.Date f = reg.getDate("fecha_hora");
                LocalDate fBuena = f.toLocalDate();
                v.add(new Cartelera(reg.getString("nombre"), reg.getString("id_cine"), reg.getInt("id_sala"), fBuena, reg.getInt("duracion"), reg.getString("tipo")));
            }

            this.cerrar();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return v;

    }

    /**
     * @author Lucía Piñán Barberán y Daniel Molano Caraballo
     *
     * @param tabla
     * @param campo
     *
     * @return
     */
    public Vector<String> listarCampoTablaString(String tabla, String campo) {

        String cadenaSQL = "SELECT " + campo + " FROM " + tabla;
        Vector<String> v = new Vector<String>();

        try {

            this.abrir();
            ps = c.prepareStatement(cadenaSQL);

            reg = ps.executeQuery();

            while (reg.next()) {
                v.add(reg.getString(1));
            }

            this.cerrar();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return v;

    }

    /**
     * @author Lucía Piñán Barberán y Daniel Molano Caraballo
     *
     * @param tabla
     * @param campo
     *
     * @return
     */
    public Vector<Date> listarCampoTablaFecha(String tabla, String campo) {

        String cadenaSQL = "SELECT ? FROM ?";
        Vector<Date> v = new Vector<Date>();

        try {

            this.abrir();
            ps = c.prepareStatement(cadenaSQL);

            ps.setString(1, campo);
            ps.setString(2, tabla);

            reg = ps.executeQuery();

            while (reg.next()) {
                v.add(reg.getDate(1));
            }

            this.cerrar();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return v;

    }

    /**
     * @author Lucía Piñán Barberán y Daniel Molano Caraballo
     *
     * @param tabla
     * @param campo
     *
     * @return
     */
    public Vector<Integer> listarCampoTablaInt(String tabla, String campo) {

        String cadenaSQL = "SELECT ? FROM ?";
        Vector<Integer> v = new Vector<Integer>();

        try {

            this.abrir();
            ps = c.prepareStatement(cadenaSQL);

            ps.setString(1, campo);
            ps.setString(2, tabla);

            reg = ps.executeQuery();

            while (reg.next()) {
                v.add(reg.getInt(1));
            }

            this.cerrar();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return v;

    }

    public String listarPassword(String nick) {

        String dato = "'%" + nick + "%'";
        String cadenaSQL = "SELECT contrasena FROM USUARIOS WHERE nick like " + dato;
        String v = "";

        try {

            this.abrir();
            s = c.createStatement();
            reg = s.executeQuery(cadenaSQL);

            
            
            while (reg.next()) {
                v = reg.getString(1);
            }

            this.cerrar();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return v;

    }
    
    public void listarUser(String nick) {

        String dato = "'%" + nick + "%'";
        String cadenaSQL = "SELECT * FROM USUARIOS WHERE nick like " + dato;

        try {

            this.abrir();
            s = c.createStatement();
            reg = s.executeQuery(cadenaSQL);

            
            
            while (reg.next()) {
                java.sql.Date f = reg.getDate("fecha_nacimiento");
                LocalDate fBuena = f.toLocalDate();
                
                 System.out.println("NICK: " + reg.getString("nick") + "       NOMBRE: " + reg.getString("nombre") + 
                         "       APELLIDOS: " + reg.getString("apellidos") +  "       CORREO: " + reg.getString("correo") + 
                         "       FECHA DE NACIMIENTO: " + fBuena + "        PREMIUM: "+reg.getBoolean("premium") + "\n");
            }

            this.cerrar();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void listarCines() {

        String cadenaSQL = "SELECT id_cine, nombre, direccion FROM CINES";

        try {

            this.abrir();
            s = c.createStatement();
            reg = s.executeQuery(cadenaSQL);

            while (reg.next()) {

                System.out.println("ID_CINE: " + reg.getString("id_cine") + "       NOMBRE: " + reg.getString("nombre") 
                        + "       DIRECCION: " + reg.getString("direccion") + "\n");

            }

            this.cerrar();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public int añadirUsuario(Usuario u) {

        String cadenaSQL = "INSERT INTO USUARIOS VALUES('" + u.getNick() + "','"
                + u.getNombre() + "','" + u.getApellidos() + "','" + u.getCorreo()
                + "','" + u.getContraseña() + "','" + u.getFecha_nac() + "')";

        try {

            this.abrir();
            s = c.createStatement();
            int filas=s.executeUpdate(cadenaSQL);
            s.close();
            this.cerrar();
            return filas;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return -1;
    }
    
    public int cambiarNick(String newNick, String oldNick) {

        String cadenaSQL = "UPDATE USUARIOS SET NICK = '" + newNick +"' WHERE NICK = '" + oldNick + "'";

        try {

            this.abrir();
            s = c.createStatement();
            int filas=s.executeUpdate(cadenaSQL);
            s.close();
            this.cerrar();
            return filas;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return -1;
        
    }

}
