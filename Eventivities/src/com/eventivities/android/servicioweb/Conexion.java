package com.eventivities.android.servicioweb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.eventivities.android.domain.ListaObras;
import com.eventivities.android.domain.ListaTeatros;
import com.eventivities.android.excepciones.ExcepcionAplicacion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Clase encargada de las conexiones al servicio Web 
* 
* @author marcos
* 
* @see         Conexion
*/
public class Conexion {
	
	private final static String url="http://10.0.2.2/www/";
	
	/**
	 * Devuelve un listado de obras de un teatro
	* <p>
	* Si la búsqueda no produce ningún resultado, 
	* 
	*
	* @author marcos
	* @
	* @param  idTeatro el identificador único del teatro 
	* @return      la lista de obras
	* @see         Conexion
	*/
	public static ListaObras obtenerObrasTeatro(String idTeatro) throws ExcepcionAplicacion
	{
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("idteatro", idTeatro));	
		JSONObject json;
		ListaObras respuesta = null;
		try {
			json = obtenerJsonDelServicio(pairs,"service.obtenerobrasciudad.php");
			if(json!=null)
			{			
				GsonBuilder gsonBuilder = new GsonBuilder();
				Gson gson = gsonBuilder.create();
				respuesta = gson.fromJson(json.toString(), ListaObras.class);
			}	
		} catch (ClientProtocolException c)
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
	
	/**
	 * Devuelve un objeto JSON de un servicio web
	* <p>
	* Si la búsqueda no produce ningún resultado, Json será null
	* <p> 
	* 
	*
	* @author marcos
	* @exception En caso de problemas de conexión con el servicio Web lanzará una excepción
	* @param  pairs pares de valores para enviar a través de Post
	* @return      Devuelve un Objeto Json con los datos solicitados 
	* @see         Conexion
	*/
	
	private static JSONObject obtenerJsonDelServicio(List<NameValuePair> pairs, String servicio) throws ClientProtocolException, IOException, JSONException {
		HttpClient client = new DefaultHttpClient();		
		JSONObject json=null;		
		
		HttpPost request = new HttpPost(url+servicio);
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
			json = new JSONObject(responseString);						
		}			
		return json;
	
	}
	
	/**
	 * Devuelve un listado de teatros de una ciudad
	* <p>
	* Si la búsqueda no produce ningún resultado, 
	* 
	*
	* @author marcos
	* @
	* @param  idCiudad el identificador único de la ciudad 
	* @return      la lista de Teatros
	* @see         Conexion
	*/
	

	public static ListaTeatros obtenerTeatrosCiudad(String idCiudad) throws ExcepcionAplicacion
	{
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("idciudad", idCiudad));	
		JSONObject json;
		ListaTeatros respuesta = null;
		try {
			json = obtenerJsonDelServicio(pairs,"service.obtenerTeatrosciudad.php");
			if(json!=null)
			{			
				GsonBuilder gsonBuilder = new GsonBuilder();
				Gson gson = gsonBuilder.create();
				respuesta = gson.fromJson(json.toString(), ListaTeatros.class);
			}	
		} catch (ClientProtocolException c)
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
	

}
