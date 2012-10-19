package org.robot.gtf.builder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;
import org.robot.gtf.configuration.BuilderConfiguration;
import org.robot.gtf.configuration.Metadata;


/**
 * This is the base class for all different Builder-classes.
 * It implements the generic parts of the template processing and the overall workflow.
 * 
 * @author thomas.jaspers
 */
public abstract class Builder {

	/**
	 * This is where the specific processing must be implemented in the derived classes.
	 * @param builderConfiguration Configuration of the Builder
	 * @param metadata The metadata
	 * @param testcaseTemplate Content of the testcase template
	 * @return String that represents the testcases-part of the "builded" testsuite
	 * @throws BuilderException in case anything goes wrong
	 */
	protected abstract String doTheWork(BuilderConfiguration builderConfiguration, Metadata metadata, String testcaseTemplate) throws BuilderException;


	/**
	 * Fills in a given testcase template with the values from one "line" of argument data.
	 * The argument data must be provided in the proper order (fitting the metadata).
	 * A filled testcase is returned.
	 * @param testcaseTemplate Template for a testcase
	 * @param values One record of argument data
	 * @param metadata The metadata
	 * @return A filled testcase
	 */
	protected String fillTestcaseTemplate(String testcaseTemplate, String [] values, Metadata metadata) {
		
		String testcase = testcaseTemplate;
		
    	for (int i=0; i<values.length; i++) {
    		String repl = "%" + metadata.getValue(i+1) + "%";	 
    		testcase = StringUtils.replace(testcase, repl, values[i]);
    	}

    	return testcase;
	}
	
	
	/**
	 * Reads in the content of a text-file and return it as a String. 
	 * @param filePath Path to the text-file to be read
	 * @return Content of that file
	 * @throws IOException
	 */
	protected String readFileContent(String filePath) throws IOException {
		
		BufferedReader reader = new BufferedReader( new FileReader (filePath));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    while( ( line = reader.readLine() ) != null ) {
	        stringBuilder.append( line );
	        stringBuilder.append( ls );
	    }

	    return stringBuilder.toString();
	}
	
	/**
	 * Main method to start building a concrete testsuite file.
	 * @param builderConfiguration Configuration of the Builder
	 * @param metadata The metadata
	 * @return Complete String that represents a "builded" testsuite
	 * @throws IOException 
	 */
	public final String build(BuilderConfiguration builderConfiguration, Metadata metadata) throws BuilderException {

		String header;
		String footer;
		String testcaseTemplate;
		try {
			header = readFileContent(metadata.getHeaderTemplateFilePath());
			testcaseTemplate = readFileContent(metadata.getTestcaseTemplateFilePath());
			footer = readFileContent(metadata.getFooterTemplateFilePath());
		} catch (IOException e) {
			throw new BuilderException(e.getMessage(), e.getCause());
		}
		
		String result = header;
		result += doTheWork(builderConfiguration, metadata, testcaseTemplate);
		result += footer;

		return result; 
	}
}