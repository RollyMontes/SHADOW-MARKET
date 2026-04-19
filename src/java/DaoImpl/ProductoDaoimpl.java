/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoImpl;

import Clases.Productos;
import Config.Conexion;
import Dao.ProductoDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author saidc
 */
public class ProductoDaoimpl implements ProductoDao {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conex;

    @Override
    public void agregar(Productos p) {
        String SQL = "INSERT INTO productos (nombre_producto, id_categoria, id_genero, precio_producto, "
                + "num_estrellas_producto, logo_producto, link_trailer_producto, img_promo1_producto, "
                + "img_promo2_producto, img_promo3_producto, img_promo4_producto, numlikes_producto, "
                + "id_plataforma, id_region) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conex = Conexion.getConexion();
            ps = conex.prepareStatement(SQL);
            ps.setString(1, p.getNombre_producto());
            ps.setInt(2, p.getId_categoria());
            ps.setInt(3, p.getId_genero());
            ps.setDouble(4, p.getPrecio_producto());
            ps.setInt(5, p.getNum_estrellas_producto());
            ps.setString(6, p.getLogo_producto());
            ps.setString(7, p.getLink_trailer_producto());
            ps.setString(8, p.getImg_promo1_producto());
            ps.setString(9, p.getImg_promo2_producto());
            ps.setString(10, p.getImg_promo3_producto());
            ps.setString(11, p.getImg_promo4_producto());
            ps.setInt(12, p.getNumlikes_producto());
            ps.setInt(13, p.getId_plataforma());
            ps.setInt(14, p.getId_region());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println(e.toString());
        }
    }

    @Override
    public void eliminar(int id_producto) {

        String SQL = "DELETE FROM productos WHERE id_producto=?";
        try {
            conex = Conexion.getConexion();
            ps = conex.prepareStatement(SQL);
            ps.setInt(1, id_producto);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

   @Override
public void editar(Productos p) {
    String updateSQL = "UPDATE productos SET nombre_producto = ?, id_categoria = ?, id_genero = ?, precio_producto = ?, logo_producto = ?, link_trailer_producto = ?, img_promo1_producto = ?, img_promo2_producto = ?, img_promo3_producto = ?, img_promo4_producto = ?, id_plataforma = ?, id_region = ? WHERE id_producto = ?";
    try {
        conex = Conexion.getConexion();
        ps = conex.prepareStatement(updateSQL);
        ps.setString(1, p.getNombre_producto());
        ps.setInt(2, p.getId_categoria());
        ps.setInt(3, p.getId_genero());
        ps.setDouble(4, p.getPrecio_producto());
        ps.setString(5, p.getLogo_producto());
        ps.setString(6, p.getLink_trailer_producto());
        ps.setString(7, p.getImg_promo1_producto());
        ps.setString(8, p.getImg_promo2_producto());
        ps.setString(9, p.getImg_promo3_producto());
        ps.setString(10, p.getImg_promo4_producto());
        ps.setInt(11, p.getId_plataforma());
        ps.setInt(12, p.getId_region());
        ps.setInt(13, p.getId_producto());
        ps.executeUpdate();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        System.out.println(e.toString());
    }
}

}
