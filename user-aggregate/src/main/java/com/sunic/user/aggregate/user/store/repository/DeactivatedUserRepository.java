package com.sunic.user.aggregate.user.store.repository;

import com.sunic.user.aggregate.user.store.jpo.DeactivatedUserJpo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeactivatedUserRepository extends JpaRepository<DeactivatedUserJpo, Integer> {
}