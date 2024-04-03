package com.betrybe.agrix.service;

import com.betrybe.agrix.exeptions.CropsException;
import com.betrybe.agrix.models.entities.Crops;
import com.betrybe.agrix.models.repositories.CropsRepositorie;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Crops.
 */
@Service
public class CropsService {
  private final CropsRepositorie cropsRepositorie;
  private final FertilizersService fertilizersService;

  /**
   * Contuctor.
   */
  @Autowired
  public CropsService(CropsRepositorie cropsRepositorie, FertilizersService fertilizersService) {
    this.cropsRepositorie = cropsRepositorie;
    this.fertilizersService = fertilizersService;
  }

  public List<Crops> findAllCrops() {
    return cropsRepositorie.findAll();
  }

  /**
   * Metodo para buscar por id.
   */
  public Crops findCropById(Long id) {
    Optional<Crops> cropsOptional = cropsRepositorie.findById(id);
    if (cropsOptional.isPresent()) {
      return cropsOptional.get();
    }
    throw new CropsException();
  }

  /**
   * Metodo para bucar por data.
   */
  public List<Crops> findCropsBetweenDates(LocalDate start, LocalDate end) {
    return cropsRepositorie.findByHarvestDateBetween(start, end);
  }

  /**
   * Associação.
   */
  public Crops cropAndFertilizers(Long cropId, Long fertilizerId) {
    Optional<Crops> cropOptional = cropsRepositorie.findById(cropId);
    if (cropOptional.isPresent()) {
      Crops crops = cropOptional.get();
      crops.getFertilizers().add(fertilizersService.fidByFertilizerId(fertilizerId));
      return cropsRepositorie.save(crops);
    }
    throw new CropsException();
  }
}