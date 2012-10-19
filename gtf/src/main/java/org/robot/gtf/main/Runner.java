package org.robot.gtf.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.robot.gtf.builder.BuilderException;
import org.robot.gtf.builder.CSVBuilder;
import org.robot.gtf.builder.IBuilder;
import org.robot.gtf.configuration.Arguments;
import org.robot.gtf.configuration.BuilderConfiguration;
import org.robot.gtf.configuration.Metadata;
import org.robot.gtf.configuration.MetadataReader;

public class Runner {

	private static final String VERSION_INFO = "Robot Generic Testdata Framework - Version 0.1a\n";
	
	public static void main(String[] args) throws FileNotFoundException, IOException, BuilderException {
		
		// Exit if no argument file is given
		if (args == null || args.length == 0) {
			System.out.println("Argument file missing, exiting ...\n\n");
			printUsage();
			System.exit(0);
		}
		
		System.out.println(VERSION_INFO);

		try {
			// Read in and parse argument file
			Arguments arguments = new Arguments();
			arguments.load(args[0]);
		
		    // Start building Robot Framework testsuite files
		    MetadataReader metadataReader = new MetadataReader(arguments.getConfigurationDirectory());
	
		    File csvFolder = new File(arguments.getCsvDirectory());
		    File[] listFiles = csvFolder.listFiles();
	
		    if (listFiles != null) {
		    	System.out.println("Processing CSV files from " + arguments.getCsvDirectory() + "\n");
			    for (File file : listFiles) {
			    	String fileName = file.getName();
			    	if (fileName.endsWith(".csv")) {
			    		System.out.println("CSV file: " + fileName + "\n");
			    		String metadataFile = StringUtils.removeEnd(fileName, ".csv");
			    		String testsuiteFile = metadataFile + ".html";
			    		
			    		if (metadataFile.contains("_")) {
			    			metadataFile = StringUtils.substring(metadataFile, 0, metadataFile.indexOf("_"));
			    		}
			    		metadataFile += ".properties";
			    		Metadata metadata = metadataReader.read(metadataFile);
			    		
			    		IBuilder csvBuilder = new CSVBuilder();
			    		BuilderConfiguration builderConfiguration = new BuilderConfiguration();
			    		builderConfiguration.setFilePath(file.getPath());
			    		String build = csvBuilder.build(builderConfiguration, metadata);
			    		
			    		String outputFileName = arguments.getTestsuiteDirectory() + File.separator + testsuiteFile;
			    		System.out.println("Writing: " + outputFileName + "\n");
			    		FileWriter outFile = new FileWriter(outputFileName);
			    		PrintWriter out = new PrintWriter(outFile);
			    		out.println(build);
			    		out.close();
			    		outFile.close();
			    	}
			    }
		    } else {
		    	System.out.println("\nNo CSV-files found in " + arguments.getCsvDirectory() + "\n");
		    }
		} catch (GTFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static void printUsage() {
		System.out.println("java -jar gtf-0.1.jar <path-to-argument-file>\n");
	}	
}