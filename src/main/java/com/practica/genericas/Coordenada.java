package com.practica.genericas;


public class Coordenada {
	private float latitud;
	private float longitud;

	
	public Coordenada() {
		super();
	}

	public Coordenada(float latitud, float longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(latitud);
		result = prime * result + Float.floatToIntBits(longitud);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if((obj != null) && (getClass() == obj.getClass())) {
			Coordenada other = (Coordenada) obj;
			return Float.floatToIntBits(this.getLatitud()) == Float.floatToIntBits(other.getLatitud()) && 
					Float.floatToIntBits(this.getLongitud()) == Float.floatToIntBits(other.getLongitud());
		}
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("%.4f;%.4f\n", latitud, longitud);
	}
}
