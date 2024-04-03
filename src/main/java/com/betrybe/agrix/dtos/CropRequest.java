package com.betrybe.agrix.dtos;

import java.time.LocalDate;

/**
 * Crops dto request.
 */
public record CropRequest(String name, Double plantedArea,
                          LocalDate plantedDate, LocalDate harvestDate) {
}