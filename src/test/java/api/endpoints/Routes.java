package api.endpoints;

/**
 * Routes class contains all the endpoint URLs for
 * User, Pet, and Store modules in the Swagger Petstore API.
 *
 * These constants are used in the endpoint classes (e.g., PetEndPoints, UserEndPoints)
 * to make HTTP requests via Rest Assured.
 */
public class Routes {

    // -------------------- Base URL --------------------
    public static String base_url = "https://petstore.swagger.io/v2";


    // -------------------- User Endpoints --------------------

    /** Create multiple users with a list */
    public static String post_create_with_list = base_url + "/user/createWithList";

    /** Create multiple users with an array */
    public static String post_create_with_array_url = base_url + "/user/createWithArray";

    /** Create a single user */
    public static String post_create_user_url = base_url + "/user";

    /** Get user by username */
    public static String get_url_by_username = base_url + "/user/{username}";

    /** Login user */
    public static String get_url_by_login = base_url + "/user/login";

    /** Logout user */
    public static String get_url_by_logout = base_url + "/user/logout";

    /** Update user by username */
    public static String update_user_url = base_url + "/user/{username}";

    /** Delete user by username */
    public static String delete_url = base_url + "/user/{username}";


    // -------------------- Pet Endpoints --------------------

    /** Add a new pet */
    public static String post_add_new_pet_url = base_url + "/pet";

    /** Upload pet image */
    public static String post_upload_pet_image_url = base_url + "/pet/{petId}/uploadImage";

    /** Update existing pet */
    public static String update_existing_pet_url = base_url + "/pet";

    /** Get pets by status */
    public static String get_pet_by_status_url = base_url + "/pet/findByStatus";

    /** Get pet by ID */
    public static String get_pet_by_id = base_url + "/pet/{petId}";

    /** Update pet with form data */
    public static String post_update_pet = base_url + "/pet/{petId}";

    /** Delete pet by ID */
    public static String delete_pet_by_id = base_url + "/pet/{petId}";


    // -------------------- Store Endpoints --------------------

    /** Place an order for a pet */
    public static String order_url = base_url + "/store/order";

    /** Get order by ID */
    public static String orderById_url = base_url + "/store/order/{orderId}";

    /** Get store inventory (status counts for pets) */
    public static String inventory_url = base_url + "/store/inventory";

}
