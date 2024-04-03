package com.betrybe.agrix.dtos;

import com.betrybe.agrix.security.Role;

public record PersonResquest(String username, String password, Role role) {
}
