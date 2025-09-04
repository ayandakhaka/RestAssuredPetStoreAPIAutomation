package api.test;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import api.endpoints.PetEndPoints;
import api.payload.model.Category;
import api.payload.model.Pet;
import api.payload.model.Tag;
import api.pet.builder.PetBuilder;
import io.restassured.response.Response;

public class PetTest {

	Pet newPet;

	@BeforeGroups(groups = "Add new pet")
	public void addNewPet() {
		newPet = new PetBuilder()
				.withName("Buddy")
				.withCategory(new Category(1, "Dogs"))
				.addPhotoUrl("src/test/resources/images/pet_image.jpg")
				.withTags(Arrays.asList(new Tag(101, "loyal"), new Tag(102, "playful")))
				.withStatus("Available")
				.build();
	}

	@Test(groups = "Add new pet", priority = 1)
	public void testAddNewPet() {
		Response response = PetEndPoints.AddNewPet(newPet);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@BeforeGroups(groups = "Upload pet image")
	public void uploadPetImage() {

		// Step 1: Create a new pet
		newPet = new PetBuilder()
				.withName("Buddy")
				.withCategory(new Category(1, "Dogs"))
				.withTags(Arrays.asList(new Tag(101, "loyal")))
				.withStatus("available")
				.build();
	}

	@Test(groups = "Upload pet image", priority = 2)
	public void testUploadPetImage() {

		// Step 2: Add pet to the store
		Response response = PetEndPoints.AddNewPet(newPet);
		//response.then().log().all();
		response.prettyPrint();
		Long petId = response.jsonPath().getLong("id");
		System.out.println("Pet Id : " + petId);

		// Step 3: Upload image url
		Response uploadResponse = PetEndPoints.uploadPetImage("src/test/resources/images/pet_image.jpg", petId);
		uploadResponse.then().log().all();
		Assert.assertEquals(uploadResponse.getStatusCode(), 200);
	}
}
