package com.eventivities.android.handlers;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.eventivities.android.domain.Producto;

public class EventoHandler {

	private Context context;
	private static final List<Producto> eventos = new ArrayList<Producto>();
	
	static {
		eventos.add(new Producto(0, "A prop"));
		eventos.add(new Producto(1, "Accidia. Danza contemporánea"));
		eventos.add(new Producto(2, "Agárrame a esos fantasmas"));
		eventos.add(new Producto(3, "Algo de mí..."));
		eventos.add(new Producto(4, "Alucina"));
		eventos.add(new Producto(5, "Argos, una arriesgada misión"));
		eventos.add(new Producto(6, "Caputxeta més enllà del bosc"));
	}

	public EventoHandler(Context context) {
		this.context = context;
	}
	
	public List<Producto> obtenerTodos () {
		return eventos;
	}
	
	public Producto obtenerEvento(int eventoId) {
		return eventos.get(eventoId);
	}
}
