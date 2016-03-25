package com.mongoSparkAPI.mongoSparkAPI.model;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;

public class User {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String dateCreated;
	private String profilePic;
	private Address address;
	private Company company;
	
	public User(BasicDBObject dbObject){
		this.id = dbObject.getString("_id");
		this.firstName = dbObject.getString("firstName");
		this.lastName = dbObject.getString("lastName");
		this.email = dbObject.getString("email");
		this.address = new Gson().fromJson(dbObject.getString("address"), Address.class);
		this.dateCreated = dbObject.getString("dateCreated");
		this.company = new Gson().fromJson(dbObject.getString("company"), Company.class);
		this.profilePic = dbObject.getString("profilePic");
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
