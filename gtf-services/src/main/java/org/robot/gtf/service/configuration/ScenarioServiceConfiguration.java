package org.robot.gtf.service.configuration;

import org.springframework.stereotype.Component;

@Component
public class ScenarioServiceConfiguration {

	private ScenarioServiceTypes type;

	private String xmlProjectsRootDirectory;

	private String jdbcConnectString;

	private String jdbcUser;

	private String jdbcPassword;

	public ScenarioServiceTypes getType() {
		return type;
	}

	public void setType(ScenarioServiceTypes type) {
		this.type = type;
	}

	public String getXmlProjectsRootDirectory() {
		return xmlProjectsRootDirectory;
	}

	public void setXmlProjectsRootDirectory(String xmlProjectsRootDirectory) {
		this.xmlProjectsRootDirectory = xmlProjectsRootDirectory;
	}

	public String getJdbcConnectString() {
		return jdbcConnectString;
	}

	public void setJdbcConnectString(String jdbcConnectString) {
		this.jdbcConnectString = jdbcConnectString;
	}

	public String getJdbcUser() {
		return jdbcUser;
	}

	public void setJdbcUser(String jdbcUser) {
		this.jdbcUser = jdbcUser;
	}

	public String getJdbcPassword() {
		return jdbcPassword;
	}

	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}
}
