package edu.miu.cs489.postgresgradle.userprofilemanagement.repository;

import edu.miu.cs489.postgresgradle.userprofilemanagement.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
}
