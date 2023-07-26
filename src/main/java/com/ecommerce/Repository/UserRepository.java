package com.ecommerce.Repository;

import com.ecommerce.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM users u where " +
            "u.userName LIKE CONCAT('%', :userName, '%')")
    Optional<UserEntity> findByUserName(String userName);

}
