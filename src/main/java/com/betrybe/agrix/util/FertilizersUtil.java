package com.betrybe.agrix.util;

import com.betrybe.agrix.dtos.FertilizersRequest;
import com.betrybe.agrix.dtos.FertilizersResponse;
import com.betrybe.agrix.models.entities.Fertilizers;

/**
 *  CLasse ultil para Fertilizer.
 */
public class FertilizersUtil {

  /**
   *  Metodo para DTO convert.
   */
  public static FertilizersResponse fertilizerResponseConvert(Fertilizers fertilizer) {
    return new FertilizersResponse(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );
  }

  /**
   *  Metodo para DTO convert.
   */
  public static Fertilizers fertilizerDtoCreateConvert(FertilizersRequest request) {
    Fertilizers fertilizer = new Fertilizers();
    fertilizer.setName(request.name());
    fertilizer.setBrand(request.brand());
    fertilizer.setComposition(request.composition());
    return fertilizer;
  }
}
