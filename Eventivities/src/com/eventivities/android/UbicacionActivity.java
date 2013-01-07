package com.eventivities.android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class UbicacionActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ubicacion);
		getSupportActionBar().setHomeButtonEnabled(true);
		
		TextView textView = (TextView) findViewById(R.id.textViewUbicacion);
		textView.setText("Su ubicación actual está establecida en <b>Valencia</b>.");
		Button buttonCambiar = (Button) findViewById(R.id.buttonCambiar);
		buttonCambiar.setText("Cambiar ciudad...");
		buttonCambiar.setEnabled(false);
		Button buttonMaps = (Button) findViewById(R.id.buttonMaps);
		buttonMaps.setText("Qué tengo cerca");
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
			startActivity(new Intent(UbicacionActivity.this, LocalesActivity.class)
			.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
			break;
		case R.id.menu_login:
			startActivity(new Intent(UbicacionActivity.this, MiPerfilActivity.class)
			.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
