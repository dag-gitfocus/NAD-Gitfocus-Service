package com.gitfocus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gitfocus.git.db.model.Units;

/**
 * @author Tech Mahindra 
 * Repository class for units table in DB
 */
@Repository
public interface UnitsRepository extends JpaRepository<Units, String> {

}
