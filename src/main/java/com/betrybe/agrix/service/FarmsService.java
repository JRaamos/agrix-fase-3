package com.betrybe.agrix.service;

import com.betrybe.agrix.exeptions.FarmsException;
import com.betrybe.agrix.models.entities.Crops;
import com.betrybe.agrix.models.entities.Farms;
import com.betrybe.agrix.models.repositories.CropsRepositorie;
import com.betrybe.agrix.models.repositories.FamsRepositorie;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service farm.
 */
@Service
public class FarmsService {

  private final FamsRepositorie famsRepositorie;
  private final CropsRepositorie cropsRepositorie;

  /**
   * Crontuctor.
   */
  @Autowired
  public FarmsService(FamsRepositorie famsRepositorie, CropsRepositorie cropsRepositorie) {

    this.famsRepositorie = famsRepositorie;
    this.cropsRepositorie = cropsRepositorie;
  }

  public Farms createFarm(Farms farm) {
    return famsRepositorie.save(farm);
  }

  public List<Farms> findAllFarms() {
    return famsRepositorie.findAll();
  }

  /**
   * Metodo para buscar por id.
   */
  public Farms findByFarmId(Long id) {
    Optional<Farms> searchedFarmById = famsRepositorie.findById(id);
    if (searchedFarmById.isPresent()) {
      return searchedFarmById.get();
    }
    throw new FarmsException();
  }

  /**
   *  Metodo para criar um Crop.
   */

  public Crops cropsCreate(Long id, Crops crop) {
    Optional<Farms> farmOptional = famsRepositorie.findById(id);
    if (farmOptional.isPresent()) {
      Farms farm = farmOptional.get();
      crop.setFarm(farm);
      return cropsRepositorie.save(crop);
    }
    throw new FarmsException();
  }

  /**
   * Metodos para buscar todos os crops.
   */
  public List<Crops> findAllByFarmIdCrops(Long id) {
    Optional<Farms> farmptional = famsRepositorie.findById(id);
    if (farmptional.isPresent()) {
      return farmptional.get().getCrops();
    }
    throw new FarmsException();
  }
}
