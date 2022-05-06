package com.practica.genericas;

import java.util.Map;

public class Hora {
		private int hora, minuto;

		public Hora (Map<String,Object> nuevaFecha) {
			super();
			this.hora = (Integer) nuevaFecha.get("hora");
			this.minuto = (Integer) nuevaFecha.get("minuto");
		}

		public int getHora() {
			return hora;
		}

		public void setHora(int hora) {
			this.hora = hora;
		}

		public int getMinuto() {
			return minuto;
		}

		public void setMinuto(int minuto) {
			this.minuto = minuto;
		}

		@Override
		public String toString() {
			return String.format("%02d:%02d", hora,minuto);
		}
		
		public static boolean comprobarHoras(Hora a, Hora b) {
			return a.getHora() == b.getHora()
					&& a.getMinuto() == b.getMinuto();
		}

	}