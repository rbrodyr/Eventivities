package com.eventivities.android;

import java.util.List;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.eventivities.android.adapters.ComentariosAdapter;
import com.eventivities.android.domain.Comentario;
import com.eventivities.android.domain.Evento;
import com.eventivities.android.excepciones.ExcepcionAplicacion;
import com.eventivities.android.servicioweb.Conexion;
import com.eventivities.android.util.TnUtil;


public class VerComentariosActivity extends SherlockActivity {

	private Evento evento;
	private String nombreLocal;
	

	//ListaComentarios comentarios=null;
	private List<Comentario> comentarios;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ver_comentarios);
		getSupportActionBar().setHomeButtonEnabled(true);
		
        Bundle extras = getIntent().getExtras();
        
		if(extras != null || true){
			evento = (Evento) extras.getSerializable(Param.EVENTO.toString());
			nombreLocal = (String) extras.getString(Param.LOCAL_NOMBRE.toString());
			TextView nomL=(TextView) findViewById(R.id.comentarios_nombreTeatro);
			TextView nomE=(TextView) findViewById(R.id.comentarios_nombreEvento);
			nomL.setText(nombreLocal);
			nomE.setText(evento.getNombre());
			if (evento != null) {
				// new ComentariosAsyncTask().execute();
			}			
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getSupportMenuInflater();
		menuInflater.inflate(R.menu.general, menu);
		return true;
	}
	
	public void aVotar(View v){


		Intent i = new Intent(VerComentariosActivity.this, VotarActivity.class);
		Bundle b = new Bundle();
		b.putSerializable(Param.EVENTO.toString(), evento); 
		b.putString(Param.LOCAL_NOMBRE.toString(), nombreLocal);
		i.putExtras(b);
		startActivity(i);
		
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

	private class ComentariosAsyncTask extends AsyncTask<Void, Void, List<Comentario>> {

		@Override
		protected void onPreExecute() {
			getSherlock().setProgressBarIndeterminateVisibility(true);
			super.onPreExecute();
		}

		@Override
		protected List<Comentario> doInBackground(Void... params) {
			comentarios= null;
			try {
				comentarios =  (List<Comentario>) Conexion.obtenerComentariosEvento(String.valueOf(evento.getIdEvento()));
			} catch (ExcepcionAplicacion e) {
				TnUtil.escribeLog("ERROR al obtener lista de comentarios");
			}
			return comentarios;
		}

		
		protected void onPostExecute(List<Comentario> result) {
			if (result == null || result.size()==0){
				Comentario mC=new Comentario();
				if (result.size()==0)
				   mC.setComentario(findViewById(R.string.comentarios_NoHayComentarios).toString());
				else
				   mC.setComentario(findViewById(R.string.comentarios_ErrorBD).toString());
				comentarios.add(mC);
			}else{
				ComentariosAdapter adapter = new ComentariosAdapter(getApplicationContext(), R.layout.item_comentario, comentarios);
				//PORQUE ME DA ERROR la linea del setListAdapter, sera una chorrada pero llevo ya 3 horas dandole vueltas
				//TNI+XXXX, l
				//setListAdapter(adapter);
			}
			
			getSherlock().setProgressBarIndeterminateVisibility(false);
			
			super.onPostExecute(result);
		}

	}

}
