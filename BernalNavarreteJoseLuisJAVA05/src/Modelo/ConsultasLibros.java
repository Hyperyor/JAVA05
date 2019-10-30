/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.GestionLibros;

/**
 *
 * @author hyperior
 */
public class ConsultasLibros {
    
    private String obtenerLibrosDeUsuario = "Select * from libro where propietario = ?";
    
    private GestionLibros gestionLibs;
    
    private Libro libroActual = null;
    
    private String usuarioActual = null;
    
    public ConsultasLibros(String user)
    {
        gestionLibs = new GestionLibros(obtenerLibrosDeUsuario);
        usuarioActual = user;
    }
    
    public Libro getFirstBook()
    {

        libroActual = gestionLibs.getFirstBook(usuarioActual);

        
        return libroActual;
    }
    
    public boolean isFirstBook()
    {
        return gestionLibs.isFirstBook();
    }
    
    public boolean isLastBook()
    {
        return gestionLibs.isLastBook();
    }
    
    public Libro nextBook()
    {

        libroActual = gestionLibs.getNextBook();
        
        return libroActual;
    }
    
    public Libro previousBook()
    {

        libroActual = gestionLibs.getPreviousBook();
        
        return libroActual;
    }
    
}
