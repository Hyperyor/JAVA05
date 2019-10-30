/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.GregorianCalendar;

/**
 *
 * @author hyperior
 */
public class Libro {
    
    private String isbn;
    private String titulo;
    private String nifPrincAutor;
    private GregorianCalendar fechaPublicacion;
    private String portada;
    private float precio;
    private String propietario;
    
    public Libro()
    {
    }

    public Libro(String isbn, String titulo, String nifPrincAutor, GregorianCalendar fechaPublicacion, String portada, float precio, String propietario) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.nifPrincAutor = nifPrincAutor;
        this.fechaPublicacion = fechaPublicacion;
        this.portada = portada;
        this.precio = precio;
        this.propietario = propietario;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNifPrincAutor() {
        return nifPrincAutor;
    }

    public void setNifPrincAutor(String nifPrincAutor) {
        this.nifPrincAutor = nifPrincAutor;
    }

    public GregorianCalendar getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(GregorianCalendar fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
    
    
    
}
