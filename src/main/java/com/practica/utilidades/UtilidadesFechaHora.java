package com.practica.utilidades;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.practica.genericas.Fecha;
import com.practica.genericas.FechaHora;
import com.practica.genericas.Hora;

public class UtilidadesFechaHora {
	public static int calcularHash(FechaHora fh) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fh.getFecha() == null) ? 0 : fh.getFecha().hashCode());
		result = prime * result + ((fh.getHora() == null) ? 0 : fh.getHora().hashCode());
		return result;
	}

	public static boolean fechasIguales(FechaHora f1, Object obj) {
		if((obj != null) && (f1.getClass() == obj.getClass())) {
			FechaHora fecha = (FechaHora) obj;
			return comprobarFechas(f1, fecha);
		}
		return false;
	}
	
	private static boolean comprobarFechas(FechaHora f1, FechaHora f2) {
		return Fecha.comprobarFechas(f1.getFecha(), f2.getFecha()) && Hora.comprobarHoras(f1.getHora(), f2.getHora());
	}
	
	public static int compararCon(FechaHora a, FechaHora o) {
		LocalDateTime dateTime1= LocalDateTime.of(a.getFecha().getAnio(), a.getFecha().getMes(), a.getFecha().getDia(), 
				a.getHora().getHora(), a.getHora().getMinuto());
		LocalDateTime dateTime2= LocalDateTime.of(o.getFecha().getAnio(), o.getFecha().getMes(), o.getFecha().getDia(), 
				o.getHora().getHora(), o.getHora().getMinuto());
		
		return dateTime1.compareTo(dateTime2);
	}
	
	public static FechaHora parsearFecha (String fecha) {
		int dia, mes, anio;
		String[] valores = fecha.split("\\/");
		dia = Integer.parseInt(valores[0]);
		mes = Integer.parseInt(valores[1]);
		anio = Integer.parseInt(valores[2]);
		Map<String,Object> nuevaFechaHora = new HashMap<String,Object>();
		nuevaFechaHora.put("dia", dia);
		nuevaFechaHora.put("mes", mes);
		nuevaFechaHora.put("anio", anio);
		nuevaFechaHora.put("hora", 0);
		nuevaFechaHora.put("minuto", 0);
		FechaHora fechaHora = new FechaHora(nuevaFechaHora);
		return fechaHora;
	}
	
	public static FechaHora parsearFecha (String fecha, String hora) {
		int dia, mes, anio;
		String[] valores = fecha.split("\\/");
		dia = Integer.parseInt(valores[0]);
		mes = Integer.parseInt(valores[1]);
		anio = Integer.parseInt(valores[2]);
		int minuto, hor;
		valores = hora.split("\\:");
		hor = Integer.parseInt(valores[0]);
		minuto = Integer.parseInt(valores[1]);
		Map<String,Object> nuevaFechaHora = new HashMap<String,Object>();
		nuevaFechaHora.put("dia", dia);
		nuevaFechaHora.put("mes", mes);
		nuevaFechaHora.put("anio", anio);
		nuevaFechaHora.put("hora", hor);
		nuevaFechaHora.put("minuto", minuto);
		FechaHora fechaHora = new FechaHora(nuevaFechaHora);
		return fechaHora;
	}
	
}
