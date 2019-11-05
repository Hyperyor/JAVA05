/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Participante;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author hyperior
 */
public class GestionParticipantes {
    
    private Statement statement;
    private ResultSet rSet;
    
    public GestionParticipantes()
    {
        statement = GestionBD.getSensitiveStatement();
    }
    
    public ArrayList<Participante> getListadoParticipantes(String query)
    {
        ArrayList<Participante> lista = new ArrayList<Participante>();
        
        Participante p = null;
        
        try
        {
            rSet = statement.executeQuery(query);
            
            while(rSet.next())
            {
                p = new Participante();
                
                p.setIsbn(rSet.getString("ISBN"));
                
                
                p.setCodigoAutor(rSet.getInt("codigo_autor"));
                
                p.setNumero(rSet.getInt("numero"));
                
                p.setBeneficio(rSet.getFloat("beneficio_autor"));
                
                
                lista.add(p);
            }
            
        }
        catch(SQLException ex)
        {
            
        }
        
        return lista;
    }
    
}
