package api.test;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.PetEndPoints;
import api.payload.model.Category;
import api.payload.model.Pet;
import api.payload.model.Tag;
import api.pet.builder.PetBuilder;
import io.restassured.response.Response;

public class PetTest {

    private Pet newPet;
    private Response response;
    private Long petId;

    /**
     * Setup a new pet once before all tests
     */
    @BeforeClass
    public void setupPet() {
        newPet = new PetBuilder()
                .withName("Buddy")
                .withCategory(new Category(1, "Dogs"))
                .addPhotoUrl("src/test/resources/images/pet_image.jpg")
                .withTags(Arrays.asList(new Tag(101, "loyal"), new Tag(102, "playful")))
                .withStatus("available")
                .build();

        response = PetEndPoints.AddNewPet(newPet);
        Assert.assertEquals(response.getStatusCode(), 200, "Add new pet failed");
        petId = response.jsonPath().getLong("id");
        newPet.setId(petId); // ensure ID is set for later tests
    }

    // -------------------- Add Pet Validations --------------------
    @Test(priority = 1, groups = "AddPet")
    public void testPetName() {
        Assert.assertEquals(response.jsonPath().getString("name"), newPet.getName());
    }

    @Test(priority = 2, groups = "AddPet")
    public void testPetCategory() {
        Assert.assertEquals(response.jsonPath().getString("category.name"), newPet.getCategory().getName());
    }

    @Test(priority = 3, groups = "AddPet")
    public void testPetTags() {
        Assert.assertEquals(response.jsonPath().getString("tags.name[0]"), newPet.getTags().get(0).getName());
        Assert.assertEquals(response.jsonPath().getString("tags.name[1]"), newPet.getTags().get(1).getName());
    }

    @Test(priority = 4, groups = "AddPet")
    public void testPetStatus() {
        Assert.assertEquals(response.jsonPath().getString("status"), newPet.getStatus());
    }

    // -------------------- Upload Pet Image --------------------
    @Test(priority = 5, groups = "UploadPetImage", dependsOnGroups = "AddPet")
    public void testUploadPetImage() {
        response = PetEndPoints.uploadPetImage("src/test/resources/images/pet_image.jpg", petId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.jsonPath().getString("message").contains("pet_image.jpg"));
    }

    // -------------------- Update Pet --------------------
    @Test(priority = 6, groups = "UpdatePet", dependsOnGroups = "UploadPetImage")
    public void testUpdatePetNameAndTags() {
        newPet.setName("BuddyUpdated");
        newPet.setTags(Arrays.asList(new Tag(101, "friendly"), new Tag(102, "active")));
        response = PetEndPoints.updateExistingPet(newPet);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), newPet.getName());
        Assert.assertEquals(response.jsonPath().getString("tags.name[0]"), newPet.getTags().get(0).getName());
        Assert.assertEquals(response.jsonPath().getString("tags.name[1]"), newPet.getTags().get(1).getName());
    }

    // -------------------- List Pets by Status --------------------
    @Test(priority = 7, groups = "ListPetsByStatus", dependsOnGroups = "UpdatePet")
    public void testListPetsByStatus() {
        response = PetEndPoints.getPetByStatus();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    // -------------------- List Pet by Pet ID --------------------
    @Test(priority = 8, groups = "ListPetById", dependsOnGroups = "ListPetsByStatus")
    public void testListPetById() {
        response = PetEndPoints.getPetByPetId(petId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("status"), newPet.getStatus());
    }

    // -------------------- Update Pet With Form Data --------------------
    @Test(priority = 9, groups = "UpdatePetFormData", dependsOnGroups = "ListPetById")
    public void testUpdatePetWithFormData() {
    	
    	Long petId = response.jsonPath().getLong("id");
        // petId must exist and match an added pet
        newPet = new PetBuilder()
                .withId(petId)
                .withName("Lihle")
                .withStatus("pending")
                .build();

        response = PetEndPoints.updatePetWithFormData(newPet, petId);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
