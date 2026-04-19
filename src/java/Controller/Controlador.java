/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Clases.Carrito;
import Clases.Categorias;
import Clases.Generos;
import Clases.Plataformas;
import Clases.Productos;
import Clases.Regiones;
import Config.Conexion;
import Dao.CarritoDao;
import Dao.CategoriaDao;
import Dao.GeneroDao;
import Dao.PlataformaDao;
import Dao.ProductoDao;
import Dao.RegionDao;
import DaoImpl.CarritoDaoimpl;
import DaoImpl.CategoriaDaoimpl;
import DaoImpl.GeneroDaoimpl;
import DaoImpl.RegionDaoimpl;
import DaoImpl.PlataformaDaoimpl;
import DaoImpl.ProductoDaoimpl;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.portlet.ModelAndView; //no sirve
import org.springframework.web.servlet.ModelAndView; //ojooo

@Controller
public class Controlador {

    private CarritoDao carritodao = new CarritoDaoimpl();
    private RegionDao rdao = new RegionDaoimpl();
    private CategoriaDao cdao = new CategoriaDaoimpl();
    private GeneroDao gdao = new GeneroDaoimpl();
    private PlataformaDao pdao = new PlataformaDaoimpl();
    private ProductoDao productodao = new ProductoDaoimpl();
    int id;
    List datos;

    Conexion con = new Conexion();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView();

    @RequestMapping("index.htm")
    public ModelAndView Listar() {
        String SQL = "SELECT p.id_producto, p.nombre_producto, p.precio_producto, p.logo_producto, "
                + "p.link_trailer_producto, p.img_promo1_producto, p.img_promo2_producto, "
                + "p.img_promo3_producto, p.img_promo4_producto, "
                + "c.nombre_categoria, g.nombre_genero, pl.nombre_plataforma, r.nombre_region "
                + "FROM productos p "
                + "LEFT JOIN categorias c ON p.id_categoria = c.id_categoria "
                + "LEFT JOIN generos g ON p.id_genero = g.id_genero "
                + "LEFT JOIN plataformas pl ON p.id_plataforma = pl.id_plataforma "
                + "LEFT JOIN regiones r ON p.id_region = r.id_region";
        String sqlcategoria = "select * from categorias";
        String sqlgenero = "select * from generos";
        String sqlplataforma = "select * from plataformas";
        String sqlregion = "select * from regiones";
        List datos = this.jdbcTemplate.queryForList(SQL);
        List categoria = this.jdbcTemplate.queryForList(sqlcategoria);
        List genero = this.jdbcTemplate.queryForList(sqlgenero);
        List plataforma = this.jdbcTemplate.queryForList(sqlplataforma);
        List region = this.jdbcTemplate.queryForList(sqlregion);
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", datos);
        mav.addObject("listacategoria", categoria);
        mav.addObject("listagenero", genero);
        mav.addObject("listaplataforma", plataforma);
        mav.addObject("listaregion", region);
        mav.setViewName("index");
        return mav;
    }

    //controller para carrito
    @RequestMapping("carrito.htm")
    public ModelAndView Carrito() {
        String SQL = "SELECT * FROM carrito";
        List datos = this.jdbcTemplate.queryForList(SQL);
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", datos);
        mav.setViewName("carrito");
        return mav;
    }

    ///CARRITO BOTON ELIMINAR
    @RequestMapping(value = "carritodelete.htm")
    public ModelAndView deleteCarrito(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        carritodao.eliminar(id);
        return new ModelAndView("redirect:/carrito.htm");
    }

    ///CARRITO BOTON AGREGAR
    @RequestMapping(value = "carritoagregar.htm")
    public ModelAndView agregarCarrito(HttpServletRequest request) {
        String nombre = request.getParameter("nombre");
        String logo=request.getParameter("logo");
        double precioU=Double.parseDouble(request.getParameter("precio"));
        carritodao.agregar(new Carrito(0, 1,nombre,1,logo,precioU,precioU));
        return new ModelAndView("redirect:/carrito.htm");
    }

    //index buscar por nombre
    @RequestMapping(value = "buscar.htm")
    public ModelAndView buscarProducto(HttpServletRequest request) {
        String nombre = request.getParameter("nombrebuscar");
        String SQL = "SELECT p.id_producto, p.nombre_producto, p.precio_producto, p.logo_producto, "
                + "p.link_trailer_producto, p.img_promo1_producto, p.img_promo2_producto, "
                + "p.img_promo3_producto, p.img_promo4_producto, "
                + "c.nombre_categoria, g.nombre_genero, pl.nombre_plataforma, r.nombre_region "
                + "FROM productos p "
                + "LEFT JOIN categorias c ON p.id_categoria = c.id_categoria "
                + "LEFT JOIN generos g ON p.id_genero = g.id_genero "
                + "LEFT JOIN plataformas pl ON p.id_plataforma = pl.id_plataforma "
                + "LEFT JOIN regiones r ON p.id_region = r.id_region "
                + "WHERE p.nombre_producto LIKE ?";

        String sqlcategoria = "SELECT * FROM categorias";
        String sqlgenero = "SELECT * FROM generos";
        String sqlplataforma = "SELECT * FROM plataformas";
        String sqlregion = "SELECT * FROM regiones";

        // Consulta para productos con parámetro
        List<Map<String, Object>> productos = jdbcTemplate.queryForList(SQL, "%" + nombre + "%");

        // Consultas para categorías, géneros, plataformas y regiones
        List<Map<String, Object>> categoria = jdbcTemplate.queryForList(sqlcategoria);
        List<Map<String, Object>> genero = jdbcTemplate.queryForList(sqlgenero);
        List<Map<String, Object>> plataforma = jdbcTemplate.queryForList(sqlplataforma);
        List<Map<String, Object>> region = jdbcTemplate.queryForList(sqlregion);

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("lista", productos);
        mav.addObject("listacategoria", categoria);
        mav.addObject("listagenero", genero);
        mav.addObject("listaplataforma", plataforma);
        mav.addObject("listaregion", region);

        return mav;
    }

    //GENERO METODOS
    ///LISTAR PLATAFORMA EN GENERO LIST- VISTA ADMIN 
    @RequestMapping(value = "generoList.htm", method = RequestMethod.GET)
    public ModelAndView redirectToVistageneroProducto() {
        String sql = "select * from generos";
        List datos = this.jdbcTemplate.queryForList(sql);
        mav.addObject("lista", datos);
        mav.setViewName("generoList");
        return mav;
    }

    ///Genero BOTON AGREGAR
    @RequestMapping(value = "GLagregar.htm")
    public ModelAndView agregarGenero(HttpServletRequest request) {
        String nombre = request.getParameter("nombreagregar");
        gdao.agregar(new Generos(0, nombre));
        return new ModelAndView("redirect:/generoList.htm");
    }

    ///GENERO BOTON ELIMINAR
    @RequestMapping(value = "GLdelete.htm")
    public ModelAndView deleteGenero(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        gdao.eliminar(id);
        return new ModelAndView("redirect:/generoList.htm");
    }

    ///GENERO BOTON EDITAR
    @RequestMapping(value = "GLeditar.htm")
    public ModelAndView editarGenero(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        Generos g = new Generos();
        g.setId_genero(id);
        g.setNombre_genero(nombre);
        gdao.editar(g);
        return new ModelAndView("redirect:/generoList.htm");
    }

    //PLATAFORMA METODOS
    ///LISTAR PLATAFORMA EN PLATAFORMA LIST- VISTA ADMIN 
    @RequestMapping(value = "plataformaList.htm", method = RequestMethod.GET)
    public ModelAndView redirectToVistaplataformaProducto() {
        String sql = "select * from plataformas";
        List datos = this.jdbcTemplate.queryForList(sql);
        mav.addObject("lista", datos);
        mav.setViewName("plataformaList");
        return mav;
    }

    ///PLATAFORMA BOTON AGREGAR
    @RequestMapping(value = "PLagregar.htm")
    public ModelAndView agregarPlataforma(HttpServletRequest request) {
        String nombre = request.getParameter("nombreagregar");
        pdao.agregar(new Plataformas(0, nombre));
        return new ModelAndView("redirect:/plataformaList.htm");
    }

    ///PLATAFORMA BOTON ELIMINAR
    @RequestMapping(value = "PLdelete.htm")
    public ModelAndView deletePlataforma(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        pdao.eliminar(id);
        return new ModelAndView("redirect:/plataformaList.htm");
    }

    ///PLATAFORMA BOTON EDITAR
    @RequestMapping(value = "PLeditar.htm")
    public ModelAndView editarPlataforma(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        Plataformas p = new Plataformas();
        p.setId_plataforma(id);
        p.setNombre_plataforma(nombre);
        pdao.editar(p);
        return new ModelAndView("redirect:/plataformaList.htm");
    }

    //REGION METODOS
    ///LISTAR REGION EN REGION LIST- VISTA ADMIN 
    @RequestMapping(value = "regionList.htm", method = RequestMethod.GET)
    public ModelAndView redirectToVistaregionProducto() {
        String sql = "select * from regiones";
        List datos = this.jdbcTemplate.queryForList(sql);
        mav.addObject("lista", datos);
        mav.setViewName("regionList");
        return mav;
    }

    ///REGION BOTON AGREGAR
    @RequestMapping(value = "RLagregar.htm")
    public ModelAndView agregarRegion(HttpServletRequest request) {
        String nombre = request.getParameter("nombreagregar");
        rdao.agregar(new Regiones(0, nombre));
        return new ModelAndView("redirect:/regionList.htm");
    }

    ///REGIONBOTON ELIMINAR
    @RequestMapping(value = "RLdelete.htm")
    public ModelAndView deleteRegion(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        rdao.eliminar(id);
        return new ModelAndView("redirect:/regionList.htm");
    }

    ///REGION BOTON EDITAR
    @RequestMapping(value = "RLeditar.htm")
    public ModelAndView editarRegion(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        Regiones r = new Regiones();
        r.setId_region(id);
        r.setNombre_region(nombre);
        rdao.editar(r);
        return new ModelAndView("redirect:/regionList.htm");
    }

    //CATEGORI METODOS
    ///LISTAR CATEGORIAS EN CATEGORIA LIST- VISTA ADMIN 
    @RequestMapping(value = "categoriaList.htm", method = RequestMethod.GET)
    public ModelAndView redirectToVistacategoriaProducto() {
        String sql = "select * from categorias";
        List datos = this.jdbcTemplate.queryForList(sql);
        mav.addObject("lista", datos);
        mav.setViewName("categoriaList");
        return mav;
    }

    ///CATEGORIA BOTON AGREGAR
    @RequestMapping(value = "CLagregar.htm")
    public ModelAndView agregarCategoria(HttpServletRequest request) {
        String nombre = request.getParameter("nombreagregar");
        cdao.agregar(new Categorias(0, nombre));
        return new ModelAndView("redirect:/categoriaList.htm");
    }

    ///CATEGORIA BOTON ELIMINAR
    @RequestMapping(value = "CLdelete.htm")
    public ModelAndView deleteCategoria(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        cdao.eliminar(id);
        return new ModelAndView("redirect:/categoriaList.htm");
    }

    ///CATEGORIA BOTON EDITAR
    @RequestMapping(value = "CLeditar.htm")
    public ModelAndView editarCategoria(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        Categorias c = new Categorias();
        c.setId_categoria(id);
        c.setNombre_categoria(nombre);
        cdao.editar(c);
        return new ModelAndView("redirect:/categoriaList.htm");
    }

    //PRODUCTO METODOS
    ///LISTAR PRODUCTOS EN PRODUCTO LIST-VISTA ADMIN 
    @RequestMapping(value = "productoList.htm", method = RequestMethod.GET)
    public ModelAndView redirectToVistaProducto() {
        String SQL = "SELECT p.id_producto, p.nombre_producto, p.precio_producto, p.logo_producto, "
                + "p.link_trailer_producto, p.img_promo1_producto, p.img_promo2_producto, "
                + "p.img_promo3_producto, p.img_promo4_producto, "
                + "c.nombre_categoria, g.nombre_genero, pl.nombre_plataforma, r.nombre_region "
                + "FROM productos p "
                + "LEFT JOIN categorias c ON p.id_categoria = c.id_categoria "
                + "LEFT JOIN generos g ON p.id_genero = g.id_genero "
                + "LEFT JOIN plataformas pl ON p.id_plataforma = pl.id_plataforma "
                + "LEFT JOIN regiones r ON p.id_region = r.id_region";
        String sqlcategoria = "select * from categorias";
        String sqlgenero = "select * from generos";
        String sqlplataforma = "select * from plataformas";
        String sqlregion = "select * from regiones";
        List datos = this.jdbcTemplate.queryForList(SQL);
        List categoria = this.jdbcTemplate.queryForList(sqlcategoria);
        List genero = this.jdbcTemplate.queryForList(sqlgenero);
        List plataforma = this.jdbcTemplate.queryForList(sqlplataforma);
        List region = this.jdbcTemplate.queryForList(sqlregion);
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", datos);
        mav.addObject("listacategoria", categoria);
        mav.addObject("listagenero", genero);
        mav.addObject("listaplataforma", plataforma);
        mav.addObject("listaregion", region);
        mav.setViewName("productoList");
        return mav;
    }

    ///PRODUCTO BOTON AGREGAR
    @RequestMapping(value = "Pagregar.htm")
    public ModelAndView agregarProducto(HttpServletRequest request) {
        String nombre = request.getParameter("nombreagregar");
        int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
        int id_genero = Integer.parseInt(request.getParameter("id_genero"));
        double precio_producto = Double.parseDouble(request.getParameter("precio"));
        int num_estrellas = 0;
        String logo = "Imagenes/" + request.getParameter("nombreagregar") + "/" + request.getParameter("logo");
        String trailer = request.getParameter("trailer");
        String promocional1 = "Imagenes/" + request.getParameter("nombreagregar") + "/" + request.getParameter("imgPromo1");
        String promocional2 = "Imagenes/" + request.getParameter("nombreagregar") + "/" + request.getParameter("imgPromo2");
        String promocional3 = "Imagenes/" + request.getParameter("nombreagregar") + "/" + request.getParameter("imgPromo3");
        String promocional4 = "Imagenes/" + request.getParameter("nombreagregar") + "/" + request.getParameter("imgPromo4");
        int num_likes = 0;
        int id_plataforma = Integer.parseInt(request.getParameter("id_plataforma"));
        int id_region = Integer.parseInt(request.getParameter("id_region"));
        productodao.agregar(new Productos(0, nombre,
                id_categoria, id_genero, precio_producto, num_estrellas, logo, trailer,
                promocional1, promocional2, promocional3, promocional4, num_likes, id_plataforma, id_region));
        return new ModelAndView("redirect:/productoList.htm");
    }

    ///PRODUCTO BOTON EDITAR
    @RequestMapping(value = "Peditar.htm")
    public ModelAndView editarProducto(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
        int id_genero = Integer.parseInt(request.getParameter("id_genero"));
        double precio_producto = Double.parseDouble(request.getParameter("precio"));
        String logo = "Imagenes/" + request.getParameter("nombre") + "/" + request.getParameter("logo");
        String trailer = request.getParameter("trailer");
        String promocional1 = "Imagenes/" + request.getParameter("nombre") + "/" + request.getParameter("imgPromo1");
        String promocional2 = "Imagenes/" + request.getParameter("nombre") + "/" + request.getParameter("imgPromo2");
        String promocional3 = "Imagenes/" + request.getParameter("nombre") + "/" + request.getParameter("imgPromo3");
        String promocional4 = "Imagenes/" + request.getParameter("nombre") + "/" + request.getParameter("imgPromo4");
        int id_plataforma = Integer.parseInt(request.getParameter("id_plataforma"));
        int id_region = Integer.parseInt(request.getParameter("id_region"));

        Productos pro = new Productos();
        pro.setId_producto(id);
        pro.setNombre_producto(nombre);
        pro.setId_categoria(id_categoria);
        pro.setId_genero(id_genero);
        pro.setPrecio_producto(precio_producto);
        pro.setLogo_producto(logo);
        pro.setLink_trailer_producto(trailer);
        pro.setImg_promo1_producto(promocional1);
        pro.setImg_promo2_producto(promocional2);
        pro.setImg_promo3_producto(promocional3);
        pro.setImg_promo4_producto(promocional4);
        pro.setId_plataforma(id_plataforma);
        pro.setId_region(id_region);

        productodao.editar(pro);

        return new ModelAndView("redirect:/productoList.htm");
    }

    ///PRODUCTO BOTON ELIMINAR
    @RequestMapping(value = "Pdelete.htm")
    public ModelAndView deleteProducto(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        productodao.eliminar(id);
        return new ModelAndView("redirect:/productoList.htm");
    }

    @RequestMapping(value = "login.htm", method = RequestMethod.GET)
    public ModelAndView redirectToLogin() {
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping(value = "registrarcuenta.htm", method = RequestMethod.GET)
    public ModelAndView redirectToRegistrar() {
        mav.setViewName("registrarcuenta");
        return mav;
    }

    @RequestMapping(value = "vistaAdmin.htm", method = RequestMethod.GET)
    public ModelAndView redirectToVistaAdmin() {
        mav.setViewName("vistaAdmin");
        return mav;
    }
}
