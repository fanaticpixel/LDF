/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldf;

import java.util.Vector;
import mdulos.Cine;
import mdulos.Pelicula;
import mdulos.Usuario;

/**
 *
 * @author administrador
 */
public class Ldf {
    private Vector<Cine> cines;
    private Vector<Usuario> Usuario;
    private Vector<Pelicula> Pelicula;

    public Vector<Cine> getCines() {
        return cines;
    }

    public void setCines(Vector<Cine> cines) {
        this.cines = cines;
    }

    public Vector<Usuario> getUsuario() {
        return Usuario;
    }

    public void setUsuario(Vector<Usuario> Usuario) {
        this.Usuario = Usuario;
    }

    public Vector<Pelicula> getPelicula() {
        return Pelicula;
    }

    public void setPelicula(Vector<Pelicula> Pelicula) {
        this.Pelicula = Pelicula;
    }
    
    
}
