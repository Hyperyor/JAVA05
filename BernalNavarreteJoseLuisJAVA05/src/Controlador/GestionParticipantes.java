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
    
    private PreparedStatement statement;
    private ResultSet rSet;
    
    public GestionParticipantes()
    {
        
    }
    
    public void prepararStamenet(String query) throws Errores
    {
        statement = GestionBD.getPreparedStatement(query);
    }
    
    public int insertarElemento(Participante p) throws Errores
    {
        try
        {
            statement.setString(1, p.getIsbn());
            
            statement.setInt(2, p.getCodigoAutor());
            
            statement.setInt(3, p.getNumero());
            
            statement.setFloat(4, p.getBeneficio());
            
            //System.out.println("\nTodo ok");
            
            
            statement.execute();
 
            return 1;
            
        }
        catch(SQLException ex)
        {
            GestionErrores.escribirMensaje(ex.getMessage());
            
            throw new Errores(GestionErrores.errorInsercion);
//            System.out.println("\n" + ex.getMessage());
//            return 0;
        }
    }
    
    public ArrayList<Participante> getListadoParticipantes(String isbn) throws Errores
    {
        ArrayList<Participante> lista = new ArrayList<Participante>();
        
        Participante p = null;
        
        try
        {
            statement.setString(1, isbn);
            
            rSet = statement.executeQuery();
            
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
            GestionErrores.escribirMensaje(ex.getMessage());
            
            throw new Errores(GestionErrores.errorDatos);
        }
        
        return lista;
    }
    
}
