/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Clases.Categorias;

/**
 *
 * @author saidc
 */
public interface CategoriaDao {
    void agregar (Categorias c);
    void eliminar (int id_categoria);
    void editar (Categorias c);
}
