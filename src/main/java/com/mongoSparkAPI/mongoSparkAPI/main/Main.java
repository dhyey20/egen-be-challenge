package com.mongoSparkAPI.mongoSparkAPI.main;

import java.net.UnknownHostException;

import com.mongoSparkAPI.mongoSparkAPI.api.UserResource;
import com.mongoSparkAPI.mongoSparkAPI.service.UserServiceImpl;
import com.mongodb.DB;
import com.mongodb.MongoClient;

public class Main {
	static MongoClient mongo = null;
	public static void main(String args[]){
		try {
			mongo = new MongoClient("localhost", 27017);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DB db = mongo.getDB("test");
		new UserResource(new UserServiceImpl(db));
	}
}
