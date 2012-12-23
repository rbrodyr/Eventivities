package com.eventivities.android;


import java.util.List;

import com.eventivities.android.adapters.ListaProductosAdapter;
import com.eventivities.android.domain.Producto;
import com.eventivities.android.handlers.ProductoHandler;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ListaProductosActivity extends ListActivity {
	
	private List<Producto> productos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getListView().setOnItemClickListener(itemClickListener);
    }

    @Override
	protected void onResume() {
		super.onResume();
        
        ProductoHandler productoHandler = new ProductoHandler(this);        
    	productos = productoHandler.obtenerTodos();

		ListaProductosAdapter adapter = new ListaProductosAdapter(this, R.layout.item_producto, productos);
		setListAdapter(adapter);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_productos, menu);
        return true;
    }
    
    private OnItemClickListener itemClickListener = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			int productoId = productos.get(arg2).getId();

			Intent i = new Intent(ListaProductosActivity.this, DetalleProductoActivity.class);
			Bundle b = new Bundle();
			b.putInt(Param.PRODUCTO_ID.toString(), productoId);
			i.putExtras(b);

			startActivity(i);
		}    	
	};
}
