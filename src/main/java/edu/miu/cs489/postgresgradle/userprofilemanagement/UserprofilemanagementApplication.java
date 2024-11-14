package edu.miu.cs489.postgresgradle.userprofilemanagement;

import edu.miu.cs489.postgresgradle.userprofilemanagement.dto.request.ProfileRequestDTO;
import edu.miu.cs489.postgresgradle.userprofilemanagement.dto.request.UserRequestDTO;
import edu.miu.cs489.postgresgradle.userprofilemanagement.dto.response.UserResponseDTO;
import edu.miu.cs489.postgresgradle.userprofilemanagement.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor
public class UserprofilemanagementApplication {

    private final UserServiceImpl userService;

    public static void main(String[] args) {
        SpringApplication.run(UserprofilemanagementApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            //create profileRequestDTO object
            ProfileRequestDTO profileRequestDTO= new ProfileRequestDTO(
                    "I am...",
                    "Fairfield",
                    LocalDate.of(2000,1,1));
            //create userRequestDTO object
            UserRequestDTO userRequestDTO = new UserRequestDTO(
                    "u1",
                    "p1",
                    profileRequestDTO
            );
            //set profileRequestDTO in UserRequestDTO
            System.out.println(userService.createUser(userRequestDTO)+" is created.");

            System.out.println(userService.getUserByUsername("u1")+"is found.");

        };
    }
}
