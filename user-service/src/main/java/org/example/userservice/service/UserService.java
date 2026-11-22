package org.example.userservice.service;

import lombok.RequiredArgsConstructor;
import org.example.userservice.dto.UserRequest;
import org.example.userservice.dto.UserResponse;
import org.example.userservice.entity.User;
import org.example.userservice.exception.DuplicateEmailException;
import org.example.userservice.exception.UserNotFoundException;
import org.example.userservice.mapper.UserMapper;
import org.example.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional                                          // every public method runs in a transaction
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse create(UserRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateEmailException(request.email());          // → 409
        }
        // normal verfication using exception already created
        // then using the mapper to communicate the changes with
        // the entity and save them using the repo basic stuff
        User saved = userRepository.save(userMapper.toEntity(request));

        return userMapper.toResponse(saved);
    }

    // reason for transactional is to make sure the id dont get modified
    // i mean eaither way dto doesnt accept the id so why add this here?
    @Transactional(readOnly = true)
    public UserResponse getById(Long id) {
        return userMapper.toResponse(findUser(id));
    }


    // what does readonly with transactional mean
    @Transactional(readOnly = true)
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .toList();
    }

    public UserResponse update(Long id, UserRequest request) {
        User user = findUser(id);                                         // → 404 if missing
        boolean emailChanged = !user.getEmail().equals(request.email());
        if (emailChanged && userRepository.existsByEmail(request.email())) {
            throw new DuplicateEmailException(request.email());          // → 409
        }
        userMapper.updateEntity(user, request);
        return userMapper.toResponse(user);                               // no save() needed: see below
    }

    // this doesnt really do anything since the basic is the user being active so it doesnt
    // really change anything no it actually does since active can true or false
    // active has two options so yeah it makes sense
    public UserResponse setActive(Long id, boolean active) {
        User user = findUser(id);
        user.setActive(active);
        return userMapper.toResponse(user);
    }

    public void delete(Long id) {
        userRepository.delete(findUser(id));                              // → 404 if missing
    }

    private User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}