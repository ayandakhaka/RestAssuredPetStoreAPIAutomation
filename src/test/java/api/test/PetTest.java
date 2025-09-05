package api.test;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
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
	Response response;

	@BeforeClass
	public void setupData() {
		newPet = new PetBuilder()
				.withName("Buddy")
				.withCategory(new Category(1, "Dogs"))
				.addPhotoUrl("src/test/resources/images/pet_image.jpg")
				.withTags(Arrays.asList(new Tag(101, "loyal"), new Tag(102, "playful")))
				.withStatus("Available")
				.build();
		response = PetEndPoints.AddNewPet(newPet);
	}

	@Test(groups = "Add new pet", priority = 1)
	public void testAddNewPet_return_200_success_statusCode() {
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(groups = "Add new pet", priority = 2)
	public void testAddNewPet_petId() {
		Assert.assertEquals(response.jsonPath().getLong("id"), this.newPet.getId());
	}
	
	@Test(groups = "Add new pet", priority = 3)
	public void testAddNewPet_category_id() {
		Assert.assertEquals(response.jsonPath().getLong("category.id"), this.newPet.getCategory().getId());
	}
	
	@Test(groups = "Add new pet", priority = 4)
	public void testAddNewPet_category_name() {
		Assert.assertEquals(response.jsonPath().getString("category.name"), this.newPet.getCategory().getName());
	}
	
	@Test(groups = "Add new pet", priority = 5)
	public void testAddNewPet_pet_name() {
		Assert.assertEquals(response.jsonPath().getString("name"), this.newPet.getName());
	}
	
	@Test(groups = "Add new pet", priority = 6)
	public void testAddNewPet_pet_photoUrls_firstElement() {
		Assert.assertEquals(response.jsonPath().getString("photoUrls[0]"), this.newPet.getPhotoUrls().get(0));
	}
	
	@Test(groups = "Add new pet", priority = 7)
	public void testAddNewPet_pet_photoUrls_SecondElement() {
		Assert.assertEquals(response.jsonPath().getString("photoUrls[1]"), this.newPet.getPhotoUrls().get(1));
	}
	
	@Test(groups = "Add new pet", priority = 8)
	public void testAddNewPet_tags_first_id() {
		Assert.assertEquals(response.jsonPath().getLong("tags.id[0]"), this.newPet.getTags().getFirst().getId());
	}
	
	@Test(groups = "Add new pet", priority = 9)
	public void testAddNewPet_tags_first_name() {
		Assert.assertEquals(response.jsonPath().getString("tags.name[0]"), this.newPet.getTags().getFirst().getName());
	}
	
	@Test(groups = "Add new pet", priority = 10)
	public void testAddNewPet_tags_second_id() {
		Assert.assertEquals(response.jsonPath().getLong("tags.id[1]"), this.newPet.getTags().getLast().getId());
	}
	
	@Test(groups = "Add new pet", priority = 11)
	public void testAddNewPet_tags_second_name() {
		Assert.assertEquals(response.jsonPath().getString("tags.name[1]"), this.newPet.getTags().getLast().getName());
	}
	
	@Test(groups = "Add new pet", priority = 12)
	public void testAddNewPet_status_available() {
		Assert.assertEquals(response.jsonPath().getString("status"), this.newPet.getStatus());
	}
	
	@BeforeGroups(groups = "Upload pet image", dependsOnGroups = "Add new pet")
	public void uploadPetImage() {
		Long petId = response.jsonPath().getLong("id");
		response = PetEndPoints.uploadPetImage("src/test/resources/images/pet_image.jpg", petId);
	}

	@Test(groups = "Upload pet image", dependsOnGroups = "Add new pet", priority = 13)
	public void testUploadPetImage_return_200OK_status_code() {
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(groups = "Upload pet image", dependsOnGroups = "Add new pet", priority = 14)
	public void testUploadPetImage_type() {
		Assert.assertEquals(response.jsonPath().getString("type"), "unknown");
	}
	
	@Test(groups = "Upload pet image", dependsOnGroups = "Add new pet", priority = 15)
	public void testUploadPetImage_Message() {
		Assert.assertTrue(response.jsonPath().getString("message").contains("pet_image.jpg"));
	}
	
	@BeforeGroups(groups = "Update existing pet", dependsOnGroups = "Upload pet image")
	public void updateExistingPet() {
		newPet = new PetBuilder()
				.withName("BuddyUpdated")
				.withTags(Arrays.asList(new Tag(101, "friendly"), new Tag(102, "active")))
				.build();
		response = PetEndPoints.updateExistingPet(newPet);
	}
	
	@Test(groups = "Update existing pet", dependsOnGroups = "Upload pet image", priority = 16)
	public void testUpdate_Pet_name() {
		Assert.assertEquals(response.jsonPath().getString("name"), this.newPet.getName());
	}
	
	@Test(groups = "Update existing pet", dependsOnGroups = "Upload pet image", priority = 17)
	public void testUpdate_Pet_return_200OK_statusCode() {
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(groups = "Update existing pet", dependsOnGroups = "Upload pet image", priority = 18)
	public void testUpdate_tags_first_name() {
		Assert.assertEquals(response.jsonPath().getString("tags.name[0]"), this.newPet.getTags().getFirst().getName());
	}
	
	@Test(groups = "Update existing pet", dependsOnGroups = "Upload pet image", priority = 19)
	public void testUpdate_tags_second_name() {
		Assert.assertEquals(response.jsonPath().getString("tags.name[1]"), this.newPet.getTags().getLast().getName());
	}
	
	@BeforeGroups(groups = "List pets by status", dependsOnGroups = "Update existing pet")
	public void listPetsByStatus() {
		response = PetEndPoints.getPetByStatus();
	}
	
	@Test(groups = "List pets by status", dependsOnGroups = "Update existing pet", priority = 20)
	public void testListPetsByStatus_return_200OK_status_code() {
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@BeforeGroups(groups = "List pets by pet id", dependsOnGroups = "List pets by status")
	public void listPetsByPetId() {
		
		newPet = new PetBuilder()
				.withName("Buddy")
				.withCategory(new Category(1, "Dogs"))
				.addPhotoUrl("src/test/resources/images/pet_image.jpg")
				.withTags(Arrays.asList(new Tag(101, "loyal"), new Tag(102, "playful")))
				.withStatus("Available")
				.build();
		response = PetEndPoints.AddNewPet(newPet);
		
		Long petId = response.jsonPath().getLong("id");
		response = PetEndPoints.getPetByPetId(petId);
	}
	
	@Test(groups = "List pets by pet id", dependsOnGroups = "List pets by status", priority = 21)
	public void testListPetsByPetId_return_200OK_status_code() {
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(groups = "List pets by pet id", dependsOnGroups = "List pets by status", priority = 22)
	public void testListPetsByPetId_status() {
		response.then().log().all();
		Assert.assertEquals(response.jsonPath().getString("status"), this.newPet.getStatus());
	}
	
	
	/**
	 * Add tests offline
	 * {
    "id": 8108,
    "category": {
        "id": 1,
        "name": "Dogs"
    },
    "name": "Buddy",
    "photoUrls": [
        "https://example.com/pet.jpg"
    ],
    "tags": [
        {
            "id": 101,
            "name": "loyal"
        }
    ],
    "status": "available"
}
	 */
}
