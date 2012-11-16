package org.robot.gtf.dblayer;

import org.robot.gtf.dblayer.to.ProjectTO;

/**
 * Interface for reading and writing project information.
 * @author thomas.jaspers
 */
public interface ProjectRepository {

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
