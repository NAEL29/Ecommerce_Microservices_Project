package org.example.productservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.util.Map;
/**
 * @author $(USER)
 **/

@Document(collection = "products")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {

    @Id                                         // org.springframework.data.annotation.Id
    private String id;

    private String name;

    @Field(targetType = FieldType.DECIMAL128)   // store as a real number, not a string
    private BigDecimal price;

    private String description;

    private String category;

    @Builder.Default
    private ProductStatus status = ProductStatus.AVAILABLE;

    @Indexed(unique = true)
    private String sku;

    private Map<String, Object> attributes;     // size, colour, specs… varies by category , not sure though how will this work


}
