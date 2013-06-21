package org.robot.gtf.processor;

import java.util.List;
import java.util.Map;

import org.robot.gtf.builder.BuilderException;
import org.robot.gtf.builder.IBuilder;
import org.robot.gtf.builder.XLSBuilder;
import org.robot.gtf.configuration.Arguments;
import org.robot.gtf.configuration.BuilderConfiguration;
import org.robot.gtf.configuration.Metadata;
import org.robot.gtf.to.MappingTO;

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
	protected String doTheProcessing(BuilderConfiguration builderConfiguration, Map<String, Metadata> metadataMap,
			List<MappingTO> mappingInformation) throws BuilderException {

		IBuilder xlsBuilder = new XLSBuilder();
		return xlsBuilder.build(builderConfiguration, metadataMap, mappingInformation);
	}

}
