package com.practica.ems.covid;


import java.util.Iterator;
import java.util.LinkedList;
import com.practica.excecption.EmsExceptions;
import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;
import com.practica.utilidades.UtilidadesFechaHora;

public class Localizacion {
	LinkedList<PosicionPersona> lista;

	public Localizacion() {
		super();
		this.lista = new LinkedList<PosicionPersona>();
	};
	
	public LinkedList<PosicionPersona> getLista() {
		return lista;
	}

	public void setLista(LinkedList<PosicionPersona> lista) {
		this.lista = lista;
	}

	public void addLocalizacion (PosicionPersona p) throws EmsExceptions.EmsDuplicateLocationException {
		try {
			findLocalizacion(p.getDocumento(), p.getFechaPosicion().getFecha().toString(),p.getFechaPosicion().getHora().toString() );
			throw new EmsExceptions.EmsDuplicateLocationException();
		}catch (EmsExceptions.EmsLocalizationNotFoundException e) {
			lista.add(p);
		}
	}
	
	public int findLocalizacion (String documento, String fecha, String hora) throws EmsExceptions.EmsLocalizationNotFoundException {
	    int cont = 0;
	    Iterator<PosicionPersona> it = lista.iterator();
	    while(it.hasNext()) {
	    	cont++;
	    	PosicionPersona pp = it.next();
	    	FechaHora fechaHora = UtilidadesFechaHora.parsearFecha(fecha, hora);
	    	if(pp.getDocumento().equals(documento) && 
	    	  UtilidadesFechaHora.fechasIguales(pp.getFechaPosicion(), fechaHora)){
	    		return cont;
	    	}
	    } 
	    throw new EmsExceptions.EmsLocalizationNotFoundException();
	}
	public void delLocalizacion(String documento, String fecha, String hora) throws EmsExceptions.EmsLocalizationNotFoundException {
	    int pos=-1;
	    /**
	     *  Busca la localización, sino existe lanza una excepción
	     */
	    try {
			pos = findLocalizacion(documento, fecha, hora);
		} catch (EmsExceptions.EmsLocalizationNotFoundException e) {
			throw new EmsExceptions.EmsLocalizationNotFoundException();
		}
	    this.lista.remove(pos);
	    
	}
	
	void printLocalizacion() {    
	    for(int i = 0; i < this.lista.size(); i++) {
	        System.out.printf(lista.get(i).toString());
	    }
	}

	@Override
	public String toString() {
		String cadena = "";
		for(int i = 0; i < this.lista.size(); i++) {
			PosicionPersona pp = lista.get(i);
	        cadena+= pp.toString();
	    }
		return cadena;		
	}
	
}
