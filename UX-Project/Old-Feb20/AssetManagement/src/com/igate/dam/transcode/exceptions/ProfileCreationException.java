package com.igate.dam.transcode.exceptions;

/**
 * @author mj802966
 *
 */
public class ProfileCreationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
private String customMessage;
	
	public ProfileCreationException(String customMessage)
	{
		
		this.customMessage=customMessage;
	}

	public String getCustomMessage() {
		return customMessage;
	}

	public void setCustomMessage(String customMessage) {
		this.customMessage = customMessage;
	}
	


}
