package org.robot.gtf.service;

import java.util.List;

import org.robot.gtf.service.to.ProjectTO;

/**
 * Interface for reading and writing project information.
 * @author thomas.jaspers
 */
public interface ProjectService {
	
	/**
	 * Reads in a list of all available projects.
	 * @return List of projects
	 */
	List<ProjectTO> read();
	
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
	
	/**
	 * Updates the project identified by this ProjectTO with this ProjectTO.
	 * @param projectTO ProjectTO
	 */
	void update(ProjectTO projectTO);
	
	/**
	 * Remove the project identified by the given id from the database.
	 * @param id
	 */
	void remove(String id);
	
}