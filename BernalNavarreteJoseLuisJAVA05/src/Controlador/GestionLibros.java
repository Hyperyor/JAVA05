/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Libro;
import java.sql.*;
import java.util.GregorianCalendar;

/**
 *
 * @author hyperior
 */
public class GestionLibros {
    
    private PreparedStatement libroStatement;
    
    private ResultSet rSet;
    
    private boolean queryExecuted;
    
    public GestionLibros(String query)
    {
        //preparamos el prepared statement
        libroStatement = GestionBD.getPreparedStatement(query);
        queryExecuted = false;
    }
    
    public Libro getFirstBook(String usuario)
    {
        Libro l = null;
        
        if(!queryExecuted)
        {
            //ejecutar query
            l = executeQuery(usuario);
            //coger el primero
        }
        else
        {
            //simplemente coger el primero
            l = firstBook();
        }
        
        return l;
    }
    
    private Libro firstBook()
    {
        Libro l = new Libro();
        
        try
        {

            
            if(rSet.first())
            {
                l = getData();
            }
            
        }
        catch(SQLException ex)
        {
            
        }
        
        return l;
    }
    
    public Libro executeQuery(String usuario)
    {
        Libro l = new Libro();
        
        try
        {
            libroStatement.setString(1, usuario);
        
            rSet = libroStatement.executeQuery();
            
            l = firstBook();
            
            queryExecuted = true;
        }
        catch(SQLException ex)
        {
            
        }
        
        return l;
        
    }
    
    private Libro getData()
    {
        Libro l = new Libro();
        
        try
        {
            l.setIsbn(rSet.getString("ISBN"));
            l.setTitulo(rSet.getString("titulo"));
            l.setPortada(rSet.getString("portada"));
            l.setPropietario(rSet.getString("propietario"));
            l.setPrecio(rSet.getFloat("precio"));
            l.setNifPrincAutor(rSet.getString("nif_autor_principal"));

            Date d = rSet.getDate("fecha_publicacion");

            GregorianCalendar fech = new GregorianCalendar();

            fech.setTime(d);

            l.setFechaPublicacion(fech);
        }
        catch(SQLException ex)
        {
            
        }
        
        return l;
        
    }
    
    public Libro getNextBook()
    {
        Libro l = new Libro();
        
        try
        {
            if(rSet.next())
            {
                l = getData();
            }
            
        }
        catch(SQLException ex)
        {
            
        }
        
        return l;
    }
    
    
    public Libro getPreviousBook()
    {
        Libro l = new Libro();
        
        try
        {
            if(rSet.previous())
            {
                l = getData();
            }
            
        }
        catch(SQLException ex)
        {
            
        }
        
        return l;
    }
    
    public boolean isFirstBook()
    {
        Libro l = new Libro();
        
        try
        {
            if(rSet.isFirst())
            {
                return true;
            }
            
        }
        catch(SQLException ex)
        {
            
        }
        
        return false;
    }
    
    public boolean isLastBook()
    {
        Libro l = new Libro();
        
        try
        {
            if(rSet.isLast())
            {
                return true;
            }
            
        }
        catch(SQLException ex)
        {
            
        }
        
        return false;
    }
    
    public boolean queryState()
    {
        return queryExecuted;
    }
    
}
