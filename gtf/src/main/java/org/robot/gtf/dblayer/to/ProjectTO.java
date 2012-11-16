package org.robot.gtf.dblayer.to;

import java.util.Map;

public class ProjectTO {

	private String id;
	
	private String name;
	
	private String description;

	Map<String, Map<String, String>> environmentParameter;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, Map<String, String>> getEnvironmentParameter() {
		return environmentParameter;
	}

	public void setEnvironmentParameter(
			Map<String, Map<String, String>> environmentParameter) {
		this.environmentParameter = environmentParameter;
	}
}
