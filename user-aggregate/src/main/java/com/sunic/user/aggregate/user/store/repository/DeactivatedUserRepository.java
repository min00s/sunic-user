package com.sunic.user.aggregate.user.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunic.user.aggregate.user.store.jpo.DeactivatedUserJpo;

public interface DeactivatedUserRepository extends JpaRepository<DeactivatedUserJpo, Integer> {
}