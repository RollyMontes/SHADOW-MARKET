/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author saidc
 */
public class Carrito {

    int id_carrito;
    int id_cuenta;
    String nom_producto;
    int cantidad;
    String logo;
    double precio_unitario;
    double total;

    public Carrito() {
    }

    public Carrito(int id_carrito, int id_cuenta, String nom_producto, int cantidad, String logo, double precio_unitario, double total) {
        this.id_carrito = id_carrito;
        this.id_cuenta = id_cuenta;
        this.nom_producto = nom_producto;
        this.cantidad = cantidad;
        this.logo = logo;
        this.precio_unitario = precio_unitario;
        this.total = total;
    }

    public int getId_carrito() {
        return id_carrito;
    }

    public void setId_carrito(int id_carrito) {
        this.id_carrito = id_carrito;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public String getNom_producto() {
        return nom_producto;
    }

    public void setNom_producto(String nom_producto) {
        this.nom_producto = nom_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
    
}
