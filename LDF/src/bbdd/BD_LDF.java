/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd;

import java.sql.*;
import java.time.LocalDate;
import java.util.Vector;
import modulos.Cartelera;

/**
 *
 * @author Alvaro.p
 */
public class BD_LDF extends BD_Conector{
    
    private static Statement s;
    private static PreparedStatement ps;
    private static ResultSet reg;
    
    public BD_LDF(String file){
		super (file);
	}
        /**
     * @author AlvaroPerez
     *     
     * 
     * @return 
     */
    public Vector<Cartelera> listarCartelera(){
    
        String cadenaSQL="SELECT * FROM CARTELERA";
        Vector<Cartelera> v = new Vector<Cartelera>();
        
        try{
        
            this.abrir();
            s = c.createStatement();
            reg = s.executeQuery(cadenaSQL);
            
            while(reg.next()){
                 java.sql.Date f = reg.getDate("fecha_hora");
                 LocalDate fBuena = f.toLocalDate();
                  v.add(new Cartelera(reg.getString("nombre"),reg.getString("id_cine"),reg.getInt("id_sala") ,fBuena, reg.getInt("duracion"),reg.getString("tipo")));
            }
            
            this.cerrar();
            
        }catch(SQLException e){
            
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
    public Vector<Cartelera> listarCarteleraFiltrada(String dato, String campo){
    
        String data = "%" + dato + "%";
        String cadenaSQL="SELECT * FROM CARTELERA WHERE " + campo + " LIKE  ?";
        Vector<Cartelera> v = new Vector<Cartelera>();
        
        try{
        
            this.abrir();
            ps=c.prepareStatement(cadenaSQL);
            
            ps.setString(1, data);
            
            reg = ps.executeQuery();
            
            while(reg.next()){
                java.sql.Date f = reg.getDate("fecha_hora");
                 LocalDate fBuena = f.toLocalDate();
                v.add(new Cartelera(reg.getString("nombre"),reg.getString("id_cine"),reg.getInt("id_sala") ,fBuena, reg.getInt("duracion"),reg.getString("tipo")));
            }
            
            this.cerrar();
            
        }catch(SQLException e){
            
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
    public Vector<String> listarCampoTablaString(String tabla, String campo){
    
        String cadenaSQL="SELECT ? FROM ?";
        Vector<String> v = new Vector<String>();
        
        try{
        
            this.abrir();
            ps=c.prepareStatement(cadenaSQL);
            
            ps.setString(1, campo);
            ps.setString(2, tabla);
            
            reg = ps.executeQuery();
            
            while(reg.next()){
                v.add(reg.getString(1));
            }
            
            this.cerrar();
            
        }catch(SQLException e){
            
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
    public Vector<Date> listarCampoTablaFecha(String tabla, String campo){
    
        String cadenaSQL="SELECT ? FROM ?";
        Vector<Date> v = new Vector<Date>();
        
        try{
        
            this.abrir();
            ps=c.prepareStatement(cadenaSQL);
            
            ps.setString(1, campo);
            ps.setString(2, tabla);
            
            reg = ps.executeQuery();
            
            while(reg.next()){
                v.add(reg.getDate(1));
            }
            
            this.cerrar();
            
        }catch(SQLException e){
            
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
    public Vector<Integer> listarCampoTablaInt(String tabla, String campo){
    
        String cadenaSQL="SELECT ? FROM ?";
        Vector<Integer> v = new Vector<Integer>();
        
        try{
        
            this.abrir();
            ps=c.prepareStatement(cadenaSQL);
            
            ps.setString(1, campo);
            ps.setString(2, tabla);
            
            reg = ps.executeQuery();
            
            while(reg.next()){
                v.add(reg.getInt(1));
            }
             
            this.cerrar();
            
        }catch(SQLException e){
            
            e.printStackTrace();          
        }
        
        return v;
        
    }
    
    public String listarPassword(String nick){
    
        String cadenaSQL="SELECT contraseña FROM USUARIOS WHERE nick = " + nick;
        String v = "";
        
        try{
        
            this.abrir();
            s=c.createStatement();
            reg = s.executeQuery(cadenaSQL);
            
            while(reg.next()){
               v = reg.getString(1);
            }
            
            this.cerrar();
            
        }catch(SQLException e){
            
            e.printStackTrace();          
        }
        
        return v;
        
    }
    
    
    public void listarCines(){
    
        String cadenaSQL="SELECT id_cine, nombre, direccion FROM CINES";
        
        try{
        
            this.abrir();
            s = c.createStatement();
            reg = s.executeQuery(cadenaSQL);
            
            while(reg.next()){

                System.out.println("ID_CINE: " + reg.getString("id_cine") + " NOMBRE: " + reg.getString("nombre") + " DIRECCION: " + reg.getString("direccion"));
                
            }
            
            this.cerrar();
            
        }catch(SQLException e){
            
            e.printStackTrace();          
        }
        
    }
}  
