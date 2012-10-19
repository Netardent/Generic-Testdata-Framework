package org.robot.gtf.builder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.read.biff.CellValue;

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
		
		try {
			Workbook workbook = Workbook.getWorkbook(new File(xlsFilePath));
			
			// Currently only using the first sheet is supported
			Sheet sheet = workbook.getSheet(0);
			
			// Loop over rows 
			for (int i=0; i< sheet.getRows(); i++) {
				Cell[] row = sheet.getRow(i);
				String [] nextLine = new String[row.length];
				for (int j=0; j<row.length; j++) {
					nextLine[j] = row[j].getContents();
				}
				
		    	String testcase = testcaseTemplate;
		    	result += fillTestcaseTemplate(testcase, nextLine, metadata);
			}
		
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}	
}