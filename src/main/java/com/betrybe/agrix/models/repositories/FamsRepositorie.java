package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Farms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface Farms.
 */

@Repository
public interface FamsRepositorie extends JpaRepository<Farms, Long> {
}
