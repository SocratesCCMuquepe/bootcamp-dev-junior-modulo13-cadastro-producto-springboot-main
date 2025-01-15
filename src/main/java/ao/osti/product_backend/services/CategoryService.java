package ao.osti.product_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import ao.osti.product_backend.repositories.CategoryRepository;
import ao.osti.product_backend.services.exceptions.DatabasesException;
import jakarta.persistence.EntityNotFoundException;
import ao.osti.product_backend.dto.CategoryRequest;
import ao.osti.product_backend.dto.CategoryResponse;
import ao.osti.product_backend.models.Category;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponse getById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        return category.toDTO();
    }

    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll().stream().map(Category::toDTO).toList();
    }

    public CategoryResponse save(CategoryRequest categoryRequest) {
        Category category = categoryRepository.save(categoryRequest.toEntity());
        return category.toDTO();
    }

    public void deleteById(int id) {
        try {
            if (categoryRepository.existsById(id)) {
                categoryRepository.deleteById(id);
            } else {
                throw new EntityNotFoundException("Category not found");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DatabasesException("Constraint violation, category cannot be deleted");
        }
    }

    public void update(int id, CategoryRequest categoryUpdate) {
        try {
            if (categoryRepository.existsById(id)) {
                Category category = categoryRepository.getReferenceById(id);

                category.setName(categoryUpdate.getName());

                categoryRepository.save(category);
            } else {
                throw new EntityNotFoundException("Category not found");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DatabasesException("Conflit updating Category");
        }
    }

}
