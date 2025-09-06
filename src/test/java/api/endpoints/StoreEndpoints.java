package api.endpoints;

import api.payload.model.Store;
import static io.restassured.RestAssured.*;
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
		
		Response response = given()
				.pathParam("orderId", orderId)
				.when()
				.delete(Routes.delete_orderById_url);
		return response;
	}
}
