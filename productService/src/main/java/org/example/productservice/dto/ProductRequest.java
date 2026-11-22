package org.example.productservice.dto;


import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Map;

public record ProductRequest(

        @NotBlank(message = "name is required")
        @Size(max = 120, message = "name must be at most 120 characters")
        String name,

        @NotNull(message = "price is required")
        @Positive(message = "price must be greater than 0")
        @Digits(integer = 10, fraction = 2, message = "price must have at most 2 decimals")
        BigDecimal price,

        @Size(max = 1000, message = "description must be at most 1000 characters")
        String description,

        @NotBlank(message = "category is required")
        String category,

        @NotBlank(message = "sku is required")
        @Pattern(regexp = "^[A-Z0-9-]{3,30}$", message = "sku must be 3-30 uppercase letters, digits or dashes")
        String sku,

        Map<String, Object> attributes      // optional: varies by category
) {}