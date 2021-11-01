package com.estock.application.exception;

public class InvalidStockException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidStockException(String message) {
		super(message);
	}
}
