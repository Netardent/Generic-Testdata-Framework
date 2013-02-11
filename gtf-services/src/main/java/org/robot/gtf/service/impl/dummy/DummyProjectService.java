package org.robot.gtf.service.impl.dummy;

import java.util.ArrayList;
import java.util.List;

import org.robot.gtf.service.ProjectService;
import org.robot.gtf.service.to.ProjectTO;

public class DummyProjectService implements ProjectService {

	@Override
	public List<ProjectTO> read() {
		
		
		ProjectTO dummy1 = new ProjectTO();
		dummy1.setName("Dummy Project 1");
		
		ProjectTO dummy2 = new ProjectTO();
		dummy2.setName("Dummy Project 2");

		
		List<ProjectTO> projectList = new ArrayList<ProjectTO>();
		projectList.add(dummy1);
		projectList.add(dummy2);
		
		return projectList;
	}

	@Override
	public ProjectTO read(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(ProjectTO projectTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ProjectTO projectTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub

	}

}
