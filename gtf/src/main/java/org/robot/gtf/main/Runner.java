package org.robot.gtf.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.robot.gtf.builder.CSVBuilder;
import org.robot.gtf.configuration.Metadata;
import org.robot.gtf.configuration.MetadataReader;

public class Runner {

	private static final String ARGUMENT_METADATA_DIRECTORY = "ConfigurationDirectory";
	private static final String ARGUMENT_CSV_DIRECTORY = "CsvDirectory";
	private static final String ARGUMENT_TESTSUITE_DIRECTORY = "TestsuiteDirectory";
	
	private static final String VERSION_INFO = "Robot Generic Testdata Framework - Version 0.1a\n";
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		// Exit if no argument file is given
		if (args == null || args.length == 0) {
			System.out.println("Argument file missing, exiting ...\n\n");
			printUsage();
			System.exit(0);
		}
		
		System.out.println(VERSION_INFO);
		
		// Read in and parse argument file
		Properties props = new Properties();
	    props.load(new FileInputStream(args[0]));

	    // Start building Robot Framework testsuite files
	    MetadataReader metadataReader = new MetadataReader(props.getProperty(ARGUMENT_METADATA_DIRECTORY));

	    File csvFolder = new File(props.getProperty(ARGUMENT_CSV_DIRECTORY));
	    File[] listFiles = csvFolder.listFiles();

	    if (listFiles != null) {
	    	System.out.println("Processing CSV files from " + props.getProperty(ARGUMENT_CSV_DIRECTORY) + "\n");
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
		    		
		    		CSVBuilder csvBuilder = new CSVBuilder();
		    		String build = csvBuilder.build(file.getPath(), metadata);
		    		
		    		String outputFileName = props.getProperty(ARGUMENT_TESTSUITE_DIRECTORY) + File.separator + testsuiteFile;
		    		System.out.println("Writing: " + outputFileName + "\n");
		    		FileWriter outFile = new FileWriter(outputFileName);
		    		PrintWriter out = new PrintWriter(outFile);
		    		out.println(build);
		    		out.close();
		    		outFile.close();
		    	}
		    }
	    } else {
	    	System.out.println("\nNo CSV-files found in " + props.getProperty(ARGUMENT_CSV_DIRECTORY) + "\n");
	    }
	}
	
	private static void printUsage() {
		System.out.println("java -jar gtf-0.1.jar <path-to-argument-file>\n");
	}	
}