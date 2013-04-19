package org.robot.gtf.processor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.robot.gtf.runtime_configuration.Arguments;

public abstract class Processor {
	
	
	/**
	 * Write out the resulting testsuite
	 * @param testsuiteContents The contents of the testsuite
	 * @param testsuiteFile The name of the testsuite file
	 * @param arguments The arguments passed in via the argument file
	 */
	protected void writeTestsuiteFile(String testsuiteContents, String testsuiteFile, Arguments arguments) {
		try {

			String outputFileName = arguments.getTestsuiteDirectory() + File.separator + testsuiteFile;
			
			// Check if backup is requires
			File file = new File(outputFileName);
			if (StringUtils.isNotEmpty(arguments.getBackupDirectory()) && file.exists()) {
				String backupFileName = arguments.getBackupDirectory() + File.separator + testsuiteFile + "." + Calendar.getInstance().getTimeInMillis();
				File backupFile = new File(backupFileName);
				file.renameTo(backupFile);
				System.out.println("Creating backup file: " + backupFileName);
			}
			
			
			// Write the new Testsuite file

			System.out.println("Writing: " + outputFileName);
			FileOutputStream outFile = new FileOutputStream(outputFileName);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outFile, "UTF-8");
			outputStreamWriter.write(testsuiteContents);
			outputStreamWriter.flush();
			outputStreamWriter.close();
			outFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
