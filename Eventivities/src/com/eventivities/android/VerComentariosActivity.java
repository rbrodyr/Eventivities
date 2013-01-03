package com.eventivities.android;

import java.util.List;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.eventivities.android.adapters.ComentariosAdapter;
import com.eventivities.android.adapters.EventosAdapter;
import com.eventivities.android.domain.Comentario;
import com.eventivities.android.domain.Evento;
import com.eventivities.android.domain.ListaComentarios;
import com.eventivities.android.excepciones.ExcepcionAplicacion;
import com.eventivities.android.servicioweb.Conexion;
import com.eventivities.android.util.TnUtil;

public class VerComentariosActivity extends SherlockListActivity{

	private Evento evento;
	private String nombreLocal;
	ListView listaComentarios;
	private List<Comentario> comentarios;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		
		getSupportActionBar().setHomeButtonEnabled(true);
		
        Bundle extras = getIntent().getExtras();        
		if(extras != null ){
			evento = (Evento) extras.getSerializable(Param.EVENTO.toString());
			nombreLocal = (String) extras.getString(Param.LOCAL_NOMBRE.toString());
			listaComentarios=(ListView) findViewById(R.id.comentarios_listView);
			
			
			TextView nomL=(TextView) findViewById(R.id.comentarios_nombreTeatro);
			TextView nomE=(TextView) findViewById(R.id.comentarios_nombreEvento);
			
			//ESTO NO ESCRIBE... explota
			
			
			//nomL.setText(nombreLocal);
			//nomE.setText(evento.getNombre());			
			
			if (evento != null) {
				new ComentariosAsyncTask().execute();
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
			startActivity(new Intent(VerComentariosActivity.this, LocalesActivity.class));
			break;
		case R.id.menu_login:
			startActivity(new Intent(VerComentariosActivity.this, MiPerfilActivity.class));
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}

    private OnItemClickListener itemClickListener = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			/*POR TENERLO IGUAL
			Evento evento = eventos.get(arg2);

			Intent i = new Intent(EventosActivity.this, DetalleEventoActivity.class);
			Bundle b = new Bundle();
			b.putSerializable(Param.EVENTO.toString(), evento); 
			b.putString(Param.LOCAL_NOMBRE.toString(), nombreLocal);
			i.putExtras(b);

			startActivity(i);
			*/
		}
	};
	
	private class ComentariosAsyncTask extends AsyncTask<Void, Void, List<Comentario>> {

		@Override
		protected void onPreExecute() {
			getSherlock().setProgressBarIndeterminateVisibility(true);
			super.onPreExecute();
		}

		@Override
		protected List<Comentario> doInBackground(Void... params) {
			comentarios = null;
			try {
				String n=String.valueOf(evento.getIdEvento());
				comentarios = Conexion.obtenerComentariosEvento(n).getComentarios();
			} catch (ExcepcionAplicacion e) {
				e.printStackTrace();
			}
			return comentarios ;
		}

		@Override
		protected void onPostExecute(List<Comentario> result) {
			if (result != null) {
				ComentariosAdapter adapter = new ComentariosAdapter(getApplicationContext(), R.layout.item_comentario, comentarios);
				setListAdapter(adapter);
			} else {
				setContentView(R.layout.error_conexion);
			}
			
			
		     
			getSherlock().setProgressBarIndeterminateVisibility(false);
			
			super.onPostExecute(result);
		}
		
	}

}
