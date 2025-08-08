package com.atipera.repolister.api.v1.dto;

import org.springframework.http.HttpStatus;

public record ErrorResponse(HttpStatus status, String message) {
}
