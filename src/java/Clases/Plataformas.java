/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author frank
 */
public class Plataformas {
    int id_plataforma;
    String nombre_plataforma;

    public Plataformas() {
    }

    
    public Plataformas(int id_plataforma, String  nombre_plataforma) {
        this.id_plataforma = id_plataforma;
        this.nombre_plataforma = nombre_plataforma;
    }

    public int getId_plataforma() {
        return id_plataforma;
    }

    public void setId_plataforma(int id_plataforma) {
        this.id_plataforma = id_plataforma;
    }

    public String  getNombre_plataforma() {
        return nombre_plataforma;
    }

    public void setNombre_plataforma(String  nombre_plataforma) {
        this.nombre_plataforma = nombre_plataforma;
    }
    
}
