package com.eventivities.android.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.eventivities.android.dao.ProductoCache;
import com.eventivities.android.domain.Producto;

import android.content.Context;

public class ProductoHandler {

	private Context context;	
	private static final List<Producto> productos = new ArrayList<Producto>();
	
	static {
		productos.add(new Producto("Martillo"));
		productos.add(new Producto("Hoz"));
	}

	public ProductoHandler(Context context) {
		this.context = context;
	}
	
	public List<Producto> obtenerTodos () {
		ProductoCache productoCache = new ProductoCache(context);
		return productoCache.getAll();
	}
	
	public Producto obtenerProducto(int productoId) {
		ProductoCache productoCache = new ProductoCache(context);
		return productoCache.getProducto(productoId);
	}
	
	public void insertarEjemplos() {
		ProductoCache productoCache = new ProductoCache(context);
		
		Iterator<Producto> iterator = productos.iterator();
		while (iterator.hasNext()) {
			Producto producto = iterator.next();
			productoCache.setProducto(producto);
		}
	}
	
	public void insertarProducto(Producto producto) {
		ProductoCache productoCache = new ProductoCache(context);
		productoCache.setProducto(producto);
	}
	
	public void borrarTodos() {
		ProductoCache productoCache = new ProductoCache(context);
		productoCache.removeAll();
	}
	
	public void renombrarProducto(int productoId, String nombre) {
		ProductoCache productoCache = new ProductoCache(context);
		Producto producto = productoCache.getProducto(productoId);
		producto.setNombre(nombre);
		productoCache.setProducto(producto);
	}
}
