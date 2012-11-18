package org.robot.gtf.service;

import org.robot.gtf.service.to.ProjectTO;

/**
 * Interface for reading and writing project information.
 * @author thomas.jaspers
 */
public interface ProjectService {

	/**
	 * Reads the project information from a data store using the given id.
	 * @param id Project-Id
	 * @return Filled ProjectTO
	 */
	ProjectTO read(String id);
	
	/**
	 * Writes the given project information to the corresponding data store.
	 * @param projectTO A Filled ProjectTO.
	 */
	void write(ProjectTO projectTO);
}
