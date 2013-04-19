package org.robot.gtf.service;

import java.net.UnknownServiceException;

import org.robot.gtf.service.configuration.ScenarioServiceConfiguration;
import org.robot.gtf.service.configuration.ScenarioServiceTypes;
import org.robot.gtf.service.impl.JDBCScenarioService;
import org.robot.gtf.service.impl.XMLScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScenarioServiceFactory {

	@Autowired
	private ScenarioServiceConfiguration scenarioServiceConfiguration;

	public IScenarioService getService() throws UnknownServiceException {

		IScenarioService scenarioService;
		if (scenarioServiceConfiguration.getType() == ScenarioServiceTypes.XML) {
			scenarioService = new XMLScenarioService();
		} else if (scenarioServiceConfiguration.getType() == ScenarioServiceTypes.JDBC) {
			scenarioService = new JDBCScenarioService();
		} else {
			throw new UnknownServiceException("");
		}

		return scenarioService;
	}
}
