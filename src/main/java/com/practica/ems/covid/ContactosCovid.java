package com.practica.ems.covid;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import com.practica.excecption.EmsExceptions;


import com.practica.genericas.Constantes;
import com.practica.genericas.Coordenada;
import com.practica.genericas.Persona;
import com.practica.genericas.PosicionPersona;
import com.practica.lista.ListaContactos;
import com.practica.utilidades.UtilidadesFechaHora;

public class ContactosCovid {
	private Poblacion poblacion;
	private Localizacion localizacion;
	private ListaContactos listaContactos;

	public ContactosCovid() {
		this.poblacion = new Poblacion();
		this.localizacion = new Localizacion();
		this.listaContactos = new ListaContactos();
	}

	public Poblacion getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(Poblacion poblacion) {
		this.poblacion = poblacion;
	}

	public Localizacion getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}
	
	

	public ListaContactos getListaContactos() {
		return listaContactos;
	}

	public void setListaContactos(ListaContactos listaContactos) {
		this.listaContactos = listaContactos;
	}

	public void loadData(String data, boolean reset) throws EmsExceptions.EmsInvalidTypeException, EmsExceptions.EmsInvalidNumberOfDataException,
	EmsExceptions.EmsDuplicatePersonException, EmsExceptions.EmsDuplicateLocationException {
		// borro información anterior
		if (reset) {
			this.poblacion = new Poblacion();
			this.localizacion = new Localizacion();
			this.listaContactos = new ListaContactos();
		}
		String datas[] = dividirEntrada(data);
		this.comprobarFichero(datas);
	}

	@SuppressWarnings("resource")
	public void loadDataFile(String fichero, boolean reset) {
		try {
			if (reset) {
				this.poblacion = new Poblacion();
				this.localizacion = new Localizacion();
				this.listaContactos = new ListaContactos();
			}
			//Uso de metodo auxiliar para abrir el fichero y leerlo
			leerFicheroAux(fichero);
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	void leerFicheroAux(String fichero) throws Exception {
		String data, datas[];
		// Apertura del fichero y creacion de BufferedReader para poder
		// hacer una lectura comoda (disponer del metodo readLine()).
		try (FileReader fr = new FileReader(new File(fichero));
			 BufferedReader br = new BufferedReader(fr)) {
			/** Lectura del fichero	línea a línea. Compruebo que cada línea 
			 * tiene el tipo PERSONA o LOCALIZACION y cargo la línea de datos en la 
			 * lista correspondiente. Sino viene ninguno de esos tipos lanzo una excepción */
			while ((data = br.readLine()) != null) {
				datas = dividirEntrada(data.trim());
				this.comprobarFichero(datas);
			}
		}
	}
	
	public int findPersona(String documento) throws EmsExceptions.EmsPersonNotFoundException {
		int pos;
		try {
			pos = this.poblacion.findPersona(documento);
			return pos;
		} catch (EmsExceptions.EmsPersonNotFoundException e) {
			throw new EmsExceptions.EmsPersonNotFoundException();
		}
	}


	public int findLocalizacion(Map<String, Object> m) throws EmsExceptions.EmsLocalizationNotFoundException {
		int pos;
		try {
			pos = localizacion.findLocalizacion((String) m.get("documento"), (String) m.get("fecha"), (String) m.get("hora"));
			return pos;
		} catch (EmsExceptions.EmsLocalizationNotFoundException e) {
			throw new EmsExceptions.EmsLocalizationNotFoundException();
		}
	}

	public List<PosicionPersona> localizacionPersona(String documento) throws EmsExceptions.EmsPersonNotFoundException {
		int cont = 0;
		List<PosicionPersona> lista = new ArrayList<PosicionPersona>();
		Iterator<PosicionPersona> it = this.localizacion.getLista().iterator();
		while (it.hasNext()) {
			PosicionPersona pp = it.next();
			if (pp.getDocumento().equals(documento)) {
				cont++; lista.add(pp);
			}
		}
		if (cont == 0)
			throw new EmsExceptions.EmsPersonNotFoundException();
		else
			return lista;
	}

	public void delPersona(String documento) throws EmsExceptions.EmsPersonNotFoundException {
		try {
			this.poblacion.delPersona(documento);
		} catch (EmsExceptions.EmsPersonNotFoundException e) {
			throw new EmsExceptions.EmsPersonNotFoundException();
		}
	}

	private String[] dividirEntrada(String input) {
		String cadenas[] = input.split("\\n");
		return cadenas;
	}

	private String[] dividirLineaData(String data) {
		String cadenas[] = data.split("\\;");
		return cadenas;
	}

	private Persona crearPersona(String[] data) {
		Persona persona = new Persona(data[2],data[3],data[1],data[4],data[5],UtilidadesFechaHora.parsearFecha(data[7]));
		persona.setCp(data[6]);
		return persona;
	}

	private PosicionPersona crearPosicionPersona(String[] data) {
		PosicionPersona posicionPersona = new PosicionPersona();
		posicionPersona.setDocumento(data[1]);
		posicionPersona.setCoordenada(new Coordenada(Float.parseFloat(data[4]),Float.parseFloat(data[5])));
		posicionPersona.setFechaPosicion(UtilidadesFechaHora.parsearFecha(data[2], data[3]));
		return posicionPersona;
	}
	
	private void comprobarFichero(String[] datas) throws EmsExceptions.EmsInvalidTypeException, EmsExceptions.EmsInvalidNumberOfDataException, EmsExceptions.EmsDuplicatePersonException, EmsExceptions.EmsDuplicateLocationException {
		for (String linea : datas) {
			String datos[] = this.dividirLineaData(linea);
			if (!datos[0].equals("PERSONA") && !datos[0].equals("LOCALIZACION")) {
				throw new EmsExceptions.EmsInvalidTypeException();
			}
			if (datos[0].equals("PERSONA")) {
				if (datos.length != Constantes.MAX_DATOS_PERSONA) {
					throw new EmsExceptions.EmsInvalidNumberOfDataException("El número de datos para PERSONA es menor de 8");
				}
				this.poblacion.addPersona(this.crearPersona(datos));
			}
			if (datos[0].equals("LOCALIZACION")) {
				if (datos.length != Constantes.MAX_DATOS_LOCALIZACION) {
					throw new EmsExceptions.EmsInvalidNumberOfDataException("El número de datos para LOCALIZACION es menor de 6" );
				}		
				this.crearPosicion(datos);
			}
		}
		
	}
	
	private void crearPosicion(String[] datos) throws EmsExceptions.EmsDuplicateLocationException {
		PosicionPersona pp = this.crearPosicionPersona(datos);
		this.localizacion.addLocalizacion(pp);
		this.listaContactos.insertarNodoTemporal(pp);
	}
}
