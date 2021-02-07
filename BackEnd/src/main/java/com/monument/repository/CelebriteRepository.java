package com.monument.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monument.model.Celebrite;

public interface CelebriteRepository extends JpaRepository<Celebrite, Long> {
	List<Celebrite> findByNomContaining(String nom);

}
