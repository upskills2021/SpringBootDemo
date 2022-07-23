package com.learnspring.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class UserDTO {
    @NotNull
    @JsonProperty("user_id")
    private UUID userId;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String role;
}
