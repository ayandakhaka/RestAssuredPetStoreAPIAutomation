package api.pet.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import api.payload.model.Category;
import api.payload.model.Pet;
import api.payload.model.Tag;

public class PetBuilder {
    private long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;

    public PetBuilder() {
        // Default values
        this.id = ThreadLocalRandom.current().nextInt(1000, 9999); // random pet id
        this.category = new Category(1, "Dogs");
        this.name = "DefaultPet";
        this.photoUrls = new ArrayList<>(Arrays.asList("https://example.com/pet.jpg"));
        this.tags = new ArrayList<>(Arrays.asList(new Tag(1, "test-tag")));
        this.status = "available";
    }

    public PetBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public PetBuilder withCategory(Category category) {
        this.category = category;
        return this;
    }

    public PetBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PetBuilder withPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
        return this;
    }

    public PetBuilder addPhotoUrl(String photoUrl) {
        this.photoUrls.add(photoUrl);
        return this;
    }

    public PetBuilder withTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public PetBuilder addTag(Tag tag) {
        this.tags.add(tag);
        return this;
    }

    public PetBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public Pet build() {
        return new Pet(id, category, name, photoUrls, tags, status);
    }
}
