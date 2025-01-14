package ao.osti.product_backend.dto;

import ao.osti.product_backend.models.Category;
import ao.osti.product_backend.models.Product;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductRequest {

    @NotBlank(message = "Name can not be blank")
    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    private String name;

    @NotBlank(message = "Description can not be blank")
    @Size(min = 3, max = 1024, message = "Description must be between 3 and 1024 characters")
    private String description;

    private boolean promotion;
    private boolean newProduct;

    @Min(value = 0, message = "Price can not be less than 0")
    @Max(value = 999999999, message = "Price can not be greater than 999999999")
    private double price;

    @NotNull
    private IntegerDTO category;

    public ProductRequest() {
    }

    public ProductRequest(String name, String description, IntegerDTO category, boolean promotion, boolean newProduct,
            double price) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.promotion = promotion;
        this.newProduct = newProduct;
        this.price = price;
    }

    public Product toEntity() {
        return new Product(name, description, new Category(category.getId()), promotion, newProduct, price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public boolean isNewProduct() {
        return newProduct;
    }

    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public IntegerDTO getCategory() {
        return category;
    }

    public void setIntegerDTO(IntegerDTO category) {
        this.category = category;
    }

}
