package com.practica.genericas;

import java.util.Map;

public class FechaHora {
	Fecha fecha;
	Hora hora;
	
	public FechaHora(Map<String,Object> nuevaFecha) {
		if(nuevaFecha.get("Fecha")!=null) {
			this.fecha =(Fecha) nuevaFecha.get("Fecha");
		}else {
			this.fecha = new Fecha(nuevaFecha);
		}
		if(nuevaFecha.get("Hora")!=null) {
			this.hora=(Hora) nuevaFecha.get("Hora");
		}
		this.hora = new Hora(nuevaFecha);
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public Hora getHora() {
		return hora;
	}

	public void setHora(Hora hora) {
		this.hora = hora;
	}
	
	@Override
	public String toString() {
		return fecha.toString() + ";" + hora.toString() + ";";
		
	}
	
}
