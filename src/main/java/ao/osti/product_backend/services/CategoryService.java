package ao.osti.product_backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ao.osti.product_backend.repositories.CategoryRepository;
import ao.osti.product_backend.services.exceptions.DatabasesException;
import jakarta.persistence.EntityNotFoundException;
import ao.osti.product_backend.dto.CategoryRequest;
import ao.osti.product_backend.dto.CategoryResponse;
import ao.osti.product_backend.models.Category;

@Service
public class CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponse getById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category not found."));
        return category.toDTO();
    }

    public List<CategoryResponse> getAll() {
        // TODO Auto-generated method stub by Copilot
        // Código da Aula
         return categoryRepository.findAll().stream().map(c -> c.toDTO()).collect(Collectors.toList());
        // Copilot suggests the following code:
        //return categoryRepository.findAll().stream().map(Category::toDTO).toList();
    }

    public CategoryResponse save(CategoryRequest categoryRequest) {
        Category category = categoryRepository.save(categoryRequest.toEntity());
        return category.toDTO();
    }

    /**
     * Deletes a category by its ID.
     *
     * @param id the ID of the category to be deleted
     *           throw new DatabaseException("Constraint violation, category cannot
     *           be deleted");
     * @throws EntityNotFoundException if the category with the specified ID is not
     *                                 found
     */
    public void deleteById(int id) {
        try {
            categoryRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabasesException("Constraint violation, category cannot be deleted");
        } catch (EmptyResultDataAccessException e) {
            logger.error("CategoryService.deleteById() - EmptyResultDataAccessException*****************");
            throw new EntityNotFoundException("Category not found.");
        }
    }

    public void update(int id, CategoryRequest categoryUpdate) {
        try {
            Category category = categoryRepository.getReferenceById(id);

            category.setName(categoryUpdate.getName());

            categoryRepository.save(category);

        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Category not found.");
        }
    }

}
