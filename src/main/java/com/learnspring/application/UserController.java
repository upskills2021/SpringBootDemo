package com.learnspring.application;

import com.learnspring.domain.dto.UserDTO;
import com.learnspring.domain.mapper.Mappers;
import com.learnspring.domain.persistance.entity.UserEntity;
import com.learnspring.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/spring-boot-user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        if (CollectionUtils.isEmpty(users)) {
           return ResponseEntity.internalServerError().body("Users not found");
        }
        return ResponseEntity.ok().body(users);
    }
    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody  UserDTO userDTO) {
        UserEntity entity = Mappers.fromDto(userDTO);
        UserEntity savedUser = userService.saveUser(entity);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping("users/{id}")
    public ResponseEntity<?> retrieveUser(@PathVariable long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }
}