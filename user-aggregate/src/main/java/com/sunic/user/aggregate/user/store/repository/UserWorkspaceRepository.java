package com.sunic.user.aggregate.user.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunic.user.aggregate.user.store.jpo.UserWorkspaceJpo;

public interface UserWorkspaceRepository extends JpaRepository<UserWorkspaceJpo, Integer> {
    boolean existsByName(String name);
    Optional<UserWorkspaceJpo> findByName(String name);
}