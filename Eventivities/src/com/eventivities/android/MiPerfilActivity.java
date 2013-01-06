package com.eventivities.android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.eventivities.android.excepciones.ExcepcionAplicacion;
import com.eventivities.android.servicioweb.Conexion;

public class MiPerfilActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_mi_perfil);
		getSupportActionBar().setHomeButtonEnabled(true);
		
		Button btnAceptar = (Button) findViewById(R.id.buttonLogIn);
		btnAceptar.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Boolean logueado = comprobarUsuarioLogueado();
				if(!logueado){
					TextView textUser = (TextView) findViewById(R.id.editTextUserName);
					TextView textPass = (TextView) findViewById(R.id.editTextUserPassword);
					String user = textUser.getText().toString();
					String pass = textPass.getText().toString();										
					
					LogInAsyncTask task = new LogInAsyncTask();
					task.setUser(user);
					task.setPassword(pass);
					task.setContext(MiPerfilActivity.this);
					task.execute();
				}else{					
					guardarPreferenciasLogOut();
					String textoBoton = getResources().getString(R.string.textButtonLogIn);
					actualizarTextoBotonLogIn(textoBoton);
					actualizarElementosInterfaz(true);
				}
			}	
		});
		btnAceptar.setEnabled(false);
		
		Button btnRegistrar = (Button) findViewById(R.id.buttonRegistro);
		btnRegistrar.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				
			}
		});
		
		EditText editTextUser = (EditText) findViewById(R.id.editTextUserName);
		editTextUser.addTextChangedListener(new TextWatcher(){
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count){
				Button btnAceptar = (Button) findViewById(R.id.buttonLogIn); 
				boolean camposCompletos = comprobarCamposCompletos();
				if(camposCompletos)
					btnAceptar.setEnabled(true);
				else
					btnAceptar.setEnabled(false);					
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

		});
		
		EditText editTextPass = (EditText) findViewById(R.id.editTextUserPassword);
		editTextPass.addTextChangedListener(new TextWatcher(){
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count){
				Button btnAceptar = (Button) findViewById(R.id.buttonLogIn); 
				boolean camposCompletos = comprobarCamposCompletos();
				if(camposCompletos)
					btnAceptar.setEnabled(true);
				else
					btnAceptar.setEnabled(false);					
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
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
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		cargarPreferencias();
		super.onResume();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			startActivity(new Intent(MiPerfilActivity.this, LocalesActivity.class));
			break;
		case R.id.menu_login:
			startActivity(new Intent(MiPerfilActivity.this, MiPerfilActivity.class));
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * Método que guarda las preferencias cuando un usuario hace LogIn
	 * en la aplicación. Se encarga de guardar los datos 'username' y 'password'
	 * además de actualizar el campo 'logIn' a true.
	 * 
	 * @author emilio
	 * @param No necesita parámetros de entrada
	 * @return No retorna ningún valor. Se crea o actualiza el fichero de preferencias
	 * @see SharedPreferences
	 * */
	public void guardarPreferenciasLogIn(){
		SharedPreferences prefs = getSharedPreferences("LogInPreferences", Context.MODE_PRIVATE);
		EditText userEditText = (EditText) findViewById(R.id.editTextUserName);
		EditText passEditText = (EditText) findViewById(R.id.editTextUserPassword);
		
		Editor editor = prefs.edit();
		editor.putString("usuarioActual", userEditText.getText().toString());
		editor.putString("passActual", passEditText.getText().toString());
		editor.putBoolean("logIn", true);
		editor.commit();
	}
	
	/**
	 * Método que guarda las preferencias cuando un usuario hace LogOut
	 * en la aplicación. Se encarga de cambiar a false el valor del campo
	 * 'logIn' del fichero.
	 * 
	 * @author emilio
	 * @param No necesita parámetros de entrada
	 * @return No retorna ningún valor. Se actualiza el fichero de preferencias
	 * @see SharedPreferences, guardarPreferenciasLogIn
	 * */
	public void guardarPreferenciasLogOut(){
		SharedPreferences prefs = getSharedPreferences("LogInPreferences", Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putBoolean("logIn", false);
		editor.commit();
	}
	
	/**
	 * Método que carga información relativa al usuario logueado en la aplicación.
	 * También configura elementos de la interfaz gráfica.
	 * 
	 * @author emilio
	 * @param No necesita parámetros
	 * @return No retorna ningún valor. Actualiza la interfaz gráfica
	 * @see 
	 * */
	public void cargarPreferencias(){
		SharedPreferences prefs = getSharedPreferences("LogInPreferences", Context.MODE_PRIVATE);
		EditText editTextUser = (EditText) findViewById(R.id.editTextUserName);
		EditText editTextPass = (EditText) findViewById(R.id.editTextUserPassword);
		if(prefs != null && prefs.getBoolean("logIn", false)){
			editTextUser.setText(prefs.getString("usuarioActual", " "));
			editTextPass.setText(prefs.getString("passActual", " "));
			actualizarTextoBotonLogIn(this.getResources().getString(R.string.textButtonLogOut));
			actualizarElementosInterfaz(false);
			
		}
	}
	
	/**
	 * Método encargado de modificar el texto del boton de LogIn cuando el usuario hace
	 * login y logout.
	 * 
	 * @author emilio
	 * @param texto
	 * @return No retorna ningún valor directamente.
	 * @see
	 * 
	 * */
	public void actualizarTextoBotonLogIn(String texto){
		Button botonLogIn = (Button) findViewById(R.id.buttonLogIn);
		botonLogIn.setText(texto);
	}
	
	/**
	 * Método que habilita y deshabilita los campos de texto de la interfaz gráfica
	 * cuando el usuario hace login y logout en la aplicación. Al hacer login se deshabilitarán
	 * para así conservar los datos introducidos. Una vez se haga logout se habilitarán para que puedan
	 * ser utilizados de nuevo.
	 * 
	 * @author emilio
	 * @param No se necesitan parámetros
	 * @return No retorna ningún valor. Tan sólo actualizará la IGU
	 * @see
	 * */
	public void actualizarElementosInterfaz(Boolean nuevoEstado){
		EditText eTextUser = (EditText) findViewById(R.id.editTextUserName);
		eTextUser.setEnabled(nuevoEstado);
		eTextUser.clearFocus();
		EditText eTextPass = (EditText) findViewById(R.id.editTextUserPassword);
		eTextPass.setEnabled(nuevoEstado);
		eTextPass.clearFocus();
		
	}
	
	/**
	 * Método que comprueba si el usuario está logueado en la apliación.
	 * 
	 * @author emilio
	 * @param No necesita parámetros. Consulta el fichero de preferencias
	 * @return Retorna true si el usuario ha hecho LogIn y false en caso contrario.
	 * @see 
	 * */
	public Boolean comprobarUsuarioLogueado(){
		SharedPreferences prefs = getSharedPreferences("LogInPreferences", Context.MODE_PRIVATE);
		Boolean logueado = prefs.getBoolean("logIn", false);
		return logueado;
	}
	
	/**
	 * 
	 * Método que comprueba si los campos Usuario y Contraseña de la interfaz tienen texto.
	 * Si ambos campos tienen texto se habilitará el botón Aceptar. En caso contrario permanecerá deshabilitado.
	 *
	 * @author emilio
	 * @param No necesita parámetros.
	 * @return True si ambos campos de texto están completos. False en caso contrario
	 * 
	 * */
	public boolean comprobarCamposCompletos(){
		boolean completos;
		EditText editTextUser = (EditText) findViewById(R.id.editTextUserName);
		EditText editTextPass = (EditText) findViewById(R.id.editTextUserPassword);
		if(editTextUser.getText().toString().equals("") || editTextPass.getText().toString().equals(""))
			completos = false;
		else 
			completos = true;
		return completos;
	}
	
	/**
	 * Clase encargada de realizar las labores de la tarea asíncrona que gestiona
	 * el logIn del usuario en la aplicación
	 * 
	 * @author emilio
	 * @param 
	 * @result No retorna ningún valor. Se encarga de invocar a los métodos necesarios
	 * 		   para actualizar el fichero de preferencias y la interfaz de la aplicación
	 * @see 
	 * */
	private class LogInAsyncTask extends AsyncTask <Void, Void, Boolean>{

		private String user, pass;
		private Context context;
		
		@Override
		protected void onPreExecute(){
			getSherlock().setProgressBarIndeterminateVisibility(true);
			//Toast.makeText(context, "Conectando con el servidor", Toast.LENGTH_LONG).show(); //TODO provisional
			super.onPreExecute();
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			getSherlock().setProgressBarIndeterminateVisibility(false);
			if(result){
				guardarPreferenciasLogIn();
				String textoBoton = getResources().getString(R.string.textButtonLogOut);
				actualizarTextoBotonLogIn(textoBoton);
				actualizarElementosInterfaz(false);
				Toast.makeText(context, context.getString(R.string.textLoginOk), Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(context, context.getString(R.string.textLoginServerError), Toast.LENGTH_LONG).show();
			}
			super.onPostExecute(result);
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			boolean existe = false;
			try{
				existe = Conexion.identificarse(user, pass);
			} catch (ExcepcionAplicacion e){
				e.printStackTrace();			
			}
			return existe;
		}
		
		/*Atributos necesarios para conectar con el servidor*/
		public void setContext(Context context){
			this.context = context;
		}
		public void setUser(String user){
			this.user = user;
		}
		public void setPassword(String pass){
			this.pass = pass;
		}
	}
}
