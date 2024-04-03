package com.betrybe.agrix.controllers;

import static com.betrybe.agrix.util.FertilizersUtil.fertilizerResponseConvert;

import com.betrybe.agrix.dtos.FertilizersRequest;
import com.betrybe.agrix.dtos.FertilizersResponse;
import com.betrybe.agrix.models.entities.Fertilizers;
import com.betrybe.agrix.service.FertilizersService;
import com.betrybe.agrix.util.FertilizersUtil;
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
 *  * Controller com respostar para o cliente.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizersController {
  private final FertilizersService fertilizerService;

  @Autowired
  public FertilizersController(FertilizersService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Metodo para criar um Fertilizer.
   */
  @PostMapping
  public ResponseEntity<FertilizersResponse> createFertilizer(@RequestBody
        FertilizersRequest fertilizerRequest) {
    Fertilizers newFertilizer = FertilizersUtil.fertilizerDtoCreateConvert(fertilizerRequest);

    Fertilizers savedFertilizer = fertilizerService.createFertilizer(newFertilizer);
    FertilizersResponse response = fertilizerResponseConvert(savedFertilizer);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  /**
   * Metodo para bucar todos Fertilizer.
   */
  @GetMapping
  public ResponseEntity<List<FertilizersResponse>> getAllFertilizers() {
    List<Fertilizers> allFertilizers = fertilizerService.findAll();
    List<FertilizersResponse> response = allFertilizers.stream()
        .map(FertilizersUtil::fertilizerResponseConvert)
        .collect(Collectors.toList());
    return ResponseEntity.ok(response);
  }

  /**
   * Metodo para buscar Fertilizer por parameto especifico.
   */
  @GetMapping("/{fertilizerId}")
  public ResponseEntity<?> getFarmById(@PathVariable Long fertilizerId) {
    Fertilizers fertilizers = fertilizerService.fidByFertilizerId(fertilizerId);
    return ResponseEntity.status(HttpStatus.OK).body(fertilizerResponseConvert(fertilizers));
  }
}
