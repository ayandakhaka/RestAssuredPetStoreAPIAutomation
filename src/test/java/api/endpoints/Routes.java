package api.endpoints;

public class Routes {

	public static String base_url = "https://petstore.swagger.io/v2";

	//User Model

	public static String post_create_with_list = base_url + "/user/createWithList";
	public static String post_create_with_array_url = base_url + "/user/createWithArray";
	public static String post_create_user_url = base_url + "/user";
	public static String get_url_by_username = base_url + "/user/{username}";
	public static String get_url_by_login = base_url + "/user/login";
	public static String get_url_by_logout = base_url + "/user/logout";
	public static String update_user_url = base_url + "/user/{username}";
	public static String delete_url = base_url + "/user/{username}";

	// Pet
	public static String post_add_new_pet_url = base_url + "/pet";
	public static String post_upload_pet_image_url = base_url + "/pet/{petId}/uploadImage";
	public static String update_existing_pet_url = base_url + "/pet";
	public static String get_pet_by_status_url = base_url + "/pet/findByStatus";
	public static String get_pet_by_id = base_url + "/pet/{petId}";
	public static String post_update_pet = base_url + "/pet/{petId}";
	public static String delete_pet_by_id = base_url + "/pet/{petId}";


	// Store
	public static String order_url            = base_url + "/store/order";
	public static String orderById_url        = base_url + "/store/order/{orderId}";
	public static String inventory_url        = base_url + "/store/inventory";



}
