package com.practica.excecption;

public abstract class EmsBaseException extends Exception{
	private String message;
	
	public EmsBaseException(String message)
	  {
	    this.message = message;
	  }
	  //Message can be retrieved using this accessor method
	  public String getMessage() {
	    return message;
	  }
}
