package org.robot.gtf.service;

import java.net.UnknownServiceException;

import org.robot.gtf.service.configuration.MappingServiceConfiguration;
import org.robot.gtf.service.impl.ExcelMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MappingServiceFactory {

	@Autowired
	private MappingServiceConfiguration mappingServiceConfiguration;

	@Autowired
	private ExcelMappingService excelMappingService;

	public IMappingService getService() throws UnknownServiceException {

		return excelMappingService;
	}
}
