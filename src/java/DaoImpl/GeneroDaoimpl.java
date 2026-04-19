/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoImpl;


import Clases.Generos;
import Config.Conexion;
import Dao.GeneroDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author LAB-USR-ATE
 */
public class GeneroDaoimpl implements GeneroDao{
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conex;

    @Override
    public void agregar(Generos g) {
        String SQL = "INSERT INTO generos(nombre_genero) VALUES (?)";
        try {
            conex = Conexion.getConexion();
            ps=conex.prepareStatement(SQL);
            ps.setString(1, g.getNombre_genero());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    @Override
    public void eliminar(int id_genero) {
        String SQL = "DELETE FROM generos WHERE id_genero=?";
        try {
            conex = Conexion.getConexion();
            ps = conex.prepareStatement(SQL);
            ps.setInt(1, id_genero);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void editar(Generos g) {
        String updateSQL = "UPDATE generos SET nombre_genero=?  WHERE id_genero=?";
        try {
            conex = Conexion.getConexion();
            ps = conex.prepareStatement(updateSQL);
            ps.setString(1, g.getNombre_genero());
            ps.setInt(2, g.getId_genero());
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
