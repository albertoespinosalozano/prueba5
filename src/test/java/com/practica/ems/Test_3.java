package com.practica.ems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.practica.ems.covid.ContactosCovid;
import com.practica.excecption.EmsExceptions;
import com.practica.genericas.FechaHora;

public class Test_3 {
	private static ContactosCovid contactosCovid;

	@BeforeEach
	void setUp() {		
		contactosCovid = new ContactosCovid();
		contactosCovid.loadDataFile("datos2.txt", false);
	}
	@DisplayName("Comprobamos que se genera la lista de contactos")
	@Test
	void test_1 () {
		assertNotNull(contactosCovid.getListaContactos());		
	}
	
	@DisplayName("Comprobamos la primera localización del fichero")
	@Test
	void test_2 () {
		assertEquals(contactosCovid.getListaContactos().getPrimerNodo(), "25/05/2021;16:30");		
	}
	
	@DisplayName("Comprobamos el número total de nodos temporales")
	@Test
	void test_3 () {
		assertEquals(contactosCovid.getListaContactos().tamanioLista(), 4);		
	}
	
	@DisplayName("Aniadimos un nuevo nodo temporal al principio de la lista")
	@Test
	void test_4 () throws EmsExceptions.EmsInvalidTypeException, EmsExceptions.EmsInvalidNumberOfDataException, EmsExceptions.EmsDuplicatePersonException, EmsExceptions.EmsDuplicateLocationException {
		contactosCovid.loadData("LOCALIZACION;66666666S;25/05/2021;11:01;44.3870;2.4698", false);
		assertEquals(contactosCovid.getListaContactos().getPrimerNodo(), "25/05/2021;11:01");		
	}
	
	@DisplayName("Al aniadir un nuevo nodo al principio la lista temporal aumenta en uno")
	@Test
	void test_5 () throws EmsExceptions.EmsInvalidTypeException, EmsExceptions.EmsInvalidNumberOfDataException, EmsExceptions.EmsDuplicatePersonException, EmsExceptions.EmsDuplicateLocationException {
		int tam_ant = contactosCovid.getListaContactos().tamanioLista();
		contactosCovid.loadData("LOCALIZACION;66666666S;25/05/2021;11:01;44.3870;2.4698", false);
		assertEquals(contactosCovid.getListaContactos().tamanioLista(), (tam_ant+1));		
	}
	
	@DisplayName("Aniadimos un nuevo nodo temporal al final de la lista")
	@Test
	void test_6 () throws EmsExceptions.EmsInvalidTypeException, EmsExceptions.EmsInvalidNumberOfDataException, EmsExceptions.EmsDuplicatePersonException, EmsExceptions.EmsDuplicateLocationException {
		contactosCovid.loadData("LOCALIZACION;66666666S;25/05/2021;19:01;44.3870;2.4698", false);
		assertEquals(contactosCovid.getListaContactos().toString(), "25/05/2021;16:30 25/05/2021;16:36 25/05/2021;17:18 25/05/2021;18:01 25/05/2021;19:01");		
	}
	
	@DisplayName("Aniadimos un nuevo nodo temporal en medio de la lista")
	@Test
	void test_7 () throws EmsExceptions.EmsInvalidTypeException, EmsExceptions.EmsInvalidNumberOfDataException, EmsExceptions.EmsDuplicatePersonException, EmsExceptions.EmsDuplicateLocationException {
		contactosCovid.loadData("LOCALIZACION;12121212R;25/05/2021;17:05;43.3870;2.3698", false);
		assertEquals(contactosCovid.getListaContactos().toString(), "25/05/2021;16:30 25/05/2021;16:36 25/05/2021;17:05 25/05/2021;17:18 25/05/2021;18:01");		
	}
	
	@DisplayName("Comprobamos el numero de personas entre dos instantes temporales")
	@Test
	void test_8 () throws EmsExceptions.EmsInvalidTypeException, EmsExceptions.EmsInvalidNumberOfDataException, EmsExceptions.EmsDuplicatePersonException, EmsExceptions.EmsDuplicateLocationException {
		Map<String,Object> nuevaFecha = new HashMap<String,Object>();
		nuevaFecha.put("dia", 25);
		nuevaFecha.put("mes", 5);
		nuevaFecha.put("anio", 2021);
		nuevaFecha.put("hora", 16);
		nuevaFecha.put("minuto", 30);
		FechaHora ini = new FechaHora(nuevaFecha);
		FechaHora fin = new FechaHora(nuevaFecha);
		assertEquals(contactosCovid.getListaContactos().numPersonasEntreDosInstantes(ini,fin), 4);		
	}
	
	@DisplayName("Comprobamos el numero de personas entre dos instantes temporales")
	@Test
	void test_9 () throws EmsExceptions.EmsInvalidTypeException, EmsExceptions.EmsInvalidNumberOfDataException, EmsExceptions.EmsDuplicatePersonException, EmsExceptions.EmsDuplicateLocationException {
		Map<String,Object> nuevaFecha = new HashMap<String,Object>();
		nuevaFecha.put("dia", 25);
		nuevaFecha.put("mes", 5);
		nuevaFecha.put("anio", 2021);
		nuevaFecha.put("hora", 16);
		nuevaFecha.put("minuto", 36);
		FechaHora ini = new FechaHora(nuevaFecha);
		FechaHora fin = new FechaHora(nuevaFecha);
		assertEquals(contactosCovid.getListaContactos().numPersonasEntreDosInstantes(ini,fin), 4);		
	}
	
	@DisplayName("Comprobamos el numero de nodos coordenada entre dos instantes temporales")
	@Test
	void test_10 () throws EmsExceptions.EmsInvalidTypeException, EmsExceptions.EmsInvalidNumberOfDataException, EmsExceptions.EmsDuplicatePersonException, EmsExceptions.EmsDuplicateLocationException {
		Map<String,Object> nuevaFecha = new HashMap<String,Object>();
		nuevaFecha.put("dia", 25);
		nuevaFecha.put("mes", 5);
		nuevaFecha.put("anio", 2021);
		nuevaFecha.put("hora", 16);
		nuevaFecha.put("minuto", 36);
		FechaHora ini = new FechaHora(nuevaFecha);
		FechaHora fin = new FechaHora(nuevaFecha);
		assertEquals(contactosCovid.getListaContactos().numNodosCoordenadaEntreDosInstantes(ini,fin), 3);		
	}
	
	@DisplayName("Comprobamos el numero de nodos coordenada y el número de personas entre dos instantes temporales")
	@Test
	void test_11 () throws EmsExceptions.EmsInvalidTypeException, EmsExceptions.EmsInvalidNumberOfDataException, EmsExceptions.EmsDuplicatePersonException, EmsExceptions.EmsDuplicateLocationException {
		Map<String,Object> nuevaFecha = new HashMap<String,Object>();
		nuevaFecha.put("dia", 25);
		nuevaFecha.put("mes", 5);
		nuevaFecha.put("anio", 2021);
		nuevaFecha.put("hora", 16);
		nuevaFecha.put("minuto", 30);
		FechaHora ini = new FechaHora(nuevaFecha);
		Map<String,Object> nuevaFechaFin = new HashMap<String,Object>();
		nuevaFechaFin.put("dia", 25);
		nuevaFechaFin.put("mes", 5);
		nuevaFechaFin.put("anio", 2021);
		nuevaFechaFin.put("hora", 18);
		nuevaFechaFin.put("minuto", 01);
		FechaHora fin = new FechaHora(nuevaFechaFin);
		assertEquals(contactosCovid.getListaContactos().numNodosCoordenadaEntreDosInstantes(ini,fin), 10);	
		assertEquals(contactosCovid.getListaContactos().numPersonasEntreDosInstantes(ini,fin), 16);
	}
	
	@DisplayName("Aniadimos un nuevo nodo coordenadas en una nodo temporal que existe")
	@Test
	void test_12 () throws EmsExceptions.EmsInvalidTypeException, EmsExceptions.EmsInvalidNumberOfDataException, EmsExceptions.EmsDuplicatePersonException, EmsExceptions.EmsDuplicateLocationException {
		Map<String,Object> nuevaFecha = new HashMap<String,Object>();
		nuevaFecha.put("dia", 25);
		nuevaFecha.put("mes", 5);
		nuevaFecha.put("anio", 2021);
		nuevaFecha.put("hora", 16);
		nuevaFecha.put("minuto", 36);
		FechaHora ini = new FechaHora(nuevaFecha);
		FechaHora fin = new FechaHora(nuevaFecha);
		assertEquals(contactosCovid.getListaContactos().numNodosCoordenadaEntreDosInstantes(ini,fin), 3);
		contactosCovid.loadData("LOCALIZACION;99998888X;25/05/2021;16:36;54.2256;32.1234", false);
		assertEquals(contactosCovid.getListaContactos().numNodosCoordenadaEntreDosInstantes(ini,fin), 4);		
	}
}
