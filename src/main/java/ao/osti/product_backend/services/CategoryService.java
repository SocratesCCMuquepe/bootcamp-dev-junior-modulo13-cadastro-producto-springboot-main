package ao.osti.product_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ao.osti.product_backend.repositories.CategoryRepository;
import ao.osti.product_backend.dto.CategoryRequest;
import ao.osti.product_backend.dto.CategoryResponse;
import ao.osti.product_backend.models.Category;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponse getDTOById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categories not found..!"));
        return category.toDTO();
    }

    public Category getById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categories not found..!"));
        return category;
    }

    public List<CategoryResponse> getAll() {
        // TODO Auto-generated method stub by Copilot
        // Código da Aula
        // return categoryRepository.findAll().stream().map(c --> c.toDTO()).collect(Collectors.toList());
        // Copilot suggests the following code:
        return categoryRepository.findAll().stream().map(Category::toDTO).toList();
    }

    public CategoryResponse save(CategoryRequest categoryRequest) {
        Category category = categoryRepository.save(categoryRequest.toEntity());
        return category.toDTO();
    }

    public void deleteById(int id) {
        Category category = getById(id);
        categoryRepository.delete(category);
    }

    public void update(int id, CategoryRequest categoryUpdate) {
        Category category = getById(id);

        category.setName(categoryUpdate.getName());

        categoryRepository.save(category);
    }

}
