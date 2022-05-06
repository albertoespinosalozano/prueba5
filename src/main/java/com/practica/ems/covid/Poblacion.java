package com.practica.ems.covid;


import java.util.Iterator;
import java.util.LinkedList;
import com.practica.excecption.EmsExceptions;
import com.practica.genericas.Persona;

public class Poblacion {
	LinkedList<Persona> lista ;

	public Poblacion() {
		super();
		this.lista = new LinkedList<Persona>();
	}
	
	public LinkedList<Persona> getLista() {
		return lista;
	}

	public void setLista(LinkedList<Persona> lista) {
		this.lista = lista;
	}

	public void addPersona (Persona persona) throws EmsExceptions.EmsDuplicatePersonException {
		try {
			findPersona(persona.getDocumento());
			throw new EmsExceptions.EmsDuplicatePersonException();
		} catch (EmsExceptions.EmsPersonNotFoundException e) {
			lista.add(persona);
		} 
	}
	
	public void delPersona(String documento) throws EmsExceptions.EmsPersonNotFoundException {
		int pos=-1;
		/**
		 * Busca la persona por documento, en caso de encontrarla
		 * devuelve la posición dentro de la lista, sino está lanza
		 * una excepción
		 */
		try {
			pos = findPersona(documento);
		} catch (EmsExceptions.EmsPersonNotFoundException e) {
			throw new EmsExceptions.EmsPersonNotFoundException();
		}
		lista.remove(pos);		
	}
	
	public int findPersona (String documento) throws EmsExceptions.EmsPersonNotFoundException {
		int cont=0;
		Iterator<Persona> it = lista.iterator();
		while (it.hasNext() ) {
			Persona persona = it.next();
			cont++;
			if(persona.getDocumento().equals(documento)) {
				return cont;
			}
		}		
		throw new EmsExceptions.EmsPersonNotFoundException();
	}
	
	public void printPoblacion() {   
		System.out.printf(this.toString());
      
	}

	@Override
	public String toString() {
		String cadena = "";
		for(int i = 0; i < lista.size(); i++) {   	    	
	        cadena+=lista.get(i).toString();
		}
		return cadena;
	}
	
	
	
}
