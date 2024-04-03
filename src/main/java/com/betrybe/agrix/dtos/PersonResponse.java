package com.betrybe.agrix.dtos;

import com.betrybe.agrix.security.Role;

public record PersonResponse(Long id, String username, Role role) {
}
