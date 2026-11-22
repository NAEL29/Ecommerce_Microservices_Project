package org.example.userservice.mapper;

import org.example.userservice.dto.UserRequest;
import org.example.userservice.dto.UserResponse;
import org.example.userservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // POST: request → new entity (id from MySQL, active = true from @Builder.Default)
    public User toEntity(UserRequest request) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .role(request.role())
                .build();
    }

    // PUT: copy new values onto the EXISTING entity (keeps id and active)
    public void updateEntity(User user, UserRequest request) {
        user.setName(request.name());
        user.setEmail(request.email());
        user.setRole(request.role());
    }

    // entity → response
    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.isActive()
        );
    }
}