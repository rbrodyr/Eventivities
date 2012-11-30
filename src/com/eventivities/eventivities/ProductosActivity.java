package com.eventivities.eventivities;

import handlers.ProductoHandler;

import java.util.List;

import adapters.ProductoAdapter;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import domain.Producto;

public class ProductosActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_productos);
        
        ProductoHandler productoHandler = new ProductoHandler(this);
        
        /*Producto producto = new Producto();
        producto.setNombre("Martillo");        
        productoHandler.insertarProducto(producto);*/
        
    	List<Producto> productos = productoHandler.obtenerProductos();
    	setTitle("Productos");

		ProductoAdapter adapter = new ProductoAdapter(this, R.layout.item_producto, productos);
		setListAdapter(adapter);
		//getListView().setOnItemClickListener(itemClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_productos, menu);
        return true;
    }
}
