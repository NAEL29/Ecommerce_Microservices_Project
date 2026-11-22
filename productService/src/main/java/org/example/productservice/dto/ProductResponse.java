package org.example.productservice.dto;

import org.example.productservice.entity.ProductStatus;

import java.math.BigDecimal;
import java.util.Map;

public record ProductResponse(
        String id,
        String name,
        BigDecimal price,
        String description,
        String category,
        String sku,
        ProductStatus status,
        Map<String, Object> attributes
) {}