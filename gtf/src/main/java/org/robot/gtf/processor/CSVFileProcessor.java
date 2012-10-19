package org.robot.gtf.processor;

import org.robot.gtf.builder.BuilderException;
import org.robot.gtf.builder.CSVBuilder;
import org.robot.gtf.builder.IBuilder;
import org.robot.gtf.configuration.Arguments;
import org.robot.gtf.configuration.BuilderConfiguration;
import org.robot.gtf.configuration.Metadata;

public class CSVFileProcessor extends FileProcessor {
	
	private static final String CSV_FILE_ENDING = ".csv";
	
	
	@Override
	protected String getFileEnding() {
		return CSV_FILE_ENDING;
	}

	@Override
	public String getFileDirectoryPath(Arguments arguments) {
		return arguments.getCsvDirectory();
	}
	
	@Override
	protected String doTheProcessing(BuilderConfiguration builderConfiguration,
			Metadata metadata) throws BuilderException {

		IBuilder csvBuilder = new CSVBuilder();
		return csvBuilder.build(builderConfiguration, metadata);
	}

}
