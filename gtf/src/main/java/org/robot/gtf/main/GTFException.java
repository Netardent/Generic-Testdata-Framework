package org.robot.gtf.main;

public class GTFException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public GTFException(String message) {
		super(message);
	}

	public GTFException(String message, Throwable cause) {
		super(message, cause);
	}

}
