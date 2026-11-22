package org.example.userservice.dto;
import org.example.userservice.entity.Role;


public record UserResponse(
        Long id,
        String name,
        String email,
        Role role,
        boolean active
) {
}
