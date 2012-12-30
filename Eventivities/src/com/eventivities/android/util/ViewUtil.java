package com.eventivities.android.util;

import java.util.Date;

/**
 * @author jorge
 *
 */
public class ViewUtil {
	
	public static final String FORMATO_FECHA = "%1$te/%1$tm/%1$tY";
	
	public static String fechaConFormato(Date fecha) {
		return String.format(FORMATO_FECHA, fecha);
	}
	
	public static String rangoFecha(String formatoRango, Date inicio, Date fin) {
		String fechaInicio = String.format(FORMATO_FECHA, inicio);
		String fechaFin = String.format(FORMATO_FECHA, fin);
		
		return String.format(formatoRango, fechaInicio, fechaFin);
	}
	
	// TODO lógica del método. Puntuaciones 1 - 5.
	public static String obtenerEstrellas(Double puntuacion) {
		return "★★★☆☆";
	}
}
