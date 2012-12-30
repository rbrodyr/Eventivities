package com.eventivities.android.util;

import java.util.Date;

/**
 * @author jorge
 *
 */
public class ViewUtil {
	
	public static final String FORMATO_FECHA = "%1$te/%1$tm/%1$tY";
	
	public static String rangoFecha(String formatoRango, Date inicio, Date fin)
	{
		String fechaInicio = String.format(FORMATO_FECHA, inicio);
		String fechaFin = String.format(FORMATO_FECHA, fin);
		
		return String.format(formatoRango, fechaInicio, fechaFin);
	}

	public static String obtenerEstrellas(Double puntuacion)
	{
		int estrellas = (int) Math.round(puntuacion);
		
		switch (estrellas) {
		case 1: return "★☆☆☆☆";
		case 2: return "★★☆☆☆";
		case 3: return "★★★☆☆";
		case 4: return "★★★★☆";
		case 5: return "★★★★★";
		default: return "☆☆☆☆☆";
		}
	}
}
