/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author hyperyor
 */
public class GestionErrores {

    public final static int errorBD = 1;
    
    public final static int errorFechaPosterior = 2;
    
    public final static int errorDNIIncorrecto = 3;
    
    public final static int errorActualizacion = 4;
    
    public final static int errorCopiaImagen = 5;
    
    public final static int errorDatos = 6;
    
    public final static int errorValidacion = 7;
    
    public final static int errorInsercion = 8;
    
    public final static int errorDatosInsertados = 9;
    
    public final static String getMensajeError(int n)
    {
        String cadena = "";
        
        switch(n)
        {
            case 1: cadena = "Error en las Bases de Datos";
                break;
            case 2: cadena = "Fecha posterior a la actual";
                break; 
            case 3: cadena = "DNI Incorrecto";
                break;
            case 4: cadena = "Error en la actualizacion";
                break;
            case 5: cadena = "Error al seleccionar imagen";
                break;
            case 6: cadena = "Error al cargar datos de BD";
                break;
            case 7: cadena = "Usuario o contrasenia incorrectos";
                break;
            case 8: cadena = "Error al insertar los datos";
                break;
            case 9: cadena = "Datos incorrectos";
                break;
        }
        
        return cadena;
    }
    
    public static void escribirMensaje(String mensaje)
    {
        FileWriter writer;
        BufferedWriter bw = null;
        
        Date d = new Date();
        
        String mensajeCompleto = d.toString() + " " + mensaje;
        
        try 
        {
            writer = new FileWriter("app.log", true);
        
	    bw  = new BufferedWriter(writer);

            System.out.println("\n" + mensajeCompleto);
            
	    bw.write(mensajeCompleto);
            bw.newLine();
            
            bw.close();

	} 
        catch (IOException e) 
        {
            System.err.format("Error en la escritura del fichero");
	}
 
    }
    
}
