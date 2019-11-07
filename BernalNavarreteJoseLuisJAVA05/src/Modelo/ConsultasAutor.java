/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.GestionAutores;
import java.util.ArrayList;

/**
 *
 * @author hyperyor
 */
public class ConsultasAutor {
    
    private String obtenerAutores = "Select * from autor;";
    
    private ArrayList<Autor> listadoAutores;
    
    private GestionAutores gestionAut;
    
    public ConsultasAutor()
    {
        listadoAutores = new ArrayList<Autor>();
        gestionAut = new GestionAutores();
    }
    
    public ArrayList<Autor> getListadoAutores()
    {
        listadoAutores = gestionAut.cargarDatosAutores(obtenerAutores);
        return listadoAutores;
    }
    
}
