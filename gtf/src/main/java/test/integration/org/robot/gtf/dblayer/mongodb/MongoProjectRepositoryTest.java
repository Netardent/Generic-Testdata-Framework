package org.robot.gtf.dblayer.mongodb;

import static org.junit.Assert.fail;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.ServiceMode;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;
import org.robot.gtf.dblayer.ProjectRepository;
import org.robot.gtf.dblayer.to.ProjectTO;

public class MongoProjectRepositoryTest {

	MongoHandler handler;
	
	@Before
	public void setUp() {
		handler = new MongoHandler();
		try {
			handler.connect("localhost", 27017, "test");
		} catch (UnknownHostException e) {
			fail("UnknownHostException-Exception caught " + e.getMessage());
		} catch (ConnectException e) {
			fail("ConnectException-Exception caught " + e.getMessage());
		}
	}
	
	@Test
	public void testProjectCreate() {
	
		ProjectTO to = new ProjectTO();
		to.setId("rating_engine");
		to.setName("Rating Engine");
		to.setDescription("This is the test project for the rating engine calculating values ...");

		Map<String, Map<String, String>> environments = new HashMap<String, Map<String, String>>();

		Map<String, String> paramsLocal = new HashMap<String, String>();
		paramsLocal.put("selenium-server", "localhost");
		paramsLocal.put("selenium-port", "8867");
		environments.put("local", paramsLocal);

		Map<String, String> paramsTest = new HashMap<String, String>();
		paramsTest.put("selenium-server", "test");
		paramsTest.put("selenium-port", "8892");
		environments.put("test", paramsTest);
		
		to.setEnvironmentParameter(environments);
		
		ProjectRepository rep = new MongoProjectRepository(handler);
		rep.write(to);
	}
	
}
