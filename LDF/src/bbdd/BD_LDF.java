/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author administrador
 */
public class BD_LDF extends BD_Conector{
    
    private static Statement s;	
    private static ResultSet reg;
    
    public BD_LDF(String file){
		super (file);
	}
}
