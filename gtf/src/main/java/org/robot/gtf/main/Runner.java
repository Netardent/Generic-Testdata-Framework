package org.robot.gtf.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.robot.gtf.builder.BuilderException;
import org.robot.gtf.configuration.Arguments;
import org.robot.gtf.configuration.MetadataReader;
import org.robot.gtf.processor.XLSFileProcessor;
import org.robot.gtf.service.IScenarioService;
import org.robot.gtf.service.ScenarioServiceFactory;
import org.robot.gtf.service.configuration.ScenarioServiceConfiguration;
import org.robot.gtf.service.configuration.ScenarioServiceTypes;
import org.robot.gtf.to.ProjectDefinitionTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {

	private static final String VERSION_INFO = "Robot Generic Testdata Framework - Version 1.0b\n";

	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException, IOException, BuilderException {

		System.out.println(VERSION_INFO);

		//
		// Initialize Spring Container
		//
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/META-INF/application-context.xml");

		//
		// Read in Scenario Metadata
		//
		ScenarioServiceConfiguration scenarioServiceConfiguration = applicationContext
				.getBean(ScenarioServiceConfiguration.class);
		scenarioServiceConfiguration.setType(ScenarioServiceTypes.XML);
		scenarioServiceConfiguration.setXmlProjectsRootDirectory("C:\\projects\\gtf");

		ScenarioServiceFactory scenarioServiceFactory = applicationContext.getBean(ScenarioServiceFactory.class);
		IScenarioService scenarioService = scenarioServiceFactory.getService();
		System.out.println("DEBUG: " + scenarioService.getClass().getName());

		List<ProjectDefinitionTO> projectList = scenarioService.getProjectList();
		System.out.println("DEBUG: " + projectList.size());

		// Exit if no argument file is given
		if (args == null || args.length == 0) {
			System.out.println("Argument file missing, exiting ...\n\n");
			printUsage();
			System.exit(1);
		}

		try {
			// Read and validate the argument file
			Arguments arguments = new Arguments();
			arguments.load(args[0]);

			// Initialize the Metadata Reader
			MetadataReader metadataReader = new MetadataReader(arguments.getConfigurationDirectory());
			metadataReader.validate();

			//
			// Processing for the different input file types
			//

			// XLS-Files
			if (StringUtils.equals(arguments.getInputType(), Arguments.INPUT_TYPE_XLS)) {
				XLSFileProcessor xlsFileProcessor = new XLSFileProcessor();
				xlsFileProcessor.processFiles(metadataReader, arguments);
			}

		} catch (GTFException e) {
			System.out.println("Detailed exception information:");
			e.printStackTrace();
			System.out.println("\n\nTermination with error:\n" + e.getMessage());
			System.exit(1);
		}

		System.exit(0);
	}

	private static void printUsage() {
		System.out.println("java -jar robot_gtf.jar <path-to-argument-file>\n");
	}
}