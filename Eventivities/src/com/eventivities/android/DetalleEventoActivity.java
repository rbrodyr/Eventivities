package com.eventivities.android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.eventivities.android.domain.Producto;
import com.eventivities.android.handlers.EventoHandler;

public class DetalleEventoActivity extends SherlockActivity {
	
	private int eventoId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_evento);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
        
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getSupportMenuInflater();
		menuInflater.inflate(R.menu.general, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			startActivity(new Intent(DetalleEventoActivity.this, LocalesActivity.class));
			break;
		case R.id.menu_login:
			startActivity(new Intent(DetalleEventoActivity.this, MiPerfilActivity.class));
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
