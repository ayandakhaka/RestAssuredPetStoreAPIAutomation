package api.test;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.model.User;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;
	User userPayload;
	List<User> users;
	User userLogin;

	public UserTest() {
		faker = new Faker();
		userPayload = new User();
		users = new ArrayList<>();
		userLogin = new User();
	}

	@BeforeGroups(groups = "Happy path")
	public void setupData() {
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername("tyrone.stehr");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

	}

	@Test(priority = 1, groups = "Happy path")
	public void testCreateNewUser() {

		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2, groups = "Happy path")
	public void testGetExistingUser() {

		Response response = UserEndPoints.readUser(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 3, groups = "Happy path")
	public void testUpdateExistingUser() {

		//Update user lastname and email
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());


		Response responseBeforeUpdate = UserEndPoints.updateUser(userPayload.getUsername(), userPayload);
		responseBeforeUpdate.then().log().all();
		Assert.assertEquals(responseBeforeUpdate.getStatusCode(), 200);

		Response responseAfterUpdate = UserEndPoints.readUser(userPayload.getUsername());

		responseBeforeUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}

	@Test(priority = 4, groups = "Happy path")
	public void testDeleteExistingUser() {

		Response response = UserEndPoints.deleteUser(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@BeforeGroups(groups = "Create users with list")
	public void createUsersWithList() {

		User user1 = new User();

		user1.setId(faker.idNumber().hashCode());
		user1.setUsername(faker.name().username());
		user1.setFirstName(faker.name().firstName());
		user1.setLastName(faker.name().lastName());
		user1.setEmail(faker.internet().safeEmailAddress());
		user1.setPassword(faker.internet().password(5, 10));
		user1.setPhone(faker.phoneNumber().cellPhone());

		User user2 = new User();

		user2.setId(faker.idNumber().hashCode());
		user2.setUsername(faker.name().username());
		user2.setFirstName(faker.name().firstName());
		user2.setLastName(faker.name().lastName());
		user2.setEmail(faker.internet().safeEmailAddress());
		user2.setPassword(faker.internet().password(5, 10));
		user2.setPhone(faker.phoneNumber().cellPhone());
		
		users.add(user1);
		users.add(user2);
	}
	
	@Test(groups = "Create users with list and array", priority = 5)
	public void testCreateUsersWithList() {
		Response response = UserEndPoints.createUsersWithList(users);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(groups = "Create users with list and array", priority = 6)
	public void testCreateUsersWithArray() {
		Response response = UserEndPoints.createUsersWithArray(users);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@BeforeGroups(groups = "User login")
	public void userLogin() {
		userLogin.setUsername(faker.name().username());
		userLogin.setPassword(faker.internet().password(5, 10));
	}
	
	@Test(groups = "User login", priority = 7)
	public void testUserLogin() {
		Response response = UserEndPoints.loginUser(userLogin.getUsername(), userLogin.getPassword());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(groups = "User logout", priority = 8)
	public void testUserLogout() {
		Response response = UserEndPoints.logoutUser();
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
