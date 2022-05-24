/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulos;

import java.time.LocalDate;

/**
 * Clase pelicula
 *
 * @author Lucía Piñán Barberan 
 *         Daniel Molano Caraballo 
 *         Fernando Martín Gay 
 *         Álvaro Pérez Hernández
 *
 * @version 1.8 24/05/2022
 */
public class Pelicula {
    private String nombre;
    private String director;
    private String generos;
    private int duracion;
    private int clasificacion;
    private String productora;
    private String descripcion;
    private LocalDate fecha_estreno;
/**
 * metodo que retorna el nombre de la pelicula
 * @return el nombre de la pelicula
 */
    public String getNombre() {
        return nombre;
    }
/**
 * metodo que actualiza el nombre de la pelicula
 * @return el nombre de la pelicula actualizado
 */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
/**
 * metodo que retorna el director de la pelicula
 * @return el director de la pelicula
 */
    public String getDirector() {
        return director;
    }
/**
 * metodo que actualiza el director de la pelicula
 * @return el director de la pelicula actualizado
 */
    public void setDirector(String director) {
        this.director = director;
    }
/**
 * metodo que retorna el genero de la pelicula
 * @return el genero de la pelicula
 */
    public String getGeneros() {
        return generos;
    }
/**
 * metodo que actualiza el genero de la pelicula
 * @return el genero de la pelicula actualizado
 */
    public void setGeneros(String generos) {
        this.generos = generos;
    }
/**
 * metodo que retorna la duracion de la pelicula
 * @return la duracion de la pelicula
 */
    public int getDuracion() {
        return duracion;
    }
/**
 * metodo que actualiza la duracion de la pelicula
 * @return la duracion la pelicula actualizado
 */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
/**
 * metodo que retorna la clasificacion de la pelicula
 * @return la clasificacion de la pelicula
 */
    public int getClasificacion() {
        return clasificacion;
    }
/**
 * metodo que actualiza la clasificacion de la pelicula
 * @return la clasificacion la pelicula actualizado
 */
    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }
/**
 * metodo que retorna la productora de la pelicula
 * @return la productora de la pelicula
 */
    public String getProductora() {
        return productora;
    }
/**
 * metodo que actualiza la productora de la pelicula
 * @return la productora la pelicula actualizado
 */
    public void setProductora(String productora) {
        this.productora = productora;
    }
/**
 * metodo que retorna la descripcion de la pelicula
 * @return la descripcion de la pelicula
 */
    public String getDescripcion() {
        return descripcion;
    }
/**
 * metodo que actualiza la descripcion de la pelicula
 * @return la descripcion la pelicula actualizado
 */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
/**
 * metodo que retorna la fecha de estrono de la pelicula
 * @return la fecha de estrono de la pelicula
 */
    public LocalDate getFecha_estreno() {
        return fecha_estreno;
    }
/**
 * metodo que actualiza la fecha de estrono de la pelicula
 * @return la fecha de estrono la pelicula actualizado
 */
    public void setFecha_estreno(LocalDate fecha_estreno) {
        this.fecha_estreno = fecha_estreno;
    }
    
    
}
