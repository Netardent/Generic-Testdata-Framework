package org.robot.gtf.processor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.robot.gtf.configuration.Arguments;

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
			System.out.println("Writing: " + outputFileName + "\n");
			FileWriter outFile = new FileWriter(outputFileName);
			PrintWriter out = new PrintWriter(outFile);
			out.println(testsuiteContents);
			out.close();
			outFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
