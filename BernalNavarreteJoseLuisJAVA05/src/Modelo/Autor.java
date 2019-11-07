/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author hyperyor
 */
public class Autor {
    
    private int codigo;
    private float porcentajeBeneficio;

    public Autor(int codigo, float porcentajeBeneficio) {
        this.codigo = codigo;
        this.porcentajeBeneficio = porcentajeBeneficio;
    }

    public Autor() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public float getPorcentajeBeneficio() {
        return porcentajeBeneficio;
    }

    public void setPorcentajeBeneficio(float porcentajeBeneficio) {
        this.porcentajeBeneficio = porcentajeBeneficio;
    }
    
    public String toString()
    {
        return "Codigo Autor: " + codigo + " Porcentaje de beneficio: " + porcentajeBeneficio; 
    }
    
}
