package handlers;

import java.util.List;

import android.content.Context;
import dao.ProductoCache;
import domain.Producto;

public class ProductoHandler {

	private Context context;

	public ProductoHandler(Context context) {
		this.context = context;
	}
	
	public List<Producto> obtenerProductos () {
		ProductoCache productoCache = new ProductoCache(context);
		return productoCache.getProductos();
	}
	
	public void insertarProducto(Producto producto) { 
		ProductoCache productoCache = new ProductoCache(context);
		productoCache.setProducto(producto);
	}
}
