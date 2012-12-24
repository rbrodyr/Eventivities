package com.eventivities.android;

import java.util.List;

import android.os.Bundle;
import android.widget.GridView;

import com.actionbarsherlock.app.SherlockActivity;
import com.eventivities.android.adapters.LocalesAdapter;
import com.eventivities.android.domain.Producto;
import com.eventivities.android.handlers.ProductoHandler;

public class LocalesActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locales);
        
        ProductoHandler productoHandler = new ProductoHandler(this);        
        List<Producto> productos = productoHandler.obtenerTodos();
		
		GridView gridView = (GridView) findViewById(R.id.GridViewLocales);
		LocalesAdapter adapter = new LocalesAdapter(this, R.layout.item_local, productos);
		gridView.setAdapter(adapter);
		//gridView.setOnClickListener(itemClickListener);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		// ANIMACION DE SALIDA
    	overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
	}
}
