package com.monument.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monument.model.Lieux;

@Repository
public interface LieuxRepository extends JpaRepository<Lieux, Long> {
	List<Lieux> findByNomComContaining(String title);

	
}
