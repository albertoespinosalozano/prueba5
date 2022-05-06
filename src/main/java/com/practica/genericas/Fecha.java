package com.practica.genericas;

import java.util.Map;

public class Fecha {
	private int dia, mes, anio;
	 
	public Fecha(Map<String,Object> nuevaFecha) {
		super();
		this.dia = (Integer) nuevaFecha.get("dia");
		this.mes = (Integer) nuevaFecha.get("mes");
		this.anio = (Integer) nuevaFecha.get("anio");
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	@Override
	public String toString() {
		String cadena = String.format("%2d/%02d/%4d",dia,mes,anio);
		return cadena;
	}
	
	public static boolean comprobarFechas(Fecha a, Fecha b) {
		return a.getDia() == b.getDia() && a.getMes() == b.getMes()
				&& a.getAnio() == b.getAnio();

	}

}