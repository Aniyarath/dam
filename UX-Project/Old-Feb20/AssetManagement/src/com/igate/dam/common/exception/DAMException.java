package com.igate.dam.common.exception;

import java.io.Serializable;

public class DAMException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String faultCode = "";

	private String faultMessage = "";

	private Throwable throwableError;

	public DAMException() {
		super();
	}

	public DAMException(String message, Throwable throwableCause) {
		super(message, throwableCause);
		this.throwableError = throwableCause;

	}

	public DAMException(String message) {
		super(message);
		faultMessage = message;
	}

	/**
	 * Throws Exception by the parameter message
	 * 
	 * @param faultCode
	 * @param faultMessage
	 */
	public DAMException(String faultCode, String faultMessage) {
		super(faultMessage);
		this.faultCode = faultCode;
		this.faultMessage = faultMessage;
		System.out.println(faultCode);
		System.out.println(faultMessage);
	}

	@Override
	public String toString() {
		return new StringBuffer("GTSException[")
				.append(faultCode).append(",  ").append(faultMessage)
				.append("]").toString();
	}

	public String getFaultCode() {
		return faultCode;
	}

	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}

	public String getFaultMessage() {
		return faultMessage;
	}

	public void setFaultMessage(String faultMessage) {
		this.faultMessage = faultMessage;
	}

}
