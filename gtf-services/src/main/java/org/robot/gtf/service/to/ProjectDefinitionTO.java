package org.robot.gtf.service.to;

import java.util.List;

/**
 * Represents a Test-Project containing basic project information and global project
 * environment settings.
 * @author thomas.jaspers
 */
public class ProjectDefinitionTO {

	private String id;
	
	private String name;
	
	private String description;
	
	private List<ScenarioDefinitionTO> testscenarioDefinition;
	
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

	public List<ScenarioDefinitionTO> getTestscenarioDefinition() {
		return testscenarioDefinition;
	}

	public void setTestscenarioDefinition(
			List<ScenarioDefinitionTO> testscenarioDefinition) {
		this.testscenarioDefinition = testscenarioDefinition;
	}
}