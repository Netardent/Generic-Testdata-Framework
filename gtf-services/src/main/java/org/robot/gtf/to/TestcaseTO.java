package org.robot.gtf.to;

import java.util.List;

public class TestcaseTO {

	private String projectId;
	
	private String scenarioId;
	
	private String name;
	
	private String description;
	
	private int position;
	
	private List<ParameterTO> parameter;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	public String getScenarioId() {
		return scenarioId;
	}

	public void setScenarioId(String scenarioId) {
		this.scenarioId = scenarioId;
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
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public List<ParameterTO> getParameter() {
		return parameter;
	}

	public void setParameter(List<ParameterTO> parameter) {
		this.parameter = parameter;
	}
}
