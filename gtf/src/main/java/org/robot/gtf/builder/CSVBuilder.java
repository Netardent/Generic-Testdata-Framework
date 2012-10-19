package org.robot.gtf.builder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import org.apache.commons.lang.StringUtils;
import org.robot.gtf.configuration.Metadata;

import au.com.bytecode.opencsv.CSVReader;

public class CSVBuilder extends Builder {

	
	
	public String build(String csvFilePath, Metadata metadata) throws IOException {
		
		String result = readFile(metadata.getHeaderTemplateFilePath());
		
		String testcaseTemplate = readFile(metadata.getTestcaseTemplateFilePath());
		CSVReader reader = new CSVReader(new FileReader(csvFilePath), metadata.getDelimiter());
	    String [] nextLine;
	    while ((nextLine = reader.readNext()) != null) {
	    	String testcase = testcaseTemplate;
	    	
	    	for (int i=0; i<nextLine.length; i++) {
	    		String repl = "%" + metadata.getValue(i+1) + "%";	 
	    		testcase = StringUtils.replace(testcase, repl, nextLine[i]);
	    	}
	    	result += testcase;
	    }
		
		result += readFile(metadata.getFooterTemplateFilePath());
		return result;
	}
	
	
	
	public String readFile(String filePath) throws IOException {
		
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
}
