package api.endpoints;

import api.payload.model.Store;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndpoints {

	public static Response placeOder(Store store) {

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(store)
				.when()
				.post(Routes.post_order_url);
		return response;
	}

	public static Response getOrderById(long orderId) {

		Response response = given()
				.pathParam("orderId", orderId)
				.when()
				.get(Routes.get_orderById_url);
		return response;
	}

	public static Response getOrderByInventory() {

		Response response = given()
				.when()
				.get(Routes.get_inventory_url);
		return response;
	}
	
	public static Response deleteOrderById(long orderId) {
		
//		Response response = given()
//				.pathParam("orderId", orderId)
//				.when()
//				.delete(Routes.delete_orderById_url);
//		return response;
		Response response = RestAssured.given()
				.config(RestAssuredConfig.config()
						.httpClient(HttpClientConfig.httpClientConfig()
								.setParam("http.connection.timeout", 5000)
								.setParam("http.socket.timeout", 5000)))
				.pathParam("orderId", orderId)
				.when()
				.delete(Routes.delete_orderById_url);
		return response;
	}
}
