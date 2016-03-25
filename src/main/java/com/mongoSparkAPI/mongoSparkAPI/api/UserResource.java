package com.mongoSparkAPI.mongoSparkAPI.api;

import com.mongoSparkAPI.mongoSparkAPI.model.User;
import com.mongoSparkAPI.mongoSparkAPI.service.UserService;
import com.mongoSparkAPI.mongoSparkAPI.util.JsonUtil;

import spark.Request;
import spark.Response;
import spark.Route;
import static spark.Spark.*;

public class UserResource {
	public UserResource(final UserService userService){
		//list of all the users
		get("/users", "application/json", new Route(){
			public Object handle(Request arg0, Response arg1){
				return JsonUtil.toJsonString(userService.getAllUsers());
			}
		});
		
		
		post("/add_user" ,new Route(){
			public Object handle(Request arg0, Response arg1){
				User user = userService.createUser(arg0.body());
				if(user == null){
					arg1.body("user already exists");
					return "User already exists";
				}
				else{
					return JsonUtil.toJsonString(user);
				}
			}
		});
		
		put("/update_user", new Route(){
			public Object handle(Request arg0, Response arg1){
				//System.out.println(arg0.body());
				User user = userService.updateUser(arg0.body());
				if(user == null){
					arg1.status(404);
					return "User not found.";
				}
				else{
					return JsonUtil.toJsonString(user);
				}
			}
		});
	}
}
