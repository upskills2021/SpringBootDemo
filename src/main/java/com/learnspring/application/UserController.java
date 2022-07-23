package com.learnspring.application;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.learnspring.domain.dto.UserDTO;
import com.learnspring.domain.dto.UserResponse;
import com.learnspring.domain.mapper.Mappers;
import com.learnspring.domain.persistance.entity.UserEntity;
import com.learnspring.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/spring-boot-user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        List<UserResponse> userResponse = users.stream().map(Mappers::fromUserDto).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(userResponse)) {
           return ResponseEntity.internalServerError().body("Users not found");
        }
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("userId","name", "role");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserFilter", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userResponse);
        mappingJacksonValue.setFilters(filterProvider);
        return ResponseEntity.ok().body(mappingJacksonValue);
    }
    @PostMapping(value = "/users", consumes = "application/json", produces = "application/json")
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
    @GetMapping(value = "/users/filter", produces = "application/json", headers = "FILTER-VERSION=1")
    public ResponseEntity<?> applyDynamicFilterV1ForGetAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        List<UserResponse> userResponse = users.stream().map(Mappers::fromUserDto).collect(Collectors.toList());
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("userId","name");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserFilter", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userResponse);
        mappingJacksonValue.setFilters(filterProvider);
        if (CollectionUtils.isEmpty(userResponse)) {
            return ResponseEntity.internalServerError().body("Users not found");
        }
        return ResponseEntity.ok().body(mappingJacksonValue);
    }
    @GetMapping(value = "/users/filter", produces = "application/json", headers = "FILTER-VERSION=2")
    public ResponseEntity<?> applyDynamicFilterV2ForGetAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        List<UserResponse> userResponse = users.stream().map(Mappers::fromUserDto).collect(Collectors.toList());
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("userId","role");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserFilter", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userResponse);
        mappingJacksonValue.setFilters(filterProvider);
        if (CollectionUtils.isEmpty(userResponse)) {
            return ResponseEntity.internalServerError().body("Users not found");
        }
        return ResponseEntity.ok().body(mappingJacksonValue);
    }
}