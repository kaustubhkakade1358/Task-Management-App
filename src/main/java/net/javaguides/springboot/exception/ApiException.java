package net.javaguides.springboot.exception;

public class ApiException extends RuntimeException{

	public ApiException() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
//		super(message, cause, enableSuppression, writableStackTrace);
//		// TODO Auto-generated constructor stub
//	}

//	public ApiException(String message, Throwable cause) {
//		super(message, cause);
//		// TODO Auto-generated constructor stub
//	}

	public ApiException(String message) {
		super(message);
		
	}

//	public ApiException(Throwable cause) {
//		super(cause);
//		// TODO Auto-generated constructor stub
//	}

	
	
	
}
