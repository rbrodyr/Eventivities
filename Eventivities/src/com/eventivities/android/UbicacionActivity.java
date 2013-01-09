package com.eventivities.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class UbicacionActivity extends SherlockActivity {
	
	private String ciudad;
	private TextView textViewCiudad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ubicacion);
		getSupportActionBar().setHomeButtonEnabled(true);
		
		textViewCiudad = (TextView) findViewById(R.id.textViewCiudad);
		
		Spinner cuidades = (Spinner) findViewById(R.id.spinnerCambiar);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.ciudades,android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cuidades.setAdapter(adapter);

		
		cuidades.setOnItemSelectedListener(
		        new AdapterView.OnItemSelectedListener() {
		        public void onItemSelected(AdapterView<?> parent,android.view.View v, int position, long id) {
		        		ciudad = (String) parent.getItemAtPosition(position);
		                textViewCiudad.setText(ciudad);
		        }
		 
		        public void onNothingSelected(AdapterView<?> parent) {
		            
		        }
		});
		

		
		final Button buttonMaps = (Button) findViewById(R.id.buttonMaps);		
		buttonMaps.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(UbicacionActivity.this, MapsActivity.class)
				.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));				
			}
		});
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
		case R.id.menu_location:
			startActivity(new Intent(UbicacionActivity.this, UbicacionActivity.class)
			.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
