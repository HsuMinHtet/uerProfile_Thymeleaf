package edu.miu.cs489.postgresgradle.userprofilemanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDTO (
        @NotBlank(message = "blank/empty/null not allowed")
        String username,
        @Size(min = 3, max = 5)
        String password,
        @NotNull
        ProfileRequestDTO profileRequestDTO) {

}
