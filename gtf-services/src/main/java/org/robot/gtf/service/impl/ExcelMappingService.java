package org.robot.gtf.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.robot.gtf.service.IMappingService;
import org.robot.gtf.service.configuration.MappingServiceConfiguration;
import org.robot.gtf.to.MappingTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcelMappingService implements IMappingService {

	private static final String EXCEL_MAPPING_FILE_NAME = "mappingInformation.xls";

	@Autowired
	private MappingServiceConfiguration mappingServiceConfiguration;

	@Override
	public List<MappingTO> getMappingList() {

		// If mapping file does not exist return an empty list
		if (!exists()) {
			return new ArrayList<MappingTO>();
		}

		// Otherwise read in the list from the defined Excel-file
		System.out.println("Processing Mapping file from: " + getMappingFilePath());
		List<MappingTO> mappingList = new ArrayList<MappingTO>();
		try {
			InputStream inp = new FileInputStream(getMappingFilePath());
			Workbook wb = WorkbookFactory.create(inp);

			int sheetNum = wb.getNumberOfSheets();
			int sheetCount = 0;
			while (sheetCount < sheetNum) {

				boolean sheetFlag = false;
				Sheet sheet = wb.getSheetAt(sheetCount);

				// Loop over rows and cells
				for (Row row : sheet) {

					int j = 0;
					String[] nextLine = new String[row.getLastCellNum()];
					for (Cell cell : row) {
						nextLine[j] = cell.getStringCellValue();
						j++;
					}

					if (!nextLine[0].trim().startsWith("##") && !nextLine[0].trim().isEmpty() && nextLine.length >= 2) {
						MappingTO mappingTO = new MappingTO();
						mappingTO.setName(nextLine[0]);
						mappingTO.setElement(nextLine[1]);
						mappingList.add(mappingTO);
						System.out.println("Mapping found: " + mappingTO.getName() + " -> " + mappingTO.getElement());
					}
				}

				sheetCount++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}

		System.out.println("");
		return mappingList;
	}

	/**
	 * Checks if the mapping Excel-file exists.
	 * 
	 * @return true or false
	 */
	private boolean exists() {
		File file = new File(getMappingFilePath());

		return file.exists();
	}

	private String getMappingFilePath() {
		return mappingServiceConfiguration.getMappingDirectory() + "/" + EXCEL_MAPPING_FILE_NAME;
	}
}