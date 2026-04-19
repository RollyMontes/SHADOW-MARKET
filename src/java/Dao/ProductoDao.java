/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Clases.Productos;

/**
 *
 * @author saidc
 */
public interface ProductoDao {
    void agregar (Productos p);
    void eliminar (int id_producto);
    void editar (Productos p);
}
