/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.*;

/**
 *
 * @author alumno
 */
public class GestionBD {
    
    private static Connection conectionPostgres = null;
    private static Connection conectionMySQL = null;
    
    public static boolean conectarConMySQL()
    {
        String driver = "com.mysql.jdbc.Driver";
        
        // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
        String url = "jdbc:mysql://localhost:3306/biblioteca" + "?useSSL=false";
        //"jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
        
        // Nombre de usuario
        String username = "root";

        // Clave de usuario
        String password = "pass";

        try {
            Class.forName(driver);
            conectionMySQL = DriverManager.getConnection(url, username, password);
            
            
            System.out.println("Se ha realizado la conexion con mysql");
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("\nConexion incorrecta con mysql");
            
            return false;
        }
        
        return true;
    }
    
    public static boolean conectarConPostgreSQL()
    {
        String urlDatabase =  "jdbc:postgresql://localhost:5432/biblioteca"; 
        
        try {
            Class.forName("org.postgresql.Driver");
            conectionPostgres = DriverManager.getConnection(urlDatabase,  "usuario", "pass");
            
            System.out.println("Se ha realizado la conexion con postgres ");
            
            
        } catch (Exception e) {
            System.out.println("\nConexion incorrecta con postgres");
            //System.out.println("Ocurrio un error : "+e.getMessage());
            return false;
        }
        
        //connected = true;
        return true;
    }
    
}
