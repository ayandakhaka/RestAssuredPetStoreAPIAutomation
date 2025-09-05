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

	public static Response updateExistingPet(Pet existingPet) {

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(existingPet)
				.when()
				.put(Routes.update_existing_pet_url);
		return response;
	}

	public static Response getPetByStatus() {

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				//.pathParam("status", status)
				.when()
				.get(Routes.get_pet_by_status_url);
		return response;
	}

	public static Response getPetByPetId(long petId) {

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("petId", petId)
				.when()
				.get(Routes.get_pet_by_id);
		return response;
	}

}
