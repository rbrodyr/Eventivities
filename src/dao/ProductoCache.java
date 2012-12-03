package dao;

import java.util.Collection;
import java.util.List;

import android.content.Context;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import domain.Producto;

public class ProductoCache extends EventivitiesDAOBase<Producto, Integer> {

	public ProductoCache(Context context) {
		super(context, Producto.class);
	}
	
	public void setAll(Collection<Producto> productos){
		RuntimeExceptionDao<Producto, Integer> productoDAO = getDAO();
		
		for(Producto producto: productos){
			productoDAO.createOrUpdate(producto);
		}
	}
	
	public void setProducto(Producto producto) {
		RuntimeExceptionDao<Producto, Integer> productoDAO = getDAO();
		productoDAO.createOrUpdate(producto);
	}
	
	public void removeAll() {
		RuntimeExceptionDao<Producto, Integer> productoDAO = getDAO();
		productoDAO.delete(productoDAO.queryForAll());
	}
	
	public List<Producto> getAll(){
		RuntimeExceptionDao<Producto, Integer> productoDAO = getDAO();
		List<Producto> productos = productoDAO.queryForAll();
		
		return productos;
	}

	public Producto getProducto(int productoID) {
		RuntimeExceptionDao<Producto, Integer> productoDAO = getDAO();
		Producto producto = productoDAO.queryForId(productoID);
		
		return producto;
	}
}
