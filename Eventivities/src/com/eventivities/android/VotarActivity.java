package com.eventivities.android;

import android.content.Context;
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
    	

        	sharingIntent.setType("text/plain");

        	EditText mTxt=(EditText) findViewById(R.id.votar_comentario);
        	String txt=mTxt.getText().toString();
        	if ( txt.trim().equals(""))
        		txt=getString(R.string.votar_compartir_sinComentario);
        	
        	RatingBar mRat=(RatingBar) findViewById(R.id.votar_ratingBar);
        	
        	String mTeatro=nombreLocal;       //"Espacio Inestable";
        	String mObra=evento.getNombre();  //"Yo nunca sere una estrella del Rock";
        	
        	//valor por defecto=3 estrellas
        	String tRat="★★★☆☆";
        	String tRatTxt=getString(R.string.votar_compartir_tresEstrella);
        	
        	int i=(int) mRat.getRating();
        	switch (i){
        		case 0: 
        			tRat="☆☆☆☆☆";
        			tRatTxt=getString(R.string.votar_compartir_ceroEstrella);
        			break;
    	        case 1:
    	        	tRat="★☆☆☆☆";
    	        	tRatTxt=getString(R.string.votar_compartir_unaEstrella);
    	        	break;
        		case 2:
        			tRatTxt=getString(R.string.votar_compartir_dosEstrella);
        			tRat="★★☆☆☆";
        			break;
        		case 4:
        			tRatTxt=getString(R.string.votar_compartir_cuatroEstrella);
	        		tRat="★★★★☆";
	        		break;
        		case 5:
        			tRatTxt=getString(R.string.votar_compartir_cincoEstrella);
	        		tRat="★★★★★";
	        		break;

        	}        		
        	//tRat+
        	
        	
        	txt=getString(R.string.votar_compartir_heEstado)+" \n" +
        			"["+mTeatro+"] "+getString(R.string.votar_compartir_viendo)+ " \"" +
        			   mObra+"\"\n  "+
        			   tRatTxt+" ("+tRat+") : " +txt;
        	sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, txt);
        	startActivity(Intent.createChooser(sharingIntent,"Share using"));
        	sharingIntent = new Intent(Intent.ACTION_SEND);
	}
}
