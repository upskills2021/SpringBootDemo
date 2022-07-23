package com.learnspring.domain.dto;


import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonFilter("UserFilter")
//@JsonIgnoreProperties(value = {"name", "role"})
public class UserResponse {
    private UUID userId;
    private String name;
    private String role;
}
