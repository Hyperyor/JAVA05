/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Errores;
import Controlador.GestionAutores;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author hyperyor
 */
public class ConsultasAutor {
    
    private String obtenerAutores = "Select * from autor;";
    
    private ArrayList<Autor> listadoAutores;
    
    private GestionAutores gestionAut;
    
    public ConsultasAutor() throws Errores
    {
        listadoAutores = new ArrayList<Autor>();
        gestionAut = new GestionAutores();
    }
    
    public ArrayList<Autor> getListadoAutores() throws Errores
    {
        
        listadoAutores = gestionAut.cargarDatosAutores(obtenerAutores);

        return listadoAutores;
    }
    
}
