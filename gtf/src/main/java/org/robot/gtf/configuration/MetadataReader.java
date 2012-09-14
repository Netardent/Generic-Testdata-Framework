package org.robot.gtf.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.robot.gtf.configuration.Metadata;


public class MetadataReader {

	private static final String TEMPLATE_HEADER_KEY_NAME = "HeaderTemplateFileName";
	private static final String TEMPLATE_FOOTER_KEY_NAME = "FooterTemplateFileName";
	
	private static final String DEFAULT_HEADER_TEMPLATE_NAME = "header.template";
	private static final String DEFAULT_FOOTER_TEMPLATE_NAME = "footer.template";
	
	private String metadataDirectory = ".";

	
	public MetadataReader(String metadataDirectory) {
		this.metadataDirectory = metadataDirectory;
	}
	
	public Metadata read(String fileName) throws IOException {
		
		Metadata metadata = new Metadata();
		
		String filePath = metadataDirectory + File.pathSeparator + fileName;
		Properties props = new Properties();
	    props.load(new FileInputStream(filePath));
	    
	    // Read the parameters for the testcase templates 
	    Enumeration<?> e = props.propertyNames(); 
	    while (e.hasMoreElements()) {
	    	String key = (String) e.nextElement();
	    	String value = props.getProperty(key);

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
	    metadata.setHeaderTemplateFilePath(metadataDirectory + File.pathSeparator + headerFileName);

	    String footerFileName = DEFAULT_FOOTER_TEMPLATE_NAME;
	    if (props.containsKey(TEMPLATE_FOOTER_KEY_NAME)) {
	    	footerFileName = props.getProperty(TEMPLATE_FOOTER_KEY_NAME);
	    }
	    metadata.setFooterTemplateFilePath(metadataDirectory + File.pathSeparator + footerFileName);
	    
		return metadata;
	}
}
