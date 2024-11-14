package edu.miu.cs489.postgresgradle.userprofilemanagement.repository;

import edu.miu.cs489.postgresgradle.userprofilemanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Long>{

    Optional<User> findByUsername(String username);

    void deleteUserByUsername(String username);

}
