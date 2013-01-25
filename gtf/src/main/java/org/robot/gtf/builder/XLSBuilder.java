package org.robot.gtf.builder;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.robot.gtf.configuration.BuilderConfiguration;
import org.robot.gtf.configuration.Metadata;


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
			
			 InputStream inp = new FileInputStream(xlsFilePath);
			 
			 //XSSFWorkbook wb = (XSSFWorkbook) WorkbookFactory.create(inp);
			 
			 Workbook wb = WorkbookFactory.create(inp);
			 Sheet sheet = wb.getSheetAt(0);
			
			// Loop over rows
		    for (Row row : sheet) {
				String testScenarioName = "";
				boolean isCommentLine = false;

				String [] nextLine = new String[row.getLastCellNum()];
				int j=0;
				
		    	for (Cell cell : row) {
		    		
					nextLine[j] = cell.getStringCellValue();
					
					if (nextLine[0].trim().startsWith("##") || nextLine[0].trim().isEmpty()) {
						isCommentLine = true;
						break;
					} else if (StringUtils.isEmpty(testScenarioName)) {
						testScenarioName = nextLine[0];
					}
					j++;
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}	
}