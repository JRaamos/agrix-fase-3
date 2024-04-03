package com.betrybe.agrix.dtos;

import java.time.LocalDate;

/**
 * Crops dto Response.
 */
public record CropsResponse(Long id, String name, Double plantedArea, Long farmId,
                            LocalDate plantedDate, LocalDate harvestDate) {
}
