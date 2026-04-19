/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Clases.Regiones;

/**
 *
 * @author saidc
 */
public interface RegionDao {
    void agregar (Regiones r);
    void eliminar (int id_region);
    void editar (Regiones r);
}
