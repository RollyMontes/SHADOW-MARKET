/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Clases.Plataformas;

/**
 *
 * @author LAB-USR-ATE
 */
public interface PlataformaDao {
    void agregar (Plataformas p);
    void eliminar (int id_plataforma);
    void editar (Plataformas p);
}
