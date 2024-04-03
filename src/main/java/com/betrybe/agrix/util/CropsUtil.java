package com.betrybe.agrix.util;

import com.betrybe.agrix.dtos.CropRequest;
import com.betrybe.agrix.dtos.CropsResponse;
import com.betrybe.agrix.models.entities.Crops;


/**
 *  CLasse ultil para Crops.
 */
public class CropsUtil {

  /**
   *  Metodo para DTO convert.
   */
  public static Crops cropsDtoCreateConvert(CropRequest cropRequest) {
    Crops crop = new Crops();
    crop.setName(cropRequest.name());
    crop.setPlantedArea(cropRequest.plantedArea());
    crop.setPlantedDate(cropRequest.plantedDate());
    crop.setHarvestDate(cropRequest.harvestDate());
    return crop;
  }

  /**
   *  Metodo para DTO convert.
   */
  public static CropsResponse cropResponseconvert(Crops crops) {
    return new CropsResponse(
        crops.getId(),
        crops.getName(),
        crops.getPlantedArea(),
        crops.getFarm().getId(),
        crops.getPlantedDate(),
        crops.getHarvestDate()
    );
  }
}
