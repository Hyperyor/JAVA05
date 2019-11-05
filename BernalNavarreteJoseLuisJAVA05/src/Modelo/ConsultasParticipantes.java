/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.GestionParticipantes;
import java.util.ArrayList;

/**
 *
 * @author hyperior
 */
public class ConsultasParticipantes {
    
    private String obtenerParticipantesLibro = "Select * from escribe where ISBN = ";
    
    private GestionParticipantes gestPartic;
    
    private ArrayList<Participante> listadoParticipantes;
    
    public ConsultasParticipantes()
    {
        gestPartic = new GestionParticipantes();
        listadoParticipantes = new ArrayList<Participante>();
    }
    
    public ArrayList<Participante> getListado(Libro l)
    {
        listadoParticipantes = gestPartic.getListadoParticipantes(obtenerParticipantesLibro + "'"+l.getIsbn()+"'" + ";");
        
        return listadoParticipantes;
    }
    
}
