/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoImpl;

import Clases.Regiones;
import Config.Conexion;
import Dao.RegionDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author saidc
 */
public class RegionDaoimpl implements RegionDao {
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conex;

    @Override
    public void agregar(Regiones r) {
        String SQL = "INSERT INTO regiones(nombre_region) VALUES (?)";
        try {
            conex = Conexion.getConexion();
            ps=conex.prepareStatement(SQL);
            ps.setString(1, r.getNombre_region());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    @Override
    public void eliminar(int id_region) {
        String SQL = "DELETE FROM regiones WHERE id_region=?";
        try {
            conex = Conexion.getConexion();
            ps = conex.prepareStatement(SQL);
            ps.setInt(1, id_region);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void editar(Regiones r) {
        String updateSQL = "UPDATE regiones SET nombre_region=?  WHERE id_region=?";
        try {
            conex = Conexion.getConexion();
            ps = conex.prepareStatement(updateSQL);
            ps.setString(1, r.getNombre_region());
            ps.setInt(2, r.getId_region());
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
