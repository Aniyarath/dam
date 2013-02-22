package com.igate.dam.metadata.exception;

public class DamSmooksException extends RuntimeException{
	
	
	private static final long serialVersionUID = 1L;
		private String customMessage;
		
		public DamSmooksException(String customMessage)
		{
			super(customMessage);
			this.customMessage=customMessage;
		}

		@Override
		public String toString() {
			return "DamSmooksException [customMessage=" + customMessage + "]";
		}
		
		 public String getMessage(String customMessage) {
		        return customMessage;
		    }
		

		
		
	}


