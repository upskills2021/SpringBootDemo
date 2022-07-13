package com.learnspring.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    @JsonProperty("user_id")
    private UUID userId;
    private String name;
    private String role;
}
