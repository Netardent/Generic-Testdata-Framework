package org.robot.gtf.dblayer;

import org.robot.gtf.dblayer.to.ProjectTO;

public interface ProjectRepository {

	ProjectTO read(String id);
	
	void write(ProjectTO projectTO);
	
}
