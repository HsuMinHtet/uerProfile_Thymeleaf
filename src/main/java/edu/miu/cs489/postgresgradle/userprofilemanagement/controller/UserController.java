package edu.miu.cs489.postgresgradle.userprofilemanagement.controller;

import edu.miu.cs489.postgresgradle.userprofilemanagement.dto.request.UserRequestDTO;
import edu.miu.cs489.postgresgradle.userprofilemanagement.dto.response.UserResponseDTO;
import edu.miu.cs489.postgresgradle.userprofilemanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getAllUsers(Model model){
        List<UserResponseDTO> userResponseDTOS=userService.getAllUsers();
        model.addAttribute("userResponseDTOS", userResponseDTOS);
        return "user_profile_list";
    }

    @PostMapping
    public String createUser(
            @ModelAttribute @Valid UserRequestDTO userRequestDTO,
            BindingResult bindingResult,
            Model model
    ){
        if(!bindingResult.hasErrors()){
            Optional<UserResponseDTO> userResponseDTO= userService.createUser(userRequestDTO);
            if(userResponseDTO.isPresent()){
                return "redirect:/api/v1/users";
            }
        }
        //if error, stay on this page again with errors message
        model.addAttribute("errors", bindingResult.getAllErrors());
        return "home_page";
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDTO> findUserByName(@PathVariable String username){
        Optional<UserResponseDTO> userResponseDTO=userService.getUserByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO.get());
    }

    @PatchMapping("/{username}")
    public ResponseEntity<UserResponseDTO>updatePartially(
            @PathVariable String username,
            @RequestBody @Valid UserRequestDTO userRequestDTO
    ){
        Optional<UserResponseDTO> userResponseDTO= userService.updateUserPartially(username, userRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO.get());
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserResponseDTO>update(
            @PathVariable String username,
            @RequestBody @Valid UserRequestDTO userRequestDTO
    ){
        Optional<UserResponseDTO> userResponseDTO= userService.updateUser(username, userRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO.get());
    }

    @DeleteMapping("/{username}")//api/v1/users/username
    public ResponseEntity<Void> deleteUser(@PathVariable String username){
        userService.deleteUserByUsername(username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
