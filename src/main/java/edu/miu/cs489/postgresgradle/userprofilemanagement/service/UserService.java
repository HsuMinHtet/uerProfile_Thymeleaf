package edu.miu.cs489.postgresgradle.userprofilemanagement.service;

import edu.miu.cs489.postgresgradle.userprofilemanagement.dto.request.UserRequestDTO;
import edu.miu.cs489.postgresgradle.userprofilemanagement.dto.response.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserResponseDTO> createUser(UserRequestDTO userRequestDTO);
    Optional<UserResponseDTO> getUserByUsername(String username);
    List<UserResponseDTO> getAllUsers();
    Optional<UserResponseDTO> updateUser(String username, UserRequestDTO userRequestDTO);
    Optional<UserResponseDTO> updateUserPartially(String username, UserRequestDTO userRequestDTO);
    void deleteUserByUsername(String username);
}

