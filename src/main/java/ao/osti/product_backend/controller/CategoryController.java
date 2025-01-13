package ao.osti.product_backend.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ao.osti.product_backend.models.Category;
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
    public ResponseEntity<Category> save(@Validated @RequestBody Category category) {
        category = categoryService.save(category);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();
        return ResponseEntity.created(location).body(category);        
    }
    

    @GetMapping("{id}")
    public ResponseEntity<Category> getCategorie(@PathVariable int id) {
        Category category = categoryService.getById(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeCategory(@PathVariable int id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable int id, @RequestBody Category categoryUpdate) {
        categoryService.update(id, categoryUpdate);
        return ResponseEntity.noContent().build();
    }

}
