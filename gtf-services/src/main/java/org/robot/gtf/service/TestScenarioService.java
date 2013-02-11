package org.robot.gtf.service;

import java.util.List;

import org.robot.gtf.service.to.TestScenarioDefinitionTO;
import org.robot.gtf.service.to.TestcaseParametersTO;

public interface TestScenarioService {
	
	
	
	List<TestScenarioDefinitionTO> read(String mainProjectName);
	
	List<TestcaseParametersTO> readParameters(String mainProjectName, String scenarioName);
}
