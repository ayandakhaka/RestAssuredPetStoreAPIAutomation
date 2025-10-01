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

/**
 * Test suite for User API endpoints.
 * Covers happy-path CRUD operations, batch user creation,
 * and login/logout functionality.
 */
public class UserTest {

    private Faker faker;
    private User userPayload;
    private List<User> users;
    private User loginUserPayload;

    public UserTest() {
        faker = new Faker();
        userPayload = new User();
        users = new ArrayList<>();
        loginUserPayload = new User();
    }

    /** Setup data for single user tests (Happy Path). */
    @BeforeGroups(groups = "HappyPath")
    public void setupSingleUserData() {
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername("tyrone.stehr");
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
    }

    @Test(priority = 1, groups = "HappyPath")
    public void testCreateNewUser() {
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200, "User creation failed!");
    }

    @Test(priority = 2, groups = "HappyPath")
    public void testGetExistingUser() {
        Response response = UserEndPoints.readUser(userPayload.getUsername());
        response.then().log().all();
        System.out.println("Status code = " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200, "Failed to fetch existing user!");
    }

    @Test(priority = 3, groups = "HappyPath")
    public void testUpdateExistingUser() {
        // Update lastname and email
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response updateResponse = UserEndPoints.updateUser(userPayload.getUsername(), userPayload);
        updateResponse.then().log().all();
        Assert.assertEquals(updateResponse.getStatusCode(), 200, "User update failed!");

        Response fetchResponse = UserEndPoints.readUser(userPayload.getUsername());
        fetchResponse.then().log().all();
        Assert.assertEquals(fetchResponse.getStatusCode(), 200, "Failed to fetch updated user!");
    }

    @Test(priority = 4, groups = "HappyPath")
    public void testDeleteExistingUser() {
        Response response = UserEndPoints.deleteUser(userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200, "User deletion failed!");
    }

    /** Setup data for batch user creation tests. */
    @BeforeGroups(groups = "BatchUsers")
    public void setupBatchUsers() {
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

    @Test(priority = 5, groups = "BatchUsers")
    public void testCreateUsersWithList() {
        Response response = UserEndPoints.createUsersWithList(users);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200, "Failed to create users with list!");
    }

    @Test(priority = 6, groups = "BatchUsers")
    public void testCreateUsersWithArray() {
        Response response = UserEndPoints.createUsersWithArray(users);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200, "Failed to create users with array!");
    }

    /** Setup data for login tests. */
    @BeforeGroups(groups = "Auth")
    public void setupLoginUser() {
        loginUserPayload.setUsername(faker.name().username());
        loginUserPayload.setPassword(faker.internet().password(5, 10));
    }

    @Test(priority = 7, groups = "Auth")
    public void testUserLogin() {
        Response response = UserEndPoints.loginUser(loginUserPayload.getUsername(), loginUserPayload.getPassword());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200, "User login failed!");
    }

    @Test(priority = 8, groups = "Auth")
    public void testUserLogout() {
        Response response = UserEndPoints.logoutUser();
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200, "User logout failed!");
    }
}
