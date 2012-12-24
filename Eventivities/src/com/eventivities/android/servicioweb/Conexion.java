package com.eventivities.android.servicioweb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.eventivities.android.clasesbase.Libro;
import com.eventivities.android.clasesbase.ListaLibros;
import com.eventivities.android.excepciones.ExcepcionAplicacion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Conexion {
	
	public static ListaLibros obtenerObrasCiudad(String ciudad) throws ExcepcionAplicacion
	{
		HttpClient client = new DefaultHttpClient();
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("ciudad", ciudad));	
		ListaLibros respuesta = null;
		try{
			HttpPost request = new HttpPost("http://10.0.2.2/www/service.libros.php");
			request.setHeader("Accept","application/json");	
			request.setEntity(new UrlEncodedFormEntity(pairs));
			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity(); 
			String responseString=null;			
			
			if (entity != null) { 
			InputStream stream = entity.getContent(); 
			BufferedReader reader = new BufferedReader( 
			new InputStreamReader(stream)); 
			StringBuilder sb = new StringBuilder();
			String line = null; 
			while ((line = reader.readLine()) != null) { 
				sb.append(line + "\n"); 
			}		 
			stream.close(); 
			responseString = sb.toString();
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.create();
			JSONObject json = new JSONObject(responseString);
			respuesta = gson.fromJson(json.toString(), ListaLibros.class);			  			
			}			
		}
		catch (ClientProtocolException c)
		{
			throw new ExcepcionAplicacion(c.getMessage(),ExcepcionAplicacion.EXCEPCION_CONEXION_SERVIDOR);
		} catch (JSONException e) {
			e.printStackTrace();
			throw new ExcepcionAplicacion(e.getMessage(),ExcepcionAplicacion.EXCEPCION_CONEXION_SERVIDOR);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new ExcepcionAplicacion(e.getMessage(),ExcepcionAplicacion.EXCEPCION_CONEXION_SERVIDOR);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExcepcionAplicacion(e.getMessage(),ExcepcionAplicacion.EXCEPCION_CONEXION_SERVIDOR);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ExcepcionAplicacion(e.getMessage(),ExcepcionAplicacion.EXCEPCION_CONEXION_SERVIDOR);
		}
		return respuesta;
	}

	
	public static void enviarAmigo(String name, String friendname) throws ExcepcionAplicacion
	{		
		HttpClient client = new DefaultHttpClient();
		//HttpPost request = new HttpPost("http://localhost/www/service.libro.add.php"); 
		HttpPost request = new HttpPost("http://10.0.2.2/www/service.libro.add.php");
		
		List<NameValuePair> pairs = new ArrayList<NameValuePair>(); 
		try{
			pairs.add(new BasicNameValuePair("libro", "libro7")); 
			pairs.add(new BasicNameValuePair("isbn", "isbn7"));			
			request.setEntity(new UrlEncodedFormEntity(pairs));
			client.execute(request);
			//HttpResponse response = client.execute(request);
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new ExcepcionAplicacion(e.getMessage(),ExcepcionAplicacion.EXCEPCION_CONEXION_SERVIDOR);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			throw new ExcepcionAplicacion(e.getMessage(),ExcepcionAplicacion.EXCEPCION_CONEXION_SERVIDOR);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExcepcionAplicacion(e.getMessage(),ExcepcionAplicacion.EXCEPCION_CONEXION_SERVIDOR);
		}
	}
	
	public static void registrarPuntuacion(String usuario, String puntuacion) throws ExcepcionAplicacion
	{
		HttpClient client = new DefaultHttpClient();
		HttpPut request = new HttpPut("http://soletaken.disca.upv.es:8080/WWTBAM/rest/highscores"); 
		List<NameValuePair> pairs = new ArrayList<NameValuePair>(); 
		pairs.add(new BasicNameValuePair("name",usuario ));
		pairs.add(new BasicNameValuePair("scoring", puntuacion)); 
		try {
			request.setEntity(new UrlEncodedFormEntity(pairs));			
			client.execute(request);
		}  catch (ClientProtocolException e) {
			e.printStackTrace();
			throw new ExcepcionAplicacion(e.getMessage(),ExcepcionAplicacion.EXCEPCION_CONEXION_SERVIDOR);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExcepcionAplicacion(e.getMessage(),ExcepcionAplicacion.EXCEPCION_CONEXION_SERVIDOR);
		}
	}
}
