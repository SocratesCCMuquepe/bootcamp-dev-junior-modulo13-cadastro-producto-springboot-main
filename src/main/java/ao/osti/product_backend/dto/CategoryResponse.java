package ao.osti.product_backend.dto;

public class CategoryResponse {
    private Integer id;
    private String name;

    public CategoryResponse() {
    }

    public CategoryResponse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
