package org.robot.gtf.dblayer.mongodb;

import org.robot.gtf.dblayer.to.ProjectTO;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * Represents a MongoDB-Document for projects and is at the the time responsible for mapping from
 * the corresponding ProjectTO to the JSON-Document and vice versa. 
 * @author thomas.jaspers
 */
public class ProjectDocument {

	private static final String DOCUMENT_ATTRIBUTE_ID = "_id";
	
	private static final String DOCUMENT_ATTRIBUTE_NAME = "name";
	
	private static final String DOCUMENT_ATTRIBUTE_DESC = "desc";
	
	private static final String DOCUMENT_ATTRIBUTE_RUNTIME_DEFINITIONS = "runtime_definitions";
	
	private ProjectTO projectTO;
	
	/**
	 * Constructor to initialize using a ProjectTO. 
	 * @param projectTO ProjectTO
	 */
	public ProjectDocument(ProjectTO projectTO) {
		this.projectTO = projectTO;
	}

	/**
	 * Constructor to initialize using a JSON document.
	 * @param jsonDoc JSON Document
	 */
	public ProjectDocument(DBObject jsonDoc) {
		projectTO = new ProjectTO();
		projectTO.setId((String) jsonDoc.get(DOCUMENT_ATTRIBUTE_ID));
		projectTO.setName((String) jsonDoc.get(DOCUMENT_ATTRIBUTE_NAME));
		projectTO.setDescription((String) jsonDoc.get(DOCUMENT_ATTRIBUTE_DESC));
	}
    
	/**
	 * Returns the JSON representation of the contained ProjectTO
	 * @return JSON Document
	 */
    public DBObject getJsonDocument() {
    	BasicDBObject jsonDoc = new BasicDBObject();
    	
    	// Setting basic attributes
        jsonDoc.put(DOCUMENT_ATTRIBUTE_ID, projectTO.getId());
        jsonDoc.put(DOCUMENT_ATTRIBUTE_NAME, projectTO.getName());
        jsonDoc.put(DOCUMENT_ATTRIBUTE_DESC, projectTO.getDescription());
        
        // Setting the List of environments, which contains a list of parameters
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