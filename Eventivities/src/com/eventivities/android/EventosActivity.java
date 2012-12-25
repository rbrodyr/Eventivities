package com.eventivities.android;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.MenuItem;
import com.eventivities.android.adapters.ListaProductosAdapter;
import com.eventivities.android.domain.Producto;
import com.eventivities.android.handlers.EventoHandler;

public class EventosActivity extends SherlockListActivity {
	
	private List<Producto> eventos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_eventos);
		getSupportActionBar().setHomeButtonEnabled(true);

        getListView().setOnItemClickListener(itemClickListener);
    }

    @Override
	protected void onResume() {
		super.onResume();
        
		EventoHandler eventoHandler = new EventoHandler(this);
		eventos = eventoHandler.obtenerTodos();

		ListaProductosAdapter adapter = new ListaProductosAdapter(this, R.layout.item_evento, eventos);
		setListAdapter(adapter);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent i = new Intent(EventosActivity.this, LocalesActivity.class);
			startActivity(i);
		}
		
		return super.onOptionsItemSelected(item);
	}
    
    private OnItemClickListener itemClickListener = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			int eventoId = eventos.get(arg2).getId();

			Intent i = new Intent(EventosActivity.this, DetalleEventoActivity.class);
			Bundle b = new Bundle();
			b.putInt(Param.EVENTO_ID.toString(), eventoId);
			i.putExtras(b);

			startActivity(i);
		}
	};
}
