package com.mongoSparkAPI.mongoSparkAPI.service;

import java.util.List;

import com.mongoSparkAPI.mongoSparkAPI.model.User;

public interface UserService {
	public User createUser(String body);
	public List<User> getAllUsers();
	public User updateUser(String body);
}
