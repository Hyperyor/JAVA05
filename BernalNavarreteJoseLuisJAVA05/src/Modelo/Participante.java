/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author hyperior
 */
public class Participante {
    
    private String isbn;
    private int codigoAutor;
    private int numero;
    private float beneficio;

    public Participante(String isbn, int codigoAutor, int numero, float beneficio) {
        this.isbn = isbn;
        this.codigoAutor = codigoAutor;
        this.numero = numero;
        this.beneficio = beneficio;
    }

    public Participante() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getCodigoAutor() {
        return codigoAutor;
    }

    public void setCodigoAutor(int codigoAutor) {
        this.codigoAutor = codigoAutor;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public float getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(float beneficio) {
        this.beneficio = beneficio;
    }
    
    
    
    
}
