package com.educandoweb.workshopmongo.services.exception;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;// runtime não precisamdos tratar a extensão se fosse exception sim

	public ObjectNotFoundException(String msg) {
		super(msg); // neste construtor so passa a mensagem
	}
}
