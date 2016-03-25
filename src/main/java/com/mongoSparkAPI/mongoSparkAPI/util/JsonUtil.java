package com.mongoSparkAPI.mongoSparkAPI.util;

import com.google.gson.Gson;

import spark.ResponseTransformer;

public class JsonUtil {
	public static String toJsonString(Object object){
		return new Gson().toJson(object);
	}
	
	public static ResponseTransformer json() {
		return JsonUtil::toJsonString;
	}
}