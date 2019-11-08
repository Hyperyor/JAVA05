/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author hyperyor
 */
public class Errores extends Exception{
    
    private int error;
    
    public Errores(int n)
    {
        error = n;
    }
    
    public String showMessage()
    {
        return GestionErrores.getMensajeError(error);
    }
    
}
