package api.endpoints;

import api.payload.model.Pet;
import io.restassured.response.Response;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.io.File;

public class PetEndPoints {

    /**
     * Add a new Pet to the store
     * HTTP Method: POST
     * Endpoint: Routes.post_add_new_pet_url
     *
     * @param pet Pet object (payload)
     * @return Response from the server
     */
    public static Response AddNewPet(Pet pet) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(pet)
                .when()
                .post(Routes.post_add_new_pet_url);
        return response;
    }

    /**
     * Upload an image for a Pet
     * HTTP Method: POST
     * Endpoint: Routes.post_upload_pet_image_url
     *
     * @param fileUrl Path to the image file
     * @param petId   ID of the pet to associate image with
     * @return Response from the server
     */
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

    /**
     * Update an existing Pet using JSON body
     * HTTP Method: PUT
     * Endpoint: Routes.update_existing_pet_url
     *
     * @param existingPet Updated Pet object
     * @return Response from the server
     */
    public static Response updateExistingPet(Pet existingPet) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(existingPet)
                .when()
                .put(Routes.update_existing_pet_url);
        return response;
    }

    /**
     * Update a Pet with form data
     * HTTP Method: POST
     * Endpoint: Routes.post_update_pet
     *
     * @param pet   Pet object containing name and status
     * @param petId ID of the pet to update
     * @return Response from the server
     */
    public static Response updatePetWithFormData(Pet pet, long petId) {
        Response response = given()
                .pathParam("petId", petId)
                .contentType("application/x-www-form-urlencoded")
                .formParam("name", pet.getName())
                .formParam("status", pet.getStatus())
                .when()
                .post(Routes.post_update_pet);
        return response;
    }

    /**
     * Delete a Pet by its ID
     * HTTP Method: DELETE
     * Endpoint: Routes.delete_pet_by_id
     *
     * @param petId ID of the pet to delete
     * @return Response from the server
     */
    public static Response deletePetWithPetId(long petId) {
        Response response = given()
                .pathParam("petId", petId)
                .when()
                .delete(Routes.delete_pet_by_id);
        return response;
    }

    /**
     * Get all Pets by their status
     * HTTP Method: GET
     * Endpoint: Routes.get_pet_by_status_url
     *
     * @return Response containing pets by status
     */
    public static Response getPetByStatus() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(Routes.get_pet_by_status_url);
        return response;
    }

    /**
     * Get a Pet by its ID
     * HTTP Method: GET
     * Endpoint: Routes.get_pet_by_id
     *
     * @param petId ID of the pet to retrieve
     * @return Response containing pet details
     */
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
