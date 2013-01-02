package com.eventivities.android.domain;

import java.io.Serializable;
import java.util.Date;

/*Tabla: Evento

Columna 	Tipo 	Nulo Predeterminado 
idEvento 	int(11) No 		
Nombre 		text 	No 		
Descripcion text 	No 		
FechaInicio date 	No 		
FechaFin 	date 	No 		
idLocal 	int(11)	No 		
idTipoEventoint(11)	No 		
Precio 		double 	No 	0 	
Director 	text 	No 		
Interpretes text 	Sí 	NULL
El campo media es la media de las puntuaciones para ese evento
*/

public class Evento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8444706501016450603L;
	private int idEvento=0;
	private String nombre="";
	private String descripcion="";
	private Date fechaInicio;
	private Date fechaFin;
	private int idLocal=0;
	private int idTipoEvento=0;
	private double precio=0;
	private String director="";
	private String interpretes="";
	private double media=0; 
	
	public double getMedia() {
		return media;
	}
	public void setMedia(double media) {
		this.media = media;
	}
	public int getIdTipoEvento() {
		return idTipoEvento;
	}
	public void setIdTipoEvento(int idTipoEvento) {
		this.idTipoEvento = idTipoEvento;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getInterpretes() {
		return interpretes;
	}
	public void setInterpretes(String interpretes) {
		this.interpretes = interpretes;
	}
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int getIdLocal() {
		return idLocal;
	}
	public void setIdLocal(int idLocal) {
		this.idLocal = idLocal;
	}
}
