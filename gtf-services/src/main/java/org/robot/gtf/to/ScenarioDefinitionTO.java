package org.robot.gtf.to;

import java.util.List;

public class ScenarioDefinitionTO {

	private String id;

	private String projectId;

	private String name;

	private String description;

	private List<ParameterDefinitionTO> parameterDefinition;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
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

	public List<ParameterDefinitionTO> getParameterDefinition() {
		return parameterDefinition;
	}

	public void setParameterDefinition(List<ParameterDefinitionTO> parameterDefinition) {
		this.parameterDefinition = parameterDefinition;
	}
}
