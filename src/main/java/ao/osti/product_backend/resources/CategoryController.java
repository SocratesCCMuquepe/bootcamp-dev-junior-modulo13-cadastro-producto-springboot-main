package ao.osti.product_backend.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ao.osti.product_backend.dto.CategoryRequest;
import ao.osti.product_backend.dto.CategoryResponse;
import ao.osti.product_backend.services.CategoryService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> save(@Validated @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse category = categoryService.save(categoryRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();
        return ResponseEntity.created(location).body(category);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryResponse> getCategorie(@PathVariable int id) {
        CategoryResponse category = categoryService.getById(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeCategory(@PathVariable int id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable int id, @RequestBody CategoryRequest categoryUpdate) {
        categoryService.update(id, categoryUpdate);
        return ResponseEntity.noContent().build();
    }

}
