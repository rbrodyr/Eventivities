package com.eventivities.android.handlers;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.eventivities.android.domain.Producto;

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
	
	public List<Producto> obtenerTodos () {
		return locales;
	}
	
	public Producto obtenerLocal(int localId) {
		return locales.get(localId);
	}
}
