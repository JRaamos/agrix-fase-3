package com.betrybe.agrix.dtos;

import com.betrybe.agrix.security.Role;

/**
 * Person DTO for request.
 */
public record PersonResquest(String username, String password, Role role) {
}
