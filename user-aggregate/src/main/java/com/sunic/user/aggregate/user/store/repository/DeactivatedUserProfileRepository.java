package com.sunic.user.aggregate.user.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunic.user.aggregate.user.store.jpo.DeactivatedUserProfileJpo;

public interface DeactivatedUserProfileRepository extends JpaRepository<DeactivatedUserProfileJpo, Integer> {
	Optional<DeactivatedUserProfileJpo> findByUserId(Integer userId);

	boolean existsByUserId(Integer userId);
}