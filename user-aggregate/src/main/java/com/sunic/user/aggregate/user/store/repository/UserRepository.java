package com.sunic.user.aggregate.user.store.repository;

import com.sunic.user.aggregate.user.store.jpo.UserJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserJpo, Integer> {
    boolean existsByEmail(String email);
    Optional<UserJpo> findByEmail(String email);
    
    @Query("SELECT u FROM UserJpo u WHERE u.lastLoginTime < :oneYearAgo OR u.lastLoginTime IS NULL")
    List<UserJpo> findUsersInactiveForMoreThanOneYear(@Param("oneYearAgo") LocalDateTime oneYearAgo);
}