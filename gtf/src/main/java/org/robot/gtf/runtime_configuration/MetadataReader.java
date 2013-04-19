package org.robot.gtf.runtime_configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.robot.gtf.main.GTFException;
import org.robot.gtf.runtime_configuration.Metadata;


/**
 * Reading Metadata information for a concrete testsuite.
 * @author thomas.jaspers
 */
public class MetadataReader {

	private static final String TEMPLATE_HEADER_KEY_NAME = "HeaderTemplateFileName";
	private static final String TEMPLATE_FOOTER_KEY_NAME = "FooterTemplateFileName";
	private static final String TEMPLATE_TESTCASE_KEY_NAME = "TestcaseTemplateFileName";
	
	private static final String DEFAULT_HEADER_TEMPLATE_NAME = "header.template";
	private static final String DEFAULT_FOOTER_TEMPLATE_NAME = "footer.template";
	
	private String metadataDirectory = ".metadata";
	private String templateDirectory = ".template";

	
	public MetadataReader(String directory) {
		metadataDirectory = directory + File.separator + "metadata" + File.separator;
		templateDirectory = directory + File.separator + "template" + File.separator;
	}
	
	
	/**
	 * Return the relative path to the metadata directory.
	 * @return Path to metadata directory
	 */
	public String getMetadataDirectory() {
		return metadataDirectory;
	}

	/**
	 * Return the relative path to the template directory.
	 * @return Path to template directory
	 */
	public String getTemplateDirectory() {
		return templateDirectory;
	}

	
	/**
	 * Checks that the basic metadata settings are valid.
	 * @throws GTFException In case of a problem
	 */
	public void validate() throws GTFException {
		File metadata = new File(metadataDirectory);
		if (!metadata.exists()) {
			throw new GTFException("The metadata directory '" + metadataDirectory + "' is missing.");
		}
		
		File template = new File(templateDirectory);
		if (!template.exists()) {
			throw new GTFException("The template directory '" + templateDirectory + "' is missing.");
		}
	}
	
	
	public Metadata read(String fileName) throws GTFException {
		
		String filePath = metadataDirectory + fileName;
		Metadata metadata = new Metadata();

	    try {
			Properties props = new Properties(); 
			props.load(new FileInputStream(filePath));

		    // Read the parameters for the testcase templates 
		    Enumeration<?> e = props.propertyNames(); 
		    while (e.hasMoreElements()) {
		    	String key = (String) e.nextElement();
		    	String value = props.getProperty(key).trim();
	
		    	if (StringUtils.isNumeric(value)) {
		    		metadata.addValue(Integer.parseInt(value), key);
		    	}
		    }	    
			
		    // Read the value paths to header and footer template
		    // Use default values if not defined in metadata file
		    String headerFileName = DEFAULT_HEADER_TEMPLATE_NAME;
		    if (props.containsKey(TEMPLATE_HEADER_KEY_NAME)) {
		    	headerFileName = props.getProperty(TEMPLATE_HEADER_KEY_NAME);
		    }
		    metadata.setHeaderTemplateFilePath(templateDirectory + headerFileName);
	
		    String footerFileName = DEFAULT_FOOTER_TEMPLATE_NAME;
		    if (props.containsKey(TEMPLATE_FOOTER_KEY_NAME)) {
		    	footerFileName = props.getProperty(TEMPLATE_FOOTER_KEY_NAME);
		    }
		    metadata.setFooterTemplateFilePath(templateDirectory + footerFileName);
	
		    metadata.setTestcaseTemplateFilePath(templateDirectory + props.getProperty(TEMPLATE_TESTCASE_KEY_NAME));
		} catch (FileNotFoundException e) {
			throw new GTFException("Metadata file '" + filePath + "' could not be found.", e.getCause());
		} catch (IOException e) {
			throw new GTFException("Metadata file '" + filePath + "' could not be read.", e.getCause());
		}

		return metadata;
	}
}