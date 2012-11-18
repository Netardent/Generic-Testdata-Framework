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
}