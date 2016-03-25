package com.mongoSparkAPI.mongoSparkAPI;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.mongoSparkAPI.mongoSparkAPI.main.Main;
import com.mongoSparkAPI.mongoSparkAPI.model.User;
import com.mongoSparkAPI.mongoSparkAPI.util.JsonUtil;

import spark.Spark;
import spark.utils.IOUtils;

public class CreateUserTest {
	@BeforeClass
	public static void beforeClass() {
		Main.main(null);
	}

	@AfterClass
	public static void afterClass() {
		Spark.stop();
	}

	@Test
	public void aNewUserShouldBeCreated() {
		TestResponse res = request("POST", "/add_user");
		Map<String, String> json = res.json();
		assertEquals(200, res.status);
		assertEquals("Dorris", json.get("firstName"));
		assertEquals("Darby_Leffler68@gmail.com", json.get("email"));
		assertNotNull(json.get("id"));
		res = request("POST", "/add_user");
		assertEquals("User already exists", res.body);
	}

	private TestResponse request(String method, String path) {
		try {
			String jsonString = "{" +  
					"\"firstName\":\"Dorris\"," +
					"\"lastName\":\"Keeling\","+
					"\"email\":\"Darby_Leffler68@gmail.com\"," +
					"\"address\":{\"street\":\"193 Talon Valley\",\"city\":\"South Tate furt\",\"zip\":\"47069\",\"state\":\"IA\",\"country\":\"US\"}," +
					"\"company\":{\"name\":\"Denesik Group\",\"website\":\"http://jodie.org\"},"+
					"\"profilePic\":\"http://lorempixel.com/640/480/people\"}";
			User user = new Gson().fromJson(jsonString, User.class);
			String body = JsonUtil.toJsonString(user);
			URL url = new URL("http://localhost:4567" + path);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-length", Integer.toString(body.length()));
			connection.getOutputStream().write(body.getBytes("UTF8"));
			connection.connect();
			body = IOUtils.toString(connection.getInputStream());
			return new TestResponse(connection.getResponseCode(), body);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Sending request failed: " + e.getMessage());
			return null;
		}
	}

	private static class TestResponse {

		public final String body;
		public final int status;

		public TestResponse(int status, String body) {
			this.status = status;
			this.body = body;
		}

		public Map<String,String> json() {
			return new Gson().fromJson(body, HashMap.class);
		}
	}
}
