/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DaoImpl;

import Clases.Carrito;
import Config.Conexion;
import Dao.CarritoDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author saidc
 */
public class CarritoDaoimpl implements CarritoDao {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conex;

    @Override
    public void agregar(Carrito c) {
        String selectSQL = "SELECT cantidad, precio_unitario FROM carrito WHERE nom_producto = ?";
        String updateSQL = "UPDATE carrito SET cantidad = ?, total = ? WHERE nom_producto = ?";
        String insertSQL = "INSERT INTO carrito(id_cuenta, nom_producto, cantidad, logo, precio_unitario, total) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            conex = Conexion.getConexion();

            // Step 1: Check if the product already exists in the cart
            ps = conex.prepareStatement(selectSQL);
            ps.setString(1, c.getNom_producto());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Step 2: If product exists, update the quantity and total price
                int existingQuantity = rs.getInt("cantidad");
                double precioUnitario = rs.getDouble("precio_unitario");
                int newQuantity = existingQuantity + c.getCantidad();
                double newTotal = precioUnitario * newQuantity;

                ps = conex.prepareStatement(updateSQL);
                ps.setInt(1, newQuantity);
                ps.setDouble(2, newTotal);
                ps.setString(3, c.getNom_producto());
                ps.executeUpdate();
            } else {
                // Step 3: If product does not exist, insert a new record
                ps = conex.prepareStatement(insertSQL);
                ps.setInt(1, c.getId_cuenta());
                ps.setString(2, c.getNom_producto());
                ps.setInt(3, c.getCantidad());
                ps.setString(4, c.getLogo());
                ps.setDouble(5, c.getPrecio_unitario());
                ps.setDouble(6, c.getTotal());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    @Override
    public void eliminar(int id_carrito) {
        String SQL = "DELETE FROM carrito WHERE id_carrito=?";
        try {
            conex = Conexion.getConexion();
            ps = conex.prepareStatement(SQL);
            ps.setInt(1, id_carrito);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
