package com.learnspring.application;

import com.learnspring.domain.dto.UserDTO;
import com.learnspring.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}