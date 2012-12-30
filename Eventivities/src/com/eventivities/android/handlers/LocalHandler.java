package com.eventivities.android.handlers;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.Toast;

import com.eventivities.android.domain.ListaLocales;
import com.eventivities.android.domain.Local;
import com.eventivities.android.domain.Producto;
import com.eventivities.android.excepciones.ExcepcionAplicacion;
import com.eventivities.android.servicioweb.Conexion;

public class LocalHandler {

	private Context context;
	private static final List<Producto> locales = new ArrayList<Producto>();
	
	static {
		locales.add(new Producto(0, "Teatro Santa Catalina"));
		locales.add(new Producto(1, "Sala iTalia"));
		locales.add(new Producto(2, "Teatro Flunken"));
		locales.add(new Producto(3, "Sala el Auditorio"));
		locales.add(new Producto(4, "Teatro Olímpico"));
	}

	public LocalHandler(Context context) {
		this.context = context;
	}
	
	/*public List<Producto> obtenerTodos () {
		return locales;
	}*/
	
	public List<Local> obtenerTodos() throws ExcepcionAplicacion {		
		ListaLocales listaLocales;
		listaLocales = Conexion.obtenerLocalesCiudad("Valencia");
		return listaLocales.getLocales();
	}
	
	public Producto obtenerLocal(int localId) {
		return locales.get(localId);
	}
}
