package org.example.userservice.dto;

import jakarta.validation.constraints.*;
import org.example.userservice.entity.Role;
/**
 * @author $(USER)
 **/
public record UserRequest(
        @NotBlank(message = "name is required")
        @Size(max = 100, message = "name must be at most 100 characters")
        String name,

        @NotBlank(message = "email is required")
        @Email(message = "email must be valid")
        @Size(max = 150, message = "email must be at most 150 characters")
        String email,

        @NotNull(message = "role is required")
        Role role

) {
}
