package ao.osti.product_backend.dto;

import jakarta.validation.constraints.Min;

public class IntegerDTO {
    @Min(value = 1, message = "Id must be greater than 0")
    private Integer id;

    public IntegerDTO() {
    }

    public IntegerDTO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
