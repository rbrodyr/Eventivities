package com.eventivities.android;

import com.eventivities.android.domain.Producto;
import com.eventivities.android.handlers.ProductoHandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetalleProductoActivity extends Activity {
	
	private int productoId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        
        Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			productoId = extras.getInt(Param.PRODUCTO_ID.toString());
			
			ProductoHandler productoHandler = new ProductoHandler(this);
			Producto producto = productoHandler.obtenerProducto(productoId);

			if (producto != null) {
				EditText editTextNombre = (EditText) findViewById(R.id.editTextEjemplo);
				if (editTextNombre != null)
					editTextNombre.setText(producto.getNombre());
				
				setTitle(producto.getNombre());
			}
		}

		final Button button = (Button) findViewById(R.id.buttonEjemploRename);
        button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				EditText editTextNombre = (EditText) findViewById(R.id.editTextEjemplo);
				String nombre = editTextNombre.getText().toString();
				
				ProductoHandler productoHandler = new ProductoHandler(DetalleProductoActivity.this);
				productoHandler.renombrarProducto(productoId, nombre);
				
    	    	finish();
			}
        	
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_detalle_producto, menu);
        return true;
    }
}
