package org.robot.gtf.dblayer.mongodb;

import org.robot.gtf.dblayer.to.ProjectTO;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class ProjectDocument {

	private static final String DOCUMENT_ATTRIBUTE_ID = "_id";
	
	private static final String DOCUMENT_ATTRIBUTE_NAME = "name";
	
	private static final String DOCUMENT_ATTRIBUTE_DESC = "desc";
	
	private static final String DOCUMENT_ATTRIBUTE_RUNTIME_DEFINITIONS = "runtime_definitions";
	
	private ProjectTO projectTO;
	
	public ProjectDocument(ProjectTO projectTO) {
		this.projectTO = projectTO;
	}

	public ProjectDocument(DBObject jsonDoc) {
		projectTO = new ProjectTO();
		projectTO.setId((String) jsonDoc.get(DOCUMENT_ATTRIBUTE_ID));
		projectTO.setName((String) jsonDoc.get(DOCUMENT_ATTRIBUTE_NAME));
		projectTO.setDescription((String) jsonDoc.get(DOCUMENT_ATTRIBUTE_DESC));
	}
    
    public DBObject getJsonDocument() {
    	BasicDBObject jsonDoc = new BasicDBObject();
        jsonDoc.put(DOCUMENT_ATTRIBUTE_ID, projectTO.getId());
        jsonDoc.put(DOCUMENT_ATTRIBUTE_NAME, projectTO.getName());
        jsonDoc.put(DOCUMENT_ATTRIBUTE_DESC, projectTO.getDescription());
        
        BasicDBObject environments = new BasicDBObject();
        for (String envName : projectTO.getEnvironmentParameter().keySet()) {
        	BasicDBObject envParams = new BasicDBObject();
       		envParams.putAll(projectTO.getEnvironmentParameter().get(envName));
        	environments.put(envName, envParams);
        }
        
        jsonDoc.put(DOCUMENT_ATTRIBUTE_RUNTIME_DEFINITIONS, environments);
        return jsonDoc;
    }

	public ProjectTO getProjectTO() {
		return projectTO;
	}

	public void setProjectTO(ProjectTO projectTO) {
		this.projectTO = projectTO;
	}

}