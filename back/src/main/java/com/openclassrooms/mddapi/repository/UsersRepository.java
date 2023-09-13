package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * The interface Users repository.
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

    Optional<Users> findByUsername(String username);

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END AS BIT FROM Users u WHERE u.email = :email OR u.username = :username")
    Integer existsByEmailOrUsername(@Param("email")String email, @Param("username") String username);
    @Query("SELECT u FROM Users u WHERE u.email = :username OR u.username = :username")
    Users findByEmailOrUsername(@Param("username")String username);
}
