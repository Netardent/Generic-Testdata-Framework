package org.robot.gtf.service.configuration;

import org.springframework.stereotype.Component;

@Component
public class MappingServiceConfiguration {

	private MappingServiceTypes type;

	private String mappingDirectory;

	public MappingServiceTypes getType() {
		return type;
	}

	public void setType(MappingServiceTypes type) {
		this.type = type;
	}

	public String getMappingDirectory() {
		return mappingDirectory;
	}

	public void setMappingDirectory(String mappingDirectory) {
		this.mappingDirectory = mappingDirectory;
	}
}
