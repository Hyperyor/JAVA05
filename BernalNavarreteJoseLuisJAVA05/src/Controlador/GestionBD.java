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
    private static boolean connectedToMySQL;
    private static boolean connectedToPostgreSQL;
    private static boolean connected;
    
    private static PreparedStatement statementDeValidacionPost;
    private static PreparedStatement statementDeValidacionMys;
    
    public static void connectToDataBase()
    {
        String consulta ="select * from cuenta where usuario = ? and password = ?";
        
        conectarConMySQL();
        conectarConPostgreSQL();
        
        try
        {
            statementDeValidacionPost = conectionPostgres.prepareStatement(consulta);
            statementDeValidacionMys = conectionMySQL.prepareStatement(consulta);
        }
        catch(SQLException ex)
        {
            
        }
        
    }
    
    public static PreparedStatement getPreparedStatement(String query)
    {
        PreparedStatement stmt = null;
        try
        {
            stmt = conectionPostgres.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        }
        catch(SQLException ex)
        {
            
        }
        
        
        return stmt;
    }
    
    public static boolean validateConnection(String user, String pass)
    {
        ResultSet mys;
        ResultSet pos;
        try
        {
            statementDeValidacionPost.setString(1, user);
            statementDeValidacionPost.setString(2, pass);
            
            statementDeValidacionMys.setString(1, user);
            statementDeValidacionMys.setString(2, pass);
            
            pos = statementDeValidacionPost.executeQuery();
            mys = statementDeValidacionMys.executeQuery();
            
            if(pos.next() && mys.next())
            {
                System.out.println("\nValidacion correcta");
                return true;
            }
        }
        catch(SQLException ex)
        {
            
        }
            
        return false;
    }
    
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
            connectedToMySQL = true;
            checkConnectionState();
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
            
            connectedToPostgreSQL = true;
            checkConnectionState();
        } catch (Exception e) {
            System.out.println("\nConexion incorrecta con postgres");
            //System.out.println("Ocurrio un error : "+e.getMessage());
            return false;
        }
        
        //connected = true;
        return true;
    }
    
    private static void checkConnectionState()
    {
        if(connectedToMySQL && connectedToPostgreSQL)
        {
            connected = true;
        }
    }
    
    public static void closeConnectionToDataBase() 
    {
        try
        {
            conectionMySQL.commit();
            conectionMySQL.close();
            connectedToMySQL = false;
            
            conectionPostgres.commit();
            conectionPostgres.close();
            connectedToPostgreSQL = false;
            
            connected = false;
            
        }
        catch(SQLException e)
        {
            System.out.println("\nNo se ha podido cerrar la conexión");
        }
        
    }
    
    public static boolean getConnectionState()
    {
        return connected;
    }
    
    //metodo que crea y devuelve un statement sensitivo, que nos permite
    //desplazarnos por el ResultSet y actualizar los datos
    public static Statement getSensitiveStatement()
    {
        Statement stmt = null;
        
        try
        {
            //ResultSet.HOLD_CURSORS_OVER_COMMIT no si se hará falta usarlo más adelante
            stmt = conectionPostgres.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
        }
        catch(SQLException ex)
        {
            System.out.println("\nError al crear el statement");
        }
         
         return stmt;
    }
    
}
