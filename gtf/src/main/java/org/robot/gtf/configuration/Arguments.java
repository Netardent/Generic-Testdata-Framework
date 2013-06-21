package org.robot.gtf.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.robot.gtf.main.GTFException;

/**
 * Reading in the argument file and providing methods for accessing the
 * different arguments.
 * 
 * @author thomas.jaspers
 */
public class Arguments {

	public static final String INPUT_TYPE_XLS = "XLS";

	private static final String SUPPORTED_INPUT_TYPES = INPUT_TYPE_XLS;

	private static final String DEFAULT_EXCEL_ENCODING = "Cp1252";
	private static final String DEFAULT_TESTSUITE_FILE_POSTFIX = "txt";

	private static final String ARGUMENT_CONFIGURATION_DIRECTORY = "ConfigurationDirectory";
	private static final String ARGUMENT_TESTSUITE_DIRECTORY = "TestsuiteDirectory";
	private static final String ARGUMENT_XLS_DIRECTORY = "XlsDirectory";
	private static final String ARGUMENT_BACKUP_DIRECTORY = "BackupDirectory";
	private static final String ARGUMENT_MAPPING_DIRECTORY = "MappingDirectory";
	private static final String ARGUMENT_INPUT_TYPE = "InputType";
	private static final String ARGUMENT_TESTSUITE_FILE_POSTFIX = "TestsuiteFilePostfix";

	private static final String ARGUMENT_EXCEL_ENCODING = "ExcelEncoding";

	private Properties props = new Properties();

	/**
	 * Loads the given argument file as.
	 * 
	 * @param filePath
	 *            Path to argument file
	 * @throws GTFException
	 *             Could not load argument file
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

			// Backup directory
			String backupDirectory = getBackupDirectory();
			if (StringUtils.isNotEmpty(backupDirectory)) {
				validateDirectoryEntry(backupDirectory, ARGUMENT_BACKUP_DIRECTORY, exceptionPrefix);
			}

			// Mapping directory
			String mappingDirectory = getMappingDirectory();
			if (StringUtils.isNotEmpty(mappingDirectory)) {
				validateDirectoryEntry(mappingDirectory, ARGUMENT_MAPPING_DIRECTORY, exceptionPrefix);
			}

			// Input Type
			String inputType = StringUtils.upperCase(props.getProperty(ARGUMENT_INPUT_TYPE));
			if (inputType != null) {
				if (!StringUtils.equals(inputType, INPUT_TYPE_XLS)) {
					throw new GTFException(exceptionPrefix + "contains an entry for '" + ARGUMENT_INPUT_TYPE
							+ "' that is not supported. Supported values are: " + SUPPORTED_INPUT_TYPES);
				}
			}

			// XLS-Directory
			if (StringUtils.equals(getInputType(), INPUT_TYPE_XLS)) {
				String xlsDirectory = getXLSDirectory();
				validateDirectoryEntry(xlsDirectory, ARGUMENT_XLS_DIRECTORY, exceptionPrefix);
			}

		} catch (FileNotFoundException e) {
			throw new GTFException(exceptionPrefix + "could not be found.", e.getCause());
		} catch (IOException e) {
			throw new GTFException(exceptionPrefix + "could not be loaded.", e.getCause());
		}
	}

	/**
	 * Return the encoding to be used when reading in Excel-Files.
	 * 
	 * @return
	 */
	public String getExcelEncoding() {
		return props.getProperty(ARGUMENT_EXCEL_ENCODING, DEFAULT_EXCEL_ENCODING);
	}

	/**
	 * Return the postfix to be used for the generated testsuite files.
	 * 
	 * @return
	 */
	public String getTestsuiteFilePostfix() {
		return props.getProperty(ARGUMENT_TESTSUITE_FILE_POSTFIX, DEFAULT_TESTSUITE_FILE_POSTFIX);
	}

	/**
	 * Returns the configuration directory
	 * 
	 * @return Configuration directory
	 */
	public String getConfigurationDirectory() {
		return props.getProperty(ARGUMENT_CONFIGURATION_DIRECTORY);
	}

	/**
	 * Returns the testsuite directory
	 * 
	 * @return Testsuite directory
	 */
	public String getTestsuiteDirectory() {
		return props.getProperty(ARGUMENT_TESTSUITE_DIRECTORY);
	}

	/**
	 * Returns the backup directory
	 * 
	 * @return Backup directory
	 */
	public String getBackupDirectory() {
		return props.getProperty(ARGUMENT_BACKUP_DIRECTORY);
	}

	/**
	 * Returns the mapping directory
	 * 
	 * @return Mapping directory
	 */
	public String getMappingDirectory() {
		return props.getProperty(ARGUMENT_MAPPING_DIRECTORY);
	}

	/**
	 * Returns the input type
	 * 
	 * @return Input type
	 */
	public String getInputType() {
		return StringUtils.upperCase(props.getProperty(ARGUMENT_INPUT_TYPE, INPUT_TYPE_XLS));
	}

	/**
	 * Returns the Xls directory
	 * 
	 * @return Xls directory
	 */
	public String getXLSDirectory() {
		return props.getProperty(ARGUMENT_XLS_DIRECTORY);
	}

	private void validateDirectoryEntry(String path, String argumentName, String exceptionPrefix) throws GTFException {
		if (StringUtils.isEmpty(path)) {
			throw new GTFException(exceptionPrefix + "is lacking the entry: " + argumentName);
		}
		File configurationFile = new File(path);
		if (!configurationFile.exists()) {
			throw new GTFException(exceptionPrefix + "contains an entry for '" + argumentName
					+ "' where the directory could not be found. Configured directory is: " + path);
		}
	}
}