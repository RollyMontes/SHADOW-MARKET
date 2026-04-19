/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoImpl;

import Clases.Categorias;
import Config.Conexion;
import Dao.CategoriaDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author saidc
 */
public class CategoriaDaoimpl implements CategoriaDao {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conex;

    @Override
    public void agregar(Categorias c) {
        String SQL = "INSERT INTO categorias(nombre_categoria) VALUES (?)";
        try {
            conex = Conexion.getConexion();
            ps=conex.prepareStatement(SQL);
            ps.setString(1, c.getNombre_categoria());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    @Override
    public void eliminar(int id_categoria) {
        String SQL = "DELETE FROM categorias WHERE id_categoria=?";
        try {
            conex = Conexion.getConexion();
            ps = conex.prepareStatement(SQL);
            ps.setInt(1, id_categoria);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void editar(Categorias c) {
        String updateSQL = "UPDATE categorias SET nombre_categoria=?  WHERE id_categoria=?";
        try {
            conex = Conexion.getConexion();
            ps = conex.prepareStatement(updateSQL);
            ps.setString(1, c.getNombre_categoria());
            ps.setInt(2, c.getId_categoria());
            ps.executeUpdate();
        } catch (SQLException e) {
            try {
                if (conex != null) {
                    conex.rollback(); // Revertir transacción en caso de error
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println(e.toString());
        }
    }

}
