package com.eventivities.android;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

public class QueTengoCercaActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_que_tengo_cerca);
		getSupportActionBar().setHomeButtonEnabled(true);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		// ANIMACION DE SALIDA
    	overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent i = new Intent(QueTengoCercaActivity.this, LocalesActivity.class);
			startActivity(i);
		}
		
		return super.onOptionsItemSelected(item);
	}
}
