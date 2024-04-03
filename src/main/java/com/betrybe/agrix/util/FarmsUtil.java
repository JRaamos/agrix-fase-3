package com.betrybe.agrix.util;

import com.betrybe.agrix.dtos.FarmRequest;
import com.betrybe.agrix.dtos.FarmResponse;
import com.betrybe.agrix.models.entities.Farms;

/**
 * Classe util para farm.
 */
public class FarmsUtil {

  /**
   * Metodo para DTO convert.
   */
  public static FarmResponse farmResponseconvert(Farms farm) {
    return new FarmResponse(
        farm.getId(),
        farm.getName(),
        farm.getSize()
    );
  }

  /**
   * Metodo para DTO convert.
   */
  public  static Farms farmsDtoCreateConvert(FarmRequest farmRequest) {
    Farms farm = new Farms();
    farm.setName(farmRequest.name());
    farm.setSize(farmRequest.size());
    return farm;
  }
}
