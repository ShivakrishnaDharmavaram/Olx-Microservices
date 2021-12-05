package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NO_CONTENT)
public class EmptyListException extends IllegalStateException{

	private String msg;

	public EmptyListException() {
		this.msg = "";
	}

	public EmptyListException(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Empty list of " + this.msg ;
	}
}
