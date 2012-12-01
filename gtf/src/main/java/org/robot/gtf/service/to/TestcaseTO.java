package org.robot.gtf.service.to;

import java.util.Map;

public class TestcaseTO {

	private String description;
	
	private Map<String, String> parameterValues;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getParameterValues() {
		return parameterValues;
	}

	public void setParameterValues(Map<String, String> parameterValues) {
		this.parameterValues = parameterValues;
	}
		
}
