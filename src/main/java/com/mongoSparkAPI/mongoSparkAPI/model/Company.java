package com.mongoSparkAPI.mongoSparkAPI.model;

import com.mongodb.BasicDBObject;

public class Company {
	private String name;
	private String website;
	
	public Company(BasicDBObject dbObject){
		this.name = dbObject.getString("name");
		this.website = dbObject.getString("website");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
}
