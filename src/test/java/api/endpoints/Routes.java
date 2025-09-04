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
	  
	  
	   
}
