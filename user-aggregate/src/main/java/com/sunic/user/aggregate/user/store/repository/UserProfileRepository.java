package com.sunic.user.aggregate.user.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunic.user.aggregate.user.store.jpo.UserProfileJpo;

public interface UserProfileRepository extends JpaRepository<UserProfileJpo, Integer> {
	Optional<UserProfileJpo> findByUserId(Integer userId);

	boolean existsByUserId(Integer userId);

	void deleteByUserId(Integer userId);
}