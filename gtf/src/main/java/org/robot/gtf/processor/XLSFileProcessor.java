package org.robot.gtf.processor;

import org.robot.gtf.builder.BuilderException;
import org.robot.gtf.builder.CSVBuilder;
import org.robot.gtf.builder.IBuilder;
import org.robot.gtf.builder.XLSBuilder;
import org.robot.gtf.configuration.Arguments;
import org.robot.gtf.configuration.BuilderConfiguration;
import org.robot.gtf.configuration.Metadata;

public class XLSFileProcessor extends FileProcessor {
	
	private static final String XLS_FILE_ENDING = ".xls";
	
	
	@Override
	protected String getFileEnding() {
		return XLS_FILE_ENDING;
	}

	@Override
	public String getFileDirectoryPath(Arguments arguments) {
		return arguments.getXLSDirectory();
	}
	
	@Override
	protected String doTheProcessing(BuilderConfiguration builderConfiguration,
			Metadata metadata) throws BuilderException {

		IBuilder xlsBuilder = new XLSBuilder();
		return xlsBuilder.build(builderConfiguration, metadata);
	}

}
