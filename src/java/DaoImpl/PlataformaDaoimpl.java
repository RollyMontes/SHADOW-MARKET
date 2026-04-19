/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoImpl;


import Clases.Plataformas;
import Config.Conexion;
import Dao.PlataformaDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author LAB-USR-ATE
 */
public class PlataformaDaoimpl implements PlataformaDao{
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conex;

    @Override
    public void agregar(Plataformas p) {
        String SQL = "INSERT INTO plataformas(nombre_plataforma) VALUES (?)";
        try {
            conex = Conexion.getConexion();
            ps=conex.prepareStatement(SQL);
            ps.setString(1, p.getNombre_plataforma());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    @Override
    public void eliminar(int id_plataforma) {
        String SQL = "DELETE FROM plataformas WHERE id_plataforma=?";
        try {
            conex = Conexion.getConexion();
            ps = conex.prepareStatement(SQL);
            ps.setInt(1, id_plataforma);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void editar(Plataformas p) {
        String updateSQL = "UPDATE plataformas SET nombre_plataforma=?  WHERE id_plataforma=?";
        try {
            conex = Conexion.getConexion();
            ps = conex.prepareStatement(updateSQL);
            ps.setString(1, p.getNombre_plataforma());
            ps.setInt(2, p.getId_plataforma());
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
