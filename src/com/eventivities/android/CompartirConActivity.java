package com.eventivities.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CompartirConActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compartir_con);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_compartir_con, menu);
		return true;
	}



}
