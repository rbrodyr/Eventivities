package com.eventivities.android;

import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.eventivities.android.adapters.EventosAdapter;
import com.eventivities.android.domain.Evento;
import com.eventivities.android.excepciones.ExcepcionAplicacion;
import com.eventivities.android.servicioweb.Conexion;

public class EventosActivity extends SherlockListActivity {
	
	private List<Evento> eventos;
	private int localId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		//setContentView(R.layout.activity_eventos);
		getSupportActionBar().setHomeButtonEnabled(true);

        getListView().setOnItemClickListener(itemClickListener);
        
        Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			localId = extras.getInt(Param.LOCAL_ID.toString());
			setTitle(extras.getString(Param.LOCAL_NOMBRE.toString()));
		}
		
		new EventosAsyncTask().execute();
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getSupportMenuInflater();
		menuInflater.inflate(R.menu.general, menu);
		menu.findItem(R.id.menu_refresh).setVisible(true);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			startActivity(new Intent(EventosActivity.this, LocalesActivity.class));
			break;
		case R.id.menu_login:
			startActivity(new Intent(EventosActivity.this, MiPerfilActivity.class));
			break;
		case R.id.menu_refresh:
			new EventosAsyncTask().execute();
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
    
    private OnItemClickListener itemClickListener = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Evento evento = eventos.get(arg2);

			Intent i = new Intent(EventosActivity.this, DetalleEventoActivity.class);
			Bundle b = new Bundle();
			b.putSerializable(Param.EVENTO.toString(), evento);
			i.putExtras(b);

			startActivity(i);
		}
	};
	
	private class EventosAsyncTask extends AsyncTask<Void, Void, List<Evento>> {

		@Override
		protected void onPreExecute() {
			getSherlock().setProgressBarIndeterminateVisibility(true);
			super.onPreExecute();
		}

		@Override
		protected List<Evento> doInBackground(Void... params) {
			eventos = null;
			try {
				eventos = Conexion.obtenerEventosLocal(localId).getEventos();
			} catch (ExcepcionAplicacion e) {
				e.printStackTrace();
			}
			return eventos;
		}

		@Override
		protected void onPostExecute(List<Evento> result) {
			if (result != null) {
				EventosAdapter adapter = new EventosAdapter(getApplicationContext(), R.layout.item_evento, eventos);
				setListAdapter(adapter);
			} else {
				setContentView(R.layout.error_conexion);
			}
			
			getSherlock().setProgressBarIndeterminateVisibility(false);
			
			super.onPostExecute(result);
		}
		
	}
}
