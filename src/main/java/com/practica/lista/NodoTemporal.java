package com.practica.lista;

import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;


/**
 * Nodo para guardar un instante de tiempo. Además guardamos una lista con las coordeandas
 * y las personas (solo número) que en ese instante están en una coordeanda en concreto  
 *
 */
public class NodoTemporal {
	private NodoPosicion listaCoordenadas;
	private FechaHora fecha;
	private NodoTemporal siguiente;
	
	
	public NodoTemporal() {
		super();
		siguiente = null;
		listaCoordenadas = null;
	}
	public NodoTemporal(FechaHora f) {
		super();
		fecha = f;
		siguiente = null;
		listaCoordenadas = null;
	}
	
	public NodoPosicion getListaCoordenadas() {
		return listaCoordenadas;
	}
	public void setListaCoordenadas(NodoPosicion listaCoordenadas) {
		this.listaCoordenadas = listaCoordenadas;
	}
	public FechaHora getFecha() {
		return fecha;
	}
	public void setFecha(FechaHora fecha) {
		this.fecha = fecha;
	}
	public NodoTemporal getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(NodoTemporal siguiente) {
		this.siguiente = siguiente;
	}	
}
