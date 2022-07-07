package com.learnspring.domain.service;

import com.learnspring.domain.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    public List<UserDTO> getAllUsers() {
        UserDTO user1 = UserDTO.builder()
                .userId(UUID.randomUUID())
                .name("TestUser1")
                .role("USER")
                .build();
        UserDTO user2 = UserDTO.builder()
                .userId(UUID.randomUUID())
                .name("TestUser2")
                .role("USER")
                .build();
        UserDTO user3 = UserDTO.builder()
                .userId(UUID.randomUUID())
                .name("TestUser3")
                .role("USER")
                .build();
       // return List.of(user1, user2, user3);
        return new ArrayList<>();
    }
}
