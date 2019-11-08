/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Errores;
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
    
    public ConsultasLibros(String user) throws Errores
    {
        gestionLibs = new GestionLibros(obtenerLibrosDeUsuario);
        usuarioActual = user;
    }
    
    public int updateBook(Libro l) throws Errores
    {
        int n = gestionLibs.updateBook(l);
        
        return n;
    }
    
    public Libro getFirstBook() throws Errores
    {

        libroActual = gestionLibs.getFirstBook(usuarioActual);

        
        return libroActual;
    }
    
    public boolean isFirstBook() throws Errores
    {
        return gestionLibs.isFirstBook();
    }
    
    public boolean isLastBook() throws Errores
    {
        return gestionLibs.isLastBook();
    }
    
    public Libro nextBook() throws Errores
    {

        libroActual = gestionLibs.getNextBook();
        
        return libroActual;
    }
    
    public Libro previousBook() throws Errores
    {

        libroActual = gestionLibs.getPreviousBook();
        
        return libroActual;
    }
    
    public int insertBook(Libro l) throws Errores
    {
        return gestionLibs.insertarLibro(l);
    }
    
}
