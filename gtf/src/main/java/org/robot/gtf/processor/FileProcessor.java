package org.robot.gtf.processor;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.robot.gtf.builder.BuilderException;
import org.robot.gtf.configuration.Arguments;
import org.robot.gtf.configuration.BuilderConfiguration;
import org.robot.gtf.configuration.Metadata;
import org.robot.gtf.configuration.MetadataReader;
import org.robot.gtf.main.GTFException;

public abstract class FileProcessor extends Processor {

	
	protected abstract String doTheProcessing(BuilderConfiguration builderConfiguration, Metadata metadata) throws BuilderException;
	
	protected abstract String getFileEnding();

	protected abstract String getFileDirectoryPath(Arguments arguments);
	
	/**
	 * Processing file based testdata
	 * @param metadataReader Metadata Reader
	 * @param arguments The argument file passed in via command line
	 * @throws GTFException In case an error occurs during processings
	 */
	public void processFiles(MetadataReader metadataReader, Arguments arguments) throws GTFException {
		
		String path = getFileDirectoryPath(arguments);
		File folder = new File(path);
		File[] listFiles = folder.listFiles();
		 
		if (listFiles != null) {
	    	System.out.println("Processing files from " + path + "\n");
		    for (File file : listFiles) {
		    	
		    	String fileName = file.getName();
		    	if (fileName.endsWith(getFileEnding())) {
		    		System.out.print("Processing file: " + fileName + " ... ");
		    		
		    		String rawName = StringUtils.removeEnd(fileName, getFileEnding());
		    		String metadataFile = rawName;
		    		String testsuiteFile = metadataFile + ".html";
		    		
		    		if (metadataFile.contains("_")) {
		    			metadataFile = StringUtils.substring(metadataFile, 0, metadataFile.indexOf("_"));
		    		}
		    		metadataFile += ".properties";
		    		Metadata metadata = metadataReader.read(metadataFile);
		    	
		    		BuilderConfiguration builderConfiguration = new BuilderConfiguration();
		    		builderConfiguration.setFilePath(file.getPath());
		    		
		    		String result = doTheProcessing(builderConfiguration, metadata);
		    		
		    		writeTestsuiteFile(result, testsuiteFile, arguments);
		    		System.out.println("ok");
		    	}
		    }
		} else {
			System.out.println("No files found ending with '" + getFileEnding() + "' from directory: " + path);
		}
	}
}
