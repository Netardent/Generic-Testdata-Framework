package org.robot.gtf.service.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.robot.gtf.service.IScenarioService;
import org.robot.gtf.service.configuration.ScenarioServiceConfiguration;
import org.robot.gtf.to.ProjectDefinitionTO;
import org.robot.gtf.to.ScenarioDefinitionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XMLScenarioService extends ScenarioService implements IScenarioService {

	private static Logger logger = Logger.getLogger(XMLScenarioService.class);

	private static final String PROJECT_FILE_NAME = "project.xml";

	@Autowired
	private ScenarioServiceConfiguration serviceConfiguration;

	@Override
	public List<ProjectDefinitionTO> getProjectList() {

		List<ProjectDefinitionTO> projectList = new ArrayList<ProjectDefinitionTO>();

		try {

			File rootDirectory = new File(serviceConfiguration.getXmlProjectsRootDirectory());
			logger.info("Reading project-list from XML-Root-Directory: "
					+ serviceConfiguration.getXmlProjectsRootDirectory());

			FilenameFilter filter = new FilenameFilter() {
				public boolean accept(File directory, String fileName) {
					return new File(directory, fileName).isDirectory();
				}
			};

			String[] projectDirectoryList = rootDirectory.list(filter);
			for (String projectDirectory : projectDirectoryList) {
				projectList.add(readProjectDefinition(projectDirectory + File.separator + PROJECT_FILE_NAME));
			}
		} catch (Exception e) {
			System.out.println("Error reading in project information.");
			e.printStackTrace();
		}
		return projectList;
	}

	@Override
	public List<ScenarioDefinitionTO> getScenarioList(String projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	private ProjectDefinitionTO readProjectDefinition(String fileName) throws DocumentException {

		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(fileName));

		return null;
	}

}
