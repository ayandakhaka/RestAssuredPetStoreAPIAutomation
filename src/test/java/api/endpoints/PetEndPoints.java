package api.endpoints;

import api.payload.model.Pet;
import io.restassured.response.Response;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;

public class PetEndPoints {
	
	public static Response AddNewPet(Pet pet) {
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(pet)
				.when()
				.post(Routes.post_add_new_pet_url);
		return response;
	}
	
	public static Response uploadPetImage(String fileUrl, long petId) {
		Response response = given()
				.contentType(ContentType.MULTIPART)
				.accept(ContentType.JSON)
				.pathParam("petId", petId)
				.multiPart("file", new File(fileUrl))
				.when()
				.post(Routes.post_upload_pet_image_url);
		return response;
	}
	
//	public static Response updateExistingPet() {
//		
//	}

}
