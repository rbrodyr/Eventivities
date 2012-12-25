package com.eventivities.android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.eventivities.android.domain.Producto;
import com.eventivities.android.handlers.EventoHandler;
import com.eventivities.android.handlers.ProductoHandler;

public class DetalleEventoActivity extends SherlockActivity {
	
	private int eventoId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_evento);
        
        Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			eventoId = extras.getInt(Param.EVENTO_ID.toString());
			
			EventoHandler eventoHandler = new EventoHandler(this);
			Producto evento = eventoHandler.obtenerEvento(eventoId);

			if (evento != null) {
				TextView textViewNombre = (TextView) findViewById(R.id.textViewNombreEvento);
				if (textViewNombre != null)
					textViewNombre.setText(evento.getNombre());
				
				setTitle(evento.getNombre());
			}
		}
    }
}
