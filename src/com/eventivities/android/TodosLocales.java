package com.eventivities.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TodosLocales extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todos_locales);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_todos_locales, menu);
		return true;
	}

	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		// ANIMACION DE SALIDA
    	overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
	}
	
}
