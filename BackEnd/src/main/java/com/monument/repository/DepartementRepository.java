package com.monument.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monument.model.Departement;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
	List<Departement> findByNomDepContaining(String nomDep);

}
