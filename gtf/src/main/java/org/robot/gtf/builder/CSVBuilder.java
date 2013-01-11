package org.robot.gtf.builder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.robot.gtf.configuration.BuilderConfiguration;
import org.robot.gtf.configuration.Metadata;
import au.com.bytecode.opencsv.CSVReader;

/**
 * Concrete builder class for processing text-files in CSV-format.
 * @author thomas.jaspers
 */
public class CSVBuilder extends Builder implements IBuilder {

	@Override
	protected String doTheWork(BuilderConfiguration builderConfiguration,
			Metadata metadata, String testcaseTemplate) throws BuilderException  {

		String result = "";
		String csvFilePath = builderConfiguration.getFilePath();
		
		try {
			CSVReader reader = new CSVReader(new FileReader(csvFilePath), metadata.getDelimiter());
		    String [] nextLine;
		    while ((nextLine = reader.readNext()) != null) {
		    	String testcase = testcaseTemplate;
		    	result += fillTestcaseTemplate(testcase, nextLine, metadata);
		    }
		    reader.close();
		} catch (FileNotFoundException e) {
			throw new BuilderException(e.getMessage(), e.getCause());
		} catch (IOException e) {
			throw new BuilderException(e.getMessage(), e.getCause());
		}
		
		return result;
	}	
}