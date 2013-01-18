package org.robot.gtf.builder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.read.biff.CellValue;

import org.apache.commons.lang.StringUtils;
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
			Map<String, Metadata> metadataMap) throws BuilderException  {

		String result = "";
		String xlsFilePath = builderConfiguration.getFilePath();
		
		try {
			// Unfortunetly there is still a problem with German Umlauts
			WorkbookSettings ws = new WorkbookSettings();
			ws.setEncoding(builderConfiguration.getExcelEncoding());
			
			Workbook workbook = Workbook.getWorkbook(new File(xlsFilePath), ws);
			
			
			// Currently only using the first sheet is supported
			Sheet sheet = workbook.getSheet(0);
			
			// Loop over rows
			for (int i=0; i< sheet.getRows(); i++) {
				String testScenarioName = "";
				boolean isCommentLine = false;
				
				Cell[] row = sheet.getRow(i);
				String [] nextLine = new String[row.length];
				for (int j=0; j<row.length; j++) {
					nextLine[j] = row[j].getContents();
					
					if (nextLine[0].trim().startsWith("##") || nextLine[0].trim().isEmpty()) {
						isCommentLine = true;
						break;
					} else if (StringUtils.isEmpty(testScenarioName)) {
						testScenarioName = nextLine[0];
					}
				}

				if (!isCommentLine) {
					if (StringUtils.isNotEmpty(builderConfiguration.getSubDirectory())) {
						testScenarioName = builderConfiguration.getSubDirectory() + "_" + testScenarioName; 
					}
					
					System.out.println("Scenario-Name: " + testScenarioName);
					Metadata metadata = metadataMap.get(testScenarioName);
			    	String testcase = readFileContent(metadata.getTestcaseTemplateFilePath());
			    	result += fillTestcaseTemplate(testcase, nextLine, metadata);
				}
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