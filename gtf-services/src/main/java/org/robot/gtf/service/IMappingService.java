package org.robot.gtf.service;

import java.util.List;

import org.robot.gtf.to.MappingTO;

public interface IMappingService {

	/**
	 * Returns a list of all Mappings found.
	 * 
	 * @return List of Mappings
	 */
	List<MappingTO> getMappingList();
}
