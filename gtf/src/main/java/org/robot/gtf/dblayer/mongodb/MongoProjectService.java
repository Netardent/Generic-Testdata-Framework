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
public class MongoProjectService implements ProjectService {

	private static final String COLLECTION_NAME_PROJECTS = "gtf_projects";
	
	private DBCollection collection;
	
	/**
	 * Constructor taking a MongoHandler with a ready-made connection.
	 * @param handler Connection to MongoDB
	 */
	public MongoProjectService(MongoHandler handler) {
		collection = handler.getCollection(COLLECTION_NAME_PROJECTS);
	}
	
	@Override
	public List<ProjectTO> read() {

		List<ProjectTO> list = new ArrayList<ProjectTO>();
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
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("_id", id);
		
		DBObject jsonDoc = collection.findOne(dbObject);
		ProjectDocument projectDocument = new ProjectDocument();		
		return projectDocument.buildProjectTO(jsonDoc);
	}

	@Override
	public void write(ProjectTO projectTO) {
		ProjectDocument projectDocument = new ProjectDocument();
		DBObject dbObject = projectDocument.buildJsonDocument(projectTO);
		collection.insert(dbObject, WriteConcern.SAFE);
	}

	@Override
	public void update(ProjectTO projectTO) {
		BasicDBObject dbObjectSearch = new BasicDBObject();
		dbObjectSearch.put("_id", projectTO.getId());
		
		ProjectDocument projectDocument = new ProjectDocument();
		DBObject dbObject = projectDocument.buildJsonDocument(projectTO);
		
		collection.update(dbObjectSearch, dbObject);		
	}
	
	@Override
	public void remove(String id) {
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("_id", id);
		
		collection.remove(dbObject);
		
		// FIXME: Related Testcases must be deleted as well
	}
}