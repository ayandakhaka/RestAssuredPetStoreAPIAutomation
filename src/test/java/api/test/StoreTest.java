package api.test;

import java.math.BigInteger;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import api.endpoints.StoreEndpoints;
import api.payload.model.Store;
import api.utilities.Log;
import io.restassured.response.Response;

public class StoreTest {

	private Faker faker;
	private Store storePayload;
	private Response response;
	private Long orderId;

	public StoreTest() {
		faker = new Faker();
		storePayload = new Store();
	}

	@BeforeClass
	public void setupStore() {
		
		Log.info("****************** Seeting up store data**********************");
		storePayload.setOrderId(faker.number().numberBetween(1000L, 9999L));
		storePayload.setPetId(faker.number().numberBetween(100, 999));
		storePayload.setQuantity(faker.number().numberBetween(1, 5));
		storePayload.setStatus("placed");
		storePayload.setComplete(faker.bool().bool());

		response = StoreEndpoints.placeOder(storePayload);
		orderId = response.jsonPath().getLong("id");
	}

	@Test(groups = "PlaceOrder", priority = 1)
	public void testPlaceOrder() {
		Log.info("********************** Testing Place Order***********************");
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertTrue(orderId > 0, "Order ID should be assigned by API");
		Assert.assertEquals(response.jsonPath().getLong("petId"), this.storePayload.getPetId());
		Assert.assertEquals(response.jsonPath().getInt("quantity"), this.storePayload.getQuantity());
		Assert.assertEquals(response.jsonPath().getString("status"), this.storePayload.getStatus());
		Assert.assertEquals(response.jsonPath().getBoolean("complete"), this.storePayload.isComplete());
	}

	@Test(groups = "ListOrderById", priority = 2)
	public void testGetOrderById() {
		Log.info("***************************Testing List orders by Id**************************");
		response = StoreEndpoints.getOrderById(orderId);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(groups = "ListOrderByInventory", priority = 3)
	public void testGetOrderInventory() {
		Log.info("************************* Testing List Orders by Inventory*************************");
		response = StoreEndpoints.getOrderByInventory();
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

	}
	
	@Test(groups = "DeleteByOrderId", priority = 4)
	public void testDeleteByOrderId() {
		Log.info("**************************** Testing Delete Orders by Id**********************************");
		response = StoreEndpoints.deleteOrderById(orderId);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		 // Verify deletion by trying to GET the pet again
        Response getResponse = StoreEndpoints.getOrderById(orderId);
        response.then().log().all();
        Assert.assertEquals(getResponse.getStatusCode(), 404);

	}



}
