/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Autor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author hyperyor
 */
public class GestionAutores {
    
    private ResultSet rSet;
    private Statement stmt;
    
    public GestionAutores()
    {
        stmt = GestionBD.getSensitiveStatement();
    }
    
    public ArrayList<Autor> cargarDatosAutores(String query)
    {
        ArrayList<Autor> listado = new ArrayList<Autor>();
        
        try
        {
            rSet = stmt.executeQuery(query);
            
            while(rSet.next())
            {
                Autor aut = new Autor();
                
                aut.setCodigo(rSet.getInt("codigo"));
                aut.setPorcentajeBeneficio(rSet.getFloat("porcentaje_beneficio"));
                
                listado.add(aut);
                
            }
            
        }
        catch(SQLException ex)
        {
            
        }
        
        return listado;
    }
    
}
