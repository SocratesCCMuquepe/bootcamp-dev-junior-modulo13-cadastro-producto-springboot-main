package ao.osti.product_backend.dto;

import ao.osti.product_backend.models.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryRequest {
    
    @NotBlank(message = "Name can not be blank")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;
    private String description;

    public CategoryRequest() {
    }

    public CategoryRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category toCategory() {
        return new Category(name);
    }
}
