package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.List;

import api.payload.model.User;

public class UserEndPoints {

	public static Response createUser(User payload) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(Routes.post_create_user_url);
		return response;
	}

	public static Response readUser(String userName) {
		Response response = given()
				.pathParam("username", userName)
				.when() 
				.get(Routes.get_url_by_username);
		return response;
	}

	public static Response updateUser(String userName, User payload) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
				.when()
				.put(Routes.update_user_url);
		return response;
	}

	public static Response deleteUser(String userName) {
		Response response = given()
				.pathParam("username", userName)
				.when() 
				.delete(Routes.delete_url);
		return response;
	}

	public static Response createUsersWithList(List<User> userList) {

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(userList)
				.when()
				.post(Routes.post_create_with_list);
		return response;
	}

	public static Response createUsersWithArray(List<User> userList) {

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(userList)
				.when()
				.post(Routes.post_create_with_array_url);
		return response;
	}
	
	public static Response loginUser(String userName, String password) {
		
		Response response = given()
				.queryParam("username", userName)
				.queryParam("password", password)
				.when()
				.get(Routes.get_url_by_login);
		return response;
	}
	
	public static Response logoutUser() {
		
		Response response = given()
				.when()
				.get(Routes.get_url_by_logout);
		return response;
	}

}
