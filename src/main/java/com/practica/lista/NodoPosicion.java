package com.practica.lista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.practica.genericas.Coordenada;
import com.practica.genericas.PosicionPersona;

/**
 * Nodo para la lista de coordenadas. En el guardamos cuántas personas  están
 * en una coordenada  en un momento temporal. 
 * También guardaremos la lista de personas que están en esa coordenada en un 
 * momento en concreto
 */
public class NodoPosicion {
	private Coordenada coordenada;	
	private int numPersonas;
	private NodoPosicion siguiente;
	
	
	public NodoPosicion() {
		super();
		siguiente = null;
	}

	
	
	
	public NodoPosicion(Map<String,Object> nuevoNodo) {
		super();
		this.coordenada = (Coordenada) nuevoNodo.get("coordenada");		
		this.numPersonas = (Integer) nuevoNodo.get("numPersonas");
		if(nuevoNodo.get("siguiente")!=null)
			this.siguiente = (NodoPosicion) nuevoNodo.get("siguiente");
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

	public int getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	public NodoPosicion getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(NodoPosicion siguiente) {
		this.siguiente = siguiente;
	}
	
	public static NodoPosicion nuevoNodoPosicionMap(PosicionPersona p) {
		Map<String,Object> nuevoNodo = new HashMap<String,Object>();
		nuevoNodo.put("coordenada", p.getCoordenada());
		nuevoNodo.put("numPersonas", 1);
		return new NodoPosicion(nuevoNodo);
	}
	
}
