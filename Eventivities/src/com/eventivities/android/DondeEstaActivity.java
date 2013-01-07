package com.eventivities.android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class DondeEstaActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_donde_esta);
		getSupportActionBar().setHomeButtonEnabled(true);
	}
	
	@Override
	protected void onResume(){
		invalidateOptionsMenu();
		super.onResume();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getSupportMenuInflater();
		menuInflater.inflate(R.menu.general, menu);
		SharedPreferences prefs = getSharedPreferences("LogInPreferences", Context.MODE_PRIVATE);
		boolean login = prefs.getBoolean("logIn", false);
		if(login)
			menu.findItem(R.id.menu_login).setTitle(prefs.getString("usuarioActual", getString(R.string.menu_login).toUpperCase()));
		else 
			menu.findItem(R.id.menu_login).setTitle(getString(R.string.menu_login));
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			startActivity(new Intent(DondeEstaActivity.this, LocalesActivity.class));
			break;
		case R.id.menu_login:
			startActivity(new Intent(DondeEstaActivity.this, MiPerfilActivity.class));
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
