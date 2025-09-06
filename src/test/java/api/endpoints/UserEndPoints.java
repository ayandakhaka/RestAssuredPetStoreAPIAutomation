package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.List;
import api.payload.model.User;

/**
 * UserEndPoints class contains CRUD operations and authentication methods
 * for User module in Swagger Petstore API.
 *
 * These methods use Rest Assured to send requests to the API endpoints
 * defined in the Routes class.
 */
public class UserEndPoints {

    /**
     * Create a single user.
     * @param payload User object with details to be created.
     * @return Response returned from API.
     */
    public static Response createUser(User payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_create_user_url);
        return response;
    }

    /**
     * Get details of a user by username.
     * @param userName Username of the user to fetch.
     * @return Response containing user details.
     */
    public static Response readUser(String userName) {
        Response response = given()
                .pathParam("username", userName)
                .when()
                .get(Routes.get_url_by_username);
        return response;
    }

    /**
     * Update an existing user by username.
     * @param userName Username of the user to update.
     * @param payload User object with updated fields.
     * @return Response after update request.
     */
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

    /**
     * Delete a user by username.
     * @param userName Username of the user to delete.
     * @return Response after deletion request.
     */
    public static Response deleteUser(String userName) {
        Response response = given()
                .pathParam("username", userName)
                .when()
                .delete(Routes.delete_url);
        return response;
    }

    /**
     * Create multiple users with a List.
     * @param userList List of User objects.
     * @return Response from API after bulk creation.
     */
    public static Response createUsersWithList(List<User> userList) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userList)
                .when()
                .post(Routes.post_create_with_list);
        return response;
    }

    /**
     * Create multiple users with an Array.
     * @param userList List of User objects (treated as array).
     * @return Response from API after bulk creation.
     */
    public static Response createUsersWithArray(List<User> userList) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userList)
                .when()
                .post(Routes.post_create_with_array_url);
        return response;
    }

    /**
     * Login user with username and password.
     * @param userName Username of the user.
     * @param password Password of the user.
     * @return Response containing session/login details.
     */
    public static Response loginUser(String userName, String password) {
        Response response = given()
                .queryParam("username", userName)
                .queryParam("password", password)
                .when()
                .get(Routes.get_url_by_login);
        return response;
    }

    /**
     * Logout the currently logged-in user.
     * @return Response after logout request.
     */
    public static Response logoutUser() {
        Response response = given()
                .when()
                .get(Routes.get_url_by_logout);
        return response;
    }
}
