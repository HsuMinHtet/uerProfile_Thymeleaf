package edu.miu.cs489.postgresgradle.userprofilemanagement.service.impl;

import edu.miu.cs489.postgresgradle.userprofilemanagement.dto.request.ProfileRequestDTO;
import edu.miu.cs489.postgresgradle.userprofilemanagement.dto.request.UserRequestDTO;
import edu.miu.cs489.postgresgradle.userprofilemanagement.dto.response.ProfileResponseDTO;
import edu.miu.cs489.postgresgradle.userprofilemanagement.dto.response.UserResponseDTO;
import edu.miu.cs489.postgresgradle.userprofilemanagement.exception.user.UserNotFoundException;
import edu.miu.cs489.postgresgradle.userprofilemanagement.model.Profile;
import edu.miu.cs489.postgresgradle.userprofilemanagement.model.User;
import edu.miu.cs489.postgresgradle.userprofilemanagement.repository.UserRepository;
import edu.miu.cs489.postgresgradle.userprofilemanagement.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<UserResponseDTO> createUser(UserRequestDTO userRequestDTO) {
        //create user object by using the data from userRequestDTO
        User newUser = new User();
        newUser.setUsername(userRequestDTO.username());
        newUser.setPassword(userRequestDTO.password());
        //create profile object from userRequestDTO
        Profile profile = new Profile();
        profile.setBio(userRequestDTO.profileRequestDTO().bio());
        profile.setLocation(userRequestDTO.profileRequestDTO().location());
        profile.setDob(userRequestDTO.profileRequestDTO().dob());
        newUser.setProfile(profile);
        //save the newUser
        User savedUser = userRepository.save(newUser);
        //construct the UserResponseDTO object
        ProfileResponseDTO profileResponseDTO=new ProfileResponseDTO(savedUser.getProfile().getBio(),savedUser.getProfile().getLocation());
        UserResponseDTO userResponseDTO= new UserResponseDTO(savedUser.getUsername(),profileResponseDTO);
        return Optional.of(userResponseDTO);
    }

    @Override
    public Optional<UserResponseDTO> getUserByUsername(String username) {
        Optional<User> foundUser = userRepository.findByUsername(username);
        if(foundUser.isPresent()){
            ProfileResponseDTO profileResponseDTO=new ProfileResponseDTO(
                    foundUser.get().getProfile().getBio(),
                    foundUser.get().getProfile().getLocation()
            );
            UserResponseDTO userResponseDTO=new UserResponseDTO(
                    foundUser.get().getUsername(),
                    profileResponseDTO
            );
            return Optional.of(userResponseDTO);
        }
        throw new UserNotFoundException(username+"is not found.");
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User>users=userRepository.findAll();
        List<UserResponseDTO> userResponseDTOS= new ArrayList<>();
        for(User user: users){
            userResponseDTOS.add(
                    new UserResponseDTO(
                            user.getUsername(),
                            new ProfileResponseDTO(
                                    user.getProfile().getBio(),
                                    user.getProfile().getLocation()
                            )
                    )
            );
        }
        return userResponseDTOS;
    }

    //Partial
    @Override
    public Optional<UserResponseDTO> updateUserPartially(String username, UserRequestDTO userRequestDTO) {
        Optional<User> foundUser= userRepository.findByUsername(username);
        if(foundUser.isPresent()){
            User user=foundUser.get();
            if(userRequestDTO.username()!=null){
                user.setUsername(userRequestDTO.username());
            }
            if(userRequestDTO.password()!=null){
                user.setPassword(userRequestDTO.username());
            }
            if(userRequestDTO.profileRequestDTO()!=null){
                Profile profile= user.getProfile();
               if(userRequestDTO.profileRequestDTO().bio()!=null){
                   profile.setBio(userRequestDTO.profileRequestDTO().bio());
               }
                if(userRequestDTO.profileRequestDTO().dob()!=null){
                    profile.setDob(userRequestDTO.profileRequestDTO().dob());
                }
                if(userRequestDTO.profileRequestDTO().location()!=null){
                    profile.setLocation(userRequestDTO.profileRequestDTO().location());
                }
            }
            //save the modified user in db
            User savedUser = userRepository.save(user);
            return Optional.of(new UserResponseDTO(savedUser.getUsername(),new ProfileResponseDTO(
                    savedUser.getProfile().getBio(),
                    savedUser.getProfile().getLocation())));
        }
        throw new UserNotFoundException(username+"is not found.");
    }

    @Override
    public Optional<UserResponseDTO> updateUser(String username, UserRequestDTO userRequestDTO) {
        Optional<User> foundUser= userRepository.findByUsername(username);
        if(foundUser.isPresent()){
            User user=foundUser.get();
                user.setUsername(userRequestDTO.username());
                user.setPassword(userRequestDTO.username());
                Profile profile= user.getProfile();
                    profile.setBio(userRequestDTO.profileRequestDTO().bio());
                    profile.setDob(userRequestDTO.profileRequestDTO().dob());
                    profile.setLocation(userRequestDTO.profileRequestDTO().location());
                    user.setProfile(profile);
            //save the modified user in db
            User savedUser = userRepository.save(user);
            return Optional.of(new UserResponseDTO(savedUser.getUsername(),new ProfileResponseDTO(
                    savedUser.getProfile().getBio(),
                    savedUser.getProfile().getLocation())));
        }
        throw new UserNotFoundException(username+"is not found.");
    }

    @Override
    @Transactional
    public void deleteUserByUsername(String username) {
        getUserByUsername(username).ifPresent(
                user -> {
                    userRepository.deleteUserByUsername(username);
                }
        );
    }
}
