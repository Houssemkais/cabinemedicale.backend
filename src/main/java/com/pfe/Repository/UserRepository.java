package com.pfe.Repository;

import com.pfe.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);

    Optional<User> findPatientByIdAndUserRole(Integer id, UserRole userRole);
   // void updatePassword(String password, Integer id);

}
