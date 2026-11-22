package org.example.productservice.service;

import lombok.RequiredArgsConstructor;
import org.example.productservice.dto.ProductRequest;
import org.example.productservice.dto.ProductResponse;
import org.example.productservice.entity.Product;
import org.example.productservice.exception.DuplicateSkuException;
import org.example.productservice.exception.ProductNotFoundException;
import org.example.productservice.mapper.ProductMapper;
import org.example.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponse create(ProductRequest request) {
        if (productRepository.existsBySku(request.sku())) {
            throw new DuplicateSkuException(request.sku());               // → 409
        }
        Product saved = productRepository.save(productMapper.toEntity(request));
        return productMapper.toResponse(saved);
        // so in the create we use mapper to turn the dto in entity data that will be used to create new products i get that
    }

    public ProductResponse getById(String id) {
        return productMapper.toResponse(findProduct(id));
    }

    public List<ProductResponse> getAll(String category) {
        List<Product> products = (category == null || category.isBlank())
                ? productRepository.findAll()
                : productRepository.findByCategory(category);
        return products.stream()
                .map(productMapper::toResponse)
                .toList();

        // ilike how this getall is create it also has an getbycathegory at the same time which is good , and thats a nice use of ternary operates
        // about the stream what exactly are we doing there
    }

    public ProductResponse update(String id, ProductRequest request) {
        Product product = findProduct(id);                                 // → 404 if missing
        boolean skuChanged = !product.getSku().equals(request.sku());
        if (skuChanged && productRepository.existsBySku(request.sku())) {
            throw new DuplicateSkuException(request.sku());               // → 409
        }
        productMapper.updateEntity(product, request);
        return productMapper.toResponse(productRepository.save(product)); // MongoDB: save() is required
    }

    public void delete(String id) {
        Product product = findProduct(id);                                 // → 404 if missing
        productRepository.delete(product);
    }

    // shared lookup: one place that turns "not found" into a 404
    private Product findProduct(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}