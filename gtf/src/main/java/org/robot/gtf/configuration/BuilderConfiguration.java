package org.robot.gtf.configuration;

/**
 * This class contains all the different configuration items the different builder might need.
 * Before calling a builder the relevant parts of this configuration must be added, so that the
 * builder-class can pick those.
 * 
 * @author thomas.jaspers
 */
public class BuilderConfiguration {

	
	private String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
