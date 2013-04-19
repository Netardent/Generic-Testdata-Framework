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
	
	private String excelEncoding;
	
	private String configurationDirectory;

	private String subDirectory;
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getExcelEncoding() {
		return excelEncoding;
	}

	public void setExcelEncoding(String excelEncoding) {
		this.excelEncoding = excelEncoding;
	}

	public String getConfigurationDirectory() {
		return configurationDirectory;
	}

	public void setConfigurationDirectory(String configurationDirectory) {
		this.configurationDirectory = configurationDirectory;
	}

	public String getSubDirectory() {
		return subDirectory;
	}

	public void setSubDirectory(String subDirectory) {
		this.subDirectory = subDirectory;
	}
}
