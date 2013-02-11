package org.robot.gtf.service.impl.dummy;

import java.util.ArrayList;
import java.util.List;

import org.robot.gtf.service.TestScenarioService;
import org.robot.gtf.service.to.TestScenarioDefinitionTO;
import org.robot.gtf.service.to.TestcaseParametersTO;

public class DummyTestScenarioService implements TestScenarioService {

	@Override
	public List<TestScenarioDefinitionTO> read(String mainProjectName) {

		List<TestScenarioDefinitionTO> list = new ArrayList<TestScenarioDefinitionTO>();
		
		
		if (mainProjectName.equals("Dummy Project 1")) {
			
			TestScenarioDefinitionTO def1 = new TestScenarioDefinitionTO();
			def1.setName("Enterprise");
			list.add(def1);

			TestScenarioDefinitionTO def2 = new TestScenarioDefinitionTO();
			def2.setName("Voyager");
			list.add(def2);
		} else {
			TestScenarioDefinitionTO def1 = new TestScenarioDefinitionTO();
			def1.setName("Terrania");
			list.add(def1);

			TestScenarioDefinitionTO def2 = new TestScenarioDefinitionTO();
			def2.setName("Marco Polo");
			list.add(def2);
		}
		
		return list;
	}

	@Override
	public List<TestcaseParametersTO> readParameters(String mainProjectName,
			String scenarioName) {

		List<TestcaseParametersTO> list = new ArrayList<TestcaseParametersTO>();
		
		
		if (mainProjectName.equals("Dummy Project 1")) {
			TestcaseParametersTO def1 = new TestcaseParametersTO();
			def1.setName("Kirk");
			list.add(def1);
			
			TestcaseParametersTO def2 = new TestcaseParametersTO();
			def2.setName("Picard");
			list.add(def2);
		} else {
			TestcaseParametersTO def1 = new TestcaseParametersTO();
			def1.setName("Perry Rhodan");
			list.add(def1);
			
			TestcaseParametersTO def2 = new TestcaseParametersTO();
			def2.setName("Reginal Bull");
			list.add(def2);
			
			TestcaseParametersTO def3 = new TestcaseParametersTO();
			def3.setName("Gucky");
			list.add(def3);

		}
		
		return list;
	}

}
