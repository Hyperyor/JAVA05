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
import javax.swing.JOptionPane;

/**
 *
 * @author hyperyor
 */
public class GestionAutores {
    
    private ResultSet rSet;
    private Statement stmt;
    
    public GestionAutores() throws Errores
    {    
        stmt = GestionBD.getSensitiveStatement();

    }
    
    public ArrayList<Autor> cargarDatosAutores(String query) throws Errores
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
            GestionErrores.escribirMensaje(ex.getMessage());
            
            throw new Errores(GestionErrores.errorDatos);
        }
        
        return listado;
    }
    
}
