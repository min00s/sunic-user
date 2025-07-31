package com.sunic.user.aggregate.user.store.repository;

import com.sunic.user.aggregate.user.store.jpo.UserWorkspaceJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserWorkspaceRepository extends JpaRepository<UserWorkspaceJpo, Integer> {
    boolean existsByName(String name);
    Optional<UserWorkspaceJpo> findByName(String name);
}