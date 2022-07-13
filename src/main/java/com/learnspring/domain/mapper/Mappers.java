package com.learnspring.domain.mapper;

import com.learnspring.domain.dto.UserDTO;
import com.learnspring.domain.persistance.entity.UserEntity;

import java.util.UUID;

public final class Mappers {
    private Mappers() {

    }
    public static UserDTO fromEntity(UserEntity entity) {
        return UserDTO.builder()
                .userId(UUID.fromString(entity.getUserId()))
                .name(entity.getName())
                .role(entity.getRole())
                .build();
    }

    public static UserEntity fromDto(UserDTO userDTO) {
        return UserEntity.builder()
                .userId(userDTO.getUserId().toString())
                .name(userDTO.getName())
                .role(userDTO.getRole())
                .build();
    }
}
