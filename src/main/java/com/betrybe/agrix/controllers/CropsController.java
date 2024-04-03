package com.betrybe.agrix.controllers;

import static com.betrybe.agrix.util.CropsUtil.cropResponseconvert;

import com.betrybe.agrix.dtos.CropsResponse;
import com.betrybe.agrix.models.entities.Crops;
import com.betrybe.agrix.service.CropsService;
import com.betrybe.agrix.util.CropsUtil;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *  CropsController com resposta para o cliente.
 */
@RestController
@RequestMapping("/crops")
public class CropsController {
  @Autowired
  private CropsService cropsService;

  /**
   * Metodo para buscar.
   */
  @GetMapping
public List<CropsResponse> getAllCrops() {
    return cropsService.findAllCrops().stream()
        .map(CropsUtil::cropResponseconvert)
        .collect(Collectors.toList());
  }

  /**
   * Metodo para buscar Crops por parametro especifico.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getCropsById(@PathVariable Long id) {
    Crops crops = cropsService.findCropById(id);
    return ResponseEntity.status(HttpStatus.OK).body(cropResponseconvert(crops));
  }

  /**
   * Metodo para buscar Crops por data.
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropsResponse>> searchCropsByHarvestDate(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
    List<Crops> cropsList = cropsService.findCropsBetweenDates(start, end);
    List<CropsResponse> responseList = cropsList.stream()
        .map(crop -> new CropsResponse(crop.getId(), crop.getName(), crop.getPlantedArea(),
            crop.getFarm().getId(), crop.getPlantedDate(), crop.getHarvestDate()))
        .collect(Collectors.toList());
    return ResponseEntity.ok(responseList);
  }

  /**
   * Metodo para criar.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associateCropAndFertilizer(
      @PathVariable Long cropId,
      @PathVariable Long fertilizerId
  ) {
    Crops crops = cropsService.cropAndFertilizers(cropId, fertilizerId);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
  }
}
