package com.learnspring.domain.service;

import com.learnspring.domain.dto.UserDTO;
import com.learnspring.domain.mapper.Mappers;
import com.learnspring.domain.persistance.entity.UserEntity;
import com.learnspring.domain.persistance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
      return userRepository.findAll()
               .stream()
               .map(Mappers::fromEntity)
               .collect(Collectors.toList());
    }

    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
