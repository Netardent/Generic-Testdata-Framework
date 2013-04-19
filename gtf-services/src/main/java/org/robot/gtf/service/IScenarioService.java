package org.robot.gtf.service;

import java.util.List;

import org.robot.gtf.to.ProjectDefinitionTO;
import org.robot.gtf.to.ScenarioDefinitionTO;

public interface IScenarioService {

	/**
	 * Returns a list of all known GTF projects.
	 * 
	 * @return List of GTF projects
	 */
	List<ProjectDefinitionTO> getProjectList();

	/**
	 * Returns the list of defined Test Scenarios for a given project.
	 * 
	 * @param projectId
	 *            Id of the GTF project
	 * @return List of Testscenario definitions
	 */
	List<ScenarioDefinitionTO> getScenarioList(String projectId);

}
