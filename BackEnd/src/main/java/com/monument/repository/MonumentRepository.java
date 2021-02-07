package com.monument.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monument.model.Monument;

public interface MonumentRepository extends JpaRepository<Monument, Long> {
	List<Monument> findByNomMContaining(String nomM);
}
