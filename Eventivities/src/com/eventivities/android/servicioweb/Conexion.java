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

import android.util.Log;

import com.eventivities.android.domain.ListaComentarios;
import com.eventivities.android.domain.ListaEventos;
import com.eventivities.android.domain.ListaLocales;
import com.eventivities.android.domain.ListaPuntuaciones;
import com.eventivities.android.domain.ListaPuntuacionesLocal;
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
	
	
	
	/*obtenerlocalesciudad
	 * obtenerpuntuacioneslocal
	 * obtenerlocalescercanos
	 * registrarpuntuacionlocal
	 * registrarpuntuacionevento
	 * registrarUsuario*/
	private final static String url="http://www.eventivitiesadm.eshost.es/servicioweb/";
	//private final static String url="http://10.0.2.2/www/";
	
	
	
	
	/**
	 * Devuelve un listado de eventos de un local
	* <p>
	* Si la búsqueda no produce ningún resultado, se devuelve una lista vacía  
	* 
	*
	* @author marcos
	* @
	* @param  idLocal el identificador único del local 
	* @return      la lista eventos
	* @see         Conexion
	*/
	public static ListaEventos obtenerEventosLocal(int idLocal) throws ExcepcionAplicacion
	{
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("idLocal", String.valueOf(idLocal)));	
		JSONObject json;
		ListaEventos respuesta = null;
		try {
			json = obtenerJsonDelServicio(pairs,"service.obtenereventoslocal.php");
			int exito=1;
			if(json!=null)
			{			
				if (json.has("exito"))
				{
					if(json.getString("exito").equalsIgnoreCase("1"))
					{
						GsonBuilder gsonBuilder = new GsonBuilder();
						gsonBuilder.setDateFormat("yyyy-MM-dd");
						Gson gson = gsonBuilder.create();				
						respuesta = gson.fromJson(json.toString(), ListaEventos.class);
					}
					else
					{
						exito=0;
					}
				}
				else
				{
					exito=0;
				}
				if (exito==0)
					throw new ExcepcionAplicacion("El servicio web no ha respondido con éxito",ExcepcionAplicacion.EXCEPCION_CONEXION_SERVIDOR);
				
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
	 * Devuelve un listado de puntuaciones de un evento
	* <p>
	* Si la búsqueda no produce ningún resultado, se devuelve una lista vacía  
	* 
	*
	* @author marcos
	* @
	* @param  idEvento el identificador único del Evento 
	* @return      la lista de puntuaciones
	* @see         Conexion
	*/	
	public static ListaPuntuaciones obtenerPuntuacionesEvento(String idEvento) throws ExcepcionAplicacion
	{
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("idEvento", idEvento));	
		JSONObject json;
		ListaPuntuaciones respuesta = null;
		try {
			json = obtenerJsonDelServicio(pairs,"service.obtenerpuntuacionesevento.php");
			int exito=1;
			if(json!=null)
			{			
				if (json.has("exito"))
				{
					if(json.getString("exito").equalsIgnoreCase("1"))
					{
						GsonBuilder gsonBuilder = new GsonBuilder();						
						Gson gson = gsonBuilder.create();				
						respuesta = gson.fromJson(json.toString(), ListaPuntuaciones.class);
					}
					else
					{
						exito=0;
					}
				}
				else
				{
					exito=0;
				}
				if (exito==0)
					throw new ExcepcionAplicacion("El servicio web no ha respondido con exito",ExcepcionAplicacion.EXCEPCION_CONEXION_SERVIDOR);
				
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
	 * Devuelve un listado de LocalesCiudad
	* <p>
	* Si la búsqueda no produce ningún resultado, se devuelve una lista vacía  
	* 
	*
	* @author marcos
	* @
	* @param  idEvento el identificador único del Evento 
	* @return      la lista de puntuaciones
	* @see         Conexion
	*/	
	public static ListaLocales obtenerLocalesCiudad(String ciudad) throws ExcepcionAplicacion
	{	
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("ciudad", ciudad));	
		JSONObject json;
		ListaLocales respuesta = null;
		try {
			json = obtenerJsonDelServicio(pairs,"service.obtenerlocalesciudad.php");
			int exito=1;
			if(json!=null)
			{			
				if (json.has("exito"))
				{
					if(json.getString("exito").equalsIgnoreCase("1"))
					{
						GsonBuilder gsonBuilder = new GsonBuilder();						
						Gson gson = gsonBuilder.create();				
						respuesta = gson.fromJson(json.toString(), ListaLocales.class);
					}
					else
					{
						exito=0;
					}
				}
				else
				{
					exito=0;
				}
				if (exito==0)
					throw new ExcepcionAplicacion("El servicio web no ha respondido con exito",ExcepcionAplicacion.EXCEPCION_CONEXION_SERVIDOR);
				
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
	 * Devuelve un listado de puntuaciones de un local
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
	public static ListaPuntuacionesLocal obtenerPuntuacionesLocal(String idLocal) throws ExcepcionAplicacion
	{	
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("idLocal", idLocal));	
		JSONObject json;
		ListaPuntuacionesLocal respuesta = null;
		try {
			json = obtenerJsonDelServicio(pairs,"service.obtenercomentariosevento.php");
			int exito=1;
			if(json!=null)
			{			
				if (json.has("exito"))
				{
					if(json.getString("exito").equalsIgnoreCase("1"))
					{
						GsonBuilder gsonBuilder = new GsonBuilder();						
						Gson gson = gsonBuilder.create();				
						respuesta = gson.fromJson(json.toString(), ListaPuntuacionesLocal.class);
					}
					else
					{
						exito=0;
					}
				}
				else
				{
					exito=0;
				}
				if (exito==0)
					throw new ExcepcionAplicacion("El servicio web no ha respondido con exito",ExcepcionAplicacion.EXCEPCION_CONEXION_SERVIDOR);
				
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
	 * Devuelve un listado de comentarios de un evento
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
	public static ListaComentarios obtenerComentariosEvento(String idEvento) throws ExcepcionAplicacion
	{	
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("idEvento", idEvento));	
		JSONObject json;
		ListaComentarios respuesta = null;
		try {
			json = obtenerJsonDelServicio(pairs,"service.obtenercomentariosevento.php");
			int exito=1;
			if(json!=null)
			{			
				if (json.has("exito"))
				{
					if(json.getString("exito").equalsIgnoreCase("1"))
					{
						GsonBuilder gsonBuilder = new GsonBuilder();						
						Gson gson = gsonBuilder.create();				
						respuesta = gson.fromJson(json.toString(), ListaComentarios.class);
					}
					else
					{
						exito=0;
					}
				}
				else
				{
					exito=0;
				}
				if (exito==0)
					throw new ExcepcionAplicacion("El servicio web no ha respondido con exito",ExcepcionAplicacion.EXCEPCION_CONEXION_SERVIDOR);
				
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
	
	/** TODO
	 * SE PUEDE ELIMINAR. NO LO ELIMINO PARA EVITAR CONFLICTOS.
	 * Devuelve true si el usuario existe y su contraseña es correcta. 
	 * En caso contrario devolverá false.
	 * 
	 *  @author emilio 
	 *  @
	 *  @param username, password
	 *  @return boolean que indica si los datos introducidos son correctos
	 *  @see Conexion
	*/
	
	public static Boolean comprobarDatosLogIn(String username, String password)throws ExcepcionAplicacion{
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("username", username));
		pairs.add(new BasicNameValuePair("password", password));
		boolean respuesta = false;
		JSONObject json;		
		try {
			json = obtenerJsonDelServicio(pairs, "service.comprobarDatosLogin.php");
			int exito=1;
			if(json != null){
				if(json.has("exito") && json.getString("exito").equalsIgnoreCase("1")){
					respuesta = true;
				}else{
					exito = 0;
				}
				if(exito==0){
					throw new ExcepcionAplicacion("El usuario o la contraseña no son correctos.", ExcepcionAplicacion.EXCEPCION_CONEXION_SERVIDOR); //TODO modificar mensaje
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			throw new ExcepcionAplicacion(e.getMessage(),ExcepcionAplicacion.EXCEPCION_CONEXION_SERVIDOR);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExcepcionAplicacion(e.getMessage(),ExcepcionAplicacion.EXCEPCION_CONEXION_SERVIDOR);
		} catch (JSONException e) {
			e.printStackTrace();
			throw new ExcepcionAplicacion(e.getMessage(),ExcepcionAplicacion.EXCEPCION_CONEXION_SERVIDOR);
		} catch (Exception e){
			e.printStackTrace();
			throw new ExcepcionAplicacion(e.getMessage(),ExcepcionAplicacion.EXCEPCION_CONEXION_SERVIDOR);
		}		
		return respuesta;
	}
	
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
	 * Trata de identificar a un usuario a través de su alias y su clave.
	* <p>
	* 
	* 
	*
	* @author marcos
	* @
	* @param  usuario
	* @param  clave
	* @return Devuelve un booleano que determina si identificacion es correcta   
	* @see         Conexion
	*/
	public static boolean identificarse(String usuario, String clave) throws ExcepcionAplicacion
	{

		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("username", usuario));	
		pairs.add(new BasicNameValuePair("password", clave));
		JSONObject json;
		boolean respuesta=true;
		try {
			json = obtenerJsonDelServicio(pairs,"service.comprobarDatosLogin.php");
			int exito=1;
			if(json!=null)
			{			
				if (json.has("exito"))
				{
					if(json.getString("exito").equalsIgnoreCase("1"))
					{
						if (json.has("usuario"))
						{
							if(!json.getString("usuario").equalsIgnoreCase("1"))
							{
								respuesta=false;
							}
						}
						else
						{
							exito=0;
						}						
					}
					else
					{
						exito=0;
					}
				}
				else
				{
					exito=0;
				}
				if (exito==0){
					throw new ExcepcionAplicacion("El servicio web no ha respondido con éxito",ExcepcionAplicacion.EXCEPCION_DATOS_ERRONEOS);
					
				}
				
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
