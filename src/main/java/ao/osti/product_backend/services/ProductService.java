package ao.osti.product_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import ao.osti.product_backend.dto.ProductRequest;
import ao.osti.product_backend.dto.ProductResponse;
import ao.osti.product_backend.models.Category;
import ao.osti.product_backend.models.Product;
import ao.osti.product_backend.repositories.ProductRepository;
import ao.osti.product_backend.services.exceptions.DatabasesException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponse getById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return product.toDTO();
    }

    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream().map(Product::toDTO).toList();
    }

    public ProductResponse save(ProductRequest productRequest) {
        try {

            Product product = productRepository.save(productRequest.toEntity());

            return product.toDTO();

        } catch (DataIntegrityViolationException e) {
            throw new EntityNotFoundException("Category not found");
        }

    }

    public void deleteById(long id) {
        try {
            if (productRepository.existsById(id)) {
                productRepository.deleteById(id);
            } else {
                throw new EntityNotFoundException("Product not found");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DatabasesException("Conflit deliting Product");
        }
    }

    public void update(long id, ProductRequest productUpdate) {
        try {
            if (productRepository.existsById(id)) {

                Product product = productRepository.getReferenceById(id);

                product.setName(productUpdate.getName());
                product.setDescription(productUpdate.getDescription());
                product.setPrice(productUpdate.getPrice());
                product.setCategory(new Category(productUpdate.getCategory().getId()));
                product.setPromotion(productUpdate.isPromotion());
                product.setNewProduct(productUpdate.isNewProduct());
                productRepository.save(product);

            } else {
                throw new EntityNotFoundException("Product not found");
            }
        } catch (DataIntegrityViolationException e) {
            throw new EntityNotFoundException("Category not found");
        }
    }
}
