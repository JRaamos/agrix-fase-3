package com.betrybe.agrix.controllers;


import static com.betrybe.agrix.util.CropsUtil.cropResponseconvert;
import static com.betrybe.agrix.util.CropsUtil.cropsDtoCreateConvert;
import static com.betrybe.agrix.util.FarmsUtil.farmResponseconvert;
import static com.betrybe.agrix.util.FarmsUtil.farmsDtoCreateConvert;

import com.betrybe.agrix.dtos.CropRequest;
import com.betrybe.agrix.dtos.CropsResponse;
import com.betrybe.agrix.dtos.FarmRequest;
import com.betrybe.agrix.dtos.FarmResponse;
import com.betrybe.agrix.models.entities.Crops;
import com.betrybe.agrix.models.entities.Farms;
import com.betrybe.agrix.service.FarmsService;
import com.betrybe.agrix.util.CropsUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller com respostar para o cliente.
 */
@RestController
@RequestMapping("/farms")
public class FarmsController {

  /**
   * FarmsService com motodos de serviço.
   */
  @Autowired
  private FarmsService farmsService;

  /**
   * Metodo para criação de um farm.
   */
  @PostMapping
  public ResponseEntity<FarmResponse> createFarm(@RequestBody FarmRequest farmRequest) {
    Farms farmsServiceFarm = farmsService.createFarm(farmsDtoCreateConvert(farmRequest));
    FarmResponse farmResponse = farmResponseconvert(farmsServiceFarm);
    return ResponseEntity.status(HttpStatus.CREATED).body(farmResponse);
  }

  /**
   * Metodo para buscar todos os Farms cadastrados.
   */
  @GetMapping
  public ResponseEntity<List<FarmResponse>> getAllFarms() {
    List<Farms> farmsList = farmsService.findAllFarms();
    List<FarmResponse> farmResponseList = farmsList.stream()
        .map(farm -> new FarmResponse(farm.getId(), farm.getName(), farm.getSize()))
        .collect(Collectors.toList());
    return ResponseEntity.ok(farmResponseList);
  }

  /**
   * Metodo para buscar Farm por parameto especifico.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getFarmById(@PathVariable Long id) {
    Farms farm = farmsService.findByFarmId(id);
    return ResponseEntity.status(HttpStatus.OK).body(farmResponseconvert(farm));
  }

  /**
   * Metodo para criar crops.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropsResponse> createCrops(@PathVariable Long farmId,
      @RequestBody CropRequest crops) {
    Crops crop = farmsService.cropsCreate(farmId, cropsDtoCreateConvert(crops));
    CropsResponse cropsResponse = cropResponseconvert(crop);
    return ResponseEntity.status(HttpStatus.CREATED).body(cropsResponse);
  }

  /**
   * Metodo para buscar todos os crops conforme o id do farms.
   */
  @GetMapping("/{farmId}/crops")
  public List<CropsResponse> getAllCropsInFarm(@PathVariable Long farmId) {
    return farmsService.findAllByFarmIdCrops(farmId).stream()
        .map(CropsUtil::cropResponseconvert).collect(Collectors.toList());
  }
}

