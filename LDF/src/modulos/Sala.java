/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulos;

/**
 * Clase sala
 *
 * @author Lucía Piñán Barberan 
 *         Daniel Molano Caraballo 
 *         Fernando Martín Gay 
 *         Álvaro Pérez Hernández
 *
 * @version 1.8 24/05/2022
 */
public class Sala {
    
    private int layout[][];
    private String id_sala;
    private String id_cine;

    public int[][] getLayout() {
        return layout;
    }

    public void setLayout(int[][] layout) {
        this.layout = layout;
    }
/**
 * metodo que devuelve el id de sala
 * @return id de sala
 */
    public String getId_sala() {
        return id_sala;
    }
/**
 * metodo que actualiza el id de sala
 * @return id de sala actualizado
 */
    public void setId_sala(String id_sala) {
        this.id_sala = id_sala;
    }
/**
 * metodo que devuelve el id de cine
 * @return  id de cine
 */
    public String getId_cine() {
        return id_cine;
    }
/**
 * metodo que actualiza el id de cine
 * @return id de cine actualizado
 */
    public void setId_cine(String id_cine) {
        this.id_cine = id_cine;
    }
    
    
}
