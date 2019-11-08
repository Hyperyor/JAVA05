
import Controlador.GestionBD;
import Modelo.ConsultasLibros;
import Modelo.Libro;
import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alumno
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
//        Connection conn = null;
//        
//        String urlDatabase =  "jdbc:postgresql://localhost:5432/biblioteca"; 
//        
//        try {
//            Class.forName("org.postgresql.Driver");
//            conn = DriverManager.getConnection(urlDatabase,  "usuario", "pass");
//        } catch (Exception e) {
//            System.out.println("Ocurrio un error : "+e.getMessage());
//            
//        }
//        System.out.println("La conexión se realizo sin problemas! =) ");
//        
//        // Librería de MySQL
////    String driver = "com.mysql.jdbc.Driver";
////
////    // Nombre de la base de datos
////    String database = "biblioteca";
////
////    // Host
////    String hostname = "localhost";
////
////    // Puerto
////    String port = "3306";
////
////    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
////    String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
////    url = "jdbc:mysql://localhost:3306/biblioteca"; 
////    // Nombre de usuario
////    String username = "root";
////
////    // Clave de usuario
////    String password = "pass";
////
////    
////        Connection conn = null;
////
////        try {
////            Class.forName(driver);
////            conn = DriverManager.getConnection(url, username, password);
////            System.out.println("La conexión se realizo sin problemas! =) ");
////        } catch (ClassNotFoundException | SQLException e) {
////            e.printStackTrace();
////            System.out.println("\nConexion incorrecta");
////        }
//        
//       Statement stmt = null;
//        
//        try
//        {
//            //ResultSet.HOLD_CURSORS_OVER_COMMIT no si se hará falta usarlo más adelante
//            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
//        }
//        catch(SQLException ex)
//        {
//            System.out.println("\nError al crear el statement");
//        }
//        
//        ResultSet rSet;
//        
//        String consulta ="select * from cuenta where usuario = ?";
//        
//        PreparedStatement prueba = conn.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//        
//        prueba.setString(1, "usuario1");
//        
//        rSet = prueba.executeQuery();
//        
//        //rSet = stmt.executeQuery("SELECT * from cuenta");
//    
//        try
//        {
//            while(rSet.next())
//            {
//                
//                System.out.println("\nTitulo: " + rSet.getString("usuario"));
//            }
//        }
//        catch(SQLException ex)
//        {
//            
//        }

//        GestionBD.connectToDataBase();
//
//        ConsultasLibros cL = new ConsultasLibros("usuario1");
//        
//        Libro l1 = cL.getFirstBook();
//        
//        System.out.println("\nISBN del libro: " + l1.getIsbn());
//        
//        System.out.println("\nEstamos viendo el primero?: " + cL.isFirstBook());
//        System.out.println("\nEstamos viendo el ultimo?: " + cL.isLastBook());
//        
//        l1 = cL.nextBook();
//        
//        System.out.println("\nISBN del libro: " + l1.getIsbn());
//        
//        System.out.println("\nEstamos viendo el primero?: " + cL.isFirstBook());
//        System.out.println("\nEstamos viendo el ultimo?: " + cL.isLastBook());
//        
//        l1 = cL.nextBook();
//        
//        System.out.println("\nISBN del libro: " + l1.getIsbn());
//        
//        System.out.println("\nEstamos viendo el primero?: " + cL.isFirstBook());
//        System.out.println("\nEstamos viendo el ultimo?: " + cL.isLastBook());
    }
    
}
