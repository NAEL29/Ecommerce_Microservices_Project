package org.example.productservice.mapper;

import org.example.productservice.dto.ProductRequest;
import org.example.productservice.dto.ProductResponse;
import org.example.productservice.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    // POST: request → new entity (id and status filled by MongoDB / @Builder.Default)
    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .description(request.description())
                .category(request.category())
                .sku(request.sku())
                .attributes(request.attributes())
                .build();
    } // so this one takes the requeat and use @Builder to make a new product

    // PUT: copy new values onto the EXISTING entity (keeps id and status)
    public void updateEntity(Product product, ProductRequest request) {
        product.setName(request.name());
        product.setPrice(request.price());
        product.setDescription(request.description());
        product.setCategory(request.category());
        product.setSku(request.sku());
        product.setAttributes(request.attributes());
    }

    // entity → response
    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getCategory(),
                product.getSku(),
                product.getStatus(),
                product.getAttributes()
        );
    }
}