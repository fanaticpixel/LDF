/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulos;

import java.time.LocalDate;

/**
 * Clase entradas
 *
 * @author Lucía Piñán Barberan 
 *         Daniel Molano Caraballo 
 *         Fernando Martín Gay 
 *         Álvaro Pérez Hernández
 *
 * @version 1.8 24/05/2022
 */
public class Entrada {
    
   private String nick;
   private String id_cine;
   private int id_sala;
   private String nombre;
   private int fila ;
   private int butaca;
   private LocalDate fecha_hora;
   private double precio;

    public Entrada(String id_cine, int id_sala, String nombre, int fila, int butaca, LocalDate fecha_hora) {
        this.id_cine = id_cine;
        this.id_sala = id_sala;
        this.nombre = nombre;
        this.fila = fila;
        this.butaca = butaca;
        this.fecha_hora = fecha_hora;
    }

   

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getId_cine() {
        return id_cine;
    }

    public void setId_cine(String id_cine) {
        this.id_cine = id_cine;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getButaca() {
        return butaca;
    }

    public void setButaca(int butaca) {
        this.butaca = butaca;
    }

    public LocalDate getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDate fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
   
   
}
