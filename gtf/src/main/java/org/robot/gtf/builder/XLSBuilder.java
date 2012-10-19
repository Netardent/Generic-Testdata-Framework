package org.robot.gtf.builder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.robot.gtf.configuration.BuilderConfiguration;
import org.robot.gtf.configuration.Metadata;
import au.com.bytecode.opencsv.CSVReader;

/**
 * Concrete builder class for processing Excel files in XLS-format.
 * @author thomas.jaspers
 */
public class XLSBuilder extends Builder implements IBuilder {

	@Override
	protected String doTheWork(BuilderConfiguration builderConfiguration,
			Metadata metadata, String testcaseTemplate) throws BuilderException  {

		String result = "";
		String xlsFilePath = builderConfiguration.getFilePath();
		
		return result;
	}	
}