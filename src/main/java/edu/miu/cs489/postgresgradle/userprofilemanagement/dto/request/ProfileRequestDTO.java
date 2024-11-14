package edu.miu.cs489.postgresgradle.userprofilemanagement.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ProfileRequestDTO (
        @NotBlank(message = "blank/empty/null not allowed")
        String bio,
        @NotBlank(message = "blank/empty/null not allowed")
        String location,
        LocalDate dob) {

}
