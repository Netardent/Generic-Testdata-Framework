package org.robot.gtf.builder;

import org.robot.gtf.main.GTFException;

/**
 * 
 * @author thomas.jaspers
 *
 */
public class BuilderException extends GTFException {
	
	private static final long serialVersionUID = 1L;

	public BuilderException(String message) {
		super(message);
	}

	public BuilderException(String message, Throwable cause) {
		super(message, cause);
	}
}
