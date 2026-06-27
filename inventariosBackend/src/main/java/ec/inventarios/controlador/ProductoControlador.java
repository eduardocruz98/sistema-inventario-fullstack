package ec.inventarios.controlador;

import ec.inventarios.modelo.Producto;
import ec.inventarios.servicio.IProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("inventario-app") // http://localhost:8080/inventario-app
@CrossOrigin(origins = "http://localhost:4200") //Permite conexión desde Angular
public class ProductoControlador {

    private final IProductoServicio productoServicio;

    private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class);

    public ProductoControlador(IProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    //Listar los productos
    @GetMapping // http://localhost:8080/inventario-app
    public List<Producto> listarProductos(){
        List<Producto> productos = productoServicio.listarProductos();
        logger.info("Productos obtenidos: ");
        productos.forEach((producto -> logger.info(producto.toString())));
        return productos;
    }

    //Buscar producto pot Id
    @GetMapping("/{id}") // http://localhost:8080/inventario-app/{id}
    public Producto buscarProductoPorId(@PathVariable Integer id){
        return productoServicio.buscarProductoPorId(id);
    }

    //Crear un nuevo producto o actualizarlo
    @PostMapping
    public Producto guardarProducto(@RequestBody Producto producto){
        return productoServicio.guardarProducto(producto);
    }

    //Actualizar un producto
    @PutMapping("/{id}")
    public Producto guardarProducto(@PathVariable Integer id, @RequestBody Producto producto){
        producto.setIdProducto(id);
        return productoServicio.guardarProducto(producto);
    }

    //Elminar un producto
    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Integer id){
        productoServicio.eliminarProductoPorId(id);
    }
}
