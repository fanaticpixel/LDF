/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd;

import java.sql.*;
import java.time.LocalDate;
import java.util.Vector;

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
}  
