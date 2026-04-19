/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

import Clases.Categorias;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author saidc
 */
public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/shadow_market?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "";
    private static Connection cx = null;

    public DriverManagerDataSource Conectar() {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setDriverClassName(DRIVER);
        datasource.setUrl(URL);
        datasource.setUsername(USER);
        datasource.setPassword(PASS);
        return datasource;
    }

    public static Connection getConexion(){
        try {
            Class.forName(DRIVER);
            if(cx==null){
                cx = DriverManager.getConnection(URL, USER, PASS);
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: "+e);
        }
    
    return cx;
    }
    
    
    public static void main(String[] args) {
        // Definir la URL de conexión, usuario y contraseña de la base de datos
        String url = "jdbc:mysql://localhost:3306/shadow_market";
        String usuario = "root";
        String contraseña = "";

        // Intentar establecer la conexión y obtener los datos de la tabla categorias
        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("¡La conexión a la base de datos fue exitosa!");

            // Consulta SQL para obtener todos los registros de la tabla categorias
            String sql = "SELECT * FROM categorias";

            // Preparar la sentencia SQL
            PreparedStatement statement = conexion.prepareStatement(sql);

            // Ejecutar la consulta y obtener el resultado
            ResultSet resultSet = statement.executeQuery();

            // Crear una lista para almacenar los objetos Categoria
            List<Categorias> categorias = new ArrayList<>();

            // Recorrer el resultado y crear objetos Categoria para cada fila
            while (resultSet.next()) {
                Categorias categoria = new Categorias();
                categoria.setId_categoria(resultSet.getInt("id_categoria"));
                categoria.setNombre_categoria(resultSet.getString("nombre_categoria"));
                System.out.println(categoria.getId_categoria() + categoria.getNombre_categoria());
// Añadir la categoria a la lista
                categorias.add(categoria);
            }
            // Ahora puedes pasar la lista 'categorias' a tu página JSP para mostrarla en la tabla
            // Aquí deberías tener lógica para pasar la lista a tu JSP, como almacenarla en el ámbito de la solicitud
        } catch (Exception e) {
            System.out.println("¡Error al conectar a la base de datos!");
            e.printStackTrace();
        }
    }

}
