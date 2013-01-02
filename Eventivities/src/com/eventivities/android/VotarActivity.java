package com.eventivities.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.eventivities.android.domain.Evento;
import com.eventivities.android.domain.Local;
import com.eventivities.android.util.TnUtil;


public class VotarActivity extends SherlockActivity {
	private Evento evento;
	private String nombreLocal;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_votar);
		getSupportActionBar().setHomeButtonEnabled(true);
        Bundle extras = getIntent().getExtras();
        
		if(extras != null || true){
			evento = (Evento) extras.getSerializable(Param.EVENTO.toString());
			nombreLocal = (String) extras.getString(Param.LOCAL_NOMBRE.toString());
			TextView nomL=(TextView) findViewById(R.id.votar_nombreTeatro);
			TextView nomE=(TextView) findViewById(R.id.votar_nombreEvento);
			nomL.setText(nombreLocal);
			nomE.setText(evento.getNombre());
		}else{
			TnUtil.escribeLog("No han llegado datos desde Ver Comentarios");
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
			startActivity(new Intent(VotarActivity.this, LocalesActivity.class));
			break;
		case R.id.menu_login:
			startActivity(new Intent(VotarActivity.this, MiPerfilActivity.class));
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public void accionVotar(View v){
		
	}
	
	public void accionCompartir(View v){
    	Intent sharingIntent = new Intent(Intent.ACTION_SEND);
    	Boolean compartirTexto =true;

        	sharingIntent.setType("text/plain");

        	EditText mTxt=(EditText) findViewById(R.id.votar_comentario);
        	String txt=mTxt.getText().toString();
        	if ( txt.trim().equals(""))
        		txt="- sin comentario -";
        	
        	RatingBar mRat=(RatingBar) findViewById(R.id.votar_ratingBar);
        	
        	String mTeatro=nombreLocal;       //"Espacio Inestable";
        	String mObra=evento.getNombre();  //"Yo nunca sere una estrella del Rock";
        	
        	//valor por defecto=3 estrellas
        	String tRat="★★★☆☆";
        	int i=(int) mRat.getRating();
        	switch (i){
        		case 0: 
        			tRat="☆☆☆☆☆";
        			break;
    	        case 1:
    	        	tRat="★☆☆☆☆";
    	        	break;
        		case 2:
        			tRat="★★☆☆☆";
        			break;
        		case 4:
	        		tRat="★★★★☆";
	        		break;
        		case 5:
	        		tRat="★★★★★";
	        		break;

        	}        		

        	txt=tRat+"He estado en \n["+mTeatro+":"+mObra+"]\n"+txt;
        	sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, txt);
        	startActivity(Intent.createChooser(sharingIntent,"Share using"));
        	sharingIntent = new Intent(Intent.ACTION_SEND);
	}
}
