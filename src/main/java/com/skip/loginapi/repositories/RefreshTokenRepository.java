package com.skip.loginapi.repositories;

import com.skip.loginapi.entities.RefreshToken;
import com.skip.loginapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

    Optional<RefreshToken> findByToken (String token);
    int deleteByUser (User user);
}
