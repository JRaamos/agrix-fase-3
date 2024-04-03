package com.betrybe.agrix.dtos;

import com.betrybe.agrix.security.Role;

/**
 * Person DTO for response.
 */
public record PersonResponse(Long id, String username, Role role) {
}
