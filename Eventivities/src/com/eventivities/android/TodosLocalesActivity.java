package com.eventivities.android;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;

public class TodosLocalesActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todos_locales);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		// ANIMACION DE SALIDA
    	overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
	}

}
