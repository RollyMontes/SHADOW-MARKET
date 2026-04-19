/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.InputStream;

/**
 *
 * @author saidc
 */
public class Productos {

    int id_producto;
    String nombre_producto;
    int id_categoria;
    String nombre_categoria;
    int id_genero;
    String nombre_genero;
    double precio_producto;
    int num_estrellas_producto;
    String logo_producto;
    String link_trailer_producto;
    String img_promo1_producto;
    String img_promo2_producto;
    String img_promo3_producto;
    String img_promo4_producto;
    int numlikes_producto;
    int id_plataforma;
    String nombre_plataforma;
    int id_region;
    String nombre_region;

    public Productos() {
    }

    public Productos(int id_producto, String nombre_producto, int id_categoria, int id_genero, double precio_producto, int num_estrellas_producto, String logo_producto, String link_trailer_producto, String img_promo1_producto, String img_promo2_producto, String img_promo3_producto, String img_promo4_producto, int numlikes_producto, int id_plataforma, int id_region) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.id_categoria = id_categoria;
        this.id_genero = id_genero;
        this.precio_producto = precio_producto;
        this.num_estrellas_producto = num_estrellas_producto;
        this.logo_producto = logo_producto;
        this.link_trailer_producto = link_trailer_producto;
        this.img_promo1_producto = img_promo1_producto;
        this.img_promo2_producto = img_promo2_producto;
        this.img_promo3_producto = img_promo3_producto;
        this.img_promo4_producto = img_promo4_producto;
        this.numlikes_producto = numlikes_producto;
        this.id_plataforma = id_plataforma;
        this.id_region = id_region;
    }
//constructor para el editar
    
    
    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    public int getId_genero() {
        return id_genero;
    }

    public void setId_genero(int id_genero) {
        this.id_genero = id_genero;
    }

    public String getNombre_genero() {
        return nombre_genero;
    }

    public void setNombre_genero(String nombre_genero) {
        this.nombre_genero = nombre_genero;
    }

    public double getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(double precio_producto) {
        this.precio_producto = precio_producto;
    }

    public int getNum_estrellas_producto() {
        return num_estrellas_producto;
    }

    public void setNum_estrellas_producto(int num_estrellas_producto) {
        this.num_estrellas_producto = num_estrellas_producto;
    }

    public String getLogo_producto() {
        return logo_producto;
    }

    public void setLogo_producto(String logo_producto) {
        this.logo_producto = logo_producto;
    }

    public String getLink_trailer_producto() {
        return link_trailer_producto;
    }

    public void setLink_trailer_producto(String link_trailer_producto) {
        this.link_trailer_producto = link_trailer_producto;
    }

    public String getImg_promo1_producto() {
        return img_promo1_producto;
    }

    public void setImg_promo1_producto(String img_promo1_producto) {
        this.img_promo1_producto = img_promo1_producto;
    }

    public String getImg_promo2_producto() {
        return img_promo2_producto;
    }

    public void setImg_promo2_producto(String img_promo2_producto) {
        this.img_promo2_producto = img_promo2_producto;
    }

    public String getImg_promo3_producto() {
        return img_promo3_producto;
    }

    public void setImg_promo3_producto(String img_promo3_producto) {
        this.img_promo3_producto = img_promo3_producto;
    }

    public String getImg_promo4_producto() {
        return img_promo4_producto;
    }

    public void setImg_promo4_producto(String img_promo4_producto) {
        this.img_promo4_producto = img_promo4_producto;
    }

    public int getNumlikes_producto() {
        return numlikes_producto;
    }

    public void setNumlikes_producto(int numlikes_producto) {
        this.numlikes_producto = numlikes_producto;
    }

    public int getId_plataforma() {
        return id_plataforma;
    }

    public void setId_plataforma(int id_plataforma) {
        this.id_plataforma = id_plataforma;
    }

    public String getNombre_plataforma() {
        return nombre_plataforma;
    }

    public void setNombre_plataforma(String nombre_plataforma) {
        this.nombre_plataforma = nombre_plataforma;
    }

    public int getId_region() {
        return id_region;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

    public String getNombre_region() {
        return nombre_region;
    }

    public void setNombre_region(String nombre_region) {
        this.nombre_region = nombre_region;
    }

    
}
