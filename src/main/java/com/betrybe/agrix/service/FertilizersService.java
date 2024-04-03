package com.betrybe.agrix.service;

import com.betrybe.agrix.exeptions.FertilizerException;
import com.betrybe.agrix.models.entities.Fertilizers;
import com.betrybe.agrix.models.repositories.FertilizerRepositorie;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  Service Fertilizers.
 */
@Service
public class FertilizersService {

  private final FertilizerRepositorie fertilizerRepositorie;

  @Autowired
  public FertilizersService(FertilizerRepositorie fertilizerRepositorie) {
    this.fertilizerRepositorie = fertilizerRepositorie;
  }

  public Fertilizers createFertilizer(Fertilizers fertilizer) {
    return fertilizerRepositorie.save(fertilizer);
  }

  public List<Fertilizers> findAll() {
    return fertilizerRepositorie.findAll();
  }

  /**
   * Metodo para buscar por id.
   */
  public Fertilizers fidByFertilizerId(Long id) {
    Optional<Fertilizers> searchedFertilizerById = fertilizerRepositorie.findById(id);
    if (searchedFertilizerById.isPresent()) {
      return searchedFertilizerById.get();
    }
    throw new FertilizerException();
  }
}
