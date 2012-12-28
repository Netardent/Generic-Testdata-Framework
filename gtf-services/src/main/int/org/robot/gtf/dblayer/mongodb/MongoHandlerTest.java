package org.robot.gtf.dblayer.mongodb;

import static org.junit.Assert.*;

import java.net.ConnectException;
import java.net.UnknownHostException;

import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robot.gtf.service.impl.mongodb.MongoHandler;

@RunWith(CdiRunner.class)
public class MongoHandlerTest  {

	@Inject
	private MongoHandler handler;
	
	@Test
	public void testDefaultConnection() {
		
		
		try {
			handler.connect("localhost", 27017, "test");
		} catch (UnknownHostException e) {
			fail("UnknownHostException-Exception caught " + e.getMessage());
		} catch (ConnectException e) {
			fail("ConnectException-Exception caught " + e.getMessage());
		}
	}
}
