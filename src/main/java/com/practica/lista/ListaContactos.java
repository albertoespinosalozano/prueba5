package com.practica.lista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.practica.genericas.Coordenada;
import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;
import com.practica.utilidades.UtilidadesFechaHora;

public class ListaContactos {
	private NodoTemporal lista;
	private int size;
	
	/**
	 * Insertamos en la lista de nodos temporales, y a la vez inserto en la lista de nodos de coordenadas. 
	 * En la lista de coordenadas metemos el documento de la persona que está en esa coordenada 
	 * en un instante 
	 */
	public void insertarNodoTemporal (PosicionPersona p) {
		NodoTemporal aux = lista, ant=null;	
		boolean encontrado = false;
		encontrado = this.buscarEnLista(p, ListaContactos.mapNodosTemporales(aux, ant));
		if(!encontrado)
			nuevoNodoLista(p, ListaContactos.mapNodosTemporales(aux, ant));
	}
	
	private boolean buscarEnLista(PosicionPersona p, Map<String, Object> NodosTemp) { ////////////////////////////////////
		NodoTemporal aux = (NodoTemporal) NodosTemp.get("aux"), ant = (NodoTemporal) NodosTemp.get("ant");
		boolean salir = false, encontrado = false;
		while (aux!=null && !salir) {
			if(UtilidadesFechaHora.compararCon(aux.getFecha(), p.getFechaPosicion())==0) {
				encontrado = salir = true;
				insertarListaCoordenadas(p, aux);
			}else if(UtilidadesFechaHora.compararCon(aux.getFecha(), p.getFechaPosicion())<0) {
				ant = aux; aux=aux.getSiguiente();
			}else
				salir=true;
		}
		return encontrado;
	}
	/**
		 * Insertamos en la lista de coordenadas
		 */
	private void insertarListaCoordenadas(PosicionPersona p, NodoTemporal aux) {
		NodoPosicion npActual = aux.getListaCoordenadas(), npAnt=null;		
		boolean npEncontrado = buscarNodo(p, ListaContactos.mapNodosPosicion(npActual, npAnt));
		if(!npEncontrado) {
			NodoPosicion npNuevo = new NodoPosicion(ListaContactos.mapNodoNuevo(p));
			if(aux.getListaCoordenadas()==null)
				aux.setListaCoordenadas(npNuevo);
			else
				npAnt.setSiguiente(npNuevo);			
		}
	}
	
	private boolean buscarNodo(PosicionPersona p, Map<String, Object> NodosPos) {
		NodoPosicion npActual = (NodoPosicion) NodosPos.get("nodo1"), npAnt = (NodoPosicion) NodosPos.get("nodo2");
		boolean npEncontrado = false;
		while (npActual!=null && !npEncontrado) {
			if(npActual.getCoordenada().equals(p.getCoordenada())) {
				npEncontrado=true;
				npActual.setNumPersonas(npActual.getNumPersonas()+1);
			}else {
				npAnt = npActual;
				npActual = npActual.getSiguiente();
			}
		}
		return npEncontrado;
	}
	/**
		 * No hemos encontrado ninguna posición temporal, así que
		 * metemos un nodo nuevo en la lista
		 */
	private void nuevoNodoLista(PosicionPersona p, Map<String, Object> NodosTemp) {
		NodoTemporal nuevo = this.crearNuevoNodoTemporal(p, NodosTemp), aux = (NodoTemporal) NodosTemp.get("aux"), ant = (NodoTemporal) NodosTemp.get("ant");
		if(ant!=null) {
			nuevo.setSiguiente(aux);
			ant.setSiguiente(nuevo);
		}else {
			nuevo.setSiguiente(lista);
			lista = nuevo;
		}
		this.size++;	

	}
	
	private NodoTemporal crearNuevoNodoTemporal(PosicionPersona p, Map<String, Object> NodosTemp) { ////////////////////////////////////
		NodoTemporal nuevo = new NodoTemporal(), aux = (NodoTemporal) NodosTemp.get("aux"), ant = (NodoTemporal) NodosTemp.get("ant");
		nuevo.setFecha(p.getFechaPosicion());
		
		NodoPosicion npActual = nuevo.getListaCoordenadas(), npAnt = null;	
		boolean npEncontrado = buscarNodo(p, ListaContactos.mapNodosPosicion(npActual, npAnt));
		if(!npEncontrado) {
			this.nuevoNodoPosicion(ListaContactos.mapNodoPosicionYTemporal(npAnt, nuevo), p);
		}
		return nuevo;
	}
	
	private void nuevoNodoPosicion(Map<String, Object> Nodos, PosicionPersona p) {
		NodoPosicion npAnt = (NodoPosicion) Nodos.get("posicion");
		NodoTemporal nuevo = (NodoTemporal) Nodos.get("temporal");
		NodoPosicion npNuevo = new NodoPosicion(ListaContactos.mapNodoNuevo(p));				
		if(nuevo.getListaCoordenadas()==null)
			nuevo.setListaCoordenadas(npNuevo);
		else
			npAnt.setSiguiente(npNuevo);		
	}
	
	private boolean buscarPersona (String documento, ArrayList<String> lista) {
		return lista.contains(documento);
	}
	
	private void insertarPersona (String documento, ArrayList<String> lista) {
		lista.add(documento);
	}
	
	public int personasEnCoordenadas () {
		NodoPosicion aux = this.lista.getListaCoordenadas();
		if(aux==null)
			return 0;
		else {
			int cont;
			for(cont=0;aux!=null;) {
				cont += aux.getNumPersonas();
				aux=aux.getSiguiente();
			}
			return cont;
		}
	}
	
	public int tamanioLista () {
		return this.size;
	}

	public String getPrimerNodo() {
		NodoTemporal aux = lista;
		String cadena = aux.getFecha().getFecha().toString();
		cadena+= ";" +  aux.getFecha().getHora().toString();
		return cadena;
	}

	/**
	 * Métodos para comprobar que insertamos de manera correcta en las listas de 
	 * coordenadas, no tienen una utilidad en sí misma, más allá de comprobar que
	 * nuestra lista funciona de manera correcta.
	 */
	public int numPersonasEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		int cont = 0;
		if(this.size!=0) {
			NodoTemporal aux = lista;
			cont = contarNodos(aux, ListaContactos.mapIntervaloFechas(inicio, fin));
		}
		return cont;
	}
	
	private int contarNodos(NodoTemporal aux, Map<String, Object> intervalo) {
		int cont = 0;
		
		while(aux!=null) {
			cont += this.contar(aux, intervalo);
		}
		return cont;
	}
	
	private int contar(NodoTemporal aux, Map<String, Object> intervalo) {
		int cont = 0;
		FechaHora inicio = (FechaHora) intervalo.get("inicio"), fin = (FechaHora) intervalo.get("fin");
		if(UtilidadesFechaHora.compararCon(aux.getFecha(), inicio)>=0 && UtilidadesFechaHora.compararCon(aux.getFecha(), fin)<=0) {
			NodoPosicion nodo = aux.getListaCoordenadas();
			while(nodo!=null) {
				cont += nodo.getNumPersonas();
				nodo = nodo.getSiguiente();
			}				
			aux = aux.getSiguiente();
		}else {
			aux=aux.getSiguiente();
		}
		return cont;
	}
	
	
	
	public int numNodosCoordenadaEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		int cont = 0;
		if(this.size!=0) {
			NodoTemporal aux = lista;
			cont = contarNodos(aux, ListaContactos.mapIntervaloFechas(inicio, fin));
		}
		return cont;
	}
	
	
	
	@Override
	public String toString() {
		String cadena="";
		int cont;
		cont=0;
		NodoTemporal aux = lista;
		for(cont=1; cont<size; cont++) {
			cadena += aux.getFecha().getFecha().toString();
			cadena += ";" +  aux.getFecha().getHora().toString() + " ";
			aux=aux.getSiguiente();
		}
		cadena += aux.getFecha().getFecha().toString();
		cadena += ";" +  aux.getFecha().getHora().toString();
		return cadena;
	}
	
	public static Map<String, Object> mapNodoNuevo(PosicionPersona p) {
		Map<String,Object> nuevoNodo = new HashMap<String,Object>();
		nuevoNodo.put("coordenada", p.getCoordenada());
		nuevoNodo.put("numPersonas", 1);
		return nuevoNodo;
	}
	
	public static Map<String, Object> mapNodosTemporales(NodoTemporal aux, NodoTemporal ant) {
		Map<String,Object> nodosTemp = new HashMap<String,Object>();
		nodosTemp.put("aux", aux);
		nodosTemp.put("ant", ant);
		return nodosTemp;
	}
	
	public static Map<String, Object> mapNodoPosicionYTemporal(NodoPosicion npAnt, NodoTemporal nuevo) {
		Map<String,Object> nodosPosTemp = new HashMap<String,Object>();
		nodosPosTemp.put("posicion", npAnt);
		nodosPosTemp.put("temporal", nuevo);
		return nodosPosTemp;
	}
	
	public static Map<String, Object> mapNodosPosicion(NodoPosicion npA, NodoPosicion npB) {
		Map<String,Object> nodosPos = new HashMap<String,Object>();
		nodosPos.put("nodo1", npA);
		nodosPos.put("nodo2", npB);
		return nodosPos;
	}
	
	public static Map<String, Object> mapIntervaloFechas(FechaHora inicio, FechaHora fin) {
		Map<String,Object> intervalo = new HashMap<String,Object>();
		intervalo.put("inicio", inicio);
		intervalo.put("fin", fin);
		return intervalo;
	}
}
