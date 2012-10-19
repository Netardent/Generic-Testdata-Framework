package org.robot.gtf.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.robot.gtf.main.GTFException;


/**
 * Reading in the argument file and providing methods for accessing the different arguments.
 * @author thomas.jaspers
 */
public class Arguments {

	public static final String INPUT_TYPE_CSV = "CSV";
	
	private static final String SUPPORTED_INPUT_TYPES = INPUT_TYPE_CSV; 
	
	private static final String ARGUMENT_CONFIGURATION_DIRECTORY = "ConfigurationDirectory";
	private static final String ARGUMENT_TESTSUITE_DIRECTORY = "TestsuiteDirectory";
	private static final String ARGUMENT_CSV_DIRECTORY = "CsvDirectory";
	private static final String ARGUMENT_INPUT_TYPE = "InputType";
	
	private Properties props = new Properties();

	/**
	 * Loads the given argument file as.
	 * @param filePath Path to argument file
	 * @throws GTFException Could not load argument file
	 */
	public void load(String filePath) throws GTFException {
		
		String exceptionPrefix = "The argument file '" + filePath + "' ";
		
		try {
			props.load(new FileInputStream(filePath));
			
			//
			// Validate the arguments (to fail early if something is wrong)
			//
			
			// Configuration directory
			String configurationDirectory = getConfigurationDirectory();
			validateDirectoryEntry(configurationDirectory, ARGUMENT_CONFIGURATION_DIRECTORY, exceptionPrefix);
			
			// Testsuite directory
			String testsuiteDirectory = getTestsuiteDirectory();
			validateDirectoryEntry(testsuiteDirectory, ARGUMENT_TESTSUITE_DIRECTORY, exceptionPrefix);			
			
			// Input Type
			String inputType = StringUtils.upperCase(props.getProperty(ARGUMENT_INPUT_TYPE));    
			if (inputType != null) {
				if (!StringUtils.equals(inputType, INPUT_TYPE_CSV)) {
					throw new GTFException(exceptionPrefix +  "contains an entry for '" + ARGUMENT_INPUT_TYPE + "' that is not supported. Supported values are: " + SUPPORTED_INPUT_TYPES);
				}
			}
			
			// CSV-Directory
			if (StringUtils.equals(getInputType(), INPUT_TYPE_CSV)) {
				String csvDirectory = getCsvDirectory();
				validateDirectoryEntry(csvDirectory, ARGUMENT_CSV_DIRECTORY, exceptionPrefix);			
			}
			
		} catch (FileNotFoundException e) {
			throw new GTFException(exceptionPrefix +  "could not be found.", e.getCause());
		} catch (IOException e) {
			throw new GTFException(exceptionPrefix +  "could not be loaded.", e.getCause());		
		}
	}
	
	/**
	 * Returns the configuration directory
	 * @return Configuration directory
	 */
	public String getConfigurationDirectory() {
		return props.getProperty(ARGUMENT_CONFIGURATION_DIRECTORY);
	}

	/**
	 * Returns the testsuite directory
	 * @return Testsuite directory
	 */
	public String getTestsuiteDirectory() {
		return props.getProperty(ARGUMENT_TESTSUITE_DIRECTORY);
	}

	/**
	 * Returns the input type
	 * @return Input type
	 */
	public String getInputType() {
		return StringUtils.upperCase(props.getProperty(ARGUMENT_INPUT_TYPE, INPUT_TYPE_CSV));
	}
	
	/**
	 * Returns the Csv directory
	 * @return Csv directory
	 */
	public String getCsvDirectory() {
		return props.getProperty(ARGUMENT_CSV_DIRECTORY);
	}
	
		
	private void validateDirectoryEntry(String path, String argumentName, String exceptionPrefix) throws GTFException {
		if (StringUtils.isEmpty(path)) {
			throw new GTFException(exceptionPrefix + "is lacking the entry: " + argumentName);				
		} 
		File configurationFile = new File(path);
		if (!configurationFile.exists()) {
			throw new GTFException(exceptionPrefix +  "contains an entry for '" + argumentName + "' where the directory could not be found. Configured directory is: " + path);
		}
		
	}
}
