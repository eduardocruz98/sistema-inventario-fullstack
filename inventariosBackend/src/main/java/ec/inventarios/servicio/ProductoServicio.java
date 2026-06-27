package ec.inventarios.servicio;

import ec.inventarios.modelo.Producto;
import ec.inventarios.repositorio.ProductoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ProductoServicio implements  IProductoServicio{

    //Agregamos esta variable local para inyeccion de dependencias
    private final ProductoRepositorio productoRepositorio;

    public ProductoServicio(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    @Override
    @Transactional
    public List<Producto> listarProductos() {
        return productoRepositorio.findAll();
    }

    @Override
    //@Transactional(readOnly = true)
    public Producto buscarProductoPorId(Integer idProducto) {
        return productoRepositorio.findById(idProducto).orElse(null);
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        //Sirve para crear o para actualizar
        return productoRepositorio.save(producto);
    }

    @Override
    public void eliminarProductoPorId(Integer idProducto) {
        if(!productoRepositorio.existsById(idProducto)){
            throw new NoSuchElementException("No existe producto con id: " +idProducto);
        }
        productoRepositorio.deleteById(idProducto);
    }
}
