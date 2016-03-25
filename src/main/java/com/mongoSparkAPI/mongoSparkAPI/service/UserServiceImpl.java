package com.mongoSparkAPI.mongoSparkAPI.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.mongoSparkAPI.mongoSparkAPI.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class UserServiceImpl implements UserService{
	private final DB db;
	private final DBCollection collection;

	public UserServiceImpl(DB db){
		this.db = db;
		this.collection = db.getCollection("users");
	}

	public User createUser(String body) {
		User user = new Gson().fromJson(body, User.class);
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("email", user.getEmail());
		DBCursor cursor = collection.find(searchQuery);
		if(cursor.hasNext()){
			//send error
			return null;
		}
		else{
			BasicDBObject obj = new BasicDBObject( 
					"firstName", user.getFirstName())
					.append("lastName", user.getLastName())
					.append("email", user.getEmail())
					.append("dateCreated", new Date())
					.append("profilePic", user.getProfilePic())
					.append("address", new BasicDBObject().append("street", user.getAddress().getStreet())
							.append("city", user.getAddress().getCity())
							.append("zip", user.getAddress().getZip())
							.append("state", user.getAddress().getState())
							.append("country", user.getAddress().getCountry())
							)
					.append("company", new BasicDBObject().append("name", user.getCompany().getName())
							.append("website", user.getCompany().getWebsite())
							);
			collection.insert(obj);
			cursor = collection.find(searchQuery);
			if(cursor.hasNext()){
				return new User((BasicDBObject) cursor.next());
			}
			return null;
		}
	}


	public List<User> getAllUsers() {
		List<User> list = new ArrayList<User>();
		DBCursor dbObjects = collection.find();
		while(dbObjects.hasNext()){
			DBObject dbObject = dbObjects.next();
			list.add(new User((BasicDBObject) dbObject));
		}
		return list;


	}


	public User updateUser(String body) {
		User user = null;
		try{
			user = new Gson().fromJson(body, User.class);
		}
		catch(Exception e){
			return null;
		}
		BasicDBObject searchQuery = new BasicDBObject();
		try{
			searchQuery.put("_id", new ObjectId(user.getId()));
		}
		catch(IllegalArgumentException e){
			return null;
		}
		DBCursor cursor = collection.find(searchQuery);
		if(cursor.hasNext()){
			//update
			BasicDBObject query1 = new BasicDBObject("_id", new ObjectId(user.getId()));
			BasicDBObject update = new BasicDBObject();
			update.put("$set", new BasicDBObject("firstName", user.getFirstName())
					.append("lastName", user.getLastName())
					.append("profilePic", user.getProfilePic())
					.append("address", new BasicDBObject().append("street", user.getAddress().getStreet())
							.append("city", user.getAddress().getCity())
							.append("zip", user.getAddress().getZip())
							.append("state", user.getAddress().getState())
							.append("country", user.getAddress().getCountry())
							)
					.append("company", new BasicDBObject().append("name", user.getCompany().getName())
							.append("website", user.getCompany().getWebsite())
							)
					);
			collection.update(query1, update);
			//System.out.println("updated");
			DBCursor cursor1 = collection.find(query1);
			if(cursor1.hasNext()){
				return new User((BasicDBObject) cursor1.next());
			}
		}
		else{
			return null;
		}
		return null;
	}
}
