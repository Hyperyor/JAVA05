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
    
    private int errorNumber;
    private String exceptionMessage;
    
    public Errores(int n)
    {
        errorNumber = n;
    }
    
    public Errores(String cad)
    {
        exceptionMessage = cad;
    }
    
    public void showMessage(int n)
    {
        
    }
    
}
