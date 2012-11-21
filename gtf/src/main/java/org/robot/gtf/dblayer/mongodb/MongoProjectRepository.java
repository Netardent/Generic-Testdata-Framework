package org.robot.gtf.dblayer.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.robot.gtf.service.ProjectService;
import org.robot.gtf.service.to.ProjectTO;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;

/**
 * Read/write access to the project data using MongoDB as the data store. 
 * @author thomas.jaspers
 */
public class MongoProjectRepository implements ProjectService {

	private static final String COLLECTION_NAME_PROJECTS = "gtf_projects";
	
	private MongoHandler handler ;
	
	
	/**
	 * Constructor taking a MongoHandler with a ready-made connection.
	 * @param handler Connection to MongoDB
	 */
	public MongoProjectRepository(MongoHandler handler) {
		this.handler = handler;
	}
	
	@Override
	public List<ProjectTO> read() {

		List<ProjectTO> list = new ArrayList<ProjectTO>();

		DBCollection collection = handler.getCollection(COLLECTION_NAME_PROJECTS);
		DBCursor cursor = collection.find();

		ProjectDocument projectDocument = new ProjectDocument();		
		while (cursor.hasNext()) {
			ProjectTO projectTO = projectDocument.buildProjectTO(cursor.next());
			list.add(projectTO);
		}
		cursor.close();
		
		return list;
	}
	
	
	@Override
	public ProjectTO read(String id) {
		DBCollection collection = handler.getCollection(COLLECTION_NAME_PROJECTS);
		
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("_id", id);
		
		DBObject jsonDoc = collection.findOne(dbObject);
		ProjectDocument projectDocument = new ProjectDocument();		
		return projectDocument.buildProjectTO(jsonDoc);
	}

	@Override
	public void write(ProjectTO projectTO) {
		DBCollection collection = handler.getCollection(COLLECTION_NAME_PROJECTS);

		ProjectDocument projectDocument = new ProjectDocument();
		DBObject dbObject = projectDocument.buildJsonDocument(projectTO);
		collection.insert(dbObject, WriteConcern.SAFE);
	}
}