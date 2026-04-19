/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Clases.Generos;

/**
 *
 * @author LAB-USR-ATE
 */
public interface GeneroDao {
     void agregar (Generos g);
    void eliminar (int id_genero);
    void editar (Generos g);
}
