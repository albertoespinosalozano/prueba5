package com.practica.excecption;

public class EmsExceptions {
	public static class EmsDuplicateLocationException extends EmsBaseException{
		private static final long serialVersionUID = 772143578579956558L;
		public EmsDuplicateLocationException() {
			super("LOCALIZACION DUPLICADA!");
		}
		public EmsDuplicateLocationException(String message) {
			super(message);
		}
	}
	
	public static class EmsDuplicatePersonException extends EmsBaseException{
		private static final long serialVersionUID = -7820067476620924855L;
		public EmsDuplicatePersonException() {
			super("PERSONA DUPLICADA!");
		}
		public EmsDuplicatePersonException(String message) {
			super(message);
		}
	}
	
	public static class EmsInvalidNumberOfDataException extends EmsBaseException {

		private static final long serialVersionUID = 5695990919488967637L;
		public EmsInvalidNumberOfDataException() {
			super("NUMERO DE PARAMETROS INCORRECTOS!");
		}
		public EmsInvalidNumberOfDataException(String message) {
			super(message);
		}
	}
	public static class EmsInvalidTypeException extends EmsBaseException{
		private static final long serialVersionUID = -3926429962050461370L;
		public EmsInvalidTypeException() {
			super("TIPO DE ENTRADA NO VALIDA!");
		}
		public EmsInvalidTypeException(String message) {
			super(message);
		}
	}
	public static class EmsLocalizationNotFoundException extends EmsBaseException{
		private static final long serialVersionUID = 8221684492948957544L;
		public EmsLocalizationNotFoundException() {
			super("LOCALIZACION NO ENCONTRADA!");
		}
		public EmsLocalizationNotFoundException(String message) {
			super(message);
		}
	}
	public static class EmsPersonNotFoundException extends EmsBaseException {
		private static final long serialVersionUID = -4321592476622780210L;
		public EmsPersonNotFoundException() {
			super("PERSONA NO ENCONTRADA!");
		}
		public EmsPersonNotFoundException(String message) {
			super(message);
		}
	}
}
