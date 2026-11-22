package org.example.productservice.repository;

import org.example.productservice.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByCategory(String category);

    boolean existsBySku(String sku);

    @Query("{ 'attributes.size': ?0 }")      // so in this we replace the ?0 with the size were searching for
    List<Product> findBySize(String size);
}
